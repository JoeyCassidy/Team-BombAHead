package teamsoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class setting {
	Boolean friendsCanSeePath;
	Boolean friendsCanSeeSchedule;
	Boolean friendsCanSeeFriend;
	Boolean lightMode;
// show groups?


	/**
	 * This class is all just getters and setters for
	 * boolean values that are the settings that are available
	 */
	public Boolean SQLgetFriendsCanSeePath(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		return rs.getBoolean(6);
	}
	public void SQLsetFriendsCanSeePath(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		if(rs.getBoolean(6)){
			       p = " update SETTINGS " +
					   " set SHOWPATH = FALSE "
					 + " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		} else {
				p = " update SETTINGS " +
					" set SHOWPATH = true "
				  + " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		}
	}
	public Boolean SQLgetFriendsCanSeeSchedule(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		return rs.getBoolean(5);
	}
	public void SQLsetFriendsCanSeeSchedule(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		if(rs.getBoolean(5)){
			p = " update SETTINGS " +
					" set SHOWSCHEDULE = FALSE "
					+ " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		} else {
			p = " update SETTINGS " +
					" set SHOWSCHEDULE = true "
					+ " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		}
	}
	public Boolean SQLgetFriendsCanSeeFriend(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		return rs.getBoolean(2);
	}
	public void SQLsetFriendsCanSeeFriend(String myid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		if(rs.getBoolean(2)){
			p = " update SETTINGS " +
					" set SHOWFRIENDS = FALSE "
					+ " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		} else {
			p = " update SETTINGS " +
					" set SHOWFRIENDS = true "
					+ " where STUDENTID = ? ";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			rs = stmt.executeQuery();
		}
	}
	public Boolean SQLgetLightMode() {
		return lightMode;
	}
	public void SQLsetLightMode(Boolean lightMode) {
		this.lightMode = lightMode;
	}
}
