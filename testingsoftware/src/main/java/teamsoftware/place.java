package teamsoftware;
import java.util.Calendar;

public class place {
	String name;
	Calendar time;
	String type;
	Boolean[] listofdays;
	String location;
	
	public place(String name, Calendar time, String type, Boolean[] listofdays, String location) {
		super();
		this.name = name;
		this.time = time;
		this.type = type;
		this.listofdays = listofdays;
		this.location = location;
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
	
	
}
