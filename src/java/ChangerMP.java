/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.MD5Password;
import controller.connexionController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sonia
 */
@WebServlet(urlPatterns = {"/ChangerMP"})
public class ChangerMP extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession session= request.getSession();
            /* TODO output your page here. You may use following sample code. */
        MD5Password md5 = new MD5Password();
        String Nouveaump = md5.getEncodedPassword(request.getParameter("Nouveaump"));
        String Confirmermp = request.getParameter("confirmermp");
	 String Email = request.getParameter("mail");
     out.println(""+Email);
       
        connexionController conn = new connexionController();
        Connection c= conn.getConnection();
        String req="Update utilisateur set Motdepasse='"+Nouveaump+"' , Etat=1 where Email='"+Email+"' ";      
        out.print(req);
            int rs = conn.executeUpdate(req);    
         conn.executeUpdate(req); 
          
         if ( Nouveaump.equals(Confirmermp))
         {
             response.sendRedirect("accueil_1.html");
         }
         
        } catch(Exception e)
          {
            System.out.print(e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
