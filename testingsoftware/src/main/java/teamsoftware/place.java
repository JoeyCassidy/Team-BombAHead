package teamsoftware;
import java.util.Calendar;

public class place {
	private String name;
	private Calendar time;
	private String type;
	private Boolean[] listOfDays;
	private String location;
	private String identifier;

	/**
	 * This method will generate a 'place' (node on the map) to help generate the path (constructor for place)
	 * @param name - name of the place
	 * @param time - time of the event at the place
	 * @param type - type of place (lecture, lab, library, etc.)
	 * @param listOfDays - lists of days that you have the event
	 * @param location - location of the place (building, room #)
	 * @param identifier - takes a primary key to find out which part of the building to go to
	 */
	public place(String name, Calendar time, String type, Boolean[] listOfDays, String location, String identifier) {
		super();
		this.name = name;
		this.time = time;
		this.type = type;
		this.listOfDays = listOfDays;
		this.location = location;
		this.identifier = identifier;
	}

	/**
	 *These methods are just getters and setters for all of the variables
	 */

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean[] getListOfDays() {
		return listOfDays;
	}
	public void setlistOfDays(Boolean[] listOfDays) {
		this.listOfDays = listOfDays;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
}
