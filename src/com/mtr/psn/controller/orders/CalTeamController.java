package com.mtr.psn.controller.orders;

 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Calendar;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang.math.RandomUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 
import com.mtr.psn.common.Constants;
import com.mtr.psn.common.PageSupport;
 
import com.mtr.psn.controller.BaseController;

 
import com.mtr.psn.model.pay.QueryWage;

import com.mtr.psn.model.pay.Wage;
import com.mtr.psn.service.pay.WageService;
import com.mysql.jdbc.Statement;
 
@Controller
public class CalTeamController extends BaseController {

	@Resource
	private WageService wageService;

	
	private static Connection con;
	/**
	 * 进入核算工资
	 calWage 1计算工资 否则不计算工资
	 * @return
	 */
	@RequestMapping(value="/pay/calTeam.do",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView maincalendar (HttpSession session, 
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="teamID",required=false) String teamID
		){
		ModelAndView model = new ModelAndView();
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
 
		
		 Calendar now = Calendar.getInstance();  
		
		QueryWage queryWage = new QueryWage();
		if (orderFrom != null && orderFrom.toString() != "") {
			queryWage.setOrderForm(orderFrom);
		}else{
			queryWage.setOrderForm(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
		}
		now = Calendar.getInstance();
		now.add(Calendar.MONTH, 1);
	
		if (orderTo != null && orderTo.toString() != "") {
			queryWage.setOrderTo(orderTo);
		}else{
			queryWage.setOrderTo(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
		}
	 
		if (teamID != null && teamID.toString() != "") {
			queryWage.setTeamID(Integer.valueOf(teamID));
		}
		 
		PageSupport page = new PageSupport();	
		
		try {
			//获取后台总记录数
	page.setTotalCount(wageService.CountByTeam(queryWage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			page.setTotalCount(0);
		}
		//总记录数必须大于0 才有意义
				if(page.getTotalCount() > 0){
					if(currentpage != null){
						page.setPage(currentpage);
					}
					if(page.getPage() <= 0){
						page.setPage(1);
					}
					if(page.getPage() > page.getPageCount()){
						page.setPage(page.getPageCount());
					}
					
					queryWage.setStarNum((page.getPage()-1)*page.getPageSize());//设置开始行
					queryWage.setPageSize(page.getPageSize());//设置每页显示数量
					//该用户当前页列表的数据
					List<Wage> wage = null;
					try {
						wage = wageService.selectTeamList(queryWage);
					} catch (Exception e) {
						// TODO: handle exception
						wage = null;
						if(page == null){
							page = new PageSupport();
							page.setItems(null);
						}
					}
					page.setItems(wage);//设置当前页列表数据
				}else{
					page.setItems(null);
				}
				model.addObject("page",page);
				
				now = Calendar.getInstance();
				
				if(orderFrom==null){
					
					model.addObject("orderFrom", now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
				}else{
					model.addObject("orderFrom", orderFrom);
				}
				
				now.add(Calendar.MONTH, 1);
				if(orderTo==null){
					model.addObject("orderTo", now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
				}else{
					model.addObject("orderTo", orderTo);
				}
				model.addAllObjects(baseModel);//加载用户所属的功能菜单
				model.addObject("teamID", teamID);
				model.setViewName("orders/calTeam");
				}
	 return  model;
	}
	 /**
	 * 导出工资表
	 * @param accendantFile
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/frontend/outputTeam.html",method=RequestMethod.POST)
	@ResponseBody
	public Object outputOrder(
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="teamID",required=false) String teamID,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		 String filedisplay = "";//导出文件名称
		 String rPath ="";//导出文件路径
		 String path="";//下载路径
		try{
		getConnect();
		  Statement stat = (Statement) con.createStatement();
		  String sql = "SELECT w.wageDate,w.teamID,te.teamName,SUM(w.totalCount) as totalCount,SUM(w.errorCount) as errorCount,SUM(w.normalCount) as normalCount,SUM(w.timeoutCount) as timeoutCount,SUM(w.onTimeCount) as onTimeCount,SUM(w.subsidization) as subsidization,SUM(w.leakageBuckle) as leakageBuckle,SUM(w.referralFee) as referralFee,";
		  sql+="		SUM(w.insuranceFee) as insuranceFee,SUM(w.amerce) as amerce,SUM(w.overAllowance) as overAllowance,SUM(w.shouldPay) as shouldPay,SUM(w.incomeTax) as incomeTax,SUM(w.goodFee) as goodFee,SUM(w.advancePay) as advancePay,SUM(w.electricity) as electricity,SUM(w.realPay) as realPay ";
		  sql+="FROM team as te ";
		  sql+="LEFT JOIN wage as w ON w.teamID = te.id ";
		  if(orderFrom!=null&&orderFrom!=""){
			  sql+=" where w.createTime >= '"+orderFrom+"'";
		  }
		  if(orderTo!=null&&orderTo!=""){
			  sql+=" and w.createTime < '"+orderTo+"'";
			  
		  }
		  if(teamID!=null&&teamID!=""){
			  sql+=" and w.teamID = '"+teamID+"'";
		  }
		  sql+="and w.wageDate <> 'null'   group by te.id ";
		  
            ResultSet resultSet = stat.executeQuery(sql);
            SXSSFWorkbook workbook=new SXSSFWorkbook(100);
            SXSSFSheet sheet=workbook.createSheet("wage");
            SXSSFRow row = sheet.createRow((short)0);
            SXSSFCell cell=null;
            int index = 0;
            cell=row.createCell((short)index++);
            cell.setCellValue("工资年月");
            cell=row.createCell((short)index++);
            cell.setCellValue("站点id");
            cell=row.createCell((short)index++);
            cell.setCellValue("站点名称");
            cell=row.createCell((short)index++);
            cell.setCellValue("异常单数");
            cell=row.createCell((short)index++);
            cell.setCellValue("正常单数");
            cell=row.createCell((short)index++);
            cell.setCellValue("超时单数");
            cell=row.createCell((short)index++);
            cell.setCellValue("准时单数");
            cell=row.createCell((short)index++);
            cell.setCellValue("提成");
            cell=row.createCell((short)index++);
            cell.setCellValue("上月未发/漏扣");
            cell=row.createCell((short)index++);
            cell.setCellValue("员工介绍费");
            cell=row.createCell((short)index++);
            cell.setCellValue("保险费");
            cell=row.createCell((short)index++);
            cell.setCellValue("投诉罚款");
            cell=row.createCell((short)index++);
            cell.setCellValue("超时补贴");
            cell=row.createCell((short)index++);
            cell.setCellValue("应发工资");
            cell=row.createCell((short)index++);
            cell.setCellValue("个人所得税");
            cell=row.createCell((short)index++);
            cell.setCellValue("预支工资");
            cell=row.createCell((short)index++);
            cell.setCellValue("电费");
            cell=row.createCell((short)index++);
            cell.setCellValue("实发工资");
           
            
            int i=1;
            index = 0;
            while(resultSet.next())
            {
                row=sheet.createRow(i);
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("wageDate"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("teamID"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("teamName"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("errorCount"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("normalCount"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("timeoutCount"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("onTimeCount"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("subsidization"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("leakageBuckle"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("referralFee"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("insuranceFee"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("amerce"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("overAllowance"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("shouldPay"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("incomeTax"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("advancePay"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("electricity"));
                cell=row.createCell(index++);
                cell.setCellValue(resultSet.getString("realPay"));
               
                i++;
             }
            path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles")+"/";
            filedisplay = RandomUtils.nextInt(1000000)+"_AdPic.xlsx";
              rPath =  System.currentTimeMillis()+filedisplay;
            FileOutputStream FOut = new FileOutputStream( path+rPath);
            workbook.write(FOut);
            FOut.flush();
            FOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return path+rPath;
	}
 
	 private static void getConnect()  
	    {  
	        try  
	        {  
	            Class.forName("com.mysql.jdbc.Driver");  
	            con = DriverManager.getConnection(  
	            		"jdbc:mysql://127.0.0.1:3306/pay_state_nuclear_db?useUnicode=true&useOldAliasMetadataBehavior=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&useSSL=true",  
	                    "root", "123456");    
	        }  
	        catch (ClassNotFoundException | SQLException e)  
	        {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }
}
