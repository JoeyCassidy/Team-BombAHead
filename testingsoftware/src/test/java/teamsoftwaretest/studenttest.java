package teamsoftwaretest;
import org.junit.Before;
import org.junit.Test;
import teamsoftware.pathfactory;
import teamsoftware.*;

import static org.junit.Assert.*;
import java.sql.*;


public class studenttest {

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " delete from CLASSROOMPOST ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from CLASSROOMREPLY ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from FRIENDS ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from SCHEDULE ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from SETTINGS ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from STUDENT ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from STUDYGROUP ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " delete from STUDYGROUPLOG ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
    }

    @Test
    public void testing1() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p =" select * "
                + " from STUDENT "
                + " where ID = ?";
        stmt = conn.prepareStatement(p);
        stmt.setInt(1, 123);
        rs = stmt.executeQuery();
        if(rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }

    @Test
    public void initstudentTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        student using = new student();
        using.initStudent(111);
//        System.out.printf("%d %s \n", using.ID, using.Email);
        assertEquals(using.ID, 111);
        assertEquals(using.Email, "gmwalker@mtu.edu");
    }

    @Test
    public void SQLinitstudentTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        String[] using = new student().SQLinitStudent(111);
//        System.out.printf("%d %s \n", using.ID, using.Email);
        assertEquals(using[0], "111");
        assertEquals(using[1], "grant walker");
        assertEquals(using[2], "gmwalker@mtu.edu");
    }

    @Test
    public void SQLacceptOrDenyFriendsTEST1() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        p = " insert into FRIENDS " +
                " values (111, 123 , 1 ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        p = " insert into FRIENDS " +
                " values (123, 111 , 2 ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        new student().SQLacceptOrDenyFriends(true,111,123);

        p = " select * " +
                " from FRIENDS " +
                " where STUDENTID = 111 " +
                " and FRIENDID = 123 ";
        stmt = conn.prepareStatement(p);
        rs = stmt.executeQuery();

        rs.next();
//        System.out.printf("%d %d %s \n", rs.getInt(1), rs.getInt(2), rs.getString(3));
        assertEquals(rs.getInt(1),111);
        assertEquals(rs.getInt(2),123);
        assertEquals(rs.getString(3),"friend");

    }

    @Test
    public void SQLacceptOrDenyFriendsTEST2() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        p = " insert into FRIENDS " +
                " values (111, 123 , 1 ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        p = " insert into FRIENDS " +
                " values (123, 111 , 2 ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        new student().SQLacceptOrDenyFriends(false,111,123);

        p = " select * " +
                " from FRIENDS " +
                " where STUDENTID = 111 " +
                " and FRIENDID = 123 ";
        stmt = conn.prepareStatement(p);
        rs = stmt.executeQuery();

        assertEquals(rs.next(),false);
        //        System.out.printf("%d %d %s \n", rs.getInt(1), rs.getInt(2), rs.getString(3));
    }

    @Test
    public void SQLaddfriendTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        new student().SQLaddFriends(111,123);
//        System.out.printf("%d %s \n", using.ID, using.Email);

        p = " select * " +
                " from FRIENDS " +
                " where STUDENTID = 111 " +
                " and FRIENDID = 123 ";
        stmt = conn.prepareStatement(p);
        rs = stmt.executeQuery();
        rs.next();
        assertEquals(rs.getInt(1), 111);
        assertEquals(rs.getInt(2), 123);
        assertEquals(rs.getInt(3), 2);
        p = " select * " +
                " from FRIENDS " +
                " where STUDENTID = 123 " +
                " and FRIENDID = 111 ";
        stmt = conn.prepareStatement(p);
        rs = stmt.executeQuery();
        rs.next();
        assertEquals(rs.getInt(1), 123);
        assertEquals(rs.getInt(2), 111);
        assertEquals(rs.getInt(3), 1);
    }

    @Test
    public void SQLgetemailTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        assertEquals(new student().SQLgetEmail(111),"gmwalker@mtu.edu");
    }

    @Test
    public void SQLdeletefriendsTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        new student().SQLaddFriends(111,123);

        new student().SQLacceptOrDenyFriends(true,111,123);

        new student().SQLdeleteFriends(111,123);

        p = " select * " +
                " from FRIENDS ";
        stmt = conn.prepareStatement(p);
        rs = stmt.executeQuery();

        assertEquals(rs.next(),false);

    }

    @Test
    public void SQLgetfriendsTEST() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String p = " insert into STUDENT " +
                " values (111, 'grant walker' , 'gmwalker@mtu.edu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();
        p = " insert into STUDENT " +
                " values (123, 'mike bloomberg' , 'randomass@yahoo.gnu' ) ";
        stmt = conn.prepareStatement(p);
        stmt.execute();

        new student().SQLaddFriends(111,123);

        new student().SQLacceptOrDenyFriends(true,111,123);

        String[][] using = new student().SQLgetFriends(111);

        assertEquals(using[0][0], "123");
        assertEquals(using[0][1], "friend");
        assertEquals(using[0][2], "mike bloomberg");
        assertEquals(using[0][3], "randomass@yahoo.gnu");

    }


}
