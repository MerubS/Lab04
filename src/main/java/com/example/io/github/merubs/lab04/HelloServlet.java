package com.example.io.github.merubs.lab04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/secure/hello")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER", "ADMIN"}))
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String principal = (request.getUserPrincipal() != null)
                ? request.getUserPrincipal().getName()
                : "anonymous";

        out.println("<!DOCTYPE html><html><head><title>Hello Servlet</title></head><body>");
        out.println("<h1>Hello from /secure/hello</h1>");
        out.println("<p><strong>Logged in as:</strong> " + principal + "</p>");
        out.println("<hr>");
        out.println("<p><strong>Role USER:</strong> " + request.isUserInRole("USER") + "</p>");
        out.println("<p><strong>Role ADMIN:</strong> " + request.isUserInRole("ADMIN") + "</p>");
        out.println("</body></html>");
    }
}