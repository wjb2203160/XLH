package com.mtr.psn.controller.user;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mtr.psn.common.Constants;
import com.mtr.psn.common.JsonDateValueProcessor;
import com.mtr.psn.common.PageSupport;
import com.mtr.psn.common.SQLTools;
import com.mtr.psn.controller.BaseController;
import com.mtr.psn.model.other.DataDictionary;
import com.mtr.psn.model.user.Role;
import com.mtr.psn.model.user.User;
import com.mtr.psn.model.webEntity.UserEntity;
import com.mtr.psn.service.other.DataDictionaryService;
import com.mtr.psn.service.user.RoleService;
import com.mtr.psn.service.user.UserService;
import com.mysql.jdbc.StringUtils;
@Controller
// @RequestMapping(value="/user")//只要是这个处理器处理的都要加上user:如果报错则考虑是否加上“ /”
public class UserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	/**
	 * 修改密码
	 * @param HttpSession session session已存在BaseController里
	 * @param userJson 页面返回的数据
	 * @return
	 */
	@RequestMapping("/backend/modifypwd.html")
	@ResponseBody
	public Object modifyPwd(@RequestParam String userJson){
		User sessionUser = this.getCurrentUser();
		if(userJson == null || userJson.equals("")){
			return "nodata";
		}else{
			//将页面返回的数据转换成User数据对象
			JSONObject userObject = JSONObject.fromObject(userJson);
			User user = (User)JSONObject.toBean(userObject, User.class);
			user.setId(sessionUser.getId());
			user.setCellphone(sessionUser.getCellphone());
			try {
				/*
				 * 如果查询到数据，则输入的密码正确
				 */
				if(userService.selectUser(user) != null){
					user.setPassword(user.getRemarks());
					user.setRemarks(null);//作为转接数据
					userService.psn_update(user);
				}else{
					return "oldpwdwroog";
				}
			} catch (Exception e) {
				// TODO: handle exception
				return "failed";
			}
		}
		return "success";
	}
	
	/**
	 * 用户列表
	 * @param model
	 * @param session
	 * @param currentpage
	 * @param s_cellphone
	 * @param s_roleId
	 * @param s_userTypeId
	 * @param s_stateId
	 * @param s_isStart
	 * @return
	 */
	@RequestMapping("/backend/userList.htm")
	public ModelAndView userList(Model model,HttpSession session, 
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="s_cellphone",required=false) String s_cellphone,
			@RequestParam(value="s_roleId",required=false) String s_roleId,
			@RequestParam(value="s_userTypeId",required=false) String s_userTypeId,
			@RequestParam(value="s_stateId",required=false) String s_stateId,
			@RequestParam(value="s_isStart",required=false) String s_isStart){
				
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			//获取roleList and cardTypeList
			List<Role> roleList = null;
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("CARD_TYPE");
			List<DataDictionary> cardTypeList = null;
			List<DataDictionary> userTypeList = null;
			try {
				Role role =new Role();
				roleList = roleService.selectAll(role);
				cardTypeList = dataDictionaryService.selectAll(dataDictionary);
				
				dataDictionary.setTypeCode("USER_TYPE");
				userTypeList = dataDictionaryService.selectAll(dataDictionary);
			} catch (Exception e) {
				// TODO: handle exception
			}
			/*
			 * 查询条件
			 */
			User user = new User();
			if(s_cellphone != null && s_cellphone != ""){
				//模糊查询
				user.setCellphone("%"+SQLTools.transfer(s_cellphone)+"%");
			}
			if(!StringUtils.isNullOrEmpty(s_roleId)){
				user.setRoleId(Long.valueOf(s_roleId));
			}else{
				user.setIsStart(null);
			}
			if(!StringUtils.isNullOrEmpty(s_userTypeId)){
				user.setUserTypeId(Long.valueOf(s_userTypeId));
			}else{
				user.setUserTypeId(null);
			}
			if(!StringUtils.isNullOrEmpty(s_stateId)){
				user.setStateId(Long.valueOf(s_stateId));
			}else{
				user.setStateId(null);
			}
			if(!StringUtils.isNullOrEmpty(s_isStart)){// != null and ""
				user.setIsStart(Integer.valueOf(s_isStart));
			}else{
				user.setIsStart(null);
			}
			if(!StringUtils.isNullOrEmpty(s_roleId)){// != null and ""
				user.setRoleId(Long.valueOf(s_roleId));
			}else{
				user.setRoleId(null);
			}
			
			/*
			 * page分页列表
			 */
			PageSupport page = new PageSupport();			
			try {
				//获取后台总记录数
				page.setTotalCount(userService.count(user));
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
				
				user.setStarNum((page.getPage()-1)*page.getPageSize());//设置开始行
				user.setPageSize(page.getPageSize());//设置每页显示数量
				//该用户当前页列表的数据
				List<UserEntity> userList = null;
				try {
					userList = userService.getUserEntities(user);
				} catch (Exception e) {
					// TODO: handle exception
					userList = null;
					if(page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(userList);//设置当前页列表数据
			}else{
				page.setItems(null);
			}			
			
			model.addAllAttributes(baseModel);//加载用户所属的功能菜单
			model.addAttribute("roleList",roleList);
			model.addAttribute("page",page);
			model.addAttribute("cardTypeList", cardTypeList);
			model.addAttribute("userTypeList", userTypeList);
			//做查询的回显用
			model.addAttribute("s_cellphone", s_cellphone);
			model.addAttribute("s_userTypeId", s_userTypeId);
			model.addAttribute("s_stateId", s_stateId);
			model.addAttribute("s_isStart", s_isStart);
			model.addAttribute("s_roleId", s_roleId);
			
			return new ModelAndView("/backend/userList");
		}
	}
	
	/*
	 * 用户类型列表
	 */
	@RequestMapping(value="/backend/loadUserTypeList.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object loadUserTypeList(@RequestParam(value="s_roleId",required=false) String s_roleId){
		String cjson = "";
		try {
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("USER_TYPE");
			//查询相应类型的数据字典
			List<DataDictionary> userTypeList = dataDictionaryService.selectAll(dataDictionary);
			//将从数据库查到的数据转换成 json类型
			JSONArray jo = JSONArray.fromObject(userTypeList);
			cjson = jo.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cjson;
	}
	/**
	 * 用户是否存在
	 * @param cellphone
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/cellphoneisexit.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String cellphoneIsExit(@RequestParam(value="cellphone",required=false) String cellphone,
								@RequestParam(value="id",required=false) String id){
		logger.debug("--------------loginCodeIsExit loginCode:"+cellphone);
		logger.debug("--------------loginCodeIsExit id:"+id );
		String result = "failed";
		User _user = new User();
		_user.setCellphone(cellphone);
		if(!id.equals("-1")){//修改操作进行同名判断
			_user.setId(Long.valueOf(id));
		}
		try {
			User user = null;
			user = userService.selectUser(_user);
			if( user == null){
				result = "only";
			}else{
				result = "repeat";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 增加用户
	 * @param session
	 * @param addUser
	 * @return
	 */
	@RequestMapping(value="/backend/addUser.htm",method=RequestMethod.POST)
	public ModelAndView addUser(HttpSession session,@ModelAttribute("addUser") User addUser){
		if(session.getAttribute(Constants.SESSION_BASE_MODEL) == null){
			return new ModelAndView("redirect:/");
		}else{
			try {
				String cardCode = addUser.getCardCode();
				String ps = cardCode.substring(cardCode.length()-6);
				addUser.setPassword(ps);
				addUser.setCreateTime(new Date());
				if(this.getCurrentUser() != null)
					addUser.setCreateBy(this.getCurrentUser().getId());
				addUser.setModifyTime(new Date());
				
				userService.psn_insert(addUser);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return new ModelAndView("redirect:/backed/userlist.htm");
		}
	}
	/**
	 * 上传图片
	 * @param cardFile
	 * @param bankFile
	 * @param mCardFile
	 * @param mBankFile
	 * @param loginCode
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/backend/upload.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object upload(@RequestParam(value="a_fileInutID",required=false) MultipartFile cardFile,
			@RequestParam(value="a_fileInputBank",required=false) MultipartFile bankFile,
			@RequestParam(value="m_fileInutID",required=false) MultipartFile mCardFile,
			@RequestParam(value="m_fileInputBank",required=false) MultipartFile mBankFile,
			@RequestParam(value="cellphone",required=false) String cellphone,
			HttpSession session,HttpServletRequest request){
		logger.debug("==================upload");
		//根据服务器的操作系统，自动获取物理路径，自适应
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		logger.debug("====================path"+path);
		DataDictionary dataDictionary = new DataDictionary();
		List<DataDictionary> list = null;
		dataDictionary.setTypeCode("PERSONALFILE_SIZE");
		try {
			list = dataDictionaryService.selectAll(dataDictionary);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int filesize = 50000;
		if(list != null){
			if(list.size() == 1)
				filesize = Integer.valueOf(list.get(0).getValueName());
		}
		if(cardFile != null){
			String oldFileName = cardFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			logger.debug("cardFile prefix =================="+prefix);
			if(cardFile.getSize() > filesize){
				return "1";
			}else if(prefix.equalsIgnoreCase("jsp") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
				//给文件进行重命名：系统毫秒数+100w以内的随机数
				String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_IDcard.jpg";
				logger.debug("new filename==============="+fileName);
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				//保存。上传
				try {
					cardFile.transferTo(targetFile);
				} catch (Exception e) {
					// TODO: handle exception
				}
				String url = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				return url;
			}else{
				return "2";
			}
		}
		return null;
	}
	/**
	 * 删除图片
	 * @param picpath
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/backend/delpic.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String delPic(@RequestParam(value="picpath",required=false)String picpath,
						@RequestParam(value="id",required=false)String id,
						HttpSession session){
		String result = "failed";
		if(picpath == null || picpath.equals("")){
			result="success";
		}else{
			//picpath解析成物理路径
			String[] paths = picpath.split("/");
			String path = session.getServletContext().getRealPath(paths[1]+File.separator+paths[2]+File.separator+paths[3]);
			File file = new File(path);
			if(file.exists()){
				if(file.delete()){
					if(id.equals(0)){
						result="success";
					}else{//修改用户时删除上传图片
						User _user = new User();
						_user.setId(Long.valueOf(id));
						if(picpath.indexOf("_IDcard.jpg") != -1){
							_user.setIdCardPicPath(picpath);
						}
						try {
							if(userService.delUserPic(_user) > 0){
								logger.debug("=====-----------modify--delPic");
								result = "success";
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}
	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/backend/getuser.html",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object getUser(@RequestParam(value="id",required=false) String id){
		String cjson ="";
		if(id == null || "".equals(id)){
			return "nodata";
		}else{
			try {
				User user = new User();
				user = userService.selectById(Long.valueOf(id));
				/*
				 * 将数据库内查询到的日期Date.class按json格式new JsonDateValueProcessor()转换
				 * user内所有日期属性，都会按照此日期格式进行json转换（对象转json）
				 */
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				JSONObject jo = JSONObject.fromObject(user,jsonConfig);
				cjson = jo.toString();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "failed";
			}
			return cjson;
		}
	}
}
