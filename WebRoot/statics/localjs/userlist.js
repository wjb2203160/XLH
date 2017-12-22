function checkEmail(str){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(str == null || str == "" || reg.test(str)){
		return true;
	}else
		return false;
}

/*隐藏域取值*/
//获取会员类型名称
$("#selectusertype").change(function(){
	$("#seletusertypevname").val($("#selectusertype").find("option:selected").text());
});
//获取角色名
$("#selectrole").change(function(){
	$("#selectrolename").val($("#selectrole").find("option:selected").text());
});
//获取证件类型名称
$("#selectcardtype").change(function(){
	$("#selectcardtypename").val($("#selectcardtype").find("option:selected").text());
});
//添加弹出添加主面板
$(".adduser").click(function(e){
	$("#add_formtip").html('');
	e.preventDefault();
	$('#addUserDiv').modal('show');
})

//取消键 清空添加后的缓存 
$('.addusercancel').click(function(e){
	$("#add_formtip").html('');
	$("#a_idPic").html('');
	$("#a_bankPic").html('');
	$("#selectrole").val('');
	$("#selectusertype").val('');
	$("#selectusertype").html('<option value=\"\" selected=\"selected\">--请选择--</option>');
	$("#a_cellphone").val('');
	$("#a_username").val('');
	$("#selectcardtype").val('');
	$("#a_idcard").val('');
	$("#a_email").val('');
	$("#a_bankname").val('');
	$("#a_bankaccount").val('');
	$("#a_accountholder").val('');
	$("#a_useraddress").val('');
})

//判断是否电话已存在（增加用户的时候）
$("#a_cellphone").blur(function(){
	var alc = $('#a_cellphone').val();
	if(alc != ""){
		//异步判断
		$.post("/backend/cellphoneisexit.html",{'cellphone':alc,'id':'-1'},function(result){
			if(result == "repeat"){
				$("#add_formtip").css("color","red");
				$("#add_formtip").html("<li>该用户手机号已存在</li>");
				$("#add_formtip").attr("key",1);
			}else if(result == "failed"){
				alert("操作超时999");
			}else if(result == "only"){
				$("#add_formtip").css("color","green");
				$("#add_formtip").html("<li>该用户手机号可以注册</li>");
				$("#add_formtip").attr("key",0);
			}
			
		},'html');
	}
});
//email 输入失焦后的验证
$("#a_email").blur(function(){
	var flag = checkEmail($("#a_email").val());
	if(flag == false){
		$("#add_formtip").css("color","red");
		$("#add_formtip").html("<li>email格式不正确啊</li>");
		$("#add_formtip").attr("email",1);
	}else{
		$("#add_formtip").html('');
		$("#add_formtip").attr("email",0);
	}
});

//添加用户信息验证
function addUserFunction(){
	$("#add_formtip").html("");
	var result = true;
	if($("#selectrole").val() == ""){
		$("#add_formtip").css("color","red");
		$("#add_formtip").html("<li>角色不能为空</li>");
		result = false;
	}
	if($.trim($("#a_cellphone").val()) == "" || $("#a_cellphone").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").html("<li>用户名不能为空</li>");
		result = false;
	}else{//若重名 则不能提交
		if($("#add_formtip").attr("key") == "1"){
			$("#add_formtip").css("color","red");
			$("#add_formtip").html("<li>用户名已存在</li>");
			result = false;
		}
	}
	if($.trim($("#a_email").val()) != "" && $("#a_email").val() != null && $("add_formtip").attr("email") == "1"){
		$("#add_formtip").css("color","red");
		$("#add_formtip").html("<li>email格式不正确</li>");
		result = false;
	}
	if($.trim($("#a_username").val()) == "" || $("#a_username").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，真实姓名不能为空。</li>");
		result = false;
	}
	if($("#selectcardtype").val() == ""){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，证件类型不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_idcard").val()) == "" || $("#a_idcard").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，证件号码不能为空。</li>");
		result = false;
	}else{
		if($("#a_idcard").val().length < 6){
			$("#add_formtip").css("color","red");
			$("#add_formtip").append("<li>对不起，证件号码长度必须超过6位。</li>");
			result = false;
		}
	}
	if($.trim($("#a_bankname").val()) == "" || $("#a_bankname").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，开户行不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_bankaccount").val()) == "" || $("#a_bankaccount").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，开户卡号不能为空。</li>");
		result = false;
	}
	if($.trim($("#a_accountholder").val()) == "" || $("#a_accountholder").val() == null){
		$("#add_formtip").css("color","red");
		$("#add_formtip").append("<li>对不起，开户人不能为空。</li>");
		result = false;
	}
	
	if(result == true) alert("添加成功！");
	
	return result;
}

