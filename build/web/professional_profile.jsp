<%-- 
    Document   : professional_profile
    Created on : Aug 20, 2018, 11:31:13 AM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <div id="profile_info">
            <img id="profile" src="data/${found.member.picture}.jpg"
             width="100" height="100" alt="profile picture"/>
            <p> First Name: ${found.professional.firstName} <br>
                Last Name: ${found.professional.lastName} <br>
                Profession: ${found.professional.profession} <br>
                Education: ${found.professional.education} <br>
                About: ${found.professional.about} <br>
                Email: ${found.professional.email} <br>
                Phone: ${found.professional.phone} <br>
            </p>
            
            <p> 
                City: ${found.location.city}<br>
                State: ${found.location.state}<br>
                Country: ${found.location.country}<br>
            </p>
            <p> 
                <c:forEach var="subject" items="${found.list}">
                    ${subject}<br>
                    </c:forEach>
            </p>
            <c:if test="${vistor!=null}">
            <a href="ambass?todo=message&who=${found.member.id}">Send Message</a><br>
            </c:if>
            <a href="message?todo=Return+To+Hub">Return to Hub</a>
        </div>
        
        
    </body>
</html>
