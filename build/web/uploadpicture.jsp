<%-- 
    Document   : uploadpicture
    Created on : Aug 10, 2018, 10:41:45 AM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Picture Uploading Form</title>
   </head>
   
   <body>
      <h3>Picture Upload:</h3>
      Select a file to upload: <br />
      ${message}<br>
      <form action = "UploadServlet?pict=${pict}&mem=${mem}" method = "post" enctype = "multipart/form-data">
         <input accept="image/*" type = "file" name = "file" size = "50" required />
         <br />
         <input type = "submit" value = "Upload File" />
         
      </form>
      
       
          
   </body>
</html>

