package com.mtr.psn.controller.goods;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.mtr.psn.model.other.DataDictionary;
import com.mtr.psn.model.webEntity.GoodsFormEntity;
import com.mtr.psn.service.goods.GoodsFormService;
import com.mtr.psn.service.other.DataDictionaryService;
import com.mysql.jdbc.StringUtils;

@Controller
public class GoodsFormController extends BaseController {

	@Resource
	private GoodsFormService goodsFormService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	
	/**
	 * 根据物品类型
	 * @param s_goodsType
	 * @return
	 */
	@RequestMapping("/goods/selectGoodsByType.html")
	public String goodsList(@RequestParam(value="s_goodsType",required=false) String s_goodsType){
		String cjson = "";
		if(s_goodsType != null && s_goodsType != ""){
			List<GoodsForm> goodsForms = null;
			try {
				goodsForms = goodsFormService.selectByGoodsType(Long.valueOf(s_goodsType));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jo = JSONArray.fromObject(goodsForms);
			cjson = jo.toString();
		}
		return cjson;
	}

	/**
	 * 查询物品类型
	 * @param s_goodsType
	 * @return
	 */
	@RequestMapping(value="/goods/selectGoodsType.html" ,produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String selectGoodsType(){
		String cjson = "";
			List<DataDictionary> dataDictionarys = null;
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypeCode("GOODS_TYPE");
			try {
				dataDictionarys = dataDictionaryService.selectAll(dataDictionary);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray jo = JSONArray.fromObject(dataDictionarys);
			cjson = jo.toString();
		 
		return cjson;
	}
	/**
	 * 物品列表
	 * @param model
	 * @param session
	 * @param currentpage
	 * @param s_goodsName
	 * @param s_goodsCode
	 * @param s_goodsType
	 * @return
	 */
	@RequestMapping("/goods/goodsEntryExitFormList.html")
	public ModelAndView goodsFormList(Model model,HttpSession session, 
			@RequestParam(value="currentpage",required=false) Integer currentpage,
			@RequestParam(value="s_goodsName",required=false) String s_goodsName,
			@RequestParam(value="s_goodsCode",required=false) String s_goodsCode,
			@RequestParam(value="s_goodsType",required=false) String s_goodsType){
				
		Map<String, Object> baseModel = (Map<String, Object>)session.getAttribute(Constants.SESSION_BASE_MODEL);
		if(baseModel == null){
			return new ModelAndView("redirect:/");
		}else{
			//获取roleList and cardTypeList
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
			GoodsForm goodsForm = new GoodsForm();
			if(s_goodsName != null && s_goodsName != ""){
				goodsForm.setGoodsName("%"+SQLTools.transfer(s_goodsName)+"%");
			}
			if(s_goodsName != null && s_goodsName != ""){
				goodsForm.setGoodsCode("%"+SQLTools.transfer(s_goodsCode)+"%");
			}
			if(!StringUtils.isNullOrEmpty(s_goodsType)){// != null and ""
				goodsForm.setGoodsType(Long.valueOf(s_goodsType));
			}else{
				goodsForm.setGoodsType(null);
			}
			
			/*
			 * page分页列表
			 */
			PageSupport page = new PageSupport();			
			try {
				//获取后台总记录数
				page.setTotalCount(goodsFormService.count(goodsForm));
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
				
				goodsForm.setStarNum((page.getPage()-1)*page.getPageSize());//设置开始行
				goodsForm.setPageSize(page.getPageSize());//设置每页显示数量
				//该用户当前页列表的数据
				List<GoodsFormEntity> formEntitys = null;
				try {
					formEntitys = goodsFormService.getPagesListEntity(goodsForm);
				} catch (Exception e) {
					// TODO: handle exception
					formEntitys = null;
					if(page == null){
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(formEntitys);//设置当前页列表数据
			}else{
				page.setItems(null);
			}			
			
			model.addAllAttributes(baseModel);//加载用户所属的功能菜单
			model.addAttribute("page",page);
			model.addAttribute("goodsTypeList", goodsTypeList);
			//做查询的回显用
			model.addAttribute("s_goodsName", s_goodsName);
			model.addAttribute("s_goodsCode", s_goodsCode);
			model.addAttribute("s_goodsType", s_goodsType);
			
			return new ModelAndView("goods/goodsFormList");
		}
	}
	/**
	 * 进入物资详情
	 * @return
	 */
	@RequestMapping(value="/goods/goodsFormDetail.do",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String goodsFormDetail ( 
			@RequestParam(value="id",required=false) String id
		){
		if(id!=null&&id!=""){
			String str = "";
			GoodsForm goodsForm = new GoodsForm();
		try {
			goodsForm =goodsFormService.selectById(Long.valueOf(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(goodsForm!=null){
		  JSONObject json = JSONObject.fromObject(goodsForm);
		  str = json.toString();
		  }
		    return str;
		}else{
			return "";
		}
	}
	/**
	 * 新增物资
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goods/GoodsFormAdd.html",method=RequestMethod.POST)
	@ResponseBody
	public String GoodsFormAdd ( 
			@RequestParam(value="jsonData",required=false) String jsonData
		) throws Exception{
		JSONObject salary_dObject = JSONObject.fromObject(jsonData);
		GoodsForm goodsForm = (GoodsForm)salary_dObject.toBean(salary_dObject, GoodsForm.class);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		goodsForm.setCreateTime(format.format(new Date()));
		goodsForm.setCreateBy(this.getCurrentUser().getId());
		if(goodsFormService.psn_insert(goodsForm)>0){
			 return "1";//成功
		}
	 return "0";//失败
	}
}
