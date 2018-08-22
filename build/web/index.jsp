<%-- 
    Document   : index
    Created on : Jul 28, 2018, 11:45:28 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        
            <form action="login" method="POST">
                <label>
                    <input type="text" name="email" placeholder="Enter Email" value="${cookie.userEmail.value}" />
                </label><br>
                <label>
                    <input type="password" name="password" placeholder="Enter Password" value="${cookie.userPass.value}" />
                </label><br>
                <input type="submit" value="Login"/>
            </form><br>
     
        
        <a href="signup.jsp">Sign Up</a>
      
    </body>
</html>
