<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Friends - warning</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <style>
        body {
            text-align: center;
            zoom: 2;
        }

        #title {
            font-family: "Microsoft JhengHei", sans-serif;
            display: inline-block;
            color: #666;
            font-size: 100px;
            text-align: center;
            cursor: default;
            line-height: 100px;
        }

        #describe {
            font-size: 18px;
            color: #AAA;
            text-align: center;
            display: block;
            cursor: default;
            line-height: 60px;
        }
    </style>
</head>
<body>
<span id="title">Oops!</span>
    <span id="describe">
        <c:out value="${param.note}" default="Page not found."/>
    </span>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script>
    $(function () {
        $("#title").hide().fadeIn(300);
        $("#describe").hide().delay(1000).show(300);
        $("body").delay(500).animate({"zoom": "1"}, 300);
    });
</script>
</body>
</html>
