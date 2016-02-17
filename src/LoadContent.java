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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hxy_zuki
 */
@WebServlet(name = "LoadContent", urlPatterns = {"/LoadContent"})
public class LoadContent extends HttpServlet {

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
        ResultSet rs;

        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            String topic = request.getParameter("topic");

            String sql = "select * from discussion where `topic`='" + topic + "'";
            rs = stm.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("content", rs.getString("content"));
                obj.put("date", rs.getDate("time"));
                obj.put("time", rs.getTime("time"));
                obj.put("author", rs.getString("author"));
                array.put(obj);
            }
            out.print(array);
            out.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadContent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(LoadContent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
