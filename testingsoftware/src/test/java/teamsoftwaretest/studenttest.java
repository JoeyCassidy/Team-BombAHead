package teamsoftwaretest;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;


public class studenttest {
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
        rs.next();
        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +  rs.getString(3));
    }
}
