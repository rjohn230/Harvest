<%-- 
    Document   : professional_hub
    Created on : Aug 11, 2018, 10:37:29 AM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/ambassador_hub.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <title>JSP Page</title>
    </head>
    <body>
        <div id="profile_section">
            <img id="profile" src="${sessionScope.picturepath}"
             width="100" height="100" alt="profile picture"/>
            <ul>
                <li>${sessionScope.professional.firstName}</li>
                <li>${sessionScope.professional.lastName}</li>
                <li>${sessionScope.professional.profession}</li>
                <li><a href="professional_edit.jsp">Edit Profile</a></li>
                <li><a href="profile">View Profile</a></li>
            </ul>
            
        
        </div>
      
        
        <div id="message_section">
            <h1>MESSAGES</h1>
            ${warning}
               <c:forEach var="message" items="${undirected}">
                    <p>
                        <a href="profess?todo=message&who=${message.ID}"> 
                        ${message.name} <img id="profile" src="${message.path}"
                        width="25" height="25" alt="profile picture"/>
                        </a>
                    <p>
                    </c:forEach>
        </div>
       
        </div>
          
          
         
          
          
          
      
      
    </body>
</html>

