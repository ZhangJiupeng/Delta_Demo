<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Friends - ${sessionScope.user.username}</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<ul id="floatBar">
    <li class="active">Info Center</li>
    <li id="addFriend">Add Friend</li>
    <li id="delFriend">Delete Friend</li>
    <a href="${pageContext.request.contextPath}/user/logout">
        <li>Logout</li>
    </a>
    <input type="text" id="searchBar" placeholder="Search For..."/>
</ul>
<div id="friendFrame">
		<span id="theme">
			<input type="text" title="Name" id="inputName" maxlength="32" value=""/>
		</span>
		<span id="textArea">
			<select id="chooser" title="">
                <option>Male</option>
                <option>Female</option>
            </select>
			<input type="text" id="inputAge" placeholder="Age" maxlength="3">
			<input type="text" id="inputQq" placeholder="QQ" maxlength="11">
			<input type="text" id="inputTel" placeholder="Tel" maxlength="32">
			<input type="text" id="inputEmail" placeholder="Email" maxlength="255">
			<input type="text" id="inputAddress" placeholder="Address" maxlength="255">
		</span>

    <div id="save">Save</div>
</div>
<div id="content">
    <c:choose>
        <c:when test="${f:length(friends) > 0}">
            <div id="empty" style="display:none">Empty Set.</div>
        </c:when>
        <c:otherwise>
            <div id="empty" style="display:block">Empty Set.</div>
        </c:otherwise>
    </c:choose>
    <table id="info" cellpadding="0" cellspacing="0">
        <tr id="thead">
            <th><input id="checkAll" title="" type="checkbox" style="opacity: 0.2"></th>
            <th>Name</th>
            <th>Sex</th>
            <th>Age</th>
            <th>QQ</th>
            <th>Tel</th>
            <th>Email</th>
            <th>Address</th>
            <th>Options</th>
        </tr>
        <c:forEach var="f" items="${friends}">
            <tr fid="${f.id}">
                <td><input type="checkbox" title=""></td>
                <td name="infoName">${f.name}</td>
                <td name="infoSex">
                    <c:choose>
                        <c:when test="${f.sex}">Female</c:when>
                        <c:otherwise>Male</c:otherwise>
                    </c:choose>
                </td>
                <td name="infoAge">${f.age}</td>
                <td name="infoQq">${f.qq}</td>
                <td name="infoTel">${f.tel}</td>
                <td name="infoEmail">${f.email}</td>
                <td name="infoAddress">${f.address}</td>
                <td><a class="modify" fid="${f.id}">Modify</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/><br/>
</div>
<div id="stateBar">Hi! ${sessionScope.user.username}</div>
<div id="mask"></div>
</body>
</html>