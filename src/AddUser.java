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

/**
 *
 * @author hxy_zuki
 */
@WebServlet(name = "AddUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {
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
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "jdbc:mysql://localhost:3306/mapchatting";
        String user = "root";
        String pass = "270329zuki";
        String name = request.getParameter("name");
        String passwd = request.getParameter("password");
        String sex = request.getParameter("sex");
        /*
           Save img in to the database. What may i get from the front?
           Likely to do it in SaveImg.java
        */
        Connection con;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            String sql = "select * from user where `username`='" + name +"'";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                out.print("Username already used!");
            } else {
                sql = "insert into user (username, password) values ('" + name + "','" + passwd + "')";
                int row = stm.executeUpdate(sql);
                if (row > 0)
                    out.print("OK");
                else
                    out.print("Fail");
            }
            out.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
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