//文件上传:身份证图片（新添加）
$("#a_uploadbtnID").click(function(){
	TajaxFileUpload('0','a_fileInutID','a_uploadbtnID','a_idPic','a_fileInputIDPath');
});
//上传银行卡图片（新添加）
$("#a_uploadbtnBank").click(function(){
	
});
//'0','a_fileInutID','a_uploadbtnID','a_idPic','a_fileInputIDPath'
function TajaxFileUpload(flag,t1,t2,t3,t4){
	 if($("#"+t1+"").val == '' || $("#"+t1+"").val == null){
		 alert("请选择上传文件！");
	 }else{
		 $.ajaxFileUpload({
			url:'/backend/upload.html',
			secureuri:false,
			fileElementId:t1,
			dataType:'json',
			success:function(data){
				data = data.replace(/(^\s*)|(\s*$)/g,"");
				if(data == "1"){
					alert("上传图片大小不得超过50k"); 
					$("#uniform-"+t1+" span:first").html('无文件');
					$("input[name-'"+t1+"']").change(function(){
						var fn = $("input[name-'"+t1+"']").val();
						if($.browser.msie){
							fn = fn.substring(fn.lastIndexOf("\\")+1);
						}
						$("#uniform-"+t1+" span:first").html(fn);
					});
				}else if(data == "2"){
					alert("上传的图片格式不正确");
					$("#uniform-"+t1+" span:first").html('无文件');
					$("input[name-'"+t1+"']").change(function(){
						var fn = $("input[name-'"+t1+"']").val();
						if($.browser.msie){
							fn = fn.substring(fn.lastIndexOf("\\")+1);
						}
						$("#uniform-"+t1+" span:first").html(fn);
					});
				}else{
					$("#"+t3+"").append("<p><span onclick=\"delpic('"+flag+"','"+t3+"','"+t2+"',this,'"+data+"','"+t4+"','"+t1+"');\">x</span><img src=\""+data+"?m="+Math.random+"\"</p>")
					$("#"+t2+"").hide();
					$("#"+t4+"").val(data);
					$("input[name-'"+t1+"']").change(function(){
						var fn = $("input[name-'"+t1+"']").val();
						if($.browser.msie){
							fn = fn.substring(fn.lastIndexOf("\\")+1);
						}
						$("#uniform-"+t1+" span:first").html(fn);
					});
				}
			},
		 	error:function(){
		 		alert("上传失败！！！");
		 	}
		 });
	 }
}

//删除图片
function delpic(id,closeSpan,uploadBtn,obj,picpath,picText,fileinputid){
	//delete
	$.post("/backend/delpic.html",{'id':id,'picpath':picpath},function(result){
		if("success" == result){
			alert("删除成功");
			$('#'+picText).val('');
			$("#uniform-"+fileinputid+" span:first").html('无文件');
			document.getElementById(closeSpan).removeChild(obj.parentElement);
			// 等价于上一行的效果  ：$("#"+closeSpan).html('');
			
			$('#'+uploadBtn).show();
		}else{
			alert("删除失败");
		}
	},'html');
}

