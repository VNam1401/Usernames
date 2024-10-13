/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import common.database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
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
            String id = request.getParameter("id");

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String kq = "";
            try {
                conn = database.getConnection();
                ps = conn.prepareStatement("select * from users where id=" + id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet EditServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Update User</h1>");
                    out.println("<form action='UpdateServlet' method='POST'>\n"
                            + "<input type='hidden' name='id' value=" + id + ">"
                            + " <table border='0'> \n"
                            + " <tr>\n"
                            + " <td>Name</td>\n"
                            + " <td><input type='text' name='uname' value='" + rs.getString(2) + "' /></td>\n"
                            + " </tr>\n"
                            + " <tr>\n"
                            + " <td>Password</td>\n"
                            + " <td><input type='password' name='upass' value='" + rs.getString(3) + "'/></td>\n"
                            + " </tr>\n"
                            + " <tr>\n"
                            + " <td>Email</td>\n"
                            + " <td><input type='email' name='email' value='" + rs.getString(4) + "' /></td>\n"
                            + " </tr>\n"
                            + " <tr>\n"
                            + " <td>Country</td>\n"
                            + " <td>\n"
                            + " <select name=\"country\">\n"
                            + " <option value='Vietnam'>Vietnam</option>\n"
                            + " <option value='USA'>USA</option>\n"
                            + " <option value='UK'>UK</option>\n"
                            + " <option value='Other'>Other</option>\n"
                            + " </select>\n"
                            + " </td>\n"
                            + " </tr>\n"
                            + " <tr>\n"
                            + " <td colspan=2><input type='submit' value='Edit & Save' /></td>\n"
                            + " </tr>\n"
                            + " </table>\n"
                            + " </form>");
                    out.println("</body>");
                    out.println("</html>");
                }
                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
            }
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
