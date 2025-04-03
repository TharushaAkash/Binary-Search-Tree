package com.example.bst_part.servlets;

import com.example.bst_part.model.*;
import com.example.bst_part.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/packages")
public class PackageServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("packages", bst.getPackagesInOrder());
        request.getRequestDispatcher("/user/dashboard.html").forward(request, response);
    }
}