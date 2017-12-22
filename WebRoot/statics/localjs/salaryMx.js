var Flag = "0"; //0 新增  1 修改
var myDate = new Date();
function TeamMxQuery(){//查询薪资表明细
	show_query_hint("query_hint");
	window.location.href='/backend/salaryMx.html?currentpage=1&siteId='+$("#teamId").val();
 
}


function DeleteSalaryMxData(teamId){
	if(confirm("此操作将删除该记录所有信息，真的要删除吗？")){
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/backend/salaryMxDelete.html',
		data:{
			"id":teamId,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
				alert("删除成功"); 
				TeamMxQuery();
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
	}
}
  function SalaryMxAdd(){
	  debugger;
	  SalaryEmpty();
	 Flag = "0";
	 $('#SalaryDiv').modal('show');
} ;
function SalaryMxDetail(teamId){
	 Flag = "1";
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/backend/salaryMxDetail.do',
		data:{
			"siteId":teamId,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
			 debugger;
			 SalaryEmpty();
			 $("#orderFrom").val(model.orderFrom);
			 $("#orderTo").val(model.orderTo);
			 $("#price").val(model.price);
			 $("#isSubsidization").val(model.isSubsidization);
			 if(model.isSubsidization=="是"){
				 $("#subs").show();
			}else{
				$("#subs").hide();
			}
			 $("#subsidization").val(model.subsidization);
			 $("#remarks").val(model.remarks);
			 $("#detailId").val(model.id);
			 
			 $('#SalaryDiv').modal('show');
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}
function isSubsidization(model){
	var modelValue = model.value;
	if(modelValue=="是"){
		 $("#subs").show();
	}else{
		$("#subs").hide();
	}
	
}
function SalaryEmpty(){

	 $("#orderFrom").val("0");
	 $("#orderTo").val("0");
	 $("#price").val("0");
	 $("#isSubsidization").val("否");
	 $("#subsidization").val("0");
	 $("#remarks").val("");
	 $("#detailId").val("");
	  
}
function mDateFormat(time){
	
	var myDate = new Date(time);
	var year = myDate.getFullYear();
	var month = myDate.getMonth()+1;
	var day =myDate.getDate();
	if(month.length==1){
		month = "0"+(myDate.getMonth()+1);
	}else{
		month =(myDate.getMonth()+1);
	}
	if(day.length==1){
		day = "0"+myDate.getDate();
	}else{
		day =myDate.getDate();
	}
	return year+"-" +month+"-"+day;
}
function SalaryMxConfirm(){
	var JsonData = '{';
	JsonData += '"id":"'+$("#detailId").val()+'",';
	JsonData += '"siteId":"'+$("#teamId").val()+'",';
	JsonData += '"orderFrom":"'+$("#orderFrom").val()+'",';
	JsonData += '"orderTo":"'+$("#orderTo").val()+'",';
	JsonData += '"price":"'+$("#price").val()+'",';
	JsonData += '"isSubsidization":"'+ $("#isSubsidization").find("option:selected").val()+'",';
	JsonData += '"subsidization":"'+$("#subsidization").val()+'",';
	JsonData += '"remarks":"'+$("#remarks").val()+'"';
	JsonData += '}';
if(Flag == "0"){
	$.ajax({
		type:'post',
		url:'/backend/salaryMxAdd.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("保存成功"); 
		 }else{
		 alert("保存失败"); 	 
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
	}else{
		if(confirm("真的要修改吗？")){
	$.ajax({
		type:'post',
		url:'/backend/salaryMxModify.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("修改成功"); 
		 TeamMxQuery();
		 }else{
		 alert("修改失败"); 	 
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
	}
	}
}