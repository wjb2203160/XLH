//页面加载完成之后去掉Loading
$(document).ready(function(){
    queryHintCallback("query_hint");
});
var Flag = "0"; //0 新增  1 修改
var myDate = new Date();

function SelectInit(){
	//生成物品类型
	$.post("/goods/selectGoodsType.html",function(teams){
		debugger;
		$('#selectusertype').empty();
		for (var i = 0; i < teams.length; i++) {
			  $('#selectusertype').append("<option value='"+teams[i].valueId+"'>"+teams[i].valueName+"</option>");
        } 
		if($("#seletusertypevname").val()!=""){
			$('#selectusertype').val($("#seletusertypevname").val());
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
		url:'/goods/goodsFormDetail.do',
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
	JsonData += '"goodsType":"'+$("#selectusertype").find("option:selected").val()+'",';
	JsonData += '"goodsCode":"'+$("#selectGoodsCode").val()+'",';
	JsonData += '"goodsName":"'+$("#selectGoodsname").val()+'",';
	JsonData += '"price":"'+$("#price").val()+'",';
	JsonData += '"goodsInManName":"'+ $("#a_accountholder").val()+'",';
	JsonData += '"goodsInTime":"'+$("#a_birthday").val()+'",';
	JsonData += '"goodsInCount":"'+$("#goodsInCount").val()+'",';
	JsonData += '"remarks":"'+$("#a_useraddress").val()+'"';
	JsonData += '}';
if(Flag == "0"){
	$.ajax({
		type:'post',
		url:'/goods/GoodsFormAdd.html',
		data:{
			"jsonData":JsonData,
			},
		
		success:function(model){ 
			queryHintCallback("query_hint");
		 if(model=="1"){
		 alert("保存成功"); 
		 window.location.href = "/goods/goodsEntryExitFormList.html?currentpage=1";
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
		 window.location.href = "/goods/goodsEntryExitFormList.html?currentpage=1";
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
				window.location.href = "/goods/goodsEntryExitFormList.html?currentpage=1";
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
				  
				  window.location.href="/goods/goodsEntryExitFormList.html";
         }
    });
	   return false;
 }
});