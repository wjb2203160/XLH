$('#loginBtn').click(function(){
	var user = new Object();
	user.cellphone=$.trim($("#cellphone").val());
	user.password=$.trim($("#password").val());
	if(user.cellphone==null || user.cellphone==""){
		$("#cellphone").focus;
		$("#formtip").css("color","red");
		$("#formtip").html("对不起，手机号不能为空！");
	}else if(user.password==null || user.password==""){
		$("#password").focus;
		$("#formtip").css("color","red");
		$("#formtip").html("对不起，登陆密码不能为空！");
	}else{
		$("#formtip").html();
		$.ajax({
			type:'POST',
			url:'login.html',
			data:{user:JSON.stringify(user)},//将js里的user转换成字符串
			dataType:'html',
			timeout:1000,
			success:function(result){
				//登陆成功,跳转到‘main.html'
				if(result != "" && result == "success"){
					window.location.href='main.html';
				}else if(result == "failed"){
					$("#formtip").css("color","red");
					$("#formtip").html("success登陆失败！");
					$("#loginCode").val('');
					$("#password").val('');
				}else if(result == "nologincode"){
					$("#formtip").css("color","red");
					$("#formtip").html("登陆账号不存在，请确认！！");
					$("#loginCode")
				}else if(result == "pwderror"){
					$("#formtip").css("color","red");
					$("#formtip").html("登陆密码错误！");
				}else if(result == "nodata"){
					$("#formtip").css("color","red");
					$("#formtip").html("无数据返回！");
				}
			},
			error:function(){
				$("#formtip").css("color","red");
				$("#formtip").html("sorry！登陆失败！");
			}
		});
	}
	
});