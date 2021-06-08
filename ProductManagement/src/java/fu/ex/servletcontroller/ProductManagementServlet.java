/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servletcontroller;

import fu.ex.daos.ProductDAO;
import fu.ex.entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ProductManagementServlet", urlPatterns = {"/ProductManagementServlet"})
public class ProductManagementServlet extends HttpServlet {

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
            throws ServletException, IOException {

        String action = request.getParameter("action");

        String displayView = "index.jsp";
        String formView = "productform.jsp";

        String controllerServlet = "ProductManagementServlet";

        try {
            if (action == null) {
                ArrayList<Product> list = new ArrayList<>();
                ProductDAO dao = new ProductDAO();
                list = dao.getAllProducts();

                request.setAttribute("data", list);

                RequestDispatcher rd = request.getRequestDispatcher(displayView);
                rd.forward(request, response);
            } else if (action.equals("addform")) {
                Product p = new Product();

                request.setAttribute("pObject", p);
                request.setAttribute("msg", "Add new product");
                request.setAttribute("action", "Create");
                request.setAttribute("errMsg", request.getParameter("errMsg"));

                RequestDispatcher rd = request.getRequestDispatcher(formView);
                rd.forward(request, response);

            } else if (action.equals("updateform")) {
                String id = request.getParameter("pid");

                ProductDAO dao = new ProductDAO();

                Product p = dao.getProductById(id);

                request.setAttribute("pObject", p);
                request.setAttribute("msg", "Update product (Id: " + id + " )");
                request.setAttribute("action", "Update");
                request.setAttribute("errMsg", request.getParameter("errMsg"));

                RequestDispatcher rd = request.getRequestDispatcher(formView);
                rd.forward(request, response);
            } else if (action.equals("delete")) {
                String id = request.getParameter("pid");

                ProductDAO dao = new ProductDAO();

                dao.deleteProduct(id);

                response.sendRedirect(controllerServlet);
            } else if (action.equals("Update")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                String url = request.getParameter("url");

                if (id.trim().equals("") || name.trim().equals("") || description.trim().equals("") || quantity.trim().equals("") || price.trim().equals("") || url.trim().equals("")) {
                    response.sendRedirect(controllerServlet + "?action=updateform&pid=" + id + "&errMsg=Cannot be empty!");
                } else {
                    ProductDAO dao = new ProductDAO();

                    Product p = new Product(id, name, description, Integer.parseInt(quantity), Double.parseDouble(price), url);

                    dao.updateProduct(p);

                    response.sendRedirect(controllerServlet);
                }

            } else if (action.equals("Create")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                String url = request.getParameter("url");

                if (id.trim().equals("") || name.trim().equals("") || description.trim().equals("") || quantity.trim().equals("") || price.trim().equals("") || url.trim().equals("")) {
                    response.sendRedirect(controllerServlet + "?action=addform&errMsg=Cannot be empty!");
                } else {
                    ProductDAO dao = new ProductDAO();

                    Product p = new Product(id, name, description, Integer.parseInt(quantity), Double.parseDouble(price), url);

                    dao.createProduct(p);

                    response.sendRedirect(controllerServlet);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
