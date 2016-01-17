<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Timer</title>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    <script type="text/javascript">
        function updateTime(){
            var now = new Date();
            var min = now.getMinutes();
            var sec = now.getSeconds();

            var hour =(now.getHours()%12)+min/60;
            var minangle = min*6;
            var hourangle = hour*30;
            var secondangle = sec*6;

            var minhand = document.getElementById("minutehand");
            var hourhand = document.getElementById("hourhand");
            var secondhand = document.getElementById("secondhand");

            minhand.setAttribute("transform","rotate("+minangle+",50,50)");
            hourhand.setAttribute("transform","rotate("+hourangle+",50,50)");
            secondhand.setAttribute("transform","rotate("+secondangle+",50,50)");
            setTimeout(updateTime,1000);
        }
    </script>
    <style type="text/css">
        body{background-color: #222;}
        #clock{stroke:black;stroke-linecap:round;fill:#eef;background-color: #222;border-radius: 50px;position: absolute;top: 50%;left: 50%;margin-top: -250px;margin-left: -250px;}
        #ticks{stroke-width:2;}
        #hourhand{stroke-width:3px;}
        #minutehand{stroke-width:2px;}
        #secondhand{stroke-width:1px;stroke:rgb(193,63,31);}
        #numbers{font-family: sans-serif;font-size: 7pt;font-weight: bold;text-anchor:middle;stroke:none;fill:black;}
    </style>
</head>
<body onload="updateTime()">
<svg id="clock" viewBox="0 0 100 100" width="500" height="500">
    <circle id="face" cx="50" cy="50" r="45"/>
    <g id="numbers">
        <text x="50" y="17">12</text>
        <text x="69" y="22">1</text>
        <text x="82" y="34">2</text>
        <text x="88" y="53">3</text>
        <text x="82" y="73">4</text>
        <text x="69" y="85">5</text>
        <text x="50" y="90">6</text>
        <text x="31" y="85">7</text>
        <text x="18" y="73">8</text>
        <text x="12" y="53">9</text>
        <text x="18" y="35">10</text>
        <text x="31" y="22">11</text>
    </g>
    <g id="hands">
        <line id="hourhand" x1="50" y1="50" x2="50" y2="26"/>
        <line id="minutehand" x1="50" y1="50" x2="50" y2="17"/>
        <circle cx='50' cy='50' r='1.8' fill='rgb(193,63,31)' stroke-width='1'/>
        <line id="secondhand" x1="50" y1="56" x2="50" y2="12"/>
    </g>
</svg>
</body>
</html>