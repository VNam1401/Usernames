/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;
import common.database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class UpdateServlet extends HttpServlet {

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
            String uname = request.getParameter("uname");
            String upass = request.getParameter("upass");
            String email = request.getParameter("email");
            String country = request.getParameter("country");

            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = database.getConnection();
                ps = conn.prepareStatement("update users set name=?, password=?,email=?, country=? where id=?");
                ps.setString(1, uname);
                ps.setString(2, upass);
                ps.setString(3, email);
                ps.setString(4, country);
                ps.setInt(5, Integer.parseInt(id));

                int kq = ps.executeUpdate();

                if (kq > 0) {
                    out.println("<h2>Cập nhật user thành công</h2>");
                } else {
                    out.println("<h2>Cập nhật user thất bại</h2>");
                }
                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
                out.println("<h2>Cập nhật user thất bại</h2>");
            }

            request.getRequestDispatcher("ViewServlet").include(request, response);

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
