/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.entity.Category;

import fu.holafood.model.UserModel;
import java.io.IOException;

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
public class CategoryListActionUpdate extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        UserModel um = new UserModel();
        UserController uc = new UserController();
        int id = Integer.parseInt(request.getParameter("categoryId"));
        Category p = um.getCategoryById(id);
        int newPid = Integer.parseInt(request.getParameter("newPid"));
        String newCategoryName = request.getParameter("newCategoryName");
        String newSlug = request.getParameter("newSlug");
        String newDescription = request.getParameter("newDescription");
        
        java.util.Date today = new java.util.Date();
        Timestamp updateAt = new java.sql.Timestamp(today.getTime());
        
        String name;
        String slug;
        String des;
        
        boolean nameChange = false;
        if (newCategoryName.equals("")) {
            name = p.getName();
        } else {
            if (newCategoryName.equals(p.getName())) {
                name = p.getName();
            } else {
                name = newCategoryName;
                nameChange = true;
            }
        }
        
        if (newSlug.equals("")) {
            if (nameChange) {
                String tmp = uc.removeAccent(newCategoryName);
                slug = tmp.replaceAll("[^a-zA-Z0-9]+", "-");
            } else {
                slug = p.getSlug();
            }
        } else {
            newSlug = uc.removeAccent(newSlug);
            slug = newSlug.replaceAll("[^a-zA-Z0-9]+", "-");
        }
        
        if (newDescription.equals("")) {
            des = p.getDescreption();
        } else {
            des = newDescription;
        }
        
        if (um.updateCategory(id, newPid, name, slug, des, p.getCreatedAt(), updateAt) != 0) {
            request.setAttribute("success", "Updated successfully");
            request.getRequestDispatcher("admin/category/list.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Cannot update");
            request.getRequestDispatcher("admin/category/list.jsp").forward(request, response);
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
            Logger.getLogger(CategoryListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryListActionUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
