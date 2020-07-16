//ajax查询用户，同时将用户放入sessionStorage中
	function getUserToSessionStorage(userNum,userPassword,ridd){
		$.ajax({
			type:"POST",
			url:"/user/queryUser",
			async:false,
			data:{
				num:userNum,
				password:userPassword,
				rid:ridd
			},
			dataType:"json",
			success:function(user){
				var userStr = JSON.stringify(user)
				sessionStorage.setItem("user",userStr)
				console.log(user+"已存入缓存")
			},
			error:function(){
				layer.msg("user放入缓存ajax异常")
			}
		})
	}
//输入验证码，键盘抬起事件
	function isTrue(code){
		var codeStr = sessionStorage.getItem("code")
		var truePhone = sessionStorage.getItem("phone")
		var phone = $("#mobile").val().trim()
		var password = $("#password").val().trim()
		var repassword = $("#repassword").val().trim()
		if(code == null || code == ""){
			$("#register").addClass("disabled")
			return false;
		}else if(phone == ""){
			layer.msg("请输入手机号")
			$("#mobile").focus()
			$("#register").addClass("disabled")
			$("#code").val("")
			return false;
		}else if(truePhone != phone) {
			layer.msg("手机号发生改变")
			$("#mobile").focus()
			$("#register").addClass("disabled")
			return false;
		}else if(password.length < 6){
			layer.msg('至少6位密码')
			$("#register").addClass("disabled")
			return false;
		}else if (repassword != password) {
			layer.msg('重复密码输入有误')
			$("#repassword").val("")
			$("#repassword").focus()
			$("#register").addClass("disabled")
			return false;
		}else if(code != codeStr){
			$("#register").addClass("disabled")
			return false
		}else{
			$("#register").removeClass("disabled")
		}
	}
