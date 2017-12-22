<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<div>
	<ul class="breadcrumb">
		<li><a href="/main.html">首页</a> <span class="divider">/</span></li>
		<li><a href="/orders/theShippingBills.html">配送订单</a></li>
	</ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-user"></i> 配送订单
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-setting btn-round"><i
					class="icon-cog"></i>
				</a> <a href="#" class="btn btn-minimize btn-round"><i
					class="icon-chevron-up"></i>
				</a> <a href="#" class="btn btn-close btn-round"><i
					class="icon-remove"></i>
				</a>
			</div>
		</div>
		<div class="box-content" style="overflow:auto;">
			<div class="control-group">
				<form id="formUpload" enctype="multipart/form-data" target="ajaxFrame">
					选择文件:<input type="file" id="a_fileInutID" name="a_fileInutID">
					<button id="sub" class="btn btn-primary" type="button">上传配送订单</button>
				</form>
			</div>
			<div class="control-group">

				<label style="display:inline" class="control-label" for="date01">开始日期</label>

				<input style="display:inline;width:80px" type="text"
					 onClick="WdatePicker();" readonly="readonly" id="date01" value="${orderFrom}">


				<label style="display:inline" class="control-label" for="date02">结束日期</label>

				<input style="display:inline;width:80px" type="text"
					 onClick="WdatePicker();" readonly="readonly"  id="date02" value="${orderTo}">


				<label style="display:inline" class="control-label" for="riderId">骑手ID</label>

				<input style="display:inline;width:80px"
					class="input-xlarge focused" id="riderId" type="text"> <label
					style="display:inline" class="control-label" for="result">清算结果</label>

				<select style="display:inline;width:80px" id="result">
					<option></option>
					<option>正常</option>
					<option>异常</option>
				</select> <label style="display:inline" class="control-label" for="overTime">是否超时</label>
				<select style="display:inline;width:80px" id="overTime">
					<option></option>
					<option>否</option>
					<option>是</option>
				</select>
				<button type="button" class="btn btn-primary"
					onclick="OrderQuery();">查询</button>
			</div>
			<table class="table table-bordered table-striped table-condensed">
				<thead>
					<tr>
						<!-- <th>操作</th> -->
						<th>日期</th>
						<th>加盟商ID</th>
						<th>加盟商名称</th>
						<th>团队ID</th>
						<th>团队名称</th>
						<th>商户ID</th>
						<th>商户名称</th>
						<th>订单来源</th>
						<th>订单号</th>
						<th>运单号</th>
						<th>骑手ID</th>
						<th>骑手</th>
						<th>是否超时</th>
						<th>配送状态</th>
						<th>快送/专送圈</th>
						<th>异常原因</th>
						<th>清算结果</th>
						<th>是否违规</th>
						<th>骑手接单时间</th>
						<th>运单结束时间</th>
						<th>提点配送费</th>
						<th>基础配送费</th>
						<th>活动补贴</th>
						<th>等级补贴</th>
						<th>时效补贴</th>
						<th>距离补贴</th>
						<th>时段补贴</th>
						<th>天气补贴</th>
						<th>优先送补贴</th>
						<th>系统异常扣款</th>
						<th>调度超时扣款</th>
						<th>时效扣款</th>
						<th>网格补贴</th>
						<th>网格扣款</th>
						<th>重量加价</th>
						<th>降级补贴</th>
						<th>冷链补贴</th>
						<th>午高峰补贴</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${page.items !=null}">
						<c:forEach items="${page.items }" var="Order">
							<tr>
								<td>${Order.orderDate}</td>
								<td class="center">${Order.franchiseeID}</td>
								<td class="center">${Order.franchiseeName}</td>
								<td class="center">${Order.teamID}</td>
								<td class="center">${Order.teamName}</td>
								<td class="center">${Order.merchantID}</td>
								<td class="center">${Order.merchantName}</td>
								<td class="center">${Order.orderFrom}</td>
								<td class="center">${Order.id}</td>
								<td class="center">${Order.billNum}</td>
								<td class="center">${Order.riderID}</td>
								<td class="center">${Order.riderName}</td>
								<td class="center">${Order.overTime}</td>
								<td class="center">${Order.status}</td>
								<td class="center">${Order.fastDelivery}</td>
								<td class="center">${Order.reason}</td>
								<td class="center">${Order.result}</td>
								<td class="center">${Order.illegal}</td>
								<td class="center">${Order.orderTime}</td>
								<td class="center">${Order.endTime}</td>
								<td class="center">${Order.tdFee}</td>
								<td class="center">${Order.jcFee}</td>
								<td class="center">${Order.hdSubsidy}</td>
								<td class="center">${Order.djSubsidy}</td>
								<td class="center">${Order.sxSubsidy}</td>
								<td class="center">${Order.jlSubsidy}</td>
								<td class="center">${Order.sdSubsidy}</td>
								<td class="center">${Order.tqSubsidy}</td>
								<td class="center">${Order.yxsSubsidy}</td>
								<td class="center">${Order.xtDebit}</td>
								<td class="center">${Order.psDebit}</td>
								<td class="center">${Order.ddDebit}</td>
								<td class="center">${Order.sxDebit}</td>
								<td class="center">${Order.wgDebit}</td>
								<td class="center">${Order.weightMarkup}</td>
								<td class="center">${Order.wgfDebit}</td>
								<td class="center">${Order.llDebit}</td>
								<td class="center">${Order.jjDebit}</td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>
			<!-- 分页 start -->
			<div class="pagination pagination-centered">
				<ul>
					<c:choose>
						<c:when test="${page.page==1 }">
							<li class="active"><a href="javascript:void();" title="首页">首页</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a onclick="show_query_hint('query_hint');"
								href="/orders/theShippingBills.html?currentpage=1&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&result=${result}&overTime=${overTime}"
								title="首页">首页</a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:if test="${page.prevPages !=null}">
						<c:forEach items="${page.prevPages }" var="num">
							<li><a onclick="show_query_hint('query_hint');"
								href="/orders/theShippingBills.html?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&result=${result}&overTime=${overTime}"
								title="${num }"> ${num } </a></li>
						</c:forEach>
					</c:if>
					<li><a href="#" title="${page.page }">${page.page }</a></li>
					<c:if test="${page.nextPages !=null}">
						<c:forEach items="${page.nextPages }" var="num">
							<li><a onclick="show_query_hint('query_hint');"
								href="/orders/theShippingBills.html?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&result=${result}&overTime=${overTime}"
		
								title="${num }"> ${num } </a></li>
						</c:forEach>
					</c:if>
					<c:if test="${page.pageCount!=null }">
						<c:choose>
							<c:when test="${page.page==page.pageCount }">
								<li class="active"><a href="javascript:void();" title="尾页">尾页</a>
								</li>
							</c:when>
							<c:otherwise>
								<li><a onclick="show_query_hint('query_hint');"
									href="/orders/theShippingBills.html?currentpage=${page.pageCount}&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&result=${result}&overTime=${overTime}"
									title="尾页">尾页</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${page.pageCount==null }">
						<li class="active"><a href="javascript:void();" title="尾页">尾页</a>
						</li>
					</c:if>
				</ul>
			</div>
			<!-- 分页 end -->
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
</div>
 
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
<script src="/statics/localjs/theShippingBills.js"></script>
<script type="text/javascript" src="/statics/medire/WdatePicker.js"></script>