/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.entity.Reviews;
import fu.holafood.model.ReviewModel;
import fu.holafood.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BH_Wind
 */
@WebServlet(name = "ReviewListActionUpdate", urlPatterns = {"/ReviewListActionUpdate"})
public class ReviewListActionUpdate extends HttpServlet {

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
        ReviewModel um = new ReviewModel();
        UserController uc = new UserController();
        int id = Integer.parseInt(request.getParameter("reviewId"));
        Reviews r = um.getReviewById(id);
        //int newitem = Integer.parseInt(request.getParameter("newitem"));

        String newValue = request.getParameter("newValue");
        java.util.Date today = new java.util.Date();
        Timestamp updateAt = new java.sql.Timestamp(today.getTime());

        String values;
        boolean nameChange = false;
        if (newValue.equals("")) {
            values = r.getValue();
        } else {
            if (newValue.equals(r.getValue())) {
                values = r.getValue();
            } else {
                values = newValue;
                nameChange = true;
            }
        }        
        if (um.updateReview(id,values, updateAt) != 0) {
            request.setAttribute("success", "Updated successfully");
            request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Cannot update");
            request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
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
            Logger.getLogger(ReviewListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReviewListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
