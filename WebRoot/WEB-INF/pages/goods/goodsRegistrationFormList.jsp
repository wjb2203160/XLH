<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp" %>
<div>
	<ul class="breadcrumb">
		<li><a href="#">库存管理</a> <span class="divider">/</span></li>
		<li><a href="/goods/goodsRegistrationFormList.html">物资申领登记表</a></li>
	</ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> 登记表</h2>
			<div class="box-icon" onclick="GoodsAdd();">
				<span class="icon32 icon-color icon-add custom-setting addregistrationform"></span>
			</div>
		</div>		
		<div class="box-content" style="overflow:auto;">
			<div class="control-group">
				<form id="formUpload" enctype="multipart/form-data"
					target="ajaxFrame">
					选择文件:<input type="file" id="a_fileInutID" name="a_fileInutID">
					<button id="subGood" type="button">上传申领表</button>
				</form>
				<a href="/statics/img/申领表导入模板.csv">下载导入模板</a>
			</div>
			<%-- <form action="/goods/goodsRegistrationFormList.html" method="post">
				<div class="searchared">
					物品名称： <input type="text" name="s_goodsName" value="${s_goodsName}">
					领取人： <input type="text" name="s_claimantName" value="${s_claimantName}">
					领取人电话： <input type="text" name="s_claimantPhone" value="${s_claimantPhone}">
					所在部门：<select name="s_dep" style="width: 100px:">
								<option value="" selected="selected">--请选择--</option>
								<c:if test="${depList != null}">
								<c:forEach items="${depList }" var="dep">
									<option <c:if test="${dep.id == s_dep }">selected="selected"</c:if>
											value="${dep.id }">
										${dep.dName }
									</option>
								</c:forEach>
								</c:if>
							</select> 
					<button class="btn bt-primary"><i class="icon-search icon-white"></i>查询</button>
				</div>
			</form> --%>
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
				<thead>
					<tr>
						<!-- <th>物品类型</th> -->
						<th>物品名称</th>
						<th>申领总量</th>
						<th>物品单价</th>
						<th>申领人</th>
						<th>电话</th>
						<th>所在部门</th>
						<th>申领时间</th>
						<th>经手人</th>
						<th>领取地点</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${page.items != null }">
						<c:forEach items="${page.items }" var="registrationForm">
							<tr>
								<%-- <td class="center">
									<c:forEach items="${goodsTypeList }" var="goodsType">
										<c:if test="${goodsType.id == registrationForm.goodsType }">${goodsType.valueName }
										</c:if>
									</c:forEach>
								</td> --%>
								<td class="center">${registrationForm.goodsName }</td>
								<td class="center">${registrationForm.claimantCount }</td>
								<td class="center">${registrationForm.price }</td>
								<td class="center">${registrationForm.claimantName }</td>
								<td class="center">${registrationForm.claimantPhone }</td>
								<td class="center">${registrationForm.dep }</td>
								<td class="center">
									<fmt:formatDate value="${registrationForm.claimantTime }" pattern="yyyy- MM-dd" />
								</td>
								<td class="center">${registrationForm.handledName }</td>
								<td class="center">${registrationForm.claimantSite }</td>
								<td class="center">
									
								 	<a class="btn btn-info modifyuser" href="#" onclick="GoodsDetail(${registrationForm.id });" >
								 	<i class="icon-edit icon-white"></i> 修改</a>
								 		<a class="btn btn-danger" href="#" onclick="DeleteGoods(${registrationForm.id });"> <i class="icon-trash icon-white"></i> 删除</a>
								</td>
							</tr>	
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div class="pagination pagination-centered">
				<ul>
					<c:choose>
						<c:when test="${page.page == 1 }">
							<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/goods/goodsRegistrationFormList.html?currentpage=1&s_goodsName=${s_goodsName }
							&s_claimantName=${s_claimantName }&s_claimantPhone=${s_claimantPhone }&s_dep=${s_dep }" title="首页">首页</a></li>
						</c:otherwise>
					</c:choose>
					<c:if test="${page.prevPages != null }">
						<c:forEach items="${page.prevPages }" var="num">
							<li>
							<a href="/goods/goodsRegistrationFormList.html?currentpage=${num }&s_goodsName=${s_goodsName }
							&s_claimantName=${s_claimantName }&s_claimantPhone=${s_claimantPhone }&s_dep=${s_dep }" title="${num }">
							${num }</a>
							</li>
						</c:forEach>
					</c:if>
					<li class="active"><a href="#" title="${page.page }">${page.page }</a></li>
					<c:if test="${page.nextPages != null }">
						<c:forEach items="${page.nextPages }" var="num">
							<li>
							<a href="/goods/goodsRegistrationFormList.html?currentpage=${num }&s_goodsName=${s_goodsName }
							&s_claimantName=${s_claimantName }&s_claimantPhone=${s_claimantPhone }&s_dep=${s_dep }" title="${num }">
							${num }</a>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page.pageCount != null }">
						<c:choose>
						<c:when test="${page.page == page.pageCount }">
							<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/goods/goodsRegistrationFormList.html?currentpage=${page.pageCount }
							&s_goodsName=${s_goodsName }&s_claimantName=${s_claimantName }&s_claimantPhone=${s_claimantPhone }
							&s_dep=${s_dep }" title="尾页">尾页</a></li>
						</c:otherwise>
					</c:choose>
					</c:if>
					<c:if test="${page.pageCount == null }">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>

 

