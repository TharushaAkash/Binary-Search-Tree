package com.example.bst_part.servlets;

import com.example.bst_part.model.User;
import com.example.bst_part.utils.FileStorage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/auth/login")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            List<User> users = FileStorage.loadUsers();
            User authenticatedUser = null;

            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    authenticatedUser = user;
                    break;
                }
            }

            if (authenticatedUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", authenticatedUser);

                if ("admin".equals(authenticatedUser.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/admin/add-package.html");
                } else {
                    response.sendRedirect(request.getContextPath() + "/user/dashboard.html");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/auth/login.html?error=1");
            }
        } catch (IOException e) {
            response.sendRedirect(request.getContextPath() + "/auth/login.html?error=2");
        }
    }
}