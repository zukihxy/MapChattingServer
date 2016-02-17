/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hxy_zuki
 */
@WebServlet(name = "LoadMine", urlPatterns = {"/LoadMine"})
public class LoadMine extends HttpServlet {
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
        //connect to JDBC
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadMine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //connect to database
        String url = "jdbc:mysql://localhost:3306/mapchatting";
        String user = "root";
        String pass = "270329zuki";
        Connection con;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            ResultSet rs;
            String name = request.getParameter("name");

            String sql = "select dislist.topic,dislist.type,discussion.author from dislist join discussion"
                    + " on dislist.topic=discussion.topic where `author`='"+name+"'";
            rs = stm.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject item = new JSONObject();
                item.put("topic", rs.getString("topic"));
                item.put("type", rs.getString("type"));
                array.put(item);
            }
            out.print(array);
            //out.close();
        } catch (SQLException ex) {
            System.out.println("error when connecting database");
            Logger.getLogger(LoadMine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(LoadMine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}