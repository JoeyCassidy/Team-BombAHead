package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classname = request.getParameter("class");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String location = request.getParameter("location");
        String[] days = request.getParameterValues("days[]");
        System.out.println(classname);
        System.out.println(startTime);
        System.out.println(endTime);
        for(int i = 0; i < days.length; i++)
        {
            System.out.println(days[i]);
        }
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/schedule.jsp");
        requestDispatcher.forward(request, response);
    }
}
