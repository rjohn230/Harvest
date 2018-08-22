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
import Model.Message;
import Model.MessageDB;
import Model.Professional;
import Model.Undirected;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author rrjoh
 */
@WebServlet(name = "AmbassServlet", urlPatterns = {"/ambass"})
public class AmbassServlet extends HttpServlet {

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
            out.println("<title>Servlet AmbassServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AmbassServlet at " + request.getContextPath() + "</h1>");
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
        
        String todo = request.getParameter("todo");
        
        switch(todo)
        {
            case "sign_up":
            {
                signUp(request,response);
                break;
            }
            case "search":
                    {
                        search(request,response);
                        break;
                    }
            case "message":
            {
                message(request,response);
                break;
            }
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
    
    public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String password = request.getParameter("password");
        String post = request.getParameter("position");
        String phone = request.getParameter("phone");
        String re_pass = request.getParameter("repassword");
            
            if(password.equals(re_pass))
            {
                Ambassador sign_up = new Ambassador();
        
        sign_up.setEmail(mail);
        sign_up.setName(name);
        sign_up.setPosition(post);
        sign_up.setSchool(school);
        sign_up.setPhone(phone);
         
        Member sign_up_member = new Member();
       
        sign_up_member.setPasswordSalted(password);
        sign_up_member.setRole("AMBASSADOR");
        sign_up_member.setID();
        sign_up_member.setPictureID();
        
        
        
        sign_up.setMember(sign_up_member);
        
         AmbassadorDB.insertAmbassador(sign_up);
        
        
        
        request.setAttribute("pict", sign_up_member.getPicture());
        request.setAttribute("mem", sign_up_member.getId());
        
        Cookie userEmail = new Cookie("user_email",mail);
        Cookie userPass = new  Cookie("pass_word",password);
        
        userEmail.setMaxAge(60*60*24*365*2);// two years
        userEmail.setPath("/");
        response.addCookie(userEmail);
        
        userPass.setMaxAge(60*60*24*365*2);// two years
        userPass.setPath("/");
        response.addCookie(userEmail);
        
        
        Message new_message = new Message();
        Long admin = Long.parseLong("3663494542698422942");
        
            
            new_message.setDate();
            new_message.setMessageID();
            new_message.setSender(admin);
            new_message.setReciever(sign_up_member.getId());
            new_message.setMessage("Welcome To Harvest America's Leading Career Day Site, Connect "
                    + " Today Message Me If You Have Any Questions");
            
            MessageDB.insertMessage(new_message);
            getServletContext().getRequestDispatcher("/uploadpicture.jsp").forward(request, response);
            }
            else{
                request.setAttribute("message", "Passowrds Dont Match");
                getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
            }
        
        
        
        
        
        
        
        
    }
    
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String input = request.getParameter("search");
        
        String cs = request.getParameter("CS");
        String med = request.getParameter("MED");
        String sci = request.getParameter("SCI");
        String egr = request.getParameter("EGR");
        String bus = request.getParameter("BUS");
        String law = request.getParameter("LAW");
        
        String[] search = new String[]{input,cs,med,sci,egr,bus,law};
        
        
        List<Professional> set = new ArrayList<Professional>();
        set = MemberDB.searchProfessional(search);
        
        if(set.isEmpty())
        {
            request.setAttribute("message", "NO Search Results");
        }
        request.setAttribute("set", set);
        
        setupMessage(request,response);
        
        getServletContext().getRequestDispatcher("/ambassador_hub.jsp").forward(request, response);
        
    }
    public void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("whom")==null)
        {
            session.setAttribute("whom", session.getAttribute("ambassador"));
            
        }
         
        request.setAttribute("who", request.getParameter("who"));
        
        getServletContext().getRequestDispatcher("/message").forward(request, response);
         
        
        
        
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
    public  String getPicturePath(long member_pict)
    {
        String filepath = getServletContext().getInitParameter("file-upload");
        filepath= filepath.substring(filepath.lastIndexOf("d"));
        return filepath +member_pict+".jpg";
    }
}
