/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.entity.User;
import fu.holafood.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "UserListActionUpdate", urlPatterns = {"/UserListActionUpdate"})
public class UserListActionUpdate extends HttpServlet {

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
        UserModel um = new UserModel();
        UserController uc = new UserController();
        int userId = Integer.parseInt(request.getParameter("userId"));
        User u = um.getUserById(userId);
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newFullName = request.getParameter("newFullName");
        int newGender = request.getParameter("newGender").equalsIgnoreCase("Male") ? 1 : 0;
        String newDob = request.getParameter("newDob");
        java.sql.Date dobDate;
        if (!newDob.equals("")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(newDob);
            dobDate = new java.sql.Date(date.getTime());
        } else {
            dobDate = u.getDob();
        }
        String password;
        String fullName;
        String email;

        if (!newPassword.equals("")) {
            newPassword = uc.encryption(newPassword);
            if (!newPassword.equals(u.getPassword())) {
                password = newPassword;
            } else {
                password = u.getPassword();
            }
        } else {
            password = u.getPassword();
        }

        if (!newFullName.equals("") && !newFullName.equals(u.getFullname())) {
            fullName = newFullName;
        } else {
            fullName = u.getFullname();
        }

        if (!newEmail.equals("") && !newEmail.equalsIgnoreCase(u.getEmail())) {
            email = newEmail;
        } else {
            email = u.getEmail();
        }

        if (um.updateUser(userId, password, email, fullName, newGender, dobDate) != 0) {
            request.setAttribute("success", "Updated successfully");
            request.getRequestDispatcher("admin/users/list.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Cannot update");
            request.getRequestDispatcher("admin/users/list.jsp").forward(request, response);
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
            Logger.getLogger(UserListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
