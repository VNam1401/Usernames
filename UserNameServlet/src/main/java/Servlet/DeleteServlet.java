package Servlet;

import common.database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");

            // Kiểm tra ID hợp lệ
            if (id == null || id.isEmpty()) {
                out.println("<h2>ID không hợp lệ</h2>");
                return;
            }

            Connection conn = null;
            PreparedStatement ps = null;

            try {
                conn = database.getConnection();
                ps = conn.prepareStatement("DELETE FROM Users WHERE id = ?");
                ps.setString(1, id);

                int kq = ps.executeUpdate();
                if (kq > 0) {
                    out.println("<h2>Xoá User thành công</h2>");
                } else {
                    out.println("<h2>Xoá User thất bại</h2>");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi: " + e.getMessage());
                out.println("<h2>Xoá User thất bại</h2>");
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Lỗi khi đóng tài nguyên: " + ex.getMessage());
                }
            }

            request.getRequestDispatcher("ViewServlet").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Delete User Servlet";
    }
}
