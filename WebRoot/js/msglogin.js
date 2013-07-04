


$(function(){
});


/**
* 登陆触发
*/  
function login() {
    if ($('#username').val() == "" || $('#username').val().length <= 0) {   
        alert("请输入昵称");
        return;
    }
    if ($('#pwd').val() == "" || $('#pwd').val().length <= 0) {   
        alert("请输入密码");
        return;
    }
    
    var url="loginMsg.do";
    var data={
    		"user.username":$("#username").val(),
    		"user.password":$("#pwd").val()
    }
    $.ajax({
    	type:"post",
    	url:url,
    	data:data,
    	async:false,//异步为false，即为同步
    	success:function(data){
	    	if('yes'==data){
	    		window.location="msgPush.do";
			}else if('no'==data){
				alert("登陆名或密码出错");
			}
    	},
    	dataType:'text'
    });
}  


