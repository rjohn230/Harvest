<%-- 
    Document   : directed_message
    Created on : Aug 18, 2018, 1:49:52 PM
    Author     : rrjoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Model.MessageDB" %>
<%@ page import="Model.Undirected" %>
<%@ page import="Model.Directed" %>
<%@ page import="Model.Member" %>
<%@ page import="Controllers.MessageServlet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>


<%
    
    Member mem = (Member) session.getAttribute("member");
     List<Undirected> und = MessageDB.getUndirected(mem.getId());
     
     for(Undirected um : und)
        {
            String path = "data/"+um.getPicturePath()+".jpg";
            um.setPath(path);
        }
     //// create directed list now
     
     long other = (Long) session.getAttribute("talk");
     
     
     
      
            
            List<Directed> dir = MessageDB.getDirected(mem.getId(),other);
            
            for(Directed d : dir)
            {
                if(d.getId()==mem.getId())
                {
                  
                    d.setProfile("data/"+mem.getPicture()+".jpg");
                }
                else
                {
                    for(Undirected un : und)
                    {
                        if(d.getId()==un.getID())
                        {
                            d.setProfile(un.getPath());
                        }
                    }
                    
                    
                }
                
                //push out data
                
                out.println("<p>"+ d.getMessage() + " @ "+ d.getDate() +  " from <img id='profile' src='" +d.getProfile()+ 
                        "'width='25' height='25' alt='profile picture'/></p>");
            }
     
   
%>


   
                     
                     
                     
