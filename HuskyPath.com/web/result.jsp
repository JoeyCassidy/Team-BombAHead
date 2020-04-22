<%@ page import="teamsoftware.test" %>
<%--
  Created by IntelliJ IDEA.
  User: caboo
  Date: 4/20/2020
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
    <h1>result.jsp</h1>
    <form action="result.jsp" method="post">

        <input type="text" name="num" id="num">
        <input type="submit" name="submit" id="submit" value="ok">
    </form>
    <%
        test test1 = new test();
        String in = request.getParameter("num");
        boolean submitted = request.getParameter("submit") != null;
        if(submitted){
            test1.input(in);
        }
    %>
</body>
</html>
