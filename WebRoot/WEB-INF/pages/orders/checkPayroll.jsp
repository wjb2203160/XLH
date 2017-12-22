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
                         
                       
                        <label style="display:inline" class="control-label" for="riderId">骑手ID</label>
                         
                        <input  style="display:inline;width:80px" class="focused" id="riderId" type="text" value="${riderID}">
                        
                        <label style="display:inline" class="control-label" for="riderName">骑手名称</label>
                         
                        <input  style="display:inline;width:80px" class="focused" id="riderName" type="text" value="${riderName}">
                        
                          <label style="display:inline" class="control-label" for="selectusertype">站点</label>
                            <input  style="display:none; class="focused" id="selectUser" type="text" value="${teamID}">
                        <select id="selectusertype" name="selectusertype" style="width: 150px;">
				 
				        </select>
                        
                           <label class="checkbox inline">
                            <input  style="display:none; class="focused" id="selectzeroNotDisp" type="text" value="${zeroNotDisp}">
									<input type="checkbox" id="zeroNotDisp" > 工资为零不显示
								  </label>
						<button type="button" class="btn btn-primary"  onclick="Query();">查询</button>
						<button type="button" class="btn btn-primary" onclick="CalMoney();">计算工资</button>	   
								  </div>
								  <button type="button" class="btn btn-primary" onclick="OutPutWage();">导出工资表</button>	
								   <button type="button" class="btn btn-primary" onclick="OutPutWageByBank();">导出银行对接工资表</button>	      
						<table class="table table-bordered table-striped table-condensed">
						  <thead>
							  <tr>
							      <th>操作</th>
							      <th>工资日期</th>
							       <th>站点名称</th> 
								  <th>骑手id</th>
								  <th>骑手名称</th> 
								   <th>总单数</th>
								  <th>异常/疑似欺诈</th>
								  <th>正常单数</th>
								  <th>超时单数</th>
								  <th>准时单数</th>
								  <th>提成</th>
								  <th>全勤奖</th>
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
								  <th>创建时间</th>
								  <th>创建人</th>
								  <th>修改时间</th>
								  <th>修改人</th>
								  <th>备注</th>
							  </tr>
						  </thead>   
						  <tbody>
						   <c:if test="${page.items !=null}">
		  					<c:forEach items="${page.items }" var="Order">
							<tr>
								 <td class="center">
								 	<button class="btn btn-info modifyuser" onclick="LoadDetail(${Order.id});"> <i class="icon-edit icon-white"></i> 修改</button>
								  	<button class="btn btn-danger" onclick="DeleteData(${Order.id});"> <i class="icon-trash icon-white"></i> 删除</button>
								</td>
								<td class="center" >${Order.wageDate}</td>
								<td class="center" >${Order.teamName}</td>
								<td class="center" >${Order.id}</td>
								<td class="center" >${Order.riderName}</td>
								<td class="center" >${Order.totalCount}</td>
								<td class="center" >${Order.errorCount}</td>
								<td class="center" >${Order.normalCount}</td>
								<td class="center" >${Order.timeoutCount}</td>
								<td class="center" >${Order.onTimeCount}</td>
								<td class="center" >${Order.subsidization}</td>
								<td class="center" >${Order.attendance}</td>
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
								<td class="center" >${Order.createTime}</td>
								<td class="center" >${Order.userName}</td>
								<td class="center" >${Order.modifyTime}</td>
								<td class="center" >${Order.userName1}</td>
								<td class="center" >${Order.remarks}</td>
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
	  			<li><a href="/pay/checkPayroll.do?currentpage=1&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&teamID=${teamID }" title="首页">首页</a></li>
	  		</c:otherwise>
	  	</c:choose>
	  	<c:if test="${page.prevPages !=null}">
	  		<c:forEach items="${page.prevPages }" var="num">
	  			<li><a href="/pay/checkPayroll.do?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&teamID=${teamID }" title="${num }">
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
	  				<a href="/pay/checkPayroll.do?currentpage=${num }&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&teamID=${teamID }" title="${num }">
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
	  			<li><a href="/pay/checkPayroll.do?currentpage=${page.pageCount}&orderFrom=${orderFrom }&orderTo=${orderTo }&riderID=${riderID }&teamID=${teamID }" title="尾页">尾页</a></li>
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
		<!-- 工资详情 -->
 <div class="modal hide fade" id="wageDiv">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>工资详情</h3>
		</div>
		<div class="modal-body">
			<ul id="add_formtip"></ul>
			<ul class="topul">
			<li>
				<label>工资年月:</label> 
					<span id="wageDate"></span>
				</li>
			    <li>
				<label>骑手Id:</label> 
					<span id="dRiderId"></span>
				</li>
				<li>
				<label>骑手名称:</label> 
					<span id="riderName"></span>
				</li>
				 <li>
				<label>异常单数:</label> 
					<span id="errorCount"></span>
				</li>
				<li>
				<label>正常单数:</label> 
					<span id="normalCount"></span>
				</li>
				<li>
				<label>超时单数:</label> 
					<span id="timeoutCount"></span>
				</li>
				<li>
				<label>准时单数:</label> 
					<span id="onTimeCount"></span>
				</li>
				<li>
				<label>提成:</label> 
					<span id="subsidization"></span>
				</li>
				<li>
				<label>全勤奖:</label> 
					<span id="attendance"></span>
				</li>
				<li>
				<label>上月未发/漏扣:</label> 
					<input id="leakageBuckle"  type="text" name="leakageBuckle"/> 
				</li>
					<li>
				<label>员工介绍费:</label> 
					<input id="referralFee" type="text"   name="referralFee"/> 
				</li>
					<li>
				<label>保险费（2元/天）:</label> 
					<input id="insuranceFee" type="text"   name="insuranceFee"/> 
				</li>
				<li>
				<label>投诉罚款:</label> 
					<input id="amerce" type="text"   name="amerce"/> 
				</li>
				<li>
				<label>超时补贴:</label> 
					<input id="overAllowance" type="text"   name="overAllowance"/> 
				</li>
				  <li>
				<label>应付工资:</label> 
					<span id="shouldPay"></span>
				</li>
				<li>
				<label>个人所得税:</label> 
					<input id="incomeTax"  type="text"  name="incomeTax"/> 
				</li>
				 <li>
				<label>物资:</label> 
					<span id="goodFee"></span>
				</li>
					<li>
				<label>预支工资:</label> 
					<input id="advancePay" type="text"   name="advancePay"/> 
				</li>
				<li>
				<label>电费:</label> 
					<input id="electricity"  type="text"  name="electricity"/> 
				</li>
				  <li>
				<label>实发工资:</label> 
					<span id="realPay"></span>
				</li>
			</ul>
			<ul class="topul" id="goodList">
		
			    
				 
			</ul>
			<div class="clear"></div>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn addusercancel" data-dismiss="modal">取消</a>
			<a href="#" class="btn addusercancel"  onclick="WageConfirm();">保存</a>
		</div>
	 
</div>	 
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
<script type="text/javascript" src="/statics/medire/WdatePicker.js"></script>
<script src="/statics/localjs/checkPayroll.js"></script>

