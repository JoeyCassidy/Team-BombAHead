package teamsotware;
import java.sql.Date;

public class place {
	String name;
	Date time;
	String type;
	Boolean[] listofdays;
	String location;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
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
