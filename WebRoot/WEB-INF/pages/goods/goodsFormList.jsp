<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp" %>
<div>
	<ul class="breadcrumb">
		<li><a href="#">库存管理</a> <span class="divider">/</span></li>
		<li><a href="/goods/goodsEntryExitFormList.html">物资管理</a></li>
	</ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> 物资管理</h2>
			<div class="box-icon" onclick="GoodsAdd();">
				<span class="icon32 icon-color icon-add custom-setting adduser"></span>
			</div>
		</div>		
		<div class="box-content" style="overflow:auto;">
			<%-- <form action="/goods/goodsEntryExitFormList.html" method="post">
				<div class="searchared">
					物品名称： <input type="text" name="s_goodsName" value="${s_goodsName}">
					物品编号： <input type="text" name="s_goodsCode" value="${s_goodsCode}">
					物品类型：<select name="s_goodsType" style="width: 100px:">
								<option value="" selected="selected">--请选择--</option>
								<c:if test="${goodsTypeList != null}">
								<c:forEach items="${goodsTypeList }" var="goodsType">
									<option <c:if test="${goodsType.id == s_goodsType }">selected="selected"</c:if>
											value="${goodsType.id }">
										${goodsType.TypeName }
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
						<th>物品类型</th>
						<th>物品名声</th>
						<th>物品编号</th>
						<th>单价</th>
						<th>剩余总量</th>
						<th>进货人</th>
						<th>进货总量</th>
						<th>进货时间</th>
						<th>经手人</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${page.items != null }">
						<c:forEach items="${page.items }" var="goodsForm">
							<tr>
								<td class="center">
									<c:forEach items="${goodsTypeList }" var="goodsType">
										<c:if test="${goodsType.valueId == goodsForm.goodsType }">${goodsType.valueName }</c:if>
									</c:forEach>
								</td>
								<td class="center">${goodsForm.goodsName }</td>
								<td class="center">${goodsForm.goodsCode }</td>
								<td class="center">${goodsForm.price }</td> 
								<td class="center">${goodsForm.goodsCount }</td>
								<td class="center">${goodsForm.goodsInByName }</td>
								<td class="center">${goodsForm.goodsInCount }</td>
								<td class="center">
									 ${goodsForm.goodsInTime } 
								</td>
								<td class="center">${goodsForm.handledName }</td>
								<td class="center">
									<a class="btn btn-success viewuser" href="#" id="${goodsForm.id }"><i class="icon-zoom-in icon-white"></i>查看 </a>
								 	<a class="btn btn-info modifyuser" href="#" id="${goodsForm.id }"> <i class="icon-edit icon-white"></i> 修改</a>
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
							<li><a href="/goods/goodsEntryExitFormList.html?currentpage=1&s_goodsName=${s_goodsName }&s_goodsCode=${s_goodsCode }&s_goodsType=${s_goodsType }" title="首页">首页</a></li>
						</c:otherwise>
					</c:choose>
					<c:if test="${page.prevPages != null }">
						<c:forEach items="${page.prevPages }" var="num">
							<li>
							<a href="/goods/goodsEntryExitFormList.html?currentpage=${num }&s_goodsName=${s_goodsName }&s_goodsCode=${s_goodsCode }&s_goodsType=${s_goodsType }" title="${num }">${num }</a>
							</li>
						</c:forEach>
					</c:if>
					<li class="active"><a href="#" title="${page.page }">${page.page }</a></li>
					<c:if test="${page.nextPages != null }">
						<c:forEach items="${page.nextPages }" var="num">
							<li>
							<a href="/goods/goodsEntryExitFormList.html?currentpage=${num }&s_goodsName=${s_goodsName }&s_goodsCode=${s_goodsCode }&s_goodsType=${s_goodsType }" title="${num }">${num }</a>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page.pageCount != null }">
						<c:choose>
						<c:when test="${page.page == page.pageCount }">
							<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/goods/goodsEntryExitFormList.html?currentpage=${page.pageCount }&s_goodsName=${s_goodsName }&s_goodsCode=${s_goodsCode }&s_goodsType=${s_goodsType }" title="尾页">尾页</a></li>
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
			<h3>添加物资信息</h3>
		</div>
		<div class="modal-body">
			<ul id="add_formtip"></ul>
			<ul class="topul">
			<li  ><label>物品类型:</label> 
				<input id="seletusertypevname" type="hidden" name="userTypeId" value="" /> 
				<select id="selectusertype" name="userType" style="width: 150px;">
				 
				</select>
				<span style="color: red; font-weight: bold;">*</span>
			</li> 
			<li  ><p style="margin-left:17px">物品编码：</p>
		
					<input id="DetailId" type="hidden" name="DetailId" value="" /> 
					<input id="selectGoodsCode" type="text" name="selectGoodsCode" value="" /> 
			</li>
			<li  ><p style="margin-left:17px">物品名称：</p>
					<input id="selectGoodsname" type="text" name="selectGoodsname" value="" /> 
			</li> 
				<li><label>物品剩余总量：</label>
					<input type="text" id="goodsCount" name="goodsCount" /> 
				
				</li>
				<li><label>进货数量：</label><input type="text" id="goodsInCount"  />
					 <span style="color: red; font-weight: bold;">*</span>
					 </li>
				<li><label>单价：</label> 
				<input id="price" type="text" name="price" />
				<span style="color: red; font-weight: bold;">*</span>
				</li>
				<li><label>进货时间：</label> 
					<input class="Wdate" id="a_birthday" size="15" name="birthday" 
					readonly="readonly" type="text" onClick="WdatePicker();" /> </li>
				 
				<li><label>进货人：</label>
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
<script  src="/statics/localjs/goodsRegistrationForm.js"></script>
