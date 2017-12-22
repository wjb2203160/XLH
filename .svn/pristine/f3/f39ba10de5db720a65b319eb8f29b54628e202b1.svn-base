 
function ComplaintQuery(){
	show_query_hint("query_hint");
	window.location.href='/orders/complaintBills.html?currentpage=1&orderFrom='+$("#date01").val()+'&orderTo='+$("#date02").val()+'&riderID='+$("#riderId").val()
 
}
$("#subComplaint").click(function () {
	show_query_hint("query_hint");
	   var fileInutID =$('input[name=c_fileInutID]').val();
	
	   if(fileInutID==""){
		   queryHintCallback("query_hint");
		   alert("未选择上传文件")
			 return false 
	    }else{
    $("#ComplaintUpload").ajaxSubmit({
 	    type: 'post', // 提交方式 get/post
         url: 'frontend/complaintUpload.html', // 需要提交的 url
         dataType:'json',
         data: {
             'c_fileInutID':fileInutID  
         },
         dataType:'json', 
         success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
             // 此处可对 data 作相关处理
        		$.ajax({
        			type:'post',
        			url:'/frontend/importComplaint.html',
        			data:{"importPath":data.returnUrl},
        			success:function(model){ 
        				ComplaintQuery();
        			},
        	 	 	error:function(){
        	 	 		ComplaintQuery();
        			}
        		});
         }
    });
	   return false;
 }
});