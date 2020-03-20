package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = Model.getInstance();
        List<String> emails = model.list();
        request.setAttribute("emails", emails);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
