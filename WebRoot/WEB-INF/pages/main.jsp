<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp"%>

	<div>
		<ul class="breadcrumb">
			<li>
				<a href="/main.html">首页</a> <span class="divider">/</span>
			</li>
			<li>
				<a href="#">Dashboard</a>
			</li>
		</ul>
	</div>
	<div class="sortable row-fluid">
		<a data-rel="tooltip" title="6 new members." class="well span3 top-block" href="#">
			<span class="icon32 icon-red icon-user"></span>
			<div>申请成员总数</div>
			<div>507</div>
			<span class="notification">6</span>
		</a>

		<a data-rel="tooltip" title="4 new pro members." class="well span3 top-block" href="#">
			<span class="icon32 icon-color icon-star-on"></span>
			<div>通过成员数</div>
			<div>228</div>
			<span class="notification green">4</span>
		</a>

		<a data-rel="tooltip" title="$34 new sales." class="well span3 top-block" href="#">
			<span class="icon32 icon-color icon-cart"></span>
			<div>Sales</div>
			<div>$13320</div>
			<span class="notification yellow">$34</span>
		</a>
		
		<a data-rel="tooltip" title="12 new messages." class="well span3 top-block" href="#">
			<span class="icon32 icon-color icon-envelope-closed"></span>
			<div>新信息</div>
			<div>25</div>
			<span class="notification red">12</span>
		</a>
	</div>
	
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header well">
				<h2><i class="icon-th"></i> 公告栏</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#info">公司二维码</a></li>
					<li><a href="#custom">人事调整</a></li>
					<li><a href="#messages">最新信息</a></li>
				</ul>
				 
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane active" id="info">
						<h3>Charisma <small>a fully featued template</small></h3>
						<p></p> <img alt="QR Code" class="charisma_qr center" src="statics/img/qrcode136.png" />
					</div>
					<div class="tab-pane" id="custom">
						<h3>Custom <small>small text</small></h3>
						<p></p>
						<p></p>
					</div>
					<div class="tab-pane" id="messages">
						<h3>Messages <small>small text</small></h3>
						<p></p>
						<p></p>
					</div>
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->
	
	<div class="row-fluid sortable">
		<div class="box span4">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-list"></i> 按键</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
					<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
					<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
				</div>
			</div>
			<div class="box-content buttons">
				<p class="btn-group">
					  <button class="btn">Left</button>
					  <button class="btn">Middle</button>
					  <button class="btn">Right</button>
				</p>
				<p>
					<button class="btn btn-small"><i class="icon-star"></i> Icon button</button>
					<button class="btn btn-small btn-primary">Small button</button>
					<button class="btn btn-small btn-danger">Small button</button>
				</p>
				<p>
					<button class="btn btn-small btn-warning">Small button</button>
					<button class="btn btn-small btn-success">Small button</button>
					<button class="btn btn-small btn-info">Small button</button>
				</p>
				<p>
					<button class="btn btn-small btn-inverse">Small button</button>
					<button class="btn btn-large btn-primary btn-round">Round button</button>
					<button class="btn btn-large btn-round"><i class="icon-ok"></i></button>
					<button class="btn btn-primary"><i class="icon-edit icon-white"></i></button>
				</p>
				<p>
					<button class="btn btn-mini">Mini button</button>
					<button class="btn btn-mini btn-primary">Mini button</button>
					<button class="btn btn-mini btn-danger">Mini button</button>
					<button class="btn btn-mini btn-warning">Mini button</button>
				</p>
				<p>
					<button class="btn btn-mini btn-info">Mini button</button>
					<button class="btn btn-mini btn-success">Mini button</button>
					<button class="btn btn-mini btn-inverse">Mini button</button>
				</p>
			</div>
		</div><!--/span-->
	
	<div class="box span4">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i>英雄榜</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<div class="box-content">
				<ul class="dashboard-list">
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Usman" src="http://www.gravatar.com/avatar/f0ea51fa1e4fae92608d8affee12f67b.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Usman
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-success">Approved</span>                                  
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Sheikh Heera" src="http://www.gravatar.com/avatar/3232415a0380253cfffe19163d04acab.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Sheikh Heera
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-warning">Pending</span>                                 
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Abdullah" src="http://www.gravatar.com/avatar/46056f772bde7c536e2086004e300a04.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Abdullah
						</a><br>
						<strong>Since:</strong> 25/05/2012<br>
						<strong>Status:</strong> <span class="label label-important">Banned</span>                                  
					</li>
					<li>
						<a href="#">
							<img class="dashboard-avatar" alt="Saruar Ahmed" src="http://www.gravatar.com/avatar/564e1bb274c074dc4f6823af229d9dbb.png?s=50"></a>
							<strong>Name:</strong> <a href="#">Saruar Ahmed
						</a><br>
						<strong>Since:</strong> 17/05/2012<br>
						<strong>Status:</strong> <span class="label label-info">Updates</span>                                  
					</li>
				</ul>
			</div>
		</div>
	</div><!--/span-->
		
	
		<!--/资讯start-->
		<div class="row-fluid">
			<div class="box span12">
				<div class="box-header well">
					<h2><i class="icon-info-sign"></i> 资信信息</h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
						<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
				<div id="realtimechart" style="height:190px;"></div>
					<p class="clearfix"></p>
					<p>hello,lingd!</p>
				</div>
				<div class="box-content">
					<h1>Charisma <small>123123</small></h1>
					<p></p>
					<p><b>这里可以写入</b></p>
					<p class="center">
						<a href="http://usman.it/free-responsive-admin-template" class="btn btn-large btn-primary"><i class="icon-chevron-left icon-white"></i> 上传</a> 
						<a href="http://usman.it/free-responsive-admin-template" class="btn btn-large"><i class="icon-download-alt"></i> 下载</a>
					</p>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!--/资信end-->
	</div>
	
<%@include file="/WEB-INF/pages/common/foot.jsp"%>
