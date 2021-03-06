$(document).ready(
		function() {
	 
			$.post("/goods/getTeam.html",function(teams){
				$('#selectusertype').empty();
				$('#selectusertype').append("<option >"+"</option>");
				for (var i = 0; i < teams.length; i++) {
					  $('#selectusertype').append("<option value='"+teams[i].id+"'>"+teams[i].id+"  " +teams[i].teamName+"</option>");
		        } 
				debugger;
				if($("#selectUser").val()!=""){
					$('#selectusertype').val($("#selectUser").val());
				}
			},'json'); 
		 
		});
var Flag = "0"; //0 新增  1 修改
var myDate = new Date();
function TeamQuery(){//查询薪资表
	show_query_hint("query_hint");
	window.location.href= '/backend/salary.html?currentpage=1&siteName='+$("#siteName").val()+'&id='+$("#teamId").val();
}


function DeleteSalaryData(teamId){
	if(confirm("此操作将删除该记录所有信息，真的要删除吗？")){
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/backend/salaryDelete.html',
		data:{
			"id":teamId,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model=="2"){
				alert("删除成功"); 
		 }
		 else if(model=="1"){
				alert("汇总删除成功,但明细删除失败"); 
		 }else{
			 alert("删除失败"); 
		 }
		 TeamQuery();
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
			
		}
	});
	}
}
  function SalaryAdd(){
	  debugger;
	  SalaryEmpty();
	 Flag = "0";
	 $("#siteId").removeAttr("readonly"); 
	 $('#SalaryDiv').modal('show');
};
function SalaryDetail(teamId){
	
	 Flag = "1";
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/backend/salaryDetail.do',
		data:{
			"id":teamId,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
			 debugger;
			 SalaryEmpty();
			/* $("#siteId").attr("readonly","readonly"); */
			 $('#selectusertype').val(model.id);
			/* $("#siteId").val(model.id);
			 $("#teamName").val(model.siteName);*/
			 $("#basicSalary").val(model.basicSalary);
			 $("#perfectAttendance").val(model.perfectAttendance);
			 $("#attendanceDay").val(model.attendanceDay);
			 $("#OrderNum").val(model.orderNum);
			 $("#orderPrice").val(model.orderPrice);
			 $("#executionDate").val(mDateFormat(model.executionDate.time));
			 $("#remarks").val(model.remarks);
			 $('#SalaryDiv').modal('show');
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}

function SalaryEmpty(){
	 $("#siteId").val("0");
	 $("#teamName").val("");
	 $("#basicSalary").val("0");
	 $("#perfectAttendance").val("0");
	 $("#attendanceDay").val("0");
	 $("#OrderNum").val("0");
	 $("#orderPrice").val("0");
	 $("#executionDate").val("");
	 $("#remarks").val("");
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
function SalaryConfirm(){
	debugger;
	var str = $("#selectusertype").find("option:selected").text();
	var strArray = str.split("  ");
	var JsonData = '{';
	JsonData += '"id":"'+strArray[0]+'",';
	JsonData += '"siteName":"'+strArray[1]+'",';
	JsonData += '"basicSalary":"'+$("#basicSalary").val()+'",';
	JsonData += '"perfectAttendance":"'+$("#perfectAttendance").val()+'",';
	JsonData += '"attendanceDay":"'+ $("#attendanceDay").val()+'",';
	JsonData += '"orderNum":"'+$("#OrderNum").val()+'",';
	JsonData += '"orderPrice":"'+$("#orderPrice").val()+'",';
	JsonData += '"executionDate":"'+$("#executionDate").val()+'",';
	JsonData += '"remarks":"'+$("#remarks").val()+'"';
	JsonData += '}';
if(Flag == "0"){
	$.ajax({
		type:'post',
		url:'/backend/salaryAdd.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("保存成功"); 
		 TeamQuery();
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
		url:'/backend/salaryModify.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("修改成功"); 
		 TeamQuery();
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