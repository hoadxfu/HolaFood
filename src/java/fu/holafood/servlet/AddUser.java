/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fu.holafood.db.DBContext;
import fu.holafood.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import fu.holafood.model.UserModel;
import java.util.ArrayList;
import fu.holafood.function.FuncUser;

/**
 *
 * @author Ray Sparrow
 */
public class AddUser extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        UserModel userModel = new UserModel();
        FuncUser funcUser = new FuncUser();

        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String date = request.getParameter("dob");
        String email = request.getParameter("email");
        String strGender = request.getParameter("gender");
        ArrayList<User> users = new ArrayList<>();
        users = userModel.getUsers();

        //Check valid for user,mail, pass
        //check valid userName
        if (userName == null) {
            String errorUser = "User can not be null!!!";
            request.setAttribute("errorUser", errorUser);
            rd.forward(request, response);
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (userName.equals(users.get(i).getUsername())) {
                    String errorUser = "User is already esixt !!";
                    request.setAttribute("errorUser", errorUser);
                    rd.forward(request, response);
                    break;
                }
            }
        }
        //checkvalid password
        if (password == null) {
            String errorPass = "Password can not be null !!";
            request.setAttribute("errorPass", errorPass);
            rd.forward(request, response);
        } else {
            password = funcUser.encryption(password);
        }
        //check valid email
        if (email == null) {
            String errorMail = "Email can not be null !!";
            request.setAttribute("errorMail", errorMail);
            rd.forward(request, response);
        }

        // convert gender to integer
        int gender;
        if (strGender.equals("Male")) {
            gender = 1;
        } else {
            gender = 0;
        }
        
        //format DOB to sql.Date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = df.parse(date);
        java.sql.Date sqlDOB = new java.sql.Date(dob.getTime());

        //get the current date
        Date created_at = new Date();
//        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        java.sql.Date sqlCreated = new java.sql.Date(created_at.getTime());

        // insert to database
        if (userModel.addUsers(userName, password, email, fullName, 1, gender, sqlDOB, sqlCreated) != 0) {
            response.sendRedirect("index.jsp");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
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
