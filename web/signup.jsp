<%-- 
    Document   : signup
    Created on : Jul 29, 2018, 12:44:17 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link rel="stylesheet" type="text/css" href="styles/signup.css">
        <script src="scripts/signup.js"></script>
    </head>
    <body>
        ${message}
        <div id="revealButtons">
            <input value="Professional sign up"   onclick="javascript: revealForms('pro')" type="button" />
            <input value="Ambassaddor sign up" onclick="javascript: revealForms('ambass')"type="button" />
        </div>
        
        <div>
            <form id="proForm" action="profess" method="post">
              <input name="email" type="email" placeholder="Enter Email" required/><br>
             <input name="password" type="password" placeholder="Enter Password"required/><br>
              <input name="repassword" type="password" placeholder="Re Enter Password" required/><br>
              <input  name="first_name" type="text" placeholder="Enter First Name" required/><br>
               <input name="last_name" type="text" placeholder="Eneter LastName"required/><br>
               <input name="phone" type="tel" placeholder="Enter Phone Number"required/><br>
                <input name="profession" type="text" placeholder="Enter Job Desciption"required/><br>
                <input name="education" type="text" placeholder=" Enter Education"required/><br>
                <textarea  name="about"rows="4" cols="50" placeholder='Talk About Yourself'></textarea><br>
                <input value="Go Back" onclick="javascript: reverse()"type="button" />
                <input name="todo" type="submit" value="sign_up"/>
                
            </form>
        </div>
        
        
        <div>
            <form id="ambassForm" action="ambass" method="post"><br>
             <input name="mail" type="email" placeholder="Enter Email" required/><br>
             <input name="password" type="password" placeholder="Enter Password" required/><br>
              <input name="repassword" type="password" placeholder="Re Type Password" required/><br>
              <input  name="name" type="text" placeholder="Enter Name" required/><br>
               <input name="school" type="text" placeholder="Enter School Name" required/><br>
                <input name="phone" type="tel" placeholder="Enter Phone Number"required/><br>
                <input name="position" type="text" placeholder="Enter Your Position"required/><br>
                <input value="Go Back" onclick="javascript: reverse()"type="button" />
            <input name="todo" type="submit" value="sign_up"/>
            </form>
        </div>
        
        
    </body>
</html>
