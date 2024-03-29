/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ray Sparrow
 */
public class CreateCategory extends HttpServlet {

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
        UserController uc = new UserController();
        boolean sent = false;
        String categoryName = request.getParameter("categoryName");
        String slug = request.getParameter("slug");
        int pid = Integer.parseInt(request.getParameter("pid"));
        String des = request.getParameter("description");

        if (categoryName.equals("") && slug.equals("")) {
            request.setAttribute("error", "Category name and slug cannot both be empty.");
            request.getRequestDispatcher("admin/category/create.jsp").forward(request, response);
            sent = true;
        } else if (!slug.equals("")) {
            slug = uc.removeAccent(slug);
            slug = slug.replaceAll("[^a-zA-Z0-9]+", "-");
        } else if (!categoryName.equals("") && slug.equals("")) {
            String tmp = uc.removeAccent(categoryName);
            slug = tmp.replaceAll("[^a-zA-Z0-9]+", "-");
        }

        java.util.Date today = new java.util.Date();
        Timestamp createdAt = new java.sql.Timestamp(today.getTime());
        Timestamp updatedAt = new java.sql.Timestamp(today.getTime());
        
        if (!sent && userModel.addCategory(pid, slug, categoryName, des, createdAt, updatedAt) != 0) {
            request.setAttribute("success", "Created successfully");
            request.getRequestDispatcher("admin/category/create.jsp").forward(request, response);
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
            Logger.getLogger(CreateCategory.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateCategory.class.getName()).log(Level.SEVERE, null, ex);
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
