package com.mtr.psn.controller.goods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtr.psn.common.Constants;
import com.mtr.psn.common.PageSupport;
import com.mtr.psn.common.SQLTools;
import com.mtr.psn.controller.BaseController;
import com.mtr.psn.model.goods.GoodsForm;
import com.mtr.psn.model.goods.GoodsRegistrationForm;
import com.mtr.psn.model.member.Rider;
import com.mtr.psn.model.member.Team;
import com.mtr.psn.model.other.DataDictionary;
import com.mtr.psn.model.pay.Salary_d;
import com.mtr.psn.model.pay.Wage;
import com.mtr.psn.model.webEntity.GoodsRegistrationFormEntiy;
import com.mtr.psn.service.goods.GoodsFormService;
import com.mtr.psn.service.goods.GoodsRegistrationFormService;
import com.mtr.psn.service.member.RiderService;
import com.mtr.psn.service.member.TeamService;
import com.mtr.psn.service.other.DataDictionaryService;
import com.mysql.jdbc.StringUtils;

@Controller
public class GoodsRegistrationFormController extends BaseController {

	@Resource
	private GoodsRegistrationFormService registrationFormService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private GoodsFormService goodsFormService;
	@Resource
	private RiderService riderService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private GoodsRegistrationFormService goodsRegistrationFormService;
	
	
	
	private static Connection con;
	