$('.modifyuser').click(function(e){
	var m_id = $(this).attr('id');
	$.ajax({
		url:'/backend/getuser.html',
		type:'POST',
		data:{id:m_id},
		dataType:'json',
		timeout:1000,
		error:function(){
			alert("error");
		},
		success:function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				$('#m_id').val(result.id);
				$('#m_cellphone').val(result.cellphone);
				$('#m_username').val(result.userName);
				$('#m_birthday').val(result.v_birthday);
				$('#m_cardtypename').val(result.cardTypeName);
				var cardType = result.cardType;
				var cardTypeName = result.cardTypeName;
				$('#m_cardtype').val('');
				if(cardType == null || cardType == ""){
					$('#m_cardtype').append("<option value=\"\" selected=\"selected\">--请选择--</option>")
				}
				for(var i = 0; i < cardTypeListJson.length-1; i++){
					if(cardTypeListJson[i].valueId == cardType){
						$('#m_cardtype').append("<option value=\""+cardType+"\" selected=\"selected\">"+cardTypeName+"</option>")
					}else{
						$('#m_cardtype').append("<option value=\""+cardTypeListJson[i].valueId+"\" selected=\"selected\">"+cardTypeListJson[i].valueName+"</option>")
					}
				}
				
				var roleId = result.roleId;
				var roleName = result.roleName;
				$("#m_roleId").html('');
				$("#m_rolename").val(result.roleName);
				if(roleId == null || roleId == ""){
					$("#m_roleId").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
				}
				for(var i = 0; i < roleListJson.length; i++){
					if(roleListJson[i].id == roleId){
						$("#m_roleId").append("<option value=\""+roleId+"\" selected=\"selected\">"+roleName+"</option>");
					}else{
						$("#m_roleId").append("<option value=\""+roleListJson[i].id+"\">"+roleListJson[i].roleName+"</option>");
					}
				}
				
				$('#m_selectusertypename').val(result.userTypeName);
				$('m_selectusertype').html('');
				if(roleId == "2"){
					var userType = result.userType;
					var userTypeName = result.userTypeName;
					if(userType == null || userType == ""){
						$('m_selectusertype').append("<option value=\"\" selected=\"selected\">--请选择--</option>");
					}
					$.post("/backend/loadUserTypeList.html",{'s_role':roleId},function(r){
						if(r != ""){
							for(var i = 0; i < r.length; i++){
								if(r[i].valueId == userType){
									$('m_selectusertype').append("<option value=\""+userType+"\" selected=\"selected\">"+userTypeName+"</option>");
								}else{
									$('m_selectusertype').append("<option value=\""+r[i].valueId+"\">"+r[i].valueName+"</option>");
								}
							}
						}else{
							alert("用户会员类型加载失败");
						}
					},'json');
				}else if(roleId == "1"){
					$('m_selectusertype').append("<option value=\"\" selected=\"selected\">--请选择--</option>");
				}
				
				var sex=result.sex;
				$("#m_sex").html('');
				if(sex==""){
					$("#m_sex").html("<option value=\"\" selected=\"selected\">--请选择--</option><option value=\"男\">男</option><option value=\"女\">女</option>");
				}else if(sex=="男"){
					$("#m_sex").html("<option value=\"男\" selected=\"selected\">男</option><option value=\"女\">女</option>");
				}else if(sex=="女"){
					$("#m_sex").html("<option value=\"男\">男</option><option value=\"女\" selected=\"selected\">女</option>");
				}
				
				$('#m_idcard').val(result.idCard);
				
				$('#m_country').val(result.country);
				$('#m_mobile').val(result.mobile);
				$('#m_email').val(result.email);
				$('#m_postcode').val(result.postCode);
				$('#m_bankname').val(result.bankName);
				$('#m_bankaccount').val(result.bankAccount);
				$('#m_accountholder').val(result.accountHolder);
				$('#m_refercode').val(result.referCode);
				$('#m_createtime').val(result.createTime);
				$('#m_useraddress').val(result.userAddress);
				
				var isstart = result.isStart;
				if(isstart == '1'){
					$('#m_isstart').append("<option value=\"1\" selected=\"selected\">启用</option><option value=\"2\">不启用</option>");
				}else{
					$('#m_isstart').append("<option value=\"1\">启用</option><option value=\"2\" selected=\"selected\">不启用</option>");
				}
				
				var m_idcardpicpath = result.idCardPicPath;
				$('#m_fileInputIDPath').val(result.idCardPicPath);
				if(m_idcardpicpath == null || m_idcardpicpath ==""){
					$("#m_idPic").append("暂无");
					$("m_uploadbtnID").show();
				}else{
					$("#m_idPic").append("<p><img src=\""+m_idcardpicpath+"?m="+Math.random()+"\"/></p>");
					$("#m_idPic").append("<p><span onclick=\"" +
							"delpic('"+result.id+"','m_idPic','m_uploadbtnID',this,'"+m_idcardpicpath+"','m_fileInputID');\">x</span>" +
									"<img src=\""+m_idcardpicpath+"?m="+Math.random()+"\"/></p>");
					$("m_uploadbtnID").hide();
				}
				
				$('#m_fileInputBankPath').val(result.bankPicPath);
				var m_bankPicPath = result.bankPicPath;
				if(m_bankPicPath == null || m_bankPicPath ==""){
					$("#m_bankPic").append("暂无");
					$("m_uploadbtnBank").show();
				}else{
					$("#m_bankPic").append("<p><img src=\""+m_bankPicPath+"?m="+Math.random()+"\"/></p>");
					$("#m_bankPic").append("<p><span onclick=\"" +
							"delpic('"+result.id+"','m_bankPic','m_uploadbtnBank',this,'"+m_bankPicPath+"','m_fileInputBank');\">x</span>" +
									"<img src=\""+m_bankPicPath+"?m="+Math.random()+"\"/></p>");
					$("m_uploadbtnBank").hide();
				}
				e.preventDefault();
				$('#modifyUserDiv').modal('show');
			}
		}
	});
});

