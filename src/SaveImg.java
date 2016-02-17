/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(name = "SaveImg", urlPatterns = {"/SaveImg"})
public class SaveImg extends HttpServlet {

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
            Logger.getLogger(SaveImg.class.getName()).log(Level.SEVERE, null, ex);
        }

        String url = "jdbc:mysql://localhost:3306/mapchatting";
        String user = "root";
        String pass = "270329zuki";
        Connection con;
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();

        if (session == null) {
            out.print("Please log in first! ");
        } else {
            String name = (String) session.getAttribute("name");

            try {
                con = DriverManager.getConnection(url, user, pass);
                String sql = "update user set `portrait`=? where `username`='" + name + "'";
                PreparedStatement pstm = con.prepareStatement(sql);
                File img = new File("saber.jpg");
                FileInputStream fis = new FileInputStream(img);               
                pstm.setBinaryStream(1, (InputStream)fis, (int)img.length());
                int row = pstm.executeUpdate();
                if (row > 0)
                    out.print("OK");
                else
                    out.print("Fail");
            } catch (SQLException ex) {
                Logger.getLogger(SaveImg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
