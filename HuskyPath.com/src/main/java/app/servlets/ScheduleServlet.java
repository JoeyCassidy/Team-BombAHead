package app.servlets;

import teamsoftware.schedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String classname = request.getParameter("class");
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String location = request.getParameter("location");
        String[] days = request.getParameterValues("days[]");
        Boolean dayBooleans[] = new Boolean[5];
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Timestamp endTimeStamp;
        Timestamp startTimeStamp;
        System.out.println(days.length);
        for(int i  = 0; i<days.length;i++){
            System.out.println(days[i]);
        }
        for(int i = 0; i < days.length; i++){
           switch (days[i]){
               case "monday":
                   dayBooleans[0] = true;
                   break;
               case "tuesday":
                   dayBooleans[1] = true;
                   break;
               case "wednesday":
                   dayBooleans[2] = true;
                   break;
               case "thursday":
                   dayBooleans[3] = true;
                   break;
               case "friday":
                   dayBooleans[4] = true;
                   break;
           }

        }
        for(int i = 0; i<5;i++){
                if(dayBooleans[i]==null){
                    dayBooleans[i] = false;

                }
            System.out.println(dayBooleans[i]);
        }

        System.out.println(endTime);
        System.out.println(startTime);
        schedule sched = new schedule();
        HttpSession session = request.getSession(false);
        System.out.println(session.getAttribute("userid"));
        try {
            Date endTimeDate = dateFormat.parse(endTime+":00");
            Date startTimeDate = dateFormat.parse(startTime+":00");
            endTimeStamp = new java.sql.Timestamp(endTimeDate.getTime());
            startTimeStamp = new Timestamp(startTimeDate.getTime());
            System.out.println((String) session.getAttribute("userid")+" "+classname+" "+startTimeStamp+" "+endTimeStamp+" "+location);
            System.out.println(dayBooleans.length);

            sched.SQLaddPlace((String) session.getAttribute("userid"),classname,startTimeStamp,endTimeStamp,location,dayBooleans);
            request.setAttribute("successful",true);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/schedule.jsp");
        requestDispatcher.forward(request, response);
    }
}
