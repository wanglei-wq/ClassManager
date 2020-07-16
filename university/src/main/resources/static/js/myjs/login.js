$(function () {
	//如果用户没有注销，也没关闭google，缓存还存在...
	sessionStorage.setItem("user","")
	//判断localStorage中user是否过期，过期则清除"user",没过期，将num,password加载到html上，并勾选”记住密码“选项
	let userStr = localStorage.getItem("user")
	if (userStr != null) {
		let user = JSON.parse(userStr)
		let now = new Date()
		let time = now.getTime() - user.createTime
		
		let day = Math.floor(time / (1000*60*60*24))
		
		let level = time % (1000*60*60*24)
		let hour = Math.floor(level/(60*60*1000))
		
		let level2 = time % (1000*60*60)
		let minute = Math.floor(level2/(60*1000))
		
		let level3 = time % (1000*60)
		let s = Math.round(level3/1000)
		if (day > 3 ) {
			clear()
		}else{
			let num = user.num
			let password = user.password
			$("#num").val(num)
			$("#password").val(password)
			$("#RememberMe").attr('checked','true')
		}
	}
	$(document).keyup((event)=>{
		if (event.keyCode == 13) {
			$("#login").click()
		}
	});
	$("#login").click(()=>{
		loginCheck()
	});
	//
})
//登录检查方法
let loginCheck = ()=>{
	let num1 = $("#num").val().trim();
	let password1 = $("#password").val().trim();
	let remember = $("#RememberMe").is(":checked")
	let user = {
		"num":num1,
		"password":password1
	}
	if (num1 == "") {
	    layer.msg("请输入账号")
		$("#num").focus()
	    return false;
	}else if (password1 == "") {
	    layer.msg("请输入密码")
		$("#password").focus()
	    return false;
	}else if (remember) {
		rememberme(user)
	}else{
		clear()
	}
	$.ajax({
	    type:"POST",
	    url:"/user/login",
	    dataType:"json",
	    data:{
	        num:num1,
	        password:password1
	    },
	    success:function (result) {
	        if (result != null && result != 0) {
	            var user = result
	            console.log(user);
	            sessionStorage.setItem("user",JSON.stringify(user))
				//如果未完成注册的家长，登录，则跳转到相应的注册步骤界面
				if (user.status > 0 && user.status < 3) {
					layer.msg("请继续完成注册步骤")
					setTimeout(function(){
						window.location.href = "parent_register.html";
					},1500)
				}else{
					layer.msg("登录成功")
					//将自己孩子信息查询并放入session中
					if (user.status == 3) {
						$.ajax({
							type:"POST",
							url:"/user/queryChild",
							async:false,
							dataType:"json",
							data:{
								num:num1,
								password:password1
							},
							success:function(result){
								var stu = result
								sessionStorage.setItem("stu",JSON.stringify(stu))
							},
							error:function(){
								layer.msg("queryChild.ajax")
							}
						})
					}
					//
					setTimeout(function(){
						window.location.href = "index.html";
					},1500)
					return false;
				}
	        }else {
	            layer.msg("账号密码错误")
	        }
	    },
	    error:function () {
	        layer.msg("ajax请求异常")
	    }
	})
}
//
let clear = ()=>{
	localStorage.removeItem("user")
}
//
let rememberme = (user)=>{
	let localStr = localStorage.getItem("user")
	if (localStr != null) {
		let local = JSON.parse(localStr)
		if (local.num == user.num && local.password == user.password) {
			user.createTime = local.createTime
		}else{
			user.createTime = new Date().getTime()
		}
	}else{
		user.createTime = new Date().getTime()
	}
	localStorage.setItem("user",JSON.stringify(user))
}