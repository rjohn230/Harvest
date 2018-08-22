/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Ambassador;
import Model.AmbassadorDB;
import Model.Directed;
import Model.Member;
import Model.MemberDB;
import Model.Message;
import Model.MessageDB;
import Model.Professional;
import Model.ProfessionalDB;
import Model.Undirected;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rrjoh
 */
@WebServlet(name = "MessageServlet", urlPatterns = {"/message"})
public class MessageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MessageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       
        
        HttpSession session = request.getSession();
        
        Member mem = (Member) session.getAttribute("member");
        
         
         String todo= request.getParameter("todo");
        
        if(todo!=null && todo.equals("Send"))
        {
            
            long senderid = mem.getId();
            
            long receiver = Long.parseLong(request.getParameter("who"));
            
            String message = request.getParameter("about");
            
            Message new_message = new Message();
            
            new_message.setDate();
            new_message.setMessageID();
            new_message.setSender(senderid);
            new_message.setReciever(receiver);
            new_message.setMessage(message);
            
            MessageDB.insertMessage(new_message);
            
        }
        
        else if(todo!=null && todo.equals("Return To Hub"))
        {
            returnToHub(request,response);
            return;
        }
        
        
        List<Undirected> und = new ArrayList<>();
        if(MessageDB.getUndirected(mem.getId())!=null)
        {
            und=MessageDB.getUndirected(mem.getId());
        }
        
        for(Undirected um : und)
        {
            String path = getPicturePath(um.getPicturePath());
            um.setPath(path);
        }
        
        
        if(!und.isEmpty())
        {
            request.setAttribute("undirected", und);
            long other = Long.parseLong(request.getParameter("who"));
            request.setAttribute("who", request.getParameter("who"));
            session.setAttribute("talk", other); 
        }
        else
        {
            request.setAttribute("warning", "No Message Avaiable");
        }
        
        
        
         Member talking_to = MemberDB.findMember(Long.parseLong(request.getParameter("who")));
         
         if(talking_to.getRole().equalsIgnoreCase("ambassador"))
         {
             Ambassador talking = AmbassadorDB.getAmbassador(talking_to);
             
             request.setAttribute("director", talking.getSchool()+
                     " "+talking.getPosition() 
                     +" "+talking.getName());
         }
         else
         {
             Professional talking = ProfessionalDB.getProfession(talking_to);
             
             request.setAttribute("director", talking.getFirstName()+" "
                     +talking.getLastName() 
                     +" THE "+talking.getProfession());
         }
            getServletContext().getRequestDispatcher("/message_hub.jsp").forward(request, response);   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public   String getPicturePath(long member_pict)
    {
        String filepath = getServletContext().getInitParameter("file-upload");
        filepath= filepath.substring(filepath.lastIndexOf("d"));
        return filepath +member_pict+".jpg";
    }
    
    
    public void returnToHub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        Member mem = (Member) session.getAttribute("member");
        
        List<Undirected> und = MessageDB.getUndirected(mem.getId());
        
        for(Undirected um : und)
        {
            String path = getPicturePath(um.getPicturePath());
            um.setPath(path);
        }
        
        
        if(!und.isEmpty())
        {
            request.setAttribute("undirected", und);
            
        }
        else
        {
            request.setAttribute("warning", "No Message Avaiable");
        }
        
        if(mem.getRole().equalsIgnoreCase("ambassador"))
        {getServletContext().getRequestDispatcher("/ambassador_hub.jsp").forward(request, response);}
        else {
            getServletContext().getRequestDispatcher("/professional_hub.jsp").forward(request, response);
        }
        
        
    }
    
}
