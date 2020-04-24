package teamsoftware;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class schedule {
	ArrayList<String>[] listOfPaths;
	public ArrayList<place> listOfListOfPlaces = new ArrayList<place>();

	public schedule() {
		super();
		this.listOfPaths = new ArrayList[5];
		for(int i = 0; i < 5; i++) {
			listOfPaths[i] = new ArrayList<String>();
		}
	}

	/**
	 * you pass in a user id and this gets there schedule for them
	 * @param myid
	 * @return
	 * @throws SQLException
	 */
	public schedule SQLintitschedule(String myid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " select * " +
				" from SCHEDULE " +
				" where STUDENTID = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		rs = stmt.executeQuery();
		while(rs.next()) {
			Boolean[] useb = {rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10)};
			place using = new place(rs.getString(2),rs.getTime(3).toLocalTime(), null, useb, rs.getString(5), rs.getString(2));
			listOfListOfPlaces.add(listOfListOfPlaces.size(), using);
		}
		place[] holdingplace = new place[listOfListOfPlaces.size()];
//		int i = 0;
//		for(place pp : listOfListOfPlaces){
//			holdingplace[i] = pp;
//			i++;
//		}
//		String[][] holdingpaths = p1.pathing3(holdingplace, new Boolean[] {true, true, true, true, true} );
//		for (int j = 0; j < 5; j++) {
//			for (String sp: holdingpaths[j]) {
//				listOfPaths[j].add(sp);
//			}
//		}
		return this;
	}

	/**
	 * This method will add a place to later be added to the path[?]
	 * @param name - name of the place
	 * @param time - time of the event at the place
	 * @param type - type of place (lecture, lab, library, etc.)
	 * @param listOfDays - lists of days that you have the event
	 * @param location - location of the place (building, room #)
	 * @param identifier - takes a primary key to find out which part of the building to go to
	 */
	public void addPlace(String name, LocalTime time, String type, Boolean[] listOfDays, String location, String identifier) {
		place tobeadded = new place(name,time,type,listOfDays,location,identifier);
		listOfListOfPlaces.add(tobeadded);
	}

	/**
	 *
	 * @param userid
	 * @param classname
	 * @param starttime
	 * @param endtime
	 * @param location
	 * @param isday
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void SQLaddPlace(String userid, String classname, Timestamp starttime, Timestamp endtime, String location, Boolean[] isday) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into SCHEDULE values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setString(2, classname);
		stmt.setTimestamp(3, starttime);
		stmt.setTimestamp(4, endtime);
		stmt.setString(5, location);
		stmt.setBoolean(6, isday[0]);
		stmt.setBoolean(7, isday[1]);
		stmt.setBoolean(8, isday[2]);
		stmt.setBoolean(9, isday[3]);
		stmt.setBoolean(10, isday[4]);
		stmt.execute();
	}

	/**
	 * This method will create the path with the specified places that were added in the above class
	 * @param listOfPlaces - the array list of the places that want to be added to the path
	 * @param pathing - the pathfactory to create the path on
	 * @return the string of all of the places
	 */
	public String[] createPath(ArrayList<place> listOfPlaces, pathfactory pathing) {
		place[] using = new place[listOfPlaces.size()];
		for(int i = 0; i < listOfPlaces.size(); i++) {
			using[i] = listOfPlaces.get(i);
		}
		return pathing.MPFUP(using);
	}

	/**
	 * This method will remove the specified place from the list of places
	 * @param delPlace - the place that is to be removed
	 * @return the place that was removed
	 */
	public place removePlace(place delPlace) {
		for(int i = 0; i < listOfListOfPlaces.size(); i++) {
			if(delPlace.equals(listOfListOfPlaces.get(i)) == true) {
				return listOfListOfPlaces.remove(i);
			}
		}
		return null;
	}

	/**
	 *
	 * @param editedPlace - the place that is wanting to be edited
	 * @param name - name of the place
	 * @param time - time of the event at the place
	 * @param type - type of place (lecture, lab, library, etc.)
	 * @param listOfDays - lists of days that you have the event
	 * @param location - location of the place (building, room #)
	 * @param identifier - takes a primary key to find out which part of the building to go to
	 */
	public void editPlace(place editedPlace, String name, LocalTime time, String type, Boolean[] listOfDays, String location, String identifier) {
		editedPlace = new place(name, time, type, listOfDays, location, identifier);
	}
}
