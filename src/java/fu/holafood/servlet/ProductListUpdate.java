/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.entity.Product;
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
 * @author NhocNho
 */
@WebServlet(name = "ProductListUpdate", urlPatterns = {"/ProductListUpdate"})
public class ProductListUpdate extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("productId"));
        Product p = um.getProductById(id);
        String newProductName = request.getParameter("newProductName");
        String newSlug = request.getParameter("newSlug");
        String newDescription = request.getParameter("newDescription");
        String[] selectedCategoriesIds = request.getParameterValues("categories");

        java.util.Date today = new java.util.Date();
        Timestamp updateAt = new java.sql.Timestamp(today.getTime());

        String name;
        String slug;
        String des;
        boolean nameChange = false;

        if (newProductName.equals(p.getName())) {
            name = p.getName();
        } else {
            name = newProductName;
            nameChange = true;
        }

        if (newSlug.equals("")) {
            if (nameChange) {
                String tmp = uc.removeAccent(newProductName);
                slug = tmp.replaceAll("[^a-zA-Z0-9]+", "-");
                slug = slug.toLowerCase();
            } else {
                slug = p.getSlug();
            }
        } else {
            newSlug = uc.removeAccent(newSlug);
            slug = newSlug.replaceAll("[^a-zA-Z0-9]+", "-");
            slug = slug.toLowerCase();
        }

        if (newDescription.equals(p.getDescription())) {
            des = p.getDescription();
        } else {
            des = newDescription;
        }

        if (um.updateProduct(id, name, slug, des, p.getCreatedAt(), updateAt) != 0) {
            um.deleteProductCategory(id);
            boolean check = true;
            for (int i = 0; i < selectedCategoriesIds.length; i++) {
                int categoryId = Integer.parseInt(selectedCategoriesIds[i]);
                if (um.addProductCategory(um.getMaxId("products"), categoryId, p.getCreatedAt(), updateAt) == 0) {
                    check = false;
                }
            }
            if (check) {
                request.setAttribute("success", "Updated successfully");
                request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Updated failed");
                request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Cannot update");
            request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
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
            Logger.getLogger(ProductListUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductListUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
