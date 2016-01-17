<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Friends - sign in</title>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
	<ul id="floatBar">
		<li class="active">Sign in</li>
		<a href="/user/regist"><li>Sign up</li></a>
	</ul>
	<span id="title">Friends.</span>
	<div id="content">
	<form>
		<table>
			<tr>
				<td>
					<input placeholder="Account" type="text" maxlength="32" required/>
				</td>
			</tr>
			<tr>
				<td>
					<input placeholder="Password" type="password" maxlength="32" required/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Sign in">
				</td>
			</tr>
		</table>
	</form>
	</div>
	<div id="stateBar">${param.note}</div>
	<script>
		$(function(){
			if ($("#stateBar").html() == "") {
				$("#stateBar").hide();
			}
			$("#content input:eq(0)").hide().fadeIn(300);
			$("#content input:eq(1)").hide().delay(100).fadeIn(300);
			$("#content input:eq(2)").hide().delay(200).fadeIn(300);
		});
		$("input[type=submit]").click(function(){
			if ($("input[type=text]").val() == "") {
				$("#stateBar").html("Account Required.");
				$("#stateBar").show().delay(3000).fadeOut(200);
				$("input[type=text]").focus();
				return false;
			}
			if ($("input[type=password]").val() == "" || $("input[type=password]").val().length < 6) {
				$("#stateBar").html("Password Required.");
				$("#stateBar").show().delay(3000).fadeOut(200);
				$("input[type=password]").focus();
				return false;
			}
			$("form").attr("action", "/user/doLogin")
			$("form").attr("method", "post")
			$("form").append("<input type='hidden' name='username' value='" + $("input[type=text]").val() + "'/>");
			$("form").append("<input type='hidden' name='password' value='" + $("input[type=password]").val() + "'/>");
			$("form").submit();
		});
	</script>
</body>
</html>