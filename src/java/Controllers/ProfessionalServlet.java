/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Member;
import Model.Message;
import Model.MessageDB;
import Model.Professional;
import Model.ProfessionalDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rrjoh
 */
@WebServlet(name = "ProfessionalServlet", urlPatterns = {"/profess"})
public class ProfessionalServlet extends HttpServlet {

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
            out.println("<title>Servlet ProfessionalServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfessionalServlet at " + request.getContextPath() + "</h1>");
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
    }
    
    public void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String email = request.getParameter("email");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String password = request.getParameter("password");
        String about = request.getParameter("about");
        String phone = request.getParameter("phone");
        String education = request.getParameter("education");
        String profession = request.getParameter("profession");
        
        String new_pass = request.getParameter("password");
            String re_pass = request.getParameter("repassword");
            
            if(new_pass.equals(re_pass))
            {
                Professional sign = new Professional();
        
        
        sign.setAbout(about);
        sign.setEducation(education);
        sign.setEmail(email);
        sign.setProfession(profession);
        sign.setFirstName(first_name);
        sign.setLastName(last_name);
        sign.setPhone(phone);
        
        
         
        Member sign_up_member = new Member();
       
        sign_up_member.setPasswordSalted(password);
        sign_up_member.setRole("PROFESSIONAL");
        sign_up_member.setID();
        sign_up_member.setPictureID();
        
        
        
        sign.setMember(sign_up_member);
        
        ProfessionalDB.insertProfessional(sign);
        
         
        
        
        
        request.setAttribute("pict", sign_up_member.getPicture());
        request.setAttribute("mem", sign_up_member.getId());
        
        Cookie userEmail = new Cookie("user_email",email);
        Cookie userPass = new  Cookie("pass_word",password);
        
        userEmail.setMaxAge(60*60*24*365*2);// two years
        userEmail.setPath("/");
        response.addCookie(userEmail);
        
        userPass.setMaxAge(60*60*24*365*2);// two years
        userPass.setPath("/");
        response.addCookie(userEmail);
        
        Message new_message = new Message();
        Long admin = Long.parseLong("2700055222235260138");
        
            
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
    
    public void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("whom")==null)
        {
            session.setAttribute("whom", session.getAttribute("professional"));
            
        }
         
        request.setAttribute("who", request.getParameter("who"));
        
        getServletContext().getRequestDispatcher("/message").forward(request, response);
         
        
        
        
    }

}
