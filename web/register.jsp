<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Friends - sign up</title>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
	<ul id="floatBar">
		<a href="/user/login"><li>Sign in</li></a>
		<li class="active">Sign up</li>
	</ul>
	<span id="title">Friends.</span>
	<div id="content">
	<form>
		<table>
			<tr>
				<td>
					<input placeholder="Your Nickname" type="text" maxlength="32" required>
				</td>
			</tr>
			<tr>
				<td>
					<input placeholder="Password" type="password" maxlength="32" required>
				</td>
			</tr>
			<tr>
				<td>
					<input placeholder="Repeat Password" type="password" maxlength="32" required>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Sign up">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<div id="stateBar">Here I know a lot of friends!</div>
	<script>
		$(function(){
			if ($("#stateBar").html() == "") {
				$("#stateBar").hide();
			}
			$("#content input:eq(0)").hide().fadeIn(300);
			$("#content input:eq(1)").hide().delay(100).fadeIn(300);
			$("#content input:eq(2)").hide().delay(200).fadeIn(300);
			$("#content input:eq(3)").hide().delay(300).fadeIn(300);
		});
		$("input[type=submit]").click(function(){
			var username = $("input:eq(0)").val();
			var password_0 = $("input:eq(1)").val();
			var password_1 = $("input:eq(2)").val();
			if (username == "") {
				$("#stateBar").html("Name is required.").fadeIn(200).delay(3000).fadeOut(100);
				$("input:eq(0)").focus();
				return false;
			}
			if (password_0 == "") {
				$("#stateBar").html("Password is required.").fadeIn(200).delay(3000).fadeOut(100);
				$("input:eq(1)").focus();
				return false;
			}
			if (password_0.length < 6) {
				$("#stateBar").html("Password is too short, at least >6.").fadeIn(200).delay(3000).fadeOut(100);
				$("input:eq(1)").focus();
				return false;
			}
			if (password_0 != password_1) {
				$("#stateBar").html("Password is different.").fadeIn(200).delay(3000).fadeOut(100);
				$("input:eq(2)").val("").focus();
				return false;
			}
			$.ajax({
				url: "/user/doRegist",
				method: "post",
				data: {
					username: username,
					password: password_0
				},
				success: function(data){
					if (data != "success") {
						$("input:eq(0)").focus();
						$("#stateBar").html(data).fadeIn(200).delay(3000).fadeOut(100);
					} else {
						window.location.href="/user/login?note=" + encodeURI("Lets get Start!");
					}
				}
			});
			return false;
		});
	</script>
</body>
</html>