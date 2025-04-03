package com.example.bst_part.servlets;

import com.example.bst_part.model.*;
import com.example.bst_part.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private BSTPackage bst;

    @Override
    public void init() throws ServletException {
        try {
            bst = FileStorage.loadPackages();
        } catch (IOException e) {
            throw new ServletException("Failed to load packages", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int packageId = Integer.parseInt(request.getParameter("packageId"));
            String name = request.getParameter("name");
            String destination = request.getParameter("destination");
            double price = Double.parseDouble(request.getParameter("price"));
            int duration = Integer.parseInt(request.getParameter("duration"));

            TourismPackage newPackage = new TourismPackage(packageId, name, destination, price, duration);
            bst.insert(newPackage);
            FileStorage.savePackages(bst);

            response.sendRedirect(request.getContextPath() + "/packages");
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("/admin/add-package.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/add-package.html").forward(request, response);
    }
}