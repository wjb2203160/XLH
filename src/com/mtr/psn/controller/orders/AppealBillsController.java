package com.mtr.psn.controller.orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
 
import java.util.Calendar;
import java.util.Date;
 
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
 
import org.springframework.stereotype.Controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mtr.psn.common.Constants;
import com.mtr.psn.common.PageSupport;
 
import com.mtr.psn.controller.BaseController;
import com.mtr.psn.model.orders.Appealform;
 
import com.mtr.psn.model.orders.QueryAppealForm;
 
import com.mtr.psn.model.other.DataDictionary;
 
import com.mtr.psn.service.orders.AppealFormService;
 
 
import com.mtr.psn.service.other.DataDictionaryService;
 
@Controller
public class AppealBillsController extends BaseController {

	@Resource
	private AppealFormService appealformService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	private static Connection con;
	/**
	 * 进入表格
	 
	 * @return
	 */
	@RequestMapping(value="/orders/appealBills.html",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView maincalendar (HttpSession session, 
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="orderFrom",required=false) String orderFrom,
			@RequestParam(value="orderTo",required=false) String orderTo,
			@RequestParam(value="result",required=false) String result,
			@RequestParam(value="overTime",required=false) String overTime,
			@RequestParam(value="riderID",required=false) Long riderID 
		){
		if(orderFrom!=null&&orderTo!=null){
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
			 
			}
			}
		ModelAndView model = new ModelAndView();
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			QueryAppealForm appealform = new QueryAppealForm();
			if (orderFrom != null && orderFrom.toString() != "") {
				appealform.setOrderForm(orderFrom);
			}
			if (orderTo != null && orderTo.toString() != "") {
				appealform.setOrderTo(orderTo);
			}
			if (riderID != null && riderID.toString() != "") {
				appealform.setRiderID(riderID);
			}
			 
			PageSupport page = new PageSupport();	
			
			try {
				//获取后台总记录数
		 	page.setTotalCount(appealformService.Count(appealform));
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
				
				appealform.setStarNum((page.getPage()-1)*page.getPageSize());//设置开始行
				appealform.setPageSize(page.getPageSize());//设置每页显示数量
				//该用户当前页列表的数据
				List<Appealform> orderForm = null;
				try {
				 	orderForm = appealformService.selectAppealFormList(appealform);
				} catch (Exception e) {
					// TODO: handle exception
					orderForm = null;
					if(page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(orderForm);//设置当前页列表数据
			}else{
				page.setItems(null);
			}
			model.addAllObjects(baseModel);//加载用户所属的功能菜单
			model.addObject("page",page);
			Calendar	now = Calendar.getInstance();
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
			model.addObject("riderID", riderID);
			model.setViewName("/orders/appealBills");
		}
	 return  model;
	}
	
	/**
	 * 上传申诉订单
	 * @param accendantFile
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/frontend/appealUpload.html",produces={"text/html;charset=UTF-8"},method=RequestMethod.POST)
	@ResponseBody
	public Object upload(
			@RequestParam(value="b_fileInutID",required=false) MultipartFile accendantFile,
			HttpSession session,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles")+"/";
		logger.debug("====================path"+path);
		DataDictionary dataDictionary = new DataDictionary();
		List<DataDictionary> list = null;
		dataDictionary.setTypeCode("FILE_SIZE");
		try {
			list = dataDictionaryService.selectAll(dataDictionary);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int filesize = 1000000000;
		if(list != null){
			if(list.size() == 1)
				filesize = Integer.valueOf(list.get(0).getValueName());
		}
		if(accendantFile != null){
			String oldFileName = accendantFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			logger.debug("cardFile prefix =================="+prefix);
			if(accendantFile.getSize() > filesize){
				return "1";
			}else if(prefix.equalsIgnoreCase("csv") || prefix.equalsIgnoreCase("CSV")
					){
				//给文件进行重命名：系统毫秒数+100w以内的随机数
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_AdPic.csv";
				logger.debug("new filename==============="+fileName);
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				//保存。上传
				try {
					accendantFile.transferTo(targetFile);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String url = "{\"returnUrl\":\""+fileName+"\"}";
				return url;
			}else{
				return "2";
			}
		}
		return null;
	}
	/**
	 * 导入申诉订单
	 * @param accendantFile
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value="/frontend/importAppeal.html",method=RequestMethod.POST)
	@ResponseBody
	public Object importOrder(
			@RequestParam(value="importPath",required=false) String importPath,
			HttpSession session,HttpServletRequest request) throws NumberFormatException, SQLException, IOException{
		  
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles")+"/";
        long startTime = System.currentTimeMillis();  
        File file = new File(path+importPath);  
        
        InputStreamReader read = null;
        BufferedReader bfr=null;
		try {
			read = new InputStreamReader(new FileInputStream(file),"gbk");
			bfr=new BufferedReader(read);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      /*  Scanner in = new Scanner(file);  */
  
        getConnect();  
        System.out.println("数据库连接成功");  
        insert_data(bfr);  
        long EndTime = System.currentTimeMillis();  
        long time = (EndTime - startTime) / 1000;  
        return time;
	}
	  private static void insert_data(BufferedReader bfr) throws SQLException, NumberFormatException, IOException  
	    {   //导入配送订单
		  int count = 0;  
	        String sql = "insert ignore into appealform (complaintDate,complaintNum,complaintType,franchiseeID,teamID,riderID,id,billNum,complaintAmount,reason)"  
	                + "values(?,?,?,?,?,?,?,?,?,?)";  
	  
	        con.setAutoCommit(false);  
	        PreparedStatement pstmt = con.prepareStatement(sql);  
	        String line="";
	        bfr.readLine();
	     
	        while ((line=bfr.readLine())!=null)  
	        {  
	        	String[] temp = null;
	            temp = line.split(",");
	            int index=1;
	            //格式化日期
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					Date date = format.parse(temp[0]);
				 
					format = new SimpleDateFormat("yyyy-MM-dd");
					temp[0] =format.format(date);
					 pstmt.setString(index++, temp[0]);  
				} catch (ParseException e2) {
					 pstmt.setString(index++, temp[0]);  
					// TODO Auto-generated catch block
				 
				}
	               
	                pstmt.setInt(index++, Integer.valueOf(temp[1]));  
	                pstmt.setString(index++, temp[2]);  
	                pstmt.setInt(index++, Integer.valueOf(temp[4]));  
	                pstmt.setInt(index++, Integer.valueOf(temp[6]));  
	                pstmt.setInt(index++, Integer.valueOf(temp[8]));  
	                pstmt.setLong(index++, Long.valueOf(temp[9].substring(1,temp[9].length()-1).trim()));  
	                pstmt.setLong(index++,  Long.valueOf(temp[10].substring(1,temp[10].length()-1).trim()));    
	                pstmt.setDouble(index++, Double.valueOf(temp[11]));  
	                pstmt.setString(index++, temp[12]);  
	             
	  
	            pstmt.addBatch();  
	  
	            count++;  
	  
	            if (count == 20000)  
	            {  
	                count = execute(pstmt, count);  
	            }  
	        }  
	        pstmt.executeBatch();  
	        con.commit();  
	  }
	  public static int execute(PreparedStatement pstmt, int count) throws SQLException  
	    {  
	  
	        pstmt.executeBatch();  
	        con.commit();  
	        return 0;  
	  
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
