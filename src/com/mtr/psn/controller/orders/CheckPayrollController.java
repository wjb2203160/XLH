package com.mtr.psn.controller.orders;

 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.mtr.psn.model.goods.GoodsRegistrationForm;
import com.mtr.psn.model.goods.QueryRegistrationForm;
import com.mtr.psn.model.member.Rider;
 
import com.mtr.psn.model.orders.Appealform;
import com.mtr.psn.model.orders.ComplaintForm;
import com.mtr.psn.model.orders.QueryAppealForm;
import com.mtr.psn.model.orders.QueryComplaintForm;
import com.mtr.psn.model.orders.QueryOrder;
 
import com.mtr.psn.model.pay.QueryWage;
import com.mtr.psn.model.pay.Salary_d;
import com.mtr.psn.model.pay.Salary_h;
import com.mtr.psn.model.pay.Wage;
 
import com.mtr.psn.service.goods.GoodsRegistrationFormService;
import com.mtr.psn.service.member.RiderService;
import com.mtr.psn.service.orders.AppealFormService;
import com.mtr.psn.service.orders.ComplaintFormService;
import com.mtr.psn.service.orders.OrderFormService;
import com.mtr.psn.service.pay.Salary_dService;
import com.mtr.psn.service.pay.Salary_hService;
import com.mtr.psn.service.pay.WageService;
import com.mysql.jdbc.Statement;
 
@Controller
public class CheckPayrollController extends BaseController {
	
	@Resource
	private OrderFormService orderFormService;
	@Resource
	private RiderService riderService;
	@Resource
	private Salary_hService  salary_hService;
	@Resource
	private Salary_dService  salary_dService;
	@Resource
	private WageService wageService;
	@Resource
	private AppealFormService appealFormService;
	@Resource
	private ComplaintFormService complaintFormService;
	@Resource
	private GoodsRegistrationFormService goodsRegistrationFormService;
	
