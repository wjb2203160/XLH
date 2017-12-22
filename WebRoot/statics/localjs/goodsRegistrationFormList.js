 
/*隐藏域取值*/
//获取会员类型名称
$("#selectusertype").change(function(){
	$("#seletusertypevname").val($("#selectusertype").find("option:selected").text());
});
//获取角色名
$("#selectrole").change(function(){
	$("#selectrolename").val($("#selectrole").find("option:selected").text());
});
 


//页面加载完成之后去掉Loading
$(document).ready(function(){
    queryHintCallback("query_hint");
});
 

/*function OrderQuery(){
	show_query_hint("query_hint");
	$.ajax({
		type:'get',
		url:'/orders/theShippingBills.html?currentpage=1&orderFrom='+$("#date01").val()+'&orderTo='+$("#date02").val()+'&riderID='+$("#riderId").val()+'&result='+$("#result").find("option:selected").text()+'&overTime='+$("#overTime").find("option:selected").text(),
		success:function(model){ 
			document.write(model);
			document.close();
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}*/
var Flag = "0"; //0 新增  1 修改
var myDate = new Date();

function SelectInit(){
	//生成部门
	$.post("/goods/getTeam.html",function(teams){
		$('#selectusertype').empty();
		for (var i = 0; i < teams.length; i++) {
			  $('#selectusertype').append("<option value='"+teams[i].id+"'>"+teams[i].id+"  " +teams[i].teamName+"</option>");
        } 
		if($("#seletusertypevname").val()!=""){
			$('#selectusertype').val($("#seletusertypevname").val());
		}
	},'json'); 
	//生成物资
	$.post("/goods/getGoods.html",function(teams){
		$('#selectGoods').empty();
		for (var i = 0; i < teams.length; i++) {
			  $('#selectGoods').append("<option value='"+teams[i].id+"'>"+teams[i].goodsName+"</option>");
        } 
		if($("#seletGoodsname").val()!=""){
			$('#selectGoods').val($("#seletGoodsname").val());
		}
	},'json'); 
}

function GoodsAdd(){
	  debugger;
	  GoodsEmpty();
	 Flag = "0";
	 SelectInit();
	 $('#GoodsDiv').modal('show');
};
function GoodsDetail(id){
	 Flag = "1";
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/goods/goodsDetail.do',
		data:{
			"id":id,
			},
		dataType:"json",
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
			 debugger;
			 GoodsEmpty();
			 
			 $("#Goods").val(model.claimantCount);
			 $("#seletusertypevname").val(model.dep);
			 $("#seletGoodsname").val(model.goodsId);
			 $("#a_idcard").val(model.claimantName);
			 $("#a_accountholder").val(model.handledName);
			 $("#a_cellphone").val(model.claimantPhone);
			 $("#a_birthday").val(model.claimantTime);
			 $("#a_username").val(model.claimantSite);
			 $("#a_useraddress").val(model.remarks);
			 $("#DetailId").val(model.id);
			 SelectInit();
			 $('#GoodsDiv').modal('show');
		 }
		},
	 	error:function(){
	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
}

function GoodsEmpty(){
	/*document.getElementById("r-helmet").checked = false;
	 $("#helmet").val("0");
	 document.getElementById("r-cotta").checked = false;
	 $("#cotta").val("0");
	 document.getElementById("r-pocket").checked = false;
	 $("#pocket").val("0");
	 document.getElementById("r-takeoutBox").checked = false;
	 $("#takeoutBox").val("0");
	 document.getElementById("r-bubble").checked = false;
	 $("#bubble").val("0");*/
	
	 $("#Goods").val("0");
	 $("#a_idcard").val("");
	 $("#a_accountholder").val("");
	 $("#a_cellphone").val("");
	 $("#a_birthday").val("");
	 $("#a_username").val("");
	 $("#a_useraddress").val("");
	 $("#DetailId").val("");
	 
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
function GoodsConfirm(){
	var JsonData = '{';
	if( $("#DetailId").val()!=""){
		JsonData += '"id":"'+$("#DetailId").val()+'",';	
	}
	JsonData += '"goodsId":"'+$("#selectGoods").find("option:selected").val()+'",';
	JsonData += '"goodsName":"'+$("#selectGoods").find("option:selected").text()+'",';
	JsonData += '"dep":"'+$("#selectusertype").find("option:selected").val()+'",';
	JsonData += '"claimantCount":"'+$("#Goods").val()+'",';
	/*JsonData += '"claimantBy":"'+$("#a_idcard").val()+'",';*/
	JsonData += '"claimantName":"'+ $("#a_idcard").val()+'",';
	JsonData += '"claimantPhone":"'+$("#a_cellphone").val()+'",';
	JsonData += '"claimantTime":"'+$("#a_birthday").val()+'",';
	JsonData += '"claimantSite":"'+$("#a_username").val()+'",';
	JsonData += '"handledName":"'+$("#a_accountholder").val()+'",';
	JsonData += '"createBy":"'+$("#createName").val()+'",';
	JsonData += '"remarks":"'+$("#a_useraddress").val()+'"';
	JsonData += '}';
if(Flag == "0"){
	$.ajax({
		type:'post',
		url:'/goods/GoodsAdd.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model=="1"){
		 alert("保存成功"); 
		
		 }else if(model=="2"){
		 alert("骑手不存在，请确认骑手名称和所属部门是否正确"); 	 
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
		url:'/goods/modifyGoods.do',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!="0"){
		 alert("修改成功"); 
		 window.location.href = "/goods/goodsRegistrationFormList.html?currentpage=1";
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
function DeleteGoods(id){
	if(confirm("真的要删除吗？")){
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/goods/deleteGoods.html',
		data:{
			"id":id,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model!=""){
				alert("删除成功"); 
				window.location.href = "/goods/goodsRegistrationFormList.html?currentpage=1";
		 }
		},
 	 	error:function(){
 	 		queryHintCallback("query_hint");
			alert("失败！");
		}
	});
	}
}
$("#subGood").click(function () {
	show_query_hint("query_hint");
	   var fileInutID =$('input[name=a_fileInutID]').val();
	   if(fileInutID==""){
		   queryHintCallback("query_hint");
		   alert("未选择上传文件");
			 return false;
	    }else{
    $("#formUpload").ajaxSubmit({
 	    type: 'post', // 提交方式 get/post
         url: 'frontend/upload.html', // 需要提交的 url
         dataType:'json',
         data: {
             'a_fileInutID':fileInutID  
         },
         dataType:'json', 
         success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
             // 此处可对 data 作相关处理
        		$.ajax({
        			type:'post',
        			url:'/frontend/importGoods.do',
        			data:{"importPath":data.returnUrl},
        			success:function(model){ 
        				  
        			},
        	 	 	error:function(){
        	 	 	 
        	 	 		 
        			}
        		});
        		queryHintCallback("query_hint");
				  alert("导入完成");
				  
				  window.location.href="/goods/goodsRegistrationFormList.html";
         }
    });
	   return false;
 }
});