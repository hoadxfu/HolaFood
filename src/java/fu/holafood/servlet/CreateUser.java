/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.entity.User;
import fu.holafood.model.UserModel;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NhocNho
 */
@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

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
        boolean sent = false;
        UserModel userModel = new UserModel();
        UserController funcUser = new UserController();
        ArrayList<User> users = userModel.getUsers();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        int permissionId = Integer.parseInt(request.getParameter("permission"));
        int gender = request.getParameter("gender").equalsIgnoreCase("male") ? 1 : 0;
        String dob = request.getParameter("dob");
        java.util.Date today = new java.util.Date();
        Timestamp createdAt = new java.sql.Timestamp(today.getTime());
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
            request.getRequestDispatcher("admin/users/create.jsp").forward(request, response);
            sent = true;
        }
        //check valid userName
        for (int i = 0; i < users.size(); i++) {
            if (userName.equals(users.get(i).getUsername())) {
                String errorUser = "User is already esixt !!";
                request.setAttribute("error", errorUser);
                request.getRequestDispatcher("admin/users/create.jsp").forward(request, response);
                sent = true;
                break;
            }
        }
        //encrypt password
        password = funcUser.encryption(password);
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
        if (!sent && userModel.addUsers(userName, password, email, fullName, permissionId, gender, dobDate, createdAt) != 0) {
            request.setAttribute("success", "Created successfully");
            request.getRequestDispatcher("admin/users/create.jsp").forward(request, response);
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
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
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
