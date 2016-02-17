/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hxy_zuki
 */
@WebServlet(name = "AddDiscussion", urlPatterns = {"/AddDiscussion"})
public class AddDiscussion extends HttpServlet {
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadContent.class.getName()).log(Level.SEVERE, null, ex);
        }

        String user = "root";
        String pass = "270329zuki";
        String url = "jdbc:mysql://localhost:3306/mapchatting";
        Connection con;
        int row;

        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            String topic = request.getParameter("topic");
            String type = request.getParameter("type");
            String x = request.getParameter("x");
            String y = request.getParameter("y");
            String tag = request.getParameter("tag");
            HttpSession session = request.getSession(false);
            String name = "";
            if (session != null)
                name = (String) session.getAttribute("name");
            else
                out.print("Please log in first! ");

            String sql = "insert into dislist values ('"+ topic
                    + "', '" + type + ", " + x + ", " + y + "', '" + tag
                    + "', '" + name + "', now()";
            row = stm.executeUpdate(sql);            
            if (row > 0)
                out.print("succeed");
            else 
                out.print("fail");
            out.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadContent.class.getName()).log(Level.SEVERE, null, ex);
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

}