	/**
	 * 去记录申领信息
	 * @param grfEntity
	 * @param session
	 * @return
	 */
	@RequestMapping("/goods/intoAddClaimant.html")
	public ModelAndView intoAddClaimant(@ModelAttribute GoodsRegistrationFormEntiy grfEntity,HttpSession session){
		ModelAndView model = new ModelAndView();
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			model.setViewName("redirect:/");
		}else{
			//获取goodsTypeList
			List<DataDictionary> goodsTypeList = null;
			try {
				goodsTypeList = dataDictionaryService.getDataByTypeCode("GOODS_TYPE");
			} catch (Exception e) {
				// TODO: handle exception
			}
			model.addAllObjects(baseModel);//加载用户所属的功能菜单
			model.addObject("goodsTypeList", goodsTypeList);
		}
		return model;
	}
	
	@RequestMapping("/goods/addClaimant.html")
	public String addClaimantForm(){
		
		
		return "";
	}
	
	/**
	 * 获取团队列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/goods/getTeam.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getMobileBrand(){
		String cjson="";
		try{
			Team team = new Team();
			List<Team> teams = teamService.selectAll(team);
			JSONArray jo=JSONArray.fromObject(teams);
			cjson=jo.toString();
		}catch(Exception e1){
			e1.printStackTrace();	
		}
		return cjson;
		}
	
	
	/**
	 * 获取物资列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/goods/getGoods.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getGoods(){
		String cjson="";
		try{
			GoodsForm goodsForm= new GoodsForm();
			List<GoodsForm> goodsForms = goodsFormService.selectAll(goodsForm);
			JSONArray jo=JSONArray.fromObject(goodsForms);
			cjson=jo.toString();
		}catch(Exception e1){
			e1.printStackTrace();	
		}
		return cjson;
		}
	
	/**
	 * 物质申领登记列表
	 * @param model
	 * @param session
	 * @param currentpage
	 * @param s_goodsName
	 * @param s_claimantName
	 * @param s_claimantPhone
	 * @param s_dep
	 * @return
	 */

	@RequestMapping("/goods/goodsRegistrationFormList.html")
	public ModelAndView goodsRegistrationFormList(Model model,HttpSession session, 
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="s_goodsName",required=false) String s_goodsName,
			@RequestParam(value="s_claimantName",required=false) String s_claimantName,
			@RequestParam(value="s_claimantPhone",required=false) String s_claimantPhone,
			@RequestParam(value="s_dep",required=false) String s_dep){
				
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			//获取goodsTypeList
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("GOODS_TYPE");
			List<DataDictionary> goodsTypeList = null;
			try {
				goodsTypeList = dataDictionaryService.selectAll(dataDictionary);
			} catch (Exception e) {
				// TODO: handle exception
			}
			/*
			 * 查询条件
			 */
			GoodsRegistrationForm registrationForm = new GoodsRegistrationForm();
			if(s_goodsName != null && s_goodsName != ""){
				registrationForm.setGoodsName("%"+SQLTools.transfer(s_goodsName)+"%");
			}
			if(s_claimantName != null && s_claimantName != ""){
				registrationForm.setClaimantName("%"+SQLTools.transfer(s_claimantName)+"%");
			}
			if(s_claimantPhone != null && s_claimantPhone != ""){
				registrationForm.setClaimantPhone("%"+SQLTools.transfer(s_claimantPhone)+"%");
			}
			if(!StringUtils.isNullOrEmpty(s_dep)){// != null and ""
				registrationForm.setDep(Long.valueOf(s_dep));
			}else{
				registrationForm.setDep(null);
			}
			
			/*
			 * page分页列表
			 */
			PageSupport page = new PageSupport();			
			try {
				//获取后台总记录数
				page.setTotalCount(registrationFormService.count(registrationForm));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				page.setTotalCount(0);
			}
			//总记录数必须大于0 才有意义
			if(page.getTotalCount() > 0){
				if(currentpage != null){
					page.setPage(currentpage);//设置当前页（用户输入）
				}
				if(page.getPage() <= 0){
					page.setPage(1);
				}
				if(page.getPage() > page.getPageCount()){
					page.setPage(page.getPageCount());
				}
				
				registrationForm.setStarNum((page.getPage()-1)*page.getPageSize());//设置开始行
				registrationForm.setPageSize(page.getPageSize());//设置每页显示数量
				//该用户当前页列表的数据
				List<GoodsRegistrationFormEntiy> registrationFormEntiys = null;
				try {
					registrationFormEntiys = registrationFormService.getPagesList(registrationForm);
				} catch (Exception e) {
					// TODO: handle exception
					registrationFormEntiys = null;
					if(page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(registrationFormEntiys);//设置当前页列表数据
			}else{
				page.setItems(null);
			}			
			
			model.addAllAttributes(baseModel);//加载用户所属的功能菜单
			model.addAttribute("page",page);
			model.addAttribute("goodsTypeList", goodsTypeList);
			model.addAttribute("s_goodsName", s_goodsName);
			model.addAttribute("s_claimantName", s_claimantName);
			model.addAttribute("s_claimantPhone", s_claimantPhone);
			model.addAttribute("s_dep", s_dep);
			
			return new ModelAndView("/goods/goodsRegistrationFormList");
		}
	}
	
	/**
	 * 导入配送订单
	 * @param accendantFile
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/frontend/importGoods.do", method = RequestMethod.POST)
	@ResponseBody
	public String importOrder(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "importPath", required = false) String importPath) {

		String path = request.getSession()
				.getServletContext().getRealPath("statics" + File.separator + "uploadfiles")+ "\\";
		long startTime = System.currentTimeMillis();
		File file = new File(path + importPath);

		InputStreamReader read = null;
		BufferedReader bfr = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), "gbk");
			bfr = new BufferedReader(read);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Scanner in = new Scanner(file); */

		getConnect();
		System.out.println("数据库连接成功");
		try {
			insert_data(bfr);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return "成功";
	}
	
	/**
	 * 进入工资详情
	 * @return
	 */
	/*@RequestMapping(value="/pay/checkPayrollDetail.do",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
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
	}*/
	/**
	 * 新增物资
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goods/GoodsAdd.html",method=RequestMethod.POST)
	@ResponseBody
	public String GoodsAdd ( 
			@RequestParam(value="jsonData",required=false) String jsonData
		) throws Exception{
		JSONObject salary_dObject = JSONObject.fromObject(jsonData);
		GoodsRegistrationForm goodsRegistrationForm = (GoodsRegistrationForm)salary_dObject.toBean(salary_dObject, GoodsRegistrationForm.class);
		//根据部门id和骑手名称获取骑手id（不一定唯一）
		Rider rider = new Rider();
		rider.setRiderName(goodsRegistrationForm.getClaimantName());
		rider.setTeamID(goodsRegistrationForm.getDep());
		rider = riderService.selectRiderName(rider);
		if(rider==null||rider.getId()==null){
			 return "2";//骑手不存在
		}
		goodsRegistrationForm.setClaimantBy(rider.getId());
		goodsRegistrationForm.setHandledBy(this.getCurrentUser().getId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		goodsRegistrationForm.setCreateTime(format.format(new Date()));
		goodsRegistrationForm.setCreateBy(this.getCurrentUser().getId());
		if(goodsRegistrationFormService.psn_insert(goodsRegistrationForm)>0){
			 return "1";//成功
		}
	 return "0";//失败
	}
	/**
	 * 进入物资详情
	 * @return
	 */
	@RequestMapping(value="/goods/goodsDetail.do",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String goodsDetail ( 
			@RequestParam(value="id",required=false) String id
		){
		if(id!=null&&id!=""){
			String str = "";
			GoodsRegistrationForm goodsRegistrationForm = new GoodsRegistrationForm();
		try {
			goodsRegistrationForm =goodsRegistrationFormService.selectById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(goodsRegistrationForm!=null){
		  JSONObject json = JSONObject.fromObject(goodsRegistrationForm);
		  str = json.toString();
		  }
		    return str;
		}else{
			return "";
		}
	}
	/**
	 * 修改物资
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goods/modifyGoods.do",method=RequestMethod.POST)
	@ResponseBody
	public String checkPayrollModify ( 
			@RequestParam(value="jsonData",required=false) String jsonData
		) throws Exception{
		JSONObject wageObject = JSONObject.fromObject(jsonData);
		GoodsRegistrationForm wage = (GoodsRegistrationForm)wageObject.toBean(wageObject, GoodsRegistrationForm.class);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wage.setModifyTime(format.format(new Date()));
		wage.setModifyBy(this.getCurrentUser().getId());
		if(goodsRegistrationFormService.psn_update(wage)>0){
			 return "1";//成功
		}
	 return "0";//失败
	}
	/**
	 * 删除物资
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goods/deleteGoods.html",method=RequestMethod.POST)
	@ResponseBody
	public String salaryMxDelete( 
			@RequestParam(value="id",required=false) Long id
		) throws Exception{
		GoodsRegistrationForm goodsRegistrationForm = new GoodsRegistrationForm();
		goodsRegistrationForm.setId(id);
		if(goodsRegistrationFormService.psn_delete(goodsRegistrationForm)>0){
			 return "1";//成功
		}
	 return "0";//失败
	}
	/**
	 * 导入物资申领表
	 * @param bfr
	 */
	private void insert_data(BufferedReader bfr) { // 导入物资申领表
		int count = 0;
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "insert IGNORE into mtr_goods_registration_form ("
				+ "goodsId," + "goodsName," + "claimantCount," + "claimantBy,"
				+ "claimantName," + "claimantPhone," + "dep," + "claimantTime,"
				+ "claimantSite," + "handledBy," + "createBy," + "createTime,"
				+ "remarks" + ")" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String line = "";
		try {
			bfr.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			while ((line = bfr.readLine()) != null) {
				String[] temp = null;
				temp = line.split(",");
				int index = 1;
				long num = 0;
				try {
					num =  goodsFormService.selectByName(temp[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (num == 0) {// 找不到物资直接跳过
					continue;
				}
				pstmt.setLong(index++, Long.valueOf(num));
				pstmt.setString(index++, temp[0]);
				pstmt.setInt(index++, Integer.valueOf(temp[1]));
				pstmt.setLong(index++, Long.valueOf(temp[2]));
				pstmt.setString(index++, temp[3]);
				pstmt.setString(index++, temp[4]);
				num = 0;
				try {
					num = riderService.selectTeamId(Long.valueOf(temp[2]));
				} catch (Exception e) {
					num = 0;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (num == 0) {// 找不到骑手直接跳过
					continue;
				}
				String date = sdf.format(new Date());
				pstmt.setLong(index++, num);
				pstmt.setString(index++, sdf.format(new Date()));
				pstmt.setString(index++, temp[5]);
				pstmt.setLong(index++, this.getCurrentUser().getId());
				pstmt.setLong(index++, this.getCurrentUser().getId());
				pstmt.setString(index++, sdf.format(new Date()));
				pstmt.setString(index++, temp[6]);
				pstmt.addBatch();
				count++;

				if (count == 20000) {
					count = execute(pstmt, count);
				}
			}
			
			pstmt.executeBatch();
			con.commit();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int execute(PreparedStatement pstmt, int count) {

		try {
			pstmt.executeBatch();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private static void getConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/pay_state_nuclear_db?useUnicode=true&useOldAliasMetadataBehavior=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&useSSL=true",
							"root", "123456");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
