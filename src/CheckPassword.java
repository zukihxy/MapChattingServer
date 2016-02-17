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
import java.sql.ResultSet;
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
@WebServlet(name = "CheckPassword", urlPatterns = {"/CheckPassword"})
public class CheckPassword extends HttpServlet {
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
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            String name = request.getParameter("name");
            String passwd = request.getParameter("passwd");
            String sql = "select `password` from user where `username`='" + name + "'";
            ResultSet rs = stm.executeQuery(sql);
            HttpSession session;
            if (rs.next() && rs.getString("password").equals(passwd)) {
                session = request.getSession(true);
                session.setAttribute("name", name);
                out.print("Succeed");
            } else {
                out.print("Wrong username or password!");
            }
            out.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CheckPassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
