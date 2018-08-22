<%-- 
    Document   : subjects
    Created on : Aug 10, 2018, 5:10:26 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        <form action="subject" method="POST">
            
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
            <input type="hidden" name="mem" value="${mem}"/>
           <input type="submit" value="Finish" />
        </form>
        
    </body>
</html>
