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
				<a href="/pay/checkPayroll.do">核算工资帐单</a>
			</li>
		</ul>
	</div>
	<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>核算工资帐单</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
 
					<div class="box-content"   style="overflow:auto;">
					<div class="control-group">
					    
					    <label style="display:inline" class="control-label" for="date01">开始日期</label>
					     
                        <input  style="display:inline;width:80px" type="text"  onClick="WdatePicker();" readonly="readonly" id="date01" value="${orderFrom}">
                      
                      
                        <label style="display:inline" class="control-label" for="date02">结束日期</label>
                           
                        <input style="display:inline;width:80px"  type="text"  onClick="WdatePicker();" readonly="readonly" id="date02" value="${orderTo}">
                        
                          <label style="display:inline" class="control-label" for="selectusertype">站点</label>
                          
                        <select id="selectusertype" name="selectusertype" style="width: 150px;">
				 
				        </select>

						<button type="button" class="btn btn-primary" onclick="Query();">查询</button>
								  </div>
								  <button type="button" class="btn btn-primary" onclick="OutPutWage();">导出工资表</button>	   
						<table class="table table-bordered table-striped table-condensed">
						  <thead>
							  <tr>
							     
							      <th>工资年月</th>
								  <th>站点id</th>
								  <th>站点名称</th> 
								   <th>总单数</th>
								  <th>异常/疑似欺诈</th>
								  <th>正常单数</th>
								  <th>超时单数</th>
								  <th>准时单数</th>
								  <th>提成</th>
								  <th>上月未发/漏扣</th>
								  <th>投诉罚款</th>
								  <th>超时补贴</th>
								  <th>保险费</th>
								  <th>员工介绍费</th>
								  <th>应发工资</th>
								  <th>个人所得税</th>
								  <th>物资费</th>
								  <th>预支工资</th>
								  <th>电费</th>
								  <th>实发工资</th>
							  </tr>
						  </thead>   
						  <tbody>
						   <c:if test="${page.items !=null}">
		  					<c:forEach items="${page.items }" var="Order">
							<tr>
								<td class="center" >${Order.wageDate}</td>
								<td class="center" >${Order.teamID}</td>
								<td class="center" >${Order.teamName}</td>
								<td class="center" >${Order.totalCount}</td>
								<td class="center" >${Order.errorCount}</td>
								<td class="center" >${Order.normalCount}</td>
								<td class="center" >${Order.timeoutCount}</td>
								<td class="center" >${Order.onTimeCount}</td>
								<td class="center" >${Order.subsidization}</td>
								<td class="center" >${Order.leakageBuckle}</td>
								<td class="center" >${Order.amerce}</td>
								<td class="center" >${Order.overAllowance}</td>
								<td class="center" >${Order.insuranceFee}</td>
								<td class="center" >${Order.referralFee}</td>
								<td class="center" >${Order.shouldPay}</td>
								<td class="center" >${Order.incomeTax}</td>
								<td class="center" >${Order.goodFee}</td>
								<td class="center" >${Order.advancePay}</td>
								<td class="center" >${Order.electricity}</td>
								<td class="center" >${Order.realPay}</td>
								 
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
	  			<li><a href="/pay/calTeam.do?currentpage=1&orderFrom=${orderFrom }&orderTo=${orderTo }&teamID=${teamID }" title="首页">首页</a></li>
	  		</c:otherwise>
	  	</c:choose>
	  	<c:if test="${page.prevPages !=null}">
	  		<c:forEach items="${page.prevPages }" var="num">
	  			<li><a href="/pay/calTeam.do?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&teamID=${teamID }" title="${num }">
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
	  				<a href="/pay/calTeam.do?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&teamID=${teamID }" title="${num }">
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
	  			<li><a href="/pay/calTeam.do?currentpage=${page.pageCount}&orderFrom=${orderFrom }&orderTo=${orderTo }&teamID=${teamID }" title="尾页">尾页</a></li>
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
	 
	 
</div>	 
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
<script src="/statics/localjs/calTeam.js"></script>
<script type="text/javascript" src="/statics/medire/WdatePicker.js"></script>
