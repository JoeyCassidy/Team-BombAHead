<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joeyb
  Date: 3/16/2020
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <ul>
        <%
            List<String> emails = (List<String>) request.getAttribute("emails");

            if (emails != null && !emails.isEmpty()){
                for (String s : emails){
                    out.print("<li>"+ s + "</li>");

                }

            } else{
                out.println("<p>There are no users yet!</p>");
            }
        %>
    </ul>
    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
