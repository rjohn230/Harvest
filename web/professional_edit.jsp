<%-- 
    Document   : professional_edit
    Created on : Aug 20, 2018, 11:29:57 AM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/professional_edit.css">
        <script src="scripts/professional_edit.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> 
        <script src="//geodata.solutions/includes/countrystatecity.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div id="revealButtons">
            <input value="Contact Info"   onclick="javascript: reveal('Contact')" type="button" />
            <input value="About" onclick="javascript: reveal('About')"type="button" />
            <input value="Profile Picture" onclick="javascript: reveal('Picture')"type="button" />
            <input value="Subjects" onclick="javascript: reveal('Subjects')"type="button" />
            <input value="Location" onclick="javascript: reveal('Location')"type="button" />  
            <input value="Password" onclick="javascript: reveal('Password')"type="button" />  
        </div>
        ${message}
        <div id="edit_forms">
            <form action="edit?todo=contact" method="post" id="Contact">
             <input name="email" type="email" placeholder="Enter Email" required/><br>
             <input name="phone" type="tel" placeholder="Enter Phone Number"required/><br>
             <input  name="first_name" type="text" placeholder="Enter First Name" required/><br>
             <input name="last_name" type="text" placeholder="Eneter LastName"required/><br>
             <input value="Go Back" onclick="javascript: reverse()"type="button" />
             <input name="todo" type="submit" value="Edit"/>
            </form>
            
            <form  action="edit?todo=about" method="post" id="About">
            <input name="profession" type="text" placeholder="Enter Job Desciption"required/><br>
            <input name="education" type="text" placeholder=" Enter Education"required/><br>
            <textarea  name="about"rows="4" cols="50" placeholder='Talk About Yourself' required></textarea><br>
            <input value="Go Back" onclick="javascript: reverse()"type="button" />
             <input name="todo" type="submit" value="Edit"/>
            </form>
            
           <form id="Picture" action = "edit?todo=picture" method = "post" enctype = "multipart/form-data">
             <input accept="image/*" type = "file" name = "file" size = "50" required /><br />
             <input value="Go Back" onclick="javascript: reverse()"type="button" />
            <input type = "submit" value = "Upload File" />
           </form>
            
            <form action="edit?todo=subjects" id="Subjects" method="post">
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
            <input value="Go Back" onclick="javascript: reverse()"type="button" />
           <input type="submit" value="Edit" />
            </form>
            
            <form action="edit?todo=location" id="Location" method="post" >
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
                <input value="Go Back" onclick="javascript: reverse()"type="button" />
                <input type="submit" value="Continue"/>
            </form>
   
            
            
            <form action = "edit?todo=password" method = "post" id="Password">
              <input name="old_password" type="password" placeholder="Enter Old Password"required/><br>
              <input name="password" type="password" placeholder="Enter New Password"required/><br>
              <input name="repassword" type="password" placeholder="Re Enter Password" required/><br>
              <input value="Go Back" onclick="javascript: reverse()"type="button" />
                <input  type="submit" value="Edit"/>
            </form>
        </div>
        
        <a href="message?todo=Return+To+Hub">Return to Hub</a>
    </body>
</html>
