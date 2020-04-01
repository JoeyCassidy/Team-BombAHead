package teamsoftware;
import java.sql.Date;
import java.util.ArrayList;

public class schedule {
	ArrayList<String>[] listOfPaths;
	ArrayList<place>[] listOfListOfPlaces;

	/**
	 * This method will add a place to later be added to the path[?]
	 * @param name - name of the place
	 * @param time - time of the event at the place
	 * @param type - type of place (lecture, lab, library, etc.)
	 * @param listOfDays - lists of days that you have the event
	 * @param location - location of the place (building, room #)
	 * @param identifier - takes a primary key to find out which part of the building to go to
	 */
	public void addPlace(String name, Date time, String type, Boolean[] listOfDays, String location, String identifier) {
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
	 * @param name - name of the place
	 * @param time - time of the event at the place
	 * @param type - type of place (lecture, lab, library, etc.)
	 * @param listOfDays - lists of days that you have the event
	 * @param location - location of the place (building, room #)
	 * @param identifier - takes a primary key to find out which part of the building to go to
	 */
	public void editPlace(place editedPlace, String name, Date time, String type, Boolean[] listOfDays, String location, String identifier) {
	}
}
