<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<div>
		<ul class="breadcrumb">
			<li>
				<a href="/main.html">首页</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">申诉账单</a>
			</li>
		</ul>
	</div>
	<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>申诉账单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
 
					<div class="box-content"   style="overflow:auto;">
					<div class="control-group">
				<form id="AppealUpload" enctype="multipart/form-data"
					target="ajaxFrame">
					选择文件:<input type="file" id="b_fileInutID" name="b_fileInutID">
					<button id="subAppeal" class="btn btn-primary" type="button">上传申诉订单</button>
				</form>
			</div>
					<div class="control-group">
					    
					    <label style="display:inline" class="control-label" for="date01">开始日期</label>
					     
                        <input  style="display:inline;width:80px" type="text"  onClick="WdatePicker();" readonly="readonly" id="date01" value="${orderFrom}">
                      
                      
                        <label style="display:inline" class="control-label" for="date02">结束日期</label>
                           
                        <input style="display:inline;width:80px"  type="text"  onClick="WdatePicker();" readonly="readonly" id="date02" value="${orderTo}">
                         
                       
                        <label style="display:inline" class="control-label" for="riderId">骑手ID</label>
                         
                        <input  style="display:inline;width:80px" class="focused" id="riderId" type="text" >
                        
                         
						<button type="button" class="btn btn-primary" onclick="appealQuery();">查询</button>
								  </div>
						<table class="table table-bordered table-striped table-condensed">
						  <thead>
							  <tr>
							      <!-- <th>操作</th> -->
							      <th>日期</th>
								  <th>对应投诉编号</th>
								  <th>对应投诉类型</th>  
								  <th>加盟商ID</th>
								  <th>加盟商名称</th>
								  <th>团队ID</th>
								  <th>团队名称</th>
								  <th>骑手ID</th>
								  <th>骑手名称</th>
								  <th>订单号</th>
								  <th>运单号</th>
								  <th>申诉应收金额</th>
								  <th>原因</th>
								  <th>备注</th>
							  </tr>
						  </thead>   
						  <tbody>
						   <c:if test="${page.items !=null}">
		  					<c:forEach items="${page.items }" var="Order">
							<tr>
								 
								<td class="center" >${Order.complaintDate}</td>
								<td class="center" >${Order.complaintNum}</td>
								<td class="center" >${Order.complaintType}</td>
								<td class="center" >${Order.franchiseeID}</td>
								<td class="center" >${Order.franchiseeName}</td>
								<td class="center" >${Order.teamID}</td>
								<td class="center" >${Order.teamName}</td>
								<td class="center" >${Order.riderID}</td>
								<td class="center" >${Order.riderName}</td>
								<td class="center" >${Order.id}</td>
								<td class="center" >${Order.billNum}</td>
								<td class="center" >${Order.complaintAmount}</td>
								<td class="center" >${Order.reason}</td>
								<td class="center" >${Order.remarks}</td>
								<!-- <td class="center">
									<span class="label label-success">Active</span>
								</td> -->
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
	  			<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
	  		</c:when>
	  		<c:otherwise>
	  			<li><a href="/orders/appealBills.html?currentpage=1&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }" title="首页">首页</a></li>
	  		</c:otherwise>
	  	</c:choose>
	  	<c:if test="${page.prevPages !=null}">
	  		<c:forEach items="${page.prevPages }" var="num">
	  			<li><a href="/orders/appealBills.html?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }" title="${num }">
	  					${num }
	  				</a>
	  			</li>
	  		</c:forEach>
	  	</c:if>
	  	<li>
	  		<a href="#" title="${page.page }">${page.page }</a>
	  	</li>
	  	<c:if test="${page.nextPages !=null}">
	  		<c:forEach items="${page.nextPages }" var="num">
	  			<li>
	  				<a href="/orders/appealBills.html?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }" title="${num }">
	  					${num }
	  				</a>
	  			</li>
	  		</c:forEach>
	  	</c:if>
	  	<c:if test="${page.pageCount!=null }">
	  		<c:choose>
	  		<c:when test="${page.page==page.pageCount }">
	  			<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
	  		</c:when>
	  		<c:otherwise>
	  			<li><a href="/orders/appealBills.html?currentpage=${page.pageCount}&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }" title="尾页">尾页</a></li>
	  		</c:otherwise>
	  		</c:choose>
	  	</c:if>
		<c:if test="${page.pageCount==null }">
			<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
		</c:if>
	  </ul>
	</div>  
	<!-- 分页 end -->          
					</div>
				</div><!--/span-->
			</div>
			</div><!--/row-->
	</div>
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
<script  src="/statics/localjs/appealBills.js"></script>
<script type="text/javascript" src="/statics/medire/WdatePicker.js"></script>