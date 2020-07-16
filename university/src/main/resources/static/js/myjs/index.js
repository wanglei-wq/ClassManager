//点击父标签，加载内嵌html到iframe中

//点击子标签，加载内嵌html到iframe中
function iframeUrl(url){
	$("#showView").attr("src",url)
	return;
}
//function:点击含有子目录的元素，加载子目录
function getChildAuthList(auth)
{
	$('ul[name="childList"]').each(function(index,element){
		//获取一个parent下a
		var ppp = $(this)
		var aList = $(this).find("a")
		if (aList.length == 0) {
			var pid = auth.getAttribute("id")
			console.log(pid)
			//根据拿到的主权限id，ajax异步查询其子权限
			$.ajax({
				type:"POST",
				url:"/user/queryChildAuth",
				dataType:"json",
				data:{
					id:pid
				},
				success:function(result){
					console.log(result)
					//向主权限下加入子权限html代码
					for (var i = 0; i < result.length; i++) {
						var childAuth = result[i]
						ppp.append('<li onclick="iframeUrl(&quot;'+childAuth.url+'&quot;)">'
												+'<a href="#">'
												+childAuth.urlname
												+'</a>'
												+'</li>')
					}
					$(".nav-parent").trigger("create");
					$(".nav nav-children").trigger("create");
				},
				error:function(){
					layer.msg("ajax请求异常")
				}
			})
		}else{
			return false;
		}
	})
}

//html加载后执行
$(function(){
	//判断是否是支付成功后加载进来的
	var url = sessionStorage.getItem("url")
	if (url == null || url == "") {
		console.log("为空")
	}else{
		iframeUrl(url)
	}
	//加载主权限列表
	var userStr = sessionStorage.getItem("user")
	var user = JSON.parse(userStr)
	//加载头像
	if (user.sex == "男") {
		$("#tou").attr("src","./img/2.jpg")
	}
	//
	var auth_list = user.list
	console.log(user)
	console.log(auth_list)
	for (var i = 0; i < auth_list.length; i++) {
		var auth = auth_list[i]
		if (auth.url.trim() != "") {
			$("#auth_list").append('<li onclick="iframeUrl(&quot;'+auth.url+'&quot;)">'
									+'<a href="#">'
                                    +'<i class="fa fa-home" aria-hidden="true"></i>'
                                    +'<span>'+auth.urlname+'</span>'
									+'</a>'
									+'</li>')
		}else{
			$("#auth_list").append('<li class="nav-parent" id="'+auth.id+'" onclick="getChildAuthList(this)">'
									+'<a>'
                                    +'<i class="fa fa-copy" aria-hidden="true"></i>'
                                    +'<span>'+auth.urlname+'</span>'
									+'</a>'
									+'<ul class="nav nav-children" name="childList">'
									+'</ul>'
									+'</li>')
		}
	}
	//加载子权限列表,只加载1次
	for (var i = 0; i < 1; i++) {
		var P_auth_list = $(".nav-parent")
		for (var i = 0; i < P_auth_list.length; i++) {
			var P_auth = P_auth_list[i]
			P_auth.click()
		}
	}
	
	//重新渲染bootstrap下拉列表
	'use strict';
	
		var $items = $( '.nav-main li.nav-parent' );
	
		function expand( li ) {
			li.children( 'ul.nav-children' ).slideDown( 'fast', function() {
				li.addClass( 'nav-expanded' );
				$(this).css( 'display', '' );
				ensureVisible( li );
			});
		}
	
		function collapse( li ) {
			li.children('ul.nav-children' ).slideUp( 'fast', function() {
				$(this).css( 'display', '' );
				li.removeClass( 'nav-expanded' );
			});
		}
	
		function ensureVisible( li ) {
			var scroller = li.offsetParent();
			if ( !scroller.get(0) ) {
				return false;
			}
	
			var top = li.position().top;
			if ( top < 0 ) {
				scroller.animate({
					scrollTop: scroller.scrollTop() + top
				}, 'fast');
			}
		}
	
		$items.find('> a').on('click', function() {
			var prev = $( this ).closest('ul.nav').find('> li.nav-expanded' ),
				next = $( this ).closest('li');
	
			if ( prev.get( 0 ) !== next.get( 0 ) ) {
				collapse( prev );
				expand( next );
			} else {
				collapse( prev );
			}
		});
		
	//name加载
	$("#name").text(user.name)
	//角色加载
	$.ajax({
		type:"POST",
		url:"/user/roleList",
		dataType:"json",
		data:{
			num:user.num,
			password:user.password
		},
		success:function(roleList){
			console.log(roleList)
			sessionStorage.setItem("roleList",JSON.stringify(roleList))
			var roleStr = roleList[0].rolename
			if (roleList.length != 1) {
				for (var i = 1; i < roleList.length; i++) {
					roleStr += "\\" + roleList[i].rolename
				}
			}
			if (user.status == 3) {
				var stu = JSON.parse(sessionStorage.getItem("stu"))
				$("#role").text(stu.name+roleStr)
			}else{
				$("#role").text(roleStr)
			}
		},
		error:function(){
			layer.msg("角色加载中···")
		}
	})
	//用户所在班级及科目加载
	if (user.status == 3) {
		var stu = JSON.parse(sessionStorage.getItem("stu"))
		//查询用户所在班级
		$.ajax({
			type:"POST",
			url:"/user/clazzList",
			dataType:"json",
			async:false,
			data:{
				num:stu.num,
				flag:21
			},
			success:function(clazzList){
				console.log(clazzList)
				sessionStorage.setItem("teach_class",JSON.stringify(clazzList))
			},
			error:function(){
				layer.msg("班级列表ajax异常")
			}
		})
	}else{
		//查询用户所在班级
		$.ajax({
			type:"POST",
			url:"/user/clazzList",
			dataType:"json",
			async:false,
			data:{
				num:user.num,
				flag:21
			},
			success:function(clazzList){
				console.log(clazzList)
				sessionStorage.setItem("teach_class",JSON.stringify(clazzList))
			},
			error:function(){
				layer.msg("班级列表ajax异常")
			}
		})
	}
	//查询用户所教课程
	if(user.courseId != 0){
		$.ajax({
			type:"POST",
			url:"/user/subjectList",
			dataType:"json",
			async:false,
			data:{
				cId:user.courseId,
				flag:1
			},
			success:function(subjectList){
				console.log(subjectList)
				sessionStorage.setItem("teach_sub",JSON.stringify(subjectList))
			},
			error:function(){
				layer.msg("科目列表ajax异常")
			}
		})
	}else{
		sessionStorage.setItem("teach_sub","")
	}
	//注销按钮
	$("#logout").click(function(){
		sessionStorage.setItem("user","")
		sessionStorage.setItem("teach_class","")
		sessionStorage.setItem("teach_sub","")
		window.location.replace("login.html")
	})
})