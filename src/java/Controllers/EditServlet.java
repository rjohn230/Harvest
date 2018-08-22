/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Ambassador;
import Model.AmbassadorDB;
import Model.Locale;
import Model.LocaleDB;
import Model.Member;
import Model.MemberDB;
import Model.Professional;
import Model.ProfessionalDB;
import Model.Subject;
import Model.SubjectDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "EditServlet", urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {

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
            out.println("<title>Servlet EditServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServlet at " + request.getContextPath() + "</h1>");
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
        
        switch (todo)
        {
            case"picture":
            {
            try {
                uploadPicture(request,response);
            } catch (Exception ex) {
                Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
            case "contact":
            {
                updateContact(request,response);
                break;
            }
            case "about":
            {
                updateAbout(request,response);
                break;
            }
            case "password":
            {
                updatePassword(request,response);
                break;
            }
            case "subjects":
            {
                updateSubjects(request,response);
                break;
            }
            case "location":
            {
                updateLocation(request,response);
                break;
            }
            case "aboutambass":
            {
                updateAboutAmbass(request,response);
                break;
            }
            case "contactambass":
            {
                updateContactAmbass(request,response);
                break;
            }
        }
        
        HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        
        if(previous.getRole().equals("ambassador"))
        {
            getServletContext().getRequestDispatcher("/ambassador_edit.jsp").forward(request, response);
        }
        else
        {
            getServletContext().getRequestDispatcher("/professional_edit.jsp").forward(request, response);
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
    
    
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        boolean isMultipart;
        String filePath;
        int maxFileSize = 250 * 1024;
        int maxMemSize = 4 * 1024;
        File file ;
        HttpSession session = request.getSession();
         
        filePath = getServletContext().getInitParameter("file-upload");
        
        // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
   
      if( !isMultipart ) {
          
         return;
      }
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try { 
         // Parse the request to get file items.
         RequestContext requestContext = new ServletRequestContext(request);
         List fileItems = upload.parseRequest(requestContext);
	
         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         
   
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               String contentType = fi.getContentType();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
               if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
               } else {
                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               }
               Member member = (Member) session.getAttribute("member");
               
               String pict = filePath + member.getPicture()+".jpg";
               File existing = new File(pict);
               existing.delete();
               
               
               fi.write( file );
               File newfile = new File(pict);
               file.renameTo(newfile);
               
               String message= "Somthing went right";
               session.setAttribute("picturepath", pict.substring(pict.lastIndexOf("d")));
               request.setAttribute("message", message);
              
                  }
         }
        
         } catch(Exception ex) {
            System.out.println(ex);
            String message= "Somthing went wrong";
            request.setAttribute("message", message);  
         }
    }

    public void updateContact(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String first = request.getParameter("first_name");
        String last = request.getParameter("last_name");
        HttpSession session = request.getSession();
        
        Professional edit = (Professional) session.getAttribute("professional");
        Member previous = edit.getMember();
        
        edit.setEmail(email);
        edit.setPhone(phone);
        edit.setFirstName(first);
        edit.setLastName(last);
        
        ProfessionalDB.updateProfessional("contact",previous, edit);
        request.setAttribute("message","Contact Info Updated");
        
    }
    

    private void updateAbout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Professional edit = (Professional) session.getAttribute("professional");
        Member previous = edit.getMember();
        
        String profession = request.getParameter("profession");
        String education = request.getParameter("education");
        String about = request.getParameter("about");
        
        edit.setAbout(about);
        edit.setEducation(education);
        edit.setProfession(profession);
        
        ProfessionalDB.updateProfessional("about",previous, edit);
        request.setAttribute("message","About Info Updated");
        
        
    }

    public void updatePassword(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        
        String old_pass = request.getParameter("old_password")+previous.getSalt();
        
        if(old_pass.equals(previous.getPassSalt()))
        {
            String new_pass = request.getParameter("password");
            String re_pass = request.getParameter("repassword");
            
            if(new_pass.equals(re_pass))
            {
                MemberDB.updatePassword(new_pass,previous.getId());
            }
            else{
                request.setAttribute("message", "Passowrds Dont Match");
            }
        }
        else
        {
            request.setAttribute("message", "Old Password is incorrect");
        }
        
    }

    private void updateSubjects(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        String cs = request.getParameter("CS");
        String med = request.getParameter("MED");
        String sci = request.getParameter("SCI");
        String egr =request.getParameter("EGR");
        String bus = request.getParameter("BUS");
        String law = request.getParameter("LAW");
        Subject sign = new Subject();
  
        sign.setBus(bus);
        sign.setCS(cs);
        sign.setLaw(law);
        sign.setMed(med);
        sign.setEgr(egr);
        sign.setSci(sci);
        
        SubjectDB.updateSubjects(previous.getId(), sign);
        request.setAttribute("message", "Subjects Were Updated");
        
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        
        Locale loco = new Locale();
        
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        
        loco.setCity(city);
        loco.setState(state);
        loco.setCountry(country);
        
        LocaleDB.updateLocation(previous.getId(),loco);
        
        request.setAttribute("message", "Location was Updated");
        
        
        
        
    }

    private void updateContactAmbass(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        Ambassador nu = (Ambassador) session.getAttribute("ambassador");
        
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        nu.setEmail(email);
        nu.setPhone(phone);
        
        AmbassadorDB.updateAmbassador("contact",previous,nu);
        
        
        request.setAttribute("message", "Contact info was Updated");
        
    }

    private void updateAboutAmbass(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        Member previous = (Member) session.getAttribute("member");
        Ambassador nu = (Ambassador) session.getAttribute("ambassador");
        
        String name = request.getParameter("name");
        String school = request.getParameter("school");
        String position = request.getParameter("position");
        
        nu.setName(name);
        nu.setPosition(position);
        nu.setSchool(school);
        
        AmbassadorDB.updateAmbassador("about",previous,nu);
        request.setAttribute("message", "About info was Updated");
        
    }

    

    
    
}
