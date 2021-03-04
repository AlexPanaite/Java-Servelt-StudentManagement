<%@ page import="project.Login.Login" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body, html {
            height: 100%;
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav-right {
            float:right;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }


        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }

        .bg-image {
            /* The image used */
            background-image: url("bg.png");

            /* Add the blur effect */
            filter: blur(3px);
            -webkit-filter: blur(3px);

            /* Full height */
            height: 100%;

            /* Center and scale the image nicely */
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        /* Position text in the middle of the page/image */
        .bg-text {
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0, 0.4); /* Black w/opacity/see-through */
            color: white;
            font-weight: bold;
            border: 3px solid #f1f1f1;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 2;
            width: 80%;
            padding: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="topnav">
    <%
        if(session.getAttribute("login")==null)
        {
            response.sendRedirect("login.jsp");
        }
    %>
    <a class="active" href="index.jsp">Home</a>
    <a href="/student">Studenti</a>
    <a href="/catalog">Catalog</a>
    <div id="mat">
        <a href="/matricol">Inmatriculare</a>
    </div>
    <div id="usr">
        <a href="/userpage">Users</a>
    </div>
    <div id="log">
        <a href="/log">Logs</a>
    </div>
    <div class="topnav-right">
        <a href="reset.jsp"  >Reset Your Password</a>
        <a href="logout.jsp" >Log Out</a>
    </div>
</div>

<c:if test="${'student' == sessionScope.login.role}">
    <style type="text/css">
        #mat{
            display:none;
        }
        #usr{
            display:none;
        }
        #log{
            display:none;
        }
    </style>
</c:if>

<c:if test="${'profesor' == sessionScope.login.role}">
    <style type="text/css">
        #usr{
            display:none;
        }
        #log{
            display:none;
        }
    </style>
</c:if>

<c:if test="${'secretar' == sessionScope.login.role}">
    <style type="text/css">
        #usr{
            display:none;
        }
        #log{
            display:none;
        }
    </style>
</c:if>

<div class="bg-image"></div>

<div class="bg-text">
    <h2><c:out value="${'Welcome '}${sessionScope.login.username}${' !'}" escapeXml="false"/></h2>
    <p><c:out value="${'Drepturi utilizator: '}${sessionScope.login.role}" escapeXml="false"/></p>

</div>

</body>
