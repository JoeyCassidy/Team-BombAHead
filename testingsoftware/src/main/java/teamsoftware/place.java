package teamsoftware;
import java.util.Calendar;

public class place {
	private String name;
	private Calendar time;
	private String type;
	private Boolean[] listofdays;
	private String location;
	private String identifier;
	
	public place(String name, Calendar time, String type, Boolean[] listofdays, String location, String identifier) {
		super();
		this.name = name;
		this.time = time;
		this.type = type;
		this.listofdays = listofdays;
		this.location = location;
		this.identifier = identifier;
	}
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
	public Boolean[] getListofdays() {
		return listofdays;
	}
	public void setListofdays(Boolean[] listofdays) {
		this.listofdays = listofdays;
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
