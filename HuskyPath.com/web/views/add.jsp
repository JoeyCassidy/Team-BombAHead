<%--
  Created by IntelliJ IDEA.
  User: joeyb
  Date: 3/16/2020
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New User</title>
</head>
<body>
    <div>
        <h1>
            Add users!
        </h1>
    </div>
        <%
            if (request.getAttribute("email") != null) {
                out.println("<p>User '" + request.getAttribute("email") + "' added!</p>");
            }
        %>
    <div>
        <form method="post">
            <label>Email:
                <input type="text" name="email"><br />
            </label>

            <label>First Name:
                <input type="text" name="firstName"><br />
            </label>

            <label>Last Name:
                <input type="text" name="lastName"><br />
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
