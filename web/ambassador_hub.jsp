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
                <li>${sessionScope.ambassador.school}</li>
                <li>${sessionScope.ambassador.position}</li>
                <li>${sessionScope.ambassador.name}</li>
                <li><a href="ambassador_edit.jsp">Edit Profile</a></li>
            </ul>
            
        
        </div>
       
        
        <div id="search_section">
            <form class="example" action="ambass">
                <input type="hidden" name="todo" value="search"/>
            <input  name ="search" type="text" placeholder="Search by profession city state or country..." >
            <button type="submit"><i class="fa fa-search"></i></button>
            
            <label>Computers and Technology
            <input type="checkbox" name="CS">
            </label><br>
            
            
            <label>Medical
            <input type="checkbox" name="MED">
            </label><br>
            
            
            <label>Science
            <input type="checkbox" name="SCI">
            </label><br>
            
            
            <label>Engineer
            <input type="checkbox" name="EGR">
            </label><br>
            
            
            <label>Business
            <input type="checkbox" name="BUS">
            </label><br>
            
            
            <label>Law
            <input type="checkbox" name="LAW">
            </label><br>
            
            </form>
            
            <table id="results" >
                <thead>
                    <tr>
                        <th>
                            Results
                        </th>
                    </tr>
                </thead>
                
                <tbody>
                    
                <c:forEach var="prof" items="${set}">
                <tr>
                    <td>
                        ${prof.firstName} ${prof.lastName} ${prof.profession} 
                        <a href="ambass?todo=message&who=${prof.member.id}">Message</a>
                        <a href="profile?view=${prof.member.id}">View Profile</a>
                    </td>
                </tr>
                </c:forEach>
                <h1>${message}<h1>
                </tbody>
            </table>

            
        </div>
        
        <div id="message_section">
            <h1>MESSAGES</h1>
            ${warning}
               <c:forEach var="message" items="${undirected}">
                    <p>
                        <a href="ambass?todo=message&who=${message.ID}"> 
                        ${message.name} <img id="profile" src="${message.path}"
                        width="25" height="25" alt="profile picture"/>
                        </a>
                    <p>
                    </c:forEach>
        </div>
       
        </div>
          
          
         
          
          
          
      
      
    </body>
</html>
