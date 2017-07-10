/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fu.holafood.entity.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import fu.holafood.model.UserModel;
import java.util.ArrayList;
import fu.holafood.controller.UserController;
import java.sql.Timestamp;

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
        UserController funcUser = new UserController();
        ArrayList<User> users = userModel.getUsers();
        boolean sent = false;
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        int gender = request.getParameter("gender").equalsIgnoreCase("male") ? 1 : 0;
        java.util.Date today = new java.util.Date();
        Timestamp createdAt = new java.sql.Timestamp(today.getTime());

        //Check valid for user,mail, pass
        if (userName.equals("") || password.equals("") || email.equals("")) {
            if (userName.equals("")) {
                request.setAttribute("emptyUserName", "User name cannot be empty.");
            }
            if (password.equals("")) {
                request.setAttribute("emptyPassword", "Password cannot be empty.");
            }
            if (email.equals("")) {
                request.setAttribute("emptyEmail", "Email cannot be empty.");
            }
            rd.forward(request, response);
            sent = true;
        }
        //check valid userName
        for (int i = 0; i < users.size(); i++) {
            if (!sent && userName.equals(users.get(i).getUsername())) {
                String errorUser = "User is already exist !!";
                request.setAttribute("error", errorUser);
                rd.forward(request, response);
                sent = true;
                break;
            }
        }
        //encrypt password
        password = funcUser.encryption(password);
        // convert gender to integer

        //format DOB to sql.Date
        java.sql.Date dobDate;
        if (!dob.equals("")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(dob);
            dobDate = new java.sql.Date(date.getTime());
        } else {
            dobDate = null;
        }

        // insert to database
        if (!sent && userModel.addUsers(userName, password, email, fullName, 1, gender, dobDate, createdAt) != 0) {
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
