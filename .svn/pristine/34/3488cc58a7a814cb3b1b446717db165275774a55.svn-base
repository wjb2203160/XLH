 
$(document).ready(
		function() {
			$.post("/goods/getTeam.html",function(teams){
				$('#selectusertype').empty();
				$('#selectusertype').append("<option >"+"</option>");
				for (var i = 0; i < teams.length; i++) {
					  $('#selectusertype').append("<option value='"+teams[i].id+"'>"+teams[i].id+"  " +teams[i].teamName+"</option>");
		        } 
			},'json'); 
		});
function Query(){//查询工资记录
	show_query_hint("query_hint");
	window.location.href='/pay/calTeam.do?currentpage=1&orderFrom='+$("#date01").val()+'&orderTo='+$("#date02").val()+'&teamID='+$("#selectusertype").find("option:selected").val()
 
}
 
function OutPutWage(){//导出工资表
	show_query_hint("query_hint");
	$.ajax({
		type:'post',
		url:'/frontend/outputTeam.html',
		data:{
			"orderFrom":$("#date01").val(),
			"orderTo":$("#date02").val(),
			"teamID":$("#selectusertype").find("option:selected").val(),
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

 