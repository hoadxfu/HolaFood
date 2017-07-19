/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.entity.Reviews;
import fu.holafood.model.ReviewModel;
import fu.holafood.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ReviewtListAction", urlPatterns = {"/ReviewtListAction"})
public class ReviewtListAction extends HttpServlet {

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
        ReviewModel um = new ReviewModel();
        String deleteAction = request.getParameter("deleteAction");
        String updateAction = request.getParameter("updateAction");
        
        //delete
        if (!deleteAction.equals("") && updateAction.equals("")) {
            int index = Integer.parseInt(deleteAction);
            if (um.deleteReview(index) != 0) {
                request.setAttribute("success", "Deleted successfully");
                request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Cannot delete");
                request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
            }
            sent = true;

        } else if (deleteAction.equals("") && !updateAction.equals("")) { //update
            int index = Integer.parseInt(updateAction);
            Reviews p = um.getReviewById(index);
            if (p != null) {
                request.setAttribute("ReviewUpdate", p);
                request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Cannot update");
                request.getRequestDispatcher("admin/reviews/list.jsp").forward(request, response);
            }
            sent = true;
        }
        
        if (!sent) {
            response.sendRedirect("admin/review/list.jsp");
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
            Logger.getLogger(ReviewtListAction.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReviewtListAction.class.getName()).log(Level.SEVERE, null, ex);
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
