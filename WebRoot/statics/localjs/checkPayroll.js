 

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
function Query(){//查询工资记录
	 
	show_query_hint("query_hint");
	window.location.href= '/pay/checkPayroll.do?currentpage=1&orderFrom='+$("#date01").val()+'&orderTo='+$("#date02").val()+'&riderID='+$("#riderId").val()+'&riderName='+$("#riderName").val()+'&teamID='+$("#selectusertype").find("option:selected").val()+'&zeroNotDisp='+$("#zeroNotDisp").is(":checked")
}
function CalMoney(){//计算工资
	if(confirm("真的要计算工资吗？")){
	show_query_hint("query_hint");
	window.location.href='/pay/checkPayroll.do?calWage=1&orderFrom='+$("#date01").val()+'&orderTo='+$("#date02").val()+'&currentpage=1&riderID='+$("#riderId").val()+'&riderName='+$("#riderName").val()+'&teamID='+$("#selectusertype").find("option:selected").val(); 
	 }
}


function OutPutWageByBank(){//导出银行对接工资表
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/frontend/outputOrderByBank.html',
		data:{
			"orderFrom":$("#date01").val(),
			"orderTo":$("#date02").val(),
			"riderID":$("#riderId").val(),
			"teamID":$("#selectusertype").find("option:selected").val(),
			"zeroNotDisp":$("#zeroNotDisp").is(":checked"),
			"riderName":$("#riderName").val(),
			},
		success:function(model){ 
			queryHintCallback("query_hint");
			window.location.href = model;
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}

function OutPutWage(){//导出工资表
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/frontend/outputOrder.html',
		data:{
			"orderFrom":$("#date01").val(),
			"orderTo":$("#date02").val(),
			"riderID":$("#riderId").val(),
			"teamID":$("#selectusertype").find("option:selected").val(),
			"zeroNotDisp":$("#zeroNotDisp").is(":checked"),
			"riderName":$("#riderName").val(),
			},
		success:function(model){ 
			queryHintCallback("query_hint");
			window.location.href = model;
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}

function DeleteData(riderId){
	if(confirm("真的要删除吗？")){
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/pay/checkPayrollDelete.do',
		data:{
			"riderID":riderId,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
			 queryHintCallback("query_hint");
				alert("删除成功"); 
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
	}
}

function LoadDetail(riderId){
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/pay/checkPayrollDetail.do',
		data:{
			"riderID":riderId,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
			 debugger;
			 wageEmpty();
			 $("#wageDate").html(model.wageDate);
			 $("#dRiderId").html(model.id);
			 $("#riderName").html(model.riderName);
			 $("#errorCount").html(model.errorCount);
			 $("#normalCount").html(model.normalCount);
			 $("#timeoutCount").html(model.timeoutCount);
			 $("#onTimeCount").html(model.onTimeCount);
			 $("#subsidization").html(model.subsidization);
			 $("#attendance").html(model.attendance);
			 $("#leakageBuckle").val(model.leakageBuckle);
			 $("#referralFee").val(model.referralFee);
			 $("#insuranceFee").val(model.insuranceFee);
			 $("#amerce").val(model.amerce);
			 $("#overAllowance").val(model.overAllowance);
			 $("#shouldPay").html(model.shouldPay);
			 $("#incomeTax").val(model.incomeTax);
			 $("#goodFee").html(model.goodFee);
			 $("#advancePay").val(model.advancePay);
			 $("#electricity").val(model.electricity);
			 $("#realPay").html(model.realPay);	
			 $.ajax({
					type:'post',
					url:'/pay/goodList.do',
					data:{
						"orderFrom":$("#date01").val(),
						"orderTo":$("#date02").val(),
						"riderID":riderId,
						},
					dataType:"json",
					success:function(model){ 	
						debugger;
					 if(model!=""){
						 var str="";
						 for(var p in model){
						      str= str+"<li><label>"+model[p].goodsName+":</label><span id=\""+p+"\">"+model[p].claimantCount+"</span></li>";
						 }
						 $("#goodList").html("");
						 $("#goodList").html(str);
					 }
					},
			 	 	error:function(){
			 	 		 
						alert("失败！");
					}
				});
			 $('#wageDiv').modal('show');
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}

function wageEmpty(){
	 $("#wageDate").html("");
	 $("#dRiderId").html("");
	 $("#riderName").html("");
	 $("#errorCount").html("");
	 $("#normalCount").html("");
	 $("#timeoutCount").html("");
	 $("#onTimeCount").html("");
	 $("#subsidization").html("");
	 $("#attendance").html("");
	 $("#leakageBuckle").val("");
	 $("#referralFee").val("");
	 $("#insuranceFee").val("");
	 $("#amerce").val("");
	 $("#overAllowance").val("");
	 $("#shouldPay").html("");
	 $("#incomeTax").val("");
	 $("#goodFee").html("");
	 $("#advancePay").val("");
	 $("#electricity").val("");
	 $("#realPay").html("");
}

function WageConfirm(){
	if(confirm("真的要修改吗？")){
	var JsonData = '{';
	JsonData += '"id":"'+$("#dRiderId").html()+'",';
	JsonData += '"errorCount":"'+$("#errorCount").html()+'",';
	JsonData += '"normalCount":"'+$("#normalCount").html()+'",';
	JsonData += '"timeoutCount":"'+$("#timeoutCount").html()+'",';
	JsonData += '"onTimeCount":"'+ $("#onTimeCount").html()+'",';
	JsonData += '"subsidization":"'+$("#subsidization").html()+'",';
	JsonData += '"attendance":"'+$("#attendance").html()+'",';
	JsonData += '"leakageBuckle":"'+$("#leakageBuckle").val()+'",';
	JsonData += '"referralFee":"'+$("#referralFee").val()+'",';
	JsonData += '"insuranceFee":"'+$("#insuranceFee").val()+'",';
	JsonData += '"amerce":"'+$("#amerce").val()+'",';
	JsonData += '"overAllowance":"'+$("#overAllowance").val()+'",';
	JsonData += '"shouldPay":"'+$("#shouldPay").html()+'",';
	JsonData += '"incomeTax":"'+$("#incomeTax").val()+'",';
	JsonData += '"goodFee":"'+$("#goodFee").html()+'",';
	JsonData += '"advancePay":"'+$("#advancePay").val()+'",';
	JsonData += '"electricity":"'+$("#electricity").val()+'",';
	JsonData += '"realPay":"'+$("#realPay").html()+'",';
	JsonData += '"wageDate":"'+$("#wageDate").html()+'"';
	JsonData += '}';
	$.ajax({
		type:'post',
		url:'/pay/checkPayrollModify.do',
		data:{
			"jsonData":JsonData,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("修改成功"); 
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

$("#leakageBuckle").blur(function(){
	CalWage();
});
$("#referralFee").blur(function(){
	CalWage();
});
$("#insuranceFee").blur(function(){
	CalWage();
});
$("#amerce").blur(function(){
	CalWage();
});
$("#overAllowance").blur(function(){
	CalWage();
});
$("#incomeTax").blur(function(){
	CalWage();
});
$("#goodFee").blur(function(){
	CalWage();
});
$("#advancePay").blur(function(){
	CalWage();
});
$("#electricity").blur(function(){
	CalWage();
});
function CalWage(){
	try 
	{ 
		debugger;
		var subsidization = parseFloat($("#subsidization").html());
		var leakageBuckle = parseFloat($("#leakageBuckle").val());
		var referralFee = parseFloat($("#referralFee").val());
		var insuranceFee = parseFloat($("#insuranceFee").val());
		var amerce =  parseFloat($("#amerce").val());
		var overAllowance = parseFloat($("#overAllowance").val());
		var incomeTax = parseFloat($("#incomeTax").val());
		var goodFee = parseFloat($("#goodFee").html());
		var advancePay = parseFloat($("#advancePay").val());
		var electricity = parseFloat($("#electricity").val());
	$("#realPay").html(subsidization+leakageBuckle+referralFee+insuranceFee+amerce+overAllowance+incomeTax+goodFee+advancePay+electricity);
	} 
	catch (e) 
	{ 
	 
	}
	
}