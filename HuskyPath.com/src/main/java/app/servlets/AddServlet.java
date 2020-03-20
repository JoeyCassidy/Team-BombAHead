package app.servlets;

import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    User user = new User(email,firstName, lastName);
    Model model = Model.getInstance();
    model.add(user);


    request.setAttribute("email", email);// once we added the stuff we passed it back to the page, this allow us to confirm it was added
    doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/add.jsp");// goes to this page
        requestDispatcher.forward(request, response);   // forwards what was in those two parameters
    }
}
