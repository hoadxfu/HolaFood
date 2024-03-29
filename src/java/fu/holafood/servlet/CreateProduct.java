/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.servlet;

import fu.holafood.controller.UserController;
import fu.holafood.model.UserModel;
import java.io.IOException;
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
@WebServlet(name = "CreateProduct", urlPatterns = {"/CreateProduct"})
public class CreateProduct extends HttpServlet {

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
        String productName = request.getParameter("productName");
        String slug = request.getParameter("slug");
        String des = request.getParameter("description");
        String img_feature = request.getParameter("img_feature");
        String[] selectedCategoriesIds = request.getParameterValues("categories");
        
        System.out.println("size: " + selectedCategoriesIds.length);

        System.out.println("img feature " + img_feature);

        if (productName.equals("") && slug.equals("")) {
            request.setAttribute("error", "Product name and slug cannot both be empty.");
            request.getRequestDispatcher("admin/products/create.jsp").forward(request, response);
            sent = true;
        } else if (!slug.equals("")) {
            slug = uc.removeAccent(slug);
            slug = slug.replaceAll("[^a-zA-Z0-9]+", "-");
            slug = slug.toLowerCase();
        } else if (!productName.equals("") && slug.equals("")) {
            String tmp = uc.removeAccent(productName);
            slug = tmp.replaceAll("[^a-zA-Z0-9]+", "-");
            slug = slug.toLowerCase();
        }

        java.util.Date today = new java.util.Date();
        Timestamp createdAt = new java.sql.Timestamp(today.getTime());

        if (!sent && userModel.addProduct(productName, slug, des, img_feature, createdAt, createdAt) != 0) {
            boolean check = true;
            for (int i = 0; i < selectedCategoriesIds.length; i++) {
                int categoryId = Integer.parseInt(selectedCategoriesIds[i]);
                if (userModel.addProductCategory(userModel.getMaxId("products"), categoryId, createdAt, createdAt) == 0) {
                    check = false;
                }
            }
            if (check) {
                response.sendRedirect("admin/products/list.jsp");
            } else {
                request.setAttribute("error", "Created failed");
                request.getRequestDispatcher("admin/products/create.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Created failed");
            request.getRequestDispatcher("admin/products/create.jsp").forward(request, response);
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
            Logger.getLogger(CreateProduct.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateProduct.class.getName()).log(Level.SEVERE, null, ex);
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
