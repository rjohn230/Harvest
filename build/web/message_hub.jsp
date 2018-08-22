<%-- 
    Document   : message_hub
    Created on : Aug 13, 2018, 11:05:29 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="scripts/update_message.js"></script>
        <title>JSP Page</title>
    </head>
    <body id="show">
        
        <div id="messages">
            ${warning}
               <c:forEach var="message" items="${undirected}">
                    <p>
                        <a href="message?who=${message.ID}"> 
                        ${message.name} <img id="profile" src="${message.path}"
                        width="25" height="25" alt="profile picture"/>
                        </a>
                    </p>
                    </c:forEach>
                        <a hidden id="update" >Refresh</a>
                        Message ${director}                 
        </div>
            
            
                <div id="directed_message"> 
                   <%@include file="directed_message.jsp" %>
                </div>
                
                     
                    <div id="message_constuctor">
                <form action="message">
                    <input id="who" type="hidden" name="who" value="${who}">
                    <textarea id="about"  name="about"rows="4" cols="50" placeholder='Send Message' value=""></textarea>
                    <input name="todo" type="submit" value="Send">
                     <input name="todo" type="submit" value="Return To Hub">
                </form>
                    </div>
                
                
                
    </body>
</html>