<!-- 物资领取信息添加start -->
<div class="modal hide fade" id="GoodsDiv">
	 
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>添加物资申领信息</h3>
		</div>
		<div class="modal-body">
			<ul id="add_formtip"></ul>
			<ul class="topul">
			<%--  <li><label>物资类型:</label> 
					<input id="seletusertypevname" type="hidden" name="userTypeId" value="" /> 
					<select id="selectusertype" name="userType" style="width: 100px;">
						<option value="" selected="selected">--请选择--</option>
						<c:if test="${goodsTypeList != null}">
							<c:forEach items="${goodsTypeList }" var="goodsType">
								<option value="${goodsType.id }">${goodsType.valueName }</option>
							</c:forEach>
						</c:if>
					</select> 
					<span style="color: red; font-weight: bold;">*</span></li>  --%> 
			<li style="margin-left:245px"><label>所在部门:</label> 
				<input id="seletusertypevname" type="hidden" name="userTypeId" value="" /> 
				<select id="selectusertype" name="userType" style="width: 150px;">
				 
				</select>
				<span style="color: red; font-weight: bold;">*</span>
			</li> 
			<li style="margin-top:-35px"><p style="margin-left:17px">物品名称：</p>
			<!-- 		<div style="margin-left:62px" >
						<input type="checkbox" id="r-helmet" style="width:20px">头盔
						<input class="text_box" id="helmet" name="helmet" type="text"
							value=""   placeholder="1"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/^a-zA-Z]/"/> </br> 
						<input type="checkbox" id="r-cotta">短袖
						<input class="text_box" id="cotta" name="cotta" type="text"
							value=""  placeholder="1" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/"/> </br> 
						<input type="checkbox" id="r-pocket" >腰包 
						<input class="text_box" id="pocket" name="pocket" type="text" value=""
							  placeholder="1" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/"/> </br> 
						<input type="checkbox" id="r-takeoutBox" >外卖箱 
						<input class="text_box" id="takeoutBox" name="takeoutBox" type="text"
							value=""  placeholder="1"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/"/> </br> 
						<input type="checkbox" id="r-bubble"  >泡沫箱 
						<input class="text_box" id="bubble" name="bubble" type="text" value=""
							 placeholder="1"  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/"/> </br>
					</div> -->
					<input id="DetailId" type="hidden" name="DetailId" value="" /> 
					<input id="seletGoodsname" type="hidden" name="seletGoodsname" value="" /> 
					<select id="selectGoods" name="selectGoods" style="width: 100px;">
				 
				</select>
				 
			</li> 
				<li><label>领取人：</label>
					<input type="text" id="a_idcard" name="cardCode" 
					 /> 
					<span style="color: red; font-weight: bold;">*</span>
				</li>
				<li><label>申领数量：</label><input type="text" id="Goods" required="required" 
				name="accountHolder" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" />
					 <span style="color: red; font-weight: bold;">*</span></li>
				<li><label>手机号：</label> 
				<input id="a_cellphone" type="text" name="cellphone" required="required"  onkeyup="value=value.replace(/[^(13|14|15|17|18)\d{9}\/]/g,'')" />
					<span style="color: red; font-weight: bold;">*</span></li>
				<li><label>领取时间：</label> 
					<input class="Wdate" id="a_birthday" size="15" name="birthday" 
					readonly="readonly" type="text" onClick="WdatePicker();" /> </li>
				<li><label>领取地点：</label> 
					<input id="a_username" type="text" name="userName" required="required" />
					<span style="color: red; font-weight: bold;">*</span>
				</li>
				<li><label>经手人：</label>
					<input type="text" id="a_accountholder" name="accountHolder" required="required"  />
				 	<span style="color: red; font-weight: bold;">*</span></li>
				<li><label>登记人：</label>
					<input type="text" id="createName" name="referCode" value="${user.userName}" readonly="readonly"/>
				</li>
				<li class="lastli" ><label>备注：</label> 
					<input type="text" id="a_useraddress" name="userAddress"  ></input></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn addusercancel" data-dismiss="modal">取消</a>
		<a href="#" class="btn addusercancel"  onclick="GoodsConfirm();">保存</a>
		</div>
	</form>
</div>
 

<%@include file="/WEB-INF/pages/common/foot.jsp" %>
<script src="/statics/localjs/goodsRegistrationFormList.js"></script>
 
 