	private static Connection con;
	/**
	 * 进入核算工资
	 calWage 1计算工资 否则不计算工资
	 * @return
	 */
	@RequestMapping(value="/pay/checkPayroll.do")
	@ResponseBody
	public ModelAndView maincalendar (HttpSession session, 
			@RequestParam(value="calWage",required=false) Integer calWage,
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="riderID",required=false) String riderID,
			@RequestParam(value="riderName",required=false) String riderName,
			@RequestParam(value="teamID",required=false) String teamID,
			@RequestParam(value="zeroNotDisp",required=false) String zeroNotDisp
			
		){
	/*	if(orderFrom!=null&&orderTo!=null){
		//格式化日期
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date date = format.parse(orderFrom);
			Date date1 = format.parse(orderTo);
			format = new SimpleDateFormat("yyyy-MM-dd");
			orderFrom =format.format(date);
			orderTo =format.format(date1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		}*/
		int totalCount=0;//所有单数
		int errorCount=0;//异常单数
		int normalCount=0;//正常单数
		int timeoutCount=0;//超时单数
		int onTimeCount=0;//准时单数
		int day =0; //全勤天数
		double goodFee =0;//物资费用
		double amerce = 0;//超时罚款
		double insuranceFee=0;//保险费（2元/天）
		double TaxMoney = 0;//个人所得税
		double attendance = 0;//全勤奖
		List<Rider> riderList=null;//骑手列表
		List<Appealform> appealList=null;//罚款金额列表
		List<ComplaintForm> complaintList=null;//申诉金额列表
		List<GoodsRegistrationForm> goodFeeList=null;//物资费用列表
		double subsidization=0;//提成
		ModelAndView model = new ModelAndView();
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
		if(calWage!= null &&calWage==1){
			//导入前清空当月工资表
			 QueryWage  del = new QueryWage();
			 if (orderFrom != null && orderFrom.toString() != "") {
			 del.setOrderForm(orderFrom);
			 }
			 if (orderTo != null && orderTo.toString() != "") {
			 del.setOrderTo(orderTo);
			 }
			 if (riderID != null && riderID.toString() != "") {
			 del.setRiderID(Integer.valueOf(riderID));
			 }
			 if (riderName != null && riderName.toString() != "") {
			 del.setRiderName(riderName);
			 }
			 if (teamID != null && teamID.toString() != "") {
			 del.setTeamID(Integer.valueOf(teamID));
			 }
			 if (zeroNotDisp != null && zeroNotDisp.toString() != "") {
			 del.setZeroNotDisp(zeroNotDisp);
			 }
			 
			 try {
				wageService.deleteByMonth(del);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//计算工资
			//获取骑手人数
			try {
				Rider r = new Rider();
				if(riderID!=null&&riderID!=""){
				r.setId(Long.valueOf(riderID));
				}
				if(riderName!=null&&riderName!=""){
					r.setRiderName(riderName);
					}
				if(teamID!=null&&teamID!=""){
					r.setTeamID(Long.valueOf(teamID));
					}
				
				riderList = riderService.selectRider(r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(riderList!=null&&riderList.size()>0){
				//计算每个骑手工资
				Salary_h h = null;//薪资方案汇总
				List<Salary_d> d = null;//薪资方案明细
				Salary_d _d = new Salary_d();
				for(int i=0;i<riderList.size();i++){
					//获取薪资方案
					try {
						h = salary_hService.selectById(riderList.get(i).getTeamID());
						 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					_d.setSiteId(riderList.get(i).getTeamID());
					_d.setIsSubsidization("否");
					_d.setStarNum(0);
					_d.setPageSize(20);
					try {
						d = salary_dService.selectAll(_d);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//金额重置
					subsidization=0;
					amerce=0;
					goodFee=0;
					insuranceFee=0;
					TaxMoney=0;
					day=0;
					attendance=0;
					//获取骑手所有订单数
					QueryOrder queryForm = new QueryOrder();
					if (orderFrom != null && orderFrom.toString() != "") {
						queryForm.setOrderForm(orderFrom);
					}
					if (orderTo != null && orderTo.toString() != "") {
						queryForm.setOrderTo(orderTo);
					}
					if (riderList.get(i).getId() != null && riderList.get(i).getId().toString() != "") {
						queryForm.setRiderID(riderList.get(i).getId());
					}
					
					try {
						totalCount = orderFormService.CountNorNumByRider(queryForm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//获取骑手正常订单数
					queryForm.setResult("正常");
					queryForm.setStatus("正常");
					try {
						normalCount = orderFormService.CountNorNumByRider(queryForm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//计算骑手异常单数
					errorCount = totalCount-normalCount;
					//获取骑手超时单数
					queryForm.setStatus("正常");
					queryForm.setResult("正常");
					queryForm.setOverTime("是");
					try {
						timeoutCount = orderFormService.CountNorNumByRider(queryForm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//获取骑手准时单数
					onTimeCount = normalCount-timeoutCount;
					/*//获取骑手准时单数
					queryForm.setResult("正常");
					queryForm.setOverTime("否");
					try {
						onTimeCount = orderFormService.CountNorNumByRider(queryForm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				 if(normalCount==0&&errorCount==0&&timeoutCount==0){
					 continue;
				 }
				if(d!=null&&h!=null){//薪资方案存在
				boolean	Flag = true;
					double num =0;//累计
					//计算不超时工资
					for(int j=0;j<d.size();j++){
						if(d.get(j).getOrderFrom()<=onTimeCount&&onTimeCount<d.get(j).getOrderTo()){
							if(onTimeCount<=d.get(0).getOrderTo()&&h.getBasicSalary()>0){
								Flag = false;
							}
							subsidization = subsidization+(onTimeCount-num)*d.get(j).getPrice();
							break;
						}else{
							if(h.getBasicSalary()<=0){
							subsidization = subsidization+d.get(j).getPrice()*(Double.valueOf(d.get(j).getOrderTo())-num);
							}else{
								if(j!=0){
							subsidization = subsidization+d.get(j).getPrice()*(Double.valueOf(d.get(j).getOrderTo())-num);
								}
							}
							num=Double.valueOf(d.get(j).getOrderTo());
						}
						
					}
					//计算超时工资
					subsidization = subsidization+(timeoutCount*h.getOrderPrice());
					//计算天数（天数怎样判断？）
				    queryForm = new QueryOrder();
					queryForm.setRiderID(riderList.get(i).getId());
					queryForm.setStatus("正常");
					queryForm.setOrderForm(orderFrom);
					queryForm.setOrderTo(orderTo);
					
					try {
						day = orderFormService.CountDayByRider(queryForm);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(Flag){
					//计算底薪
					subsidization= subsidization+h.getBasicSalary();
					//计算全勤奖
					if(day>=h.getAttendanceDay()&&h.getAttendanceDay()!=0){
						attendance=attendance+h.getPerfectAttendance();
					}
				
				//计算奖励补助
				_d = new Salary_d();
				_d.setSiteId(riderList.get(i).getId());
				_d.setIsSubsidization("是");
				_d.setStarNum(0);
				_d.setPageSize(20);
				try {
					d = salary_dService.selectAll(_d);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(d!=null){
					for(int j=0;j<d.size();j++){
						if(d.get(j).getOrderFrom()<onTimeCount&&onTimeCount<d.get(j).getOrderTo()){
							subsidization = subsidization+d.get(j).getSubsidization();
							break;
						}else{
							subsidization = subsidization+d.get(j).getSubsidization();
						}
					}
				}
				}
				}
				//计算保险费
				insuranceFee = day*2;
				//补回申诉金额
				QueryAppealForm qa = new QueryAppealForm();
				qa.setOrderForm(orderFrom);
				qa.setOrderTo(orderTo);
				qa.setRiderID(riderList.get(i).getId());
				try {
					appealList =  appealFormService.selectMoneyByRider(qa);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(appealList!=null){
					for(int k=0;k<appealList.size();k++){
						//subsidization = subsidization+appealList.get(k).getComplaintAmount();
						amerce = amerce+appealList.get(k).getComplaintAmount();
					}
				}
				//扣除罚款金额
				QueryComplaintForm qc = new QueryComplaintForm();
				qc.setOrderForm(orderFrom);
				qc.setOrderTo(orderTo);
				qc.setRiderID(riderList.get(i).getId());
				try {
					complaintList =  complaintFormService.selectMoneyByRider(qc);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(complaintList!=null){
					for(int l=0;l<complaintList.size();l++){
						amerce = amerce+complaintList.get(l).getComplaintAmount();
					}
				}
				//扣除物资费用
				QueryRegistrationForm qf = new QueryRegistrationForm();
				qf.setOrderForm(orderFrom);
				qf.setOrderTo(orderTo);
				qf.setRiderID(riderList.get(i).getId());
				try {
					goodFeeList = goodsRegistrationFormService.selectClaimantBy(qf);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(goodFeeList!=null){
					for(int k=0;k<goodFeeList.size();k++){
						double price = goodFeeList.get(k).getClaimantCount()*goodFeeList.get(k).getPrice();
					 
						goodFee = goodFee-price;
					}
				}
				//计算个人所得税
				double Tax = 0;//税率
				double Calculation  =0;//速算扣除数
				double CalMount = subsidization-insuranceFee+amerce-3500;//应纳税所得额(含税)
				if(CalMount<=1500&&CalMount>0){
					Tax = 0.03;
					Calculation = 0;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(1500<CalMount&&CalMount<=4500){
					Tax = 0.10;
					Calculation = 105;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(4500<CalMount&&CalMount<=9000){
					Tax = 0.20;
					Calculation = 555;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(9000<CalMount&&CalMount<=35000){
					Tax = 0.25;
					Calculation = 1005;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(35000<CalMount&&CalMount<=55000){
					Tax = 0.30;
					Calculation = 2755;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(55000<CalMount&&CalMount<=80000){
					Tax = 0.35;
					Calculation = 5505;
					TaxMoney = CalMount*Tax-Calculation;
				}
				else if(80000<CalMount){
					Tax = 0.45;
					Calculation = 13505;
					TaxMoney = CalMount*Tax-Calculation;
				}
			//写入数据
			 Calendar now = Calendar.getInstance();  
			 Wage wage = new Wage();
			 wage.setTeamID(riderList.get(i).getTeamID());
			 wage.setId(riderList.get(i).getId());
			 wage.setTotalCount(totalCount);
			 wage.setErrorCount(errorCount);
			 wage.setNormalCount(normalCount);
			 wage.setTimeoutCount(timeoutCount);
			 wage.setOnTimeCount(onTimeCount);
			 wage.setSubsidization(subsidization);
			 wage.setAttendance(attendance);
			 wage.setAdvancePay(0.0);
			 wage.setAmerce(amerce);
			 if(this.getCurrentUser()!=null) {
				 wage.setCreateBy(this.getCurrentUser().getId());
			 }else{
				 wage.setCreateBy((long) 1001);
			 }
			//格式化日期
			String dateStr = "";
				SimpleDateFormat	format = new SimpleDateFormat("yyyy-MM-dd");
			 dateStr =format.format(new Date());
			 wage.setCreateTime(dateStr);
			 wage.setElectricity(0.0);
			 wage.setIncomeTax(-TaxMoney);
			 wage.setInsuranceFee(-insuranceFee);
			 wage.setLeakageBuckle(0.0);
			 wage.setOverAllowance(0.0);
			 wage.setRealPay(subsidization+goodFee-insuranceFee+amerce+attendance-TaxMoney);
			 wage.setReferralFee(0.0);
			 wage.setGoodFee(goodFee);
			 wage.setShouldPay(subsidization-insuranceFee+amerce+attendance);
			 now.add(Calendar.MONTH, -1);
			 wage.setWageDate(format.format(now.getTime()));
			 
			 //插入数据
			 try {
				if(wageService.psn_insert(wage)>0){
				};
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}
			}
		}
		
		 Calendar now = Calendar.getInstance();  
		 now = Calendar.getInstance();
			now.add(Calendar.MONTH, -1);
		QueryWage queryWage = new QueryWage();
		if (orderFrom != null && orderFrom.toString() != "") {
			queryWage.setOrderForm(orderFrom);
		}else{
			queryWage.setOrderForm(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
		}
		now = Calendar.getInstance();
	 
	
		if (orderTo != null && orderTo.toString() != "") {
			queryWage.setOrderTo(orderTo);
		}else{
			queryWage.setOrderTo(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
		}
		if (riderID != null && riderID.toString() != "") {
			queryWage.setRiderID(Integer.valueOf(riderID));
		}
		if (riderName != null && riderName.toString() != "") {
			queryWage.setRiderName(riderName);
		}
		if (teamID != null && teamID.toString() != "" && teamID.toString() != "undefined") {
			queryWage.setTeamID(Integer.valueOf(teamID));
		}
		if (zeroNotDisp != null && zeroNotDisp.toString() != "") {
			if(zeroNotDisp.equals("true")){
			queryWage.setZeroNotDisp("0");	
			}
			
		}
		PageSupport page = new PageSupport();	
		
		try {
			//获取后台总记录数
	page.setTotalCount(wageService.CountByRider(queryWage));
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
						wage = wageService.selectWageList(queryWage);
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
				now.add(Calendar.MONTH, -1);
				if(orderFrom==null){
					
					model.addObject("orderFrom", now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
				}else{
					model.addObject("orderFrom", orderFrom);
				}
				
				now = Calendar.getInstance();
				if(orderTo==null){
					model.addObject("orderTo", now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1) + "-01");
				}else{
					model.addObject("orderTo", orderTo);
				}
				model.addAllObjects(baseModel);//加载用户所属的功能菜单
				model.addObject("riderID", riderID);
				model.addObject("riderName", riderName);
				model.addObject("teamID", teamID);
				model.addObject("zeroNotDisp", zeroNotDisp);
				model.setViewName("orders/checkPayroll");
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
	@RequestMapping(value="/frontend/outputOrder.html",method=RequestMethod.POST)
	@ResponseBody
	public Object outputOrder(
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="riderID",required=false) String riderID,
			@RequestParam(value="riderName",required=false) String riderName,
			@RequestParam(value="teamID",required=false) String teamID,
			@RequestParam(value="zeroNotDisp",required=false) String zeroNotDisp,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		 String filedisplay = "";//导出文件名称
		 String rPath ="";//导出文件路径
		 String path="";//下载路径
		try{
		getConnect();
		  Statement stat = (Statement) con.createStatement();
		  String sql = "SELECT te.teamName,w.wageDate,w.id,r.riderName,w.errorCount,w.normalCount,w.timeoutCount,w.onTimeCount,w.subsidization,w.leakageBuckle,w.referralFee,";
		  sql+="w.insuranceFee,w.amerce,w.overAllowance,w.shouldPay,w.incomeTax,w.advancePay,w.electricity,w.realPay,";
		  sql+="w.createTime,u.userName,w.modifyTime,t.userName,w.remarks  FROM wage as w ";
		  sql+="LEFT JOIN u_user as u ON w.createBy = u.id ";
		  sql+="LEFT JOIN u_user as t ON w.modifyBy = t.id ";
		  sql+="LEFT JOIN rider as r ON w.id = r.id ";
		  sql+="LEFT JOIN team as te ON w.teamID = te.id ";
		  if(orderFrom!=null&&orderFrom!=""){
			  sql+=" where w.createTime >= '"+orderFrom+"'";
		  }
		  if(orderTo!=null&&orderTo!=""){
			  sql+=" and w.createTime < '"+orderTo+"'";
		  }
		  if(riderID!=null&&riderID!=""){
			  sql+=" and w.id = '"+riderID+"'";
		  }
		  if(riderName!=null&&riderName!=""){
			  sql+=" and r.riderName = '"+riderName+"'";
		  }
		  if(teamID!=null&&teamID!=""){
			  sql+=" and w.teamID = '"+teamID+"'";
		  }
		  if(zeroNotDisp!=null&&zeroNotDisp!=""){
			  if(zeroNotDisp.equals("true")){
			  sql+=" and w.subsidization <> '"+"0"+"'";
			  }
		  }
            ResultSet resultSet = stat.executeQuery(sql);
            SXSSFWorkbook workbook=new SXSSFWorkbook(100);
            SXSSFSheet sheet=workbook.createSheet("wage");
            SXSSFRow row = sheet.createRow((short)0);
            SXSSFCell cell=null;
            cell=row.createCell((short)0);
            cell.setCellValue("站点名称");
            cell=row.createCell((short)1);
            cell.setCellValue("工资年月");
            cell=row.createCell((short)2);
            cell.setCellValue("骑手id");
            cell=row.createCell((short)3);
            cell.setCellValue("骑手名称");
            cell=row.createCell((short)4);
            cell.setCellValue("异常单数");
            cell=row.createCell((short)5);
            cell.setCellValue("正常单数");
            cell=row.createCell((short)6);
            cell.setCellValue("超时单数");
            cell=row.createCell((short)7);
            cell.setCellValue("准时单数");
            cell=row.createCell((short)8);
            cell.setCellValue("提成");
            cell=row.createCell((short)9);
            cell.setCellValue("上月未发/漏扣");
            cell=row.createCell((short)10);
            cell.setCellValue("员工介绍费");
            cell=row.createCell((short)11);
            cell.setCellValue("保险费");
            cell=row.createCell((short)12);
            cell.setCellValue("投诉罚款");
            cell=row.createCell((short)13);
            cell.setCellValue("超时补贴");
            cell=row.createCell((short)14);
            cell.setCellValue("应发工资");
            cell=row.createCell((short)15);
            cell.setCellValue("个人所得税");
            cell=row.createCell((short)16);
            cell.setCellValue("预支工资");
            cell=row.createCell((short)17);
            cell.setCellValue("电费");
            cell=row.createCell((short)18);
            cell.setCellValue("实发工资");
            cell=row.createCell((short)19);
            cell.setCellValue("创建时间");
            cell=row.createCell((short)20);
            cell.setCellValue("创建人");
            cell=row.createCell((short)21);
            cell.setCellValue("修改时间");
            cell=row.createCell((short)22);
            cell.setCellValue("修改人");
            cell=row.createCell((short)23);
            cell.setCellValue("备注");
            
            int i=1;
            while(resultSet.next())
            {
                row=sheet.createRow(i);
                cell=row.createCell(0);
                cell.setCellValue(resultSet.getString("teamName"));
                cell=row.createCell(1);
                cell.setCellValue(resultSet.getString("wageDate"));
                cell=row.createCell(2);
                cell.setCellValue(resultSet.getString("id"));
                cell=row.createCell(3);
                cell.setCellValue(resultSet.getString("riderName"));
                cell=row.createCell(4);
                cell.setCellValue(resultSet.getString("errorCount"));
                cell=row.createCell(5);
                cell.setCellValue(resultSet.getString("normalCount"));
                cell=row.createCell(6);
                cell.setCellValue(resultSet.getString("timeoutCount"));
                cell=row.createCell(7);
                cell.setCellValue(resultSet.getString("onTimeCount"));
                cell=row.createCell(8);
                cell.setCellValue(resultSet.getString("subsidization"));
                cell=row.createCell(9);
                cell.setCellValue(resultSet.getString("leakageBuckle"));
                cell=row.createCell(10);
                cell.setCellValue(resultSet.getString("referralFee"));
                cell=row.createCell(11);
                cell.setCellValue(resultSet.getString("insuranceFee"));
                cell=row.createCell(12);
                cell.setCellValue(resultSet.getString("amerce"));
                cell=row.createCell(13);
                cell.setCellValue(resultSet.getString("overAllowance"));
                cell=row.createCell(14);
                cell.setCellValue(resultSet.getString("shouldPay"));
                cell=row.createCell(15);
                cell.setCellValue(resultSet.getString("incomeTax"));
                cell=row.createCell(16);
                cell.setCellValue(resultSet.getString("advancePay"));
                cell=row.createCell(17);
                cell.setCellValue(resultSet.getString("electricity"));
                cell=row.createCell(18);
                cell.setCellValue(resultSet.getString("realPay"));
                cell=row.createCell(19);
                cell.setCellValue(resultSet.getString("createTime"));
                cell=row.createCell(20);
                cell.setCellValue(resultSet.getString("userName"));
                cell=row.createCell(21);
                cell.setCellValue(resultSet.getString("modifyTime"));
                cell=row.createCell(22);
                cell.setCellValue(resultSet.getString("userName"));
                cell=row.createCell(23);
                cell.setCellValue(resultSet.getString("remarks"));
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
		String rePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/statics/uploadfiles/"+rPath;
		return rePath;
	}
	
	 /**
		 * 导出银行对接工资表
		 * @param accendantFile
		 * @param session
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value="/frontend/outputOrderByBank.html",method=RequestMethod.POST)
		@ResponseBody
		public Object outputOrderByBank(
				@RequestParam(value="orderFrom",required=false) String orderFrom,
				@RequestParam(value="orderTo",required=false) String orderTo,
				@RequestParam(value="riderID",required=false) String riderID,
				@RequestParam(value="riderName",required=false) String riderName,
				@RequestParam(value="teamID",required=false) String teamID,
				@RequestParam(value="zeroNotDisp",required=false) String zeroNotDisp,
				HttpServletRequest request, HttpServletResponse response) throws IOException{
			 String filedisplay = "";//导出文件名称
			 String rPath ="";//导出文件路径
			 String path="";//下载路径
			 
			try{
			getConnect();
			  Statement stat = (Statement) con.createStatement();
			  String sql = "SELECT u.bankAccount,u.accountHolder,w.realPay,u.bankOfDeposit,u.bankAddress,w.remarks  FROM wage as w ";
			  sql+="LEFT JOIN u_bank_account as u ON w.id = u.empId ";
			  if(orderFrom!=null&&orderFrom!=""){
				  sql+=" where w.wageDate >= '"+orderFrom+"'";
			  }
			  if(orderTo!=null&&orderTo!=""){
				  sql+=" and w.wageDate < '"+orderTo+"'";
			  }
			  if(riderID!=null&&riderID!=""){
				  sql+=" and w.id = '"+riderID+"'";
			  }
			  if(riderName!=null&&riderName!=""){
				  sql+=" and r.riderName = '"+riderName+"'";
			  }
			  if(teamID!=null&&teamID!=""){
				  sql+=" and w.teamID = '"+teamID+"'";
			  }
			  if(zeroNotDisp!=null&&zeroNotDisp!=""){
				  if(zeroNotDisp.equals("true")){
				  sql+=" and w.subsidization <> '"+"0"+"'";
				  }
			  }
	            ResultSet resultSet = stat.executeQuery(sql);
	            SXSSFWorkbook workbook=new SXSSFWorkbook(100);
	            SXSSFSheet sheet=workbook.createSheet("wage");
	            SXSSFRow row = sheet.createRow((short)0);
	            SXSSFCell cell=null;
	            cell=row.createCell((short)0);
	            cell.setCellValue("帐号");
	            cell=row.createCell((short)1);
	            cell.setCellValue("户名");
	            cell=row.createCell((short)2);
	            cell.setCellValue("金额");
	            cell=row.createCell((short)3);
	            cell.setCellValue("开户行");
	            cell=row.createCell((short)4);
	            cell.setCellValue("开户地");
	            cell=row.createCell((short)5);
	            cell.setCellValue("注释");
	            
	            
	            int i=1;
	            while(resultSet.next())
	            {
	                row=sheet.createRow(i);
	                cell=row.createCell(0);
	                cell.setCellValue(resultSet.getString("bankAccount"));
	                cell=row.createCell(1);
	                cell.setCellValue(resultSet.getString("accountHolder"));
	                cell=row.createCell(2);
	                cell.setCellValue(resultSet.getString("realPay"));
	                cell=row.createCell(3);
	                cell.setCellValue(resultSet.getString("bankOfDeposit"));
	                cell=row.createCell(4);
	                cell.setCellValue(resultSet.getString("bankAddress"));
	                cell=row.createCell(5);
	                cell.setCellValue(resultSet.getString("remarks"));
	                
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
			String rePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/statics/uploadfiles/"+rPath;
			return rePath;
		}
	
	/**
	 * 进入工资详情
	 * @return
	 */
	@RequestMapping(value="/pay/checkPayrollDetail.do",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkPayrollDetail ( 
			@RequestParam(value="riderID",required=false) String riderID
		){
		if(riderID!=null&&riderID!=""){
			String str = "";
		Wage wage = new Wage();
		try {
			wage =wageService.selectById(Long.valueOf(riderID));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wage!=null){
		  JSONObject json = JSONObject.fromObject(wage);
		  str = json.toString();
		  }
		    return str;
		}else{
			return "";
		}
	}
	
	/**
	 * 获取骑手领用记录
	 * @return
	 */
	@RequestMapping(value="/pay/goodList.do",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String goodList ( 
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="riderID",required=false) String riderID
		){
		QueryWage queryWage = new QueryWage();
		if (orderFrom != null && orderFrom.toString() != "") {
			queryWage.setOrderForm(orderFrom);
		}
		if (orderTo != null && orderTo.toString() != "") {
			queryWage.setOrderTo(orderTo);
		}
		if(riderID!=null&&riderID!=""){
			queryWage.setRiderID(Integer.valueOf(riderID));
			String str = "";
			List<GoodsRegistrationForm> goodList=null;
		try {
			goodList =wageService.selectGoodsList(queryWage);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(goodList!=null){
			JSONArray jsonArray =JSONArray.fromObject(goodList);//把list转成JSONArray
			str= jsonArray.toString();//json字符串
		  }
		    return str;
		}else{
			return "";
		}
	}
	
	/**
	 * 修改工资信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pay/checkPayrollModify.do",method=RequestMethod.POST)
	@ResponseBody
	public String checkPayrollModify ( 
			@RequestParam(value="jsonData",required=false) String jsonData
		) throws Exception{
		JSONObject wageObject = JSONObject.fromObject(jsonData);
		Wage wage = (Wage)wageObject.toBean(wageObject, Wage.class);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wage.setModifyTime(format.format(new Date()));
		wage.setModifyBy(this.getCurrentUser().getId());
		if(wageService.psn_update(wage)>0){
			 return "1";//成功
		}
	 return "0";//失败
	}
	/**
	 * 删除工资记录 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pay/checkPayrollDelete.do",method=RequestMethod.POST)
	@ResponseBody
	public String checkPayrollDelete( 
			@RequestParam(value="riderID",required=false) Long riderID
		) throws Exception{
		 
		Wage wage = new Wage();
		 wage.setId(riderID);
		if(wageService.psn_delete(wage)>0){
			 return "1";//成功
		}
	 return "0";//失败
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
