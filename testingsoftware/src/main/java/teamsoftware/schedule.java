package teamsoftware;
import java.sql.Date;
import java.util.ArrayList;

public class schedule {
	ArrayList<String>[] listOfPaths;
	ArrayList<place>[] listOfListOfPlaces;

	/**
	 * This method will add a place to later be added to the path[?]
	 * @param a
	 * @param b
	 * @param c
	 * @param e
	 * @param f
	 */
	public void addPlace(String a, Date b, String c, Boolean[] e, String f) {
	}

	/**
	 * This method will create the path with the specified places that were added in the above class
	 * @param listOfPlaces - the array list of the places that want to be added to the path
	 * @return the string of all of the places
	 */
	public String[] createPath(ArrayList<place> listOfPlaces) {
		return null;
	}

	/**
	 * This method will remove the specified place from the list of places
	 * @param delPlace - the place that is to be removed
	 * @return the place that was removed
	 */
	public place removePlace(place delPlace) {
		return null;
	}

	/**
	 *
	 * @param editedPlace - the place that is wanting to be edited
	 * @param a
	 * @param b
	 * @param c
	 * @param e
	 * @param f
	 */
	public void editPlace(place editedPlace, String a, Date b, String c, Boolean[] e, String f) {
	}
}
