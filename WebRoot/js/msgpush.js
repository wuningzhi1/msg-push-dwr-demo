$(function(){
	//在线用户列表中过滤本身
	var id="#"+$("#username").val();
	$(id).css("display","none");
});

function pushMsg(){
	var title=$("#title").val();
	//var content=$("#content").val();
	var receiverName=$(":radio:checked").val();
	if (receiverName == undefined) {   
        alert("请选择发送的对象");
        return;
    }
	if (title == "" || title.length <= 0) {   
        alert("请输入主题");
        return;
    }
	/*
	if (content == "" || content.length <= 0) {   
        alert("请输入内容");
        return;
    }
	*/
	
	/*
	MsgPushService.send($("#username").val(),$(":radio:checked").val(),$("#title").val(),$("#content").val(),{
    	//指定回调函数  
        callback:getMsg,  
        //指定超时时长  
        timeout:1000,  
        //指定错误处理函数  
        errorHandler:function(message) { alert("错误提示: " + message); },  
        //指定  
        warningHandler:function(message) { alert("Oops: " + message); },  
        textHtmlHandler: function(message) { alert("Oops: " + message); },  
        exceptionHandler: function(message) { alert("Oops: " + message); },  
        //指定发送请求的方式  
        httpMethod:'POST',  
        async:true,  
        //指定发送请求之前的勾子函数  
        preHook:function(){
        	//alert('远程调用之前...')
        },  
        //指定发送请求之后的勾子函数  
        postHook:function(){
        	//alert('远程调用之后...')
        }  
    });
	*/
	MsgPushService.pushMsg($("#username").val(),receiverName,title,"",function(msg){
		//alert(msg);
	});
	//MsgPushService.test();
}

function getMsg(msg){
	//alert(msg);
}

function doReply(msg){
	if('ok'==msg){
		$('#notice').poshytip({
			className: 'tip-darkgray',
			showOn:'none',//一直显示
			content:'你有新消息  <a href="sessionMgmt.do?pageIndex=0" onclick="hide();" target="_blank">查看留言</a>',
			alignTo:'target',//定位的相对目标
			alignX:'right',
			alignY:'center',
			offsetX:5
		});
		$('#notice').poshytip('show');
	}
}

function hide(){
	$('#notice').poshytip('hide');
}

/**
* 页面初始化
*/  
function init() {   
     dwr.engine.setActiveReverseAjax(true); // 激活反转 重要   
     MsgPushService.addScriptSession($("#username").val(),function(msg){});
} 

window.onload = init;//页面加载完毕后执行初始化方法init 