//查看用户
$('.viewuser').click(function(e){
	var m_id = $(this).attr('id');
	//ajax
	$.ajax({
		url:'/backend/getuser.html',
		type:'POST',
		data:{id:m_id},
		dataType:'json',
		timeout:1000,
		error:function(){
			alert("error");
		},
		success:function(result){
			if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				$('#v_id').val(result.id);
				$('#v_rolename').val(result.roleName);
				$('#v_usertypename').val(result.userTypeName);
				$('#v_cellphone').val(result.cellphone);
				$('#v_username').val(result.userName);
				$('#v_sex').val(result.sex);
				$('#v_cardtypename').val(result.cardTypeName);
				$('#v_idcard').val(result.idCard);
				$('#v_birthday').val(result.v_birthday);
				$('#v_country').val(result.country);
				$('#v_mobile').val(result.mobile);
				$('#v_email').val(result.email);
				$('#v_postcode').val(result.postCode);
				$('#v_bankname').val(result.bankName);
				$('#v_bankaccount').val(result.bankAccount);
				$('#v_accountholder').val(result.accountHolder);
				$('#v_refercode').val(result.referCode);
				$('#v_createtime').val(result.createTime);
				$('#v_isstart').val(result.isStart);
				
				var isstart = result.isStart;
				if(isstart == '1'){
					$('#v_isstart').append("<option value=\"1\" selected=\"selected\">启用</option><option value=\"2\">不启用</option>");
				}else{
					$('#v_isstart').append("<option value=\"1\">启用</option><option value=\"2\" selected=\"selected\">不启用</option>");
				}
				
				$('#v_useraddress').val(result.userAddress);
				
				$('#v_fileInputIDPath').val(result.idCardPicPath);
				var v_idcardpicpath = result.idCardPicPath;
				if(v_idcardpicpath == null || v_idcardpicpath ==""){
					$("#v_idPic").append("暂无");
				}else{
					$("#v_idPic").append("<p><img src=\""+v_idcardpicpath+"?m="+Math.random()+"\"/></p>");
				}
				
				$('#v_fileInputBankPath').val(result.bankPicPath);
				var v_bankPicPath = result.bankPicPath;
				if(v_bankPicPath == null || v_bankPicPath ==""){
					$("#v_bankPic").append("暂无");
				}else{
					$("#v_bankPic").append("<p><img src=\""+v_bankPicPath+"?m="+Math.random()+"\"/></p>");
				}
				e.preventDefault();
				$('#viewUserDiv').modal('show');
			}
		}
	});
});

/*取消键 清空查看后区域内的数据*/
$('.viewusercancel').click(function(e){
	$("#v_idPic").html('');
	$("#v_bankPic").html('');
	$("#v_isstart").html('');
});