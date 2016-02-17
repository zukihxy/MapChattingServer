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
@WebServlet(name = "LoadDisList", urlPatterns = {"/LoadDisList"})
public class LoadDisList extends HttpServlet {
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
            Logger.getLogger(LoadDisList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "jdbc:mysql://localhost:3306/mapchatting";
        String user = "root";
        String pass = "270329zuki";
        Connection con;
        ResultSet rs;
        
        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            PrintWriter out = response.getWriter();
            int x = Integer.parseInt(request.getParameter("x"));
            int y = Integer.parseInt(request.getParameter("y"));
            int range = 10;
            String sql = "select * from dislist where `x_cordinate`<" + (x+range)
                    + " and `x_cordinate`>"+ (x-range) +" and `y_cordinate`>"
                    + (y-range) + " and `y_cordinate`<" + (y+range);
            rs = stm.executeQuery(sql);
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("topic", rs.getString("topic"));
                obj.put("type", rs.getString("type"));
                obj.put("tag", rs.getString("tag"));
                obj.put("x", rs.getDouble("x_cordinate"));
                obj.put("y", rs.getDouble("y_cordinate"));
                obj.put("starter", rs.getString("starter"));
                obj.put("time", rs.getTime("start_time"));
                obj.put("date", rs.getDate("start_time"));
                array.put(obj);
            }
            out.print(array);
            out.close();
            stm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoadDisList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(LoadDisList.class.getName()).log(Level.SEVERE, null, ex);
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
