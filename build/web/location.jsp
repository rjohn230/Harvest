<%-- 
    Document   : location
    Created on : Aug 10, 2018, 1:55:44 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
        <script src="//geodata.solutions/includes/countrystatecity.js"></script>           
        
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Where Do You Live</h1>
        <form action="locale" method="post">
        <select name="country" class="countries" id="countryId">
        <option value="">Select Country</option>
        </select>
        <select name="state" class="states" id="stateId">
        <option value="">Select State</option>
        </select>
        <select name="city" class="cities" id="cityId">
        <option value="">Select City</option>
        </select>
            <input type="hidden" name="mem" value="${mem}"/>
            <input type="submit" value="Continue"/>
        </form>
    </body>
</html>