$(function(){
	//用户首次到该html，缓存中没有status，所以初始化status=0的缓存
	//同时，当用户刷新html，若没存在，则自定义。
	var userStr = sessionStorage.getItem("user")
	if(userStr == null || userStr == ""){
		var user={
			"status":0
		}
		sessionStorage.setItem("user",JSON.stringify(user))
	}
	// else {
	// 	var user = JSON.parse(userStr)
	// 	for (var i = 0; i < user.status; i++) {
	// 		console.log("点击")
	// 		$("#nnn").click()
	// 	}
	// }
	//发送验证码验证
	$("#send").on("click",function(){
		if($("#send").hasClass("disabled")){
			return;
		}
		var mobile = $("#mobile").val().trim()
		var password = $("#password").val().trim()
		var repassword = $("#repassword").val().trim()
		if (!(/^[1][3,4,5,7,8,9][0-9]{9}$/.test(mobile))) {
			layer.msg('手机号格式错误')
			$("#mobile").focus();
			return false;
		}else if (password.length < 6) {
			layer.msg('至少6位密码')
			$("#password").focus();
			return false;
		}else if (repassword != password) {
			layer.msg('重复密码输入有误')
			$("#repassword").val("")
			$("#repassword").focus()
			return false;
		}else {
			//发送验证码
			$.ajax({
			    type:"POST",
			        url:"/user/isRegister",
			        async:true,
			        data:{
			            phone:mobile,
			        },
			        success:function (result) {
			            console.log(result);
			            if(result==0){
			                layer.msg('该手机已注册')
			                return;
			            }
						//点击一瞬间，变为不可用
						$("#send").addClass("disabled")
						//实现倒计时60s
						var time = 60
						var timeOut = setInterval(function(){
							if(time-- != 0){
								$("#send").val("重新发送("+time+")")
							}else {
								$("#send").removeClass("disabled")
								$("#send").val("发送验证码")
								time = 60
								clearInterval(timeOut)
							}
						},1000)
						//发送验证码
			            $.ajax({
			                type:"POST",
			                url:"/user/yzm",
			                async:true,
			                data:{
			                    phone:mobile,
			                },
			                success:function (result) {
			                    console.log(result);
								layer.msg('验证码已发送')
								sessionStorage.setItem("phone",mobile);
			                    sessionStorage.setItem("code",result);
								var coded = setInterval(function(){
									sessionStorage.setItem("code","");
									clearInterval(coded)
								},60000)
			                },
			                error:function () {
			                    layer.msg('发送失败')
			                }
			            })
			        },
			        error:function () {
			            layer.msg('数据异常')
			        }
			    })
			//发送验证码
		}
	})
	
	
	//注册按钮可用，点击注册事件
	$("#register").click(function(){
		if($(this).hasClass("disabled")){
			return;
		}
		var registerData = {
			phone:$("#mobile").val(),
			password:$("#password").val(),
			ids:[2],
			status:1
		}
		//后台添加用户
		$.ajax({
			type:"POST",
			url:"/user/register",
			data:registerData,
			dataType:"json",
			traditional: true,
			success:function(result){
				if(result == 1){
					window.confirm("【家长注册】成功！点击【下一步】进入“学生绑定”")
					//调用自定义方法，将刚刚添加的家长，查询出来，同时放入缓存中
					var num = $("#mobile").val()
					var password = $("#password").val()
					console.log("num:"+num+",password:"+password)
					getUserToSessionStorage(num,password,0)
					//清空code
					sessionStorage.setItem("code","")
					//注册成功，将输入框中内容清空
					window.location.reload()
					//设置步骤1的内容为"已经注册"内容
					//$("#step-1").html("")
					//修改步骤1中内容
					//$("#step-1").html('<span>家长注册（已完成）</span>')
					//自动点击Next按钮
					//$('.sw-btn-next').click()
				}else{
					layer.msg("zcsb...")
				}
			},
			error:function(){
				layer.msg("请求异常")
			}
		})
	})
	//步骤2->绑定按钮点击事件
	$("#bind").click(function(){
		//一个布尔值，及时退出整个方法
		var flag = true;
		//获得家长num，解析sessionStorage
		var parentStr = sessionStorage.getItem("user")
		var parent = JSON.parse(parentStr)
		//
		var numStu = $("#stuNum").val().trim()
		var numPassword = $("#stuPassword").val().trim()
		if(numStu == ""){
			layer.msg("账号为空")
			$("#stuNum").focus()
			return false;
		}else if(numPassword == ""){
			layer.msg("密码为空")
			$("#stuPassword").focus()
			return false;
		}else{
			//判断该家长是否与该学生已经进行了绑定
			$.ajax({
				type:"POST",
				url:"/user/isBind",
				async:false,
				data:{
					sNum:numStu,
					pNum:parent.num
				},
				dataType:"json",
				success:function(num){
					if(num != 0){
						flag = false;
						layer.msg("请勿重复绑定学生")
					}
				},
				error:function(){
					layer.msg("查询重复绑定数ajax异常")
				}
			})
			//重复绑定直接退出点击事件
			if(!flag){
				return;
			}
			//
			//查询学生姓名，询问是否坚持绑定
			$.ajax({
				type:"POST",
				url:"/user/queryUser",
				data:{
					num:numStu,
					password:numPassword,
					rid:1
				},
				dataType:"json",
				success:function(user){
					if(user != null && user != ""){
						if(user.name != null && user.name != ""){
							if(window.confirm("您确定与学生【"+user.name+"】绑定么？")){
								//当家长确定绑定，执行绑定
								$.ajax({
									type:"POST",
									url:"/user/bindStudent",
									data:{
										sNum:numStu,
										pNum:parent.num,
										status:2
									},
									dataType:"json",
									success:function(bindStatus){
										if(bindStatus == 1 ){
											layer.msg("【学生绑定】成功！点击【下一步】进入“完善信息”")
											//调用自定义方法，将刚刚添加的家长，查询出来，同时放入缓存中
											getUserToSessionStorage(parent.num,parent.password,0)
											$("#stuNum").val("")
											$("#stuPassword").val("")
										}else{
											layer.msg("绑定ajax请求异常")
										}
									}
								})
							}else{
								layer.msg("用户取消绑定!!")
							}
						}
					}else {
						layer.msg("学生账号密码错误")
						$("#stuNum").focus()
					}
				},
				error:function(){
					layer.msg("查询学生ajax异常")
				}
			})
		}
	})
	//步骤3->完善信息按钮点击事件
	$("#perfect").click(function(){
		//获得家长num，解析sessionStorage
		var parentStr = sessionStorage.getItem("user")
		var parent = JSON.parse(parentStr)
		//
		var pName = $("#myName").val().trim()
		var pSex = $("input[name='sex']:checked").val()
		var pAge = $("#age").val().trim()
		if(pName == ""){
			layer.msg("请输入姓名")
			$("#myName").focus()
			return false;
		}else if(pAge == null || pAge <= 0){
			layer.msg("请输入正确年龄")
			$("#age").focus()
			return false;
		}else{
			$.ajax({
				type:"POST",
				url:"/user/improveInformation",
				data:{
					num:parent.num,
					password:parent.password,
					name:pName,
					sex:pSex,
					age:pAge,
					status:3
				},
				dataType:"json",
				success:function(result){
					if(result == 1){
						window.confirm("【完善信息】成功！点击任意按钮，跳转至登录界面")
						location.replace("login.html")
					}else{
						layer.msg("完善信息异常错误")
					}
				},
				error:function(){
					layer.msg("完善信息ajax请求异常")
				}
			})
		}
	})
})