/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Ambassador;
import Model.AmbassadorDB;
import Model.Member;
import Model.MemberDB;
import Model.MessageDB;
import Model.Professional;
import Model.ProfessionalDB;
import Model.Undirected;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        
        if(ProfessionalDB.ProfessionalExist(email))
        {
           Professional login= ProfessionalDB.findProfessional(email);
           
           if(MemberDB.MemeberPasswordMataches(password, login.getMember()))
           {
               HttpSession session = request.getSession();
               
               session.setMaxInactiveInterval(-1);
               
               session.setAttribute("professional", login);
               session.setAttribute("member", login.getMember());
               session.setAttribute("picturepath", getPicturePath(login.getMember().getPicture()));
               
               setupMessage(request,response);
               
               getServletContext().getRequestDispatcher("/professional_hub.jsp").forward(request, response);
           }
           else
           {
              request.setAttribute("message", "Username or Password Doesn't Match");
            
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
             
           }
           
           
           
        }
        else if(AmbassadorDB.AmbassadorExist(email))
        {
            Ambassador login = AmbassadorDB.findAmbassador(email);
            
            if(MemberDB.MemeberPasswordMataches(password, login.getMember()))
           {
               HttpSession session = request.getSession();
               
               session.setMaxInactiveInterval(-1);
               
               session.setAttribute("ambassador", login);
               session.setAttribute("member", login.getMember());
               session.setAttribute("picturepath", getPicturePath(login.getMember().getPicture()));
               
               setupMessage(request,response);
               
               
               getServletContext().getRequestDispatcher("/ambassador_hub.jsp").forward(request, response);
           }
           else
           {
              request.setAttribute("message", "Username or Password Doesn't Match");
            
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            
           }
        }
        else
        {
            request.setAttribute("message", "User Does Not Exist");
            
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            
        }
        
        
        
        
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

    public  String getPicturePath(long member_pict)
    {
        String filepath = getServletContext().getInitParameter("file-upload");
        filepath= filepath.substring(filepath.lastIndexOf("d"));
        return filepath +member_pict+".jpg";
    }
    
    public void setupMessage(HttpServletRequest request, HttpServletResponse response)
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
        
    }
}
