package app.servlets;

import teamsoftware.student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post: "+request.getParameter("uid"));
        HttpSession session = request.getSession();
        session.setAttribute("userid", request.getParameter("uid"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/profile.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("do get: "+request.getParameter("uid"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/profile.jsp");
        requestDispatcher.forward(request, response);
    }
}
