package teamsoftware;

public class setting {
	Boolean friendsCanSeePath;
	Boolean friendsCanSeeSchedule;
	Boolean friendsCanSeeFriend;
	Boolean lightMode;

	/**
	 * This class is all just getters and setters for
	 * boolean values that are the settings that are available
	 */
	public Boolean getFriendsCanSeePath() {
		return friendsCanSeePath;
	}
	public void setFriendsCanSeePath(Boolean friendsCanSeePath) {
		this.friendsCanSeePath = friendsCanSeePath;
	}
	public Boolean getFriendsCanSeeSchedule() {
		return friendsCanSeeSchedule;
	}
	public void setFriendsCanSeeSchedule(Boolean friendsCanSeeSchedule) {
		this.friendsCanSeeSchedule = friendsCanSeeSchedule;
	}
	public Boolean getFriendsCanSeeFriend() {
		return friendsCanSeeFriend;
	}
	public void setFriendsCanSeeFriend(Boolean friendsCanSeeFriend) {
		this.friendsCanSeeFriend = friendsCanSeeFriend;
	}
	public Boolean getLightMode() {
		return lightMode;
	}
	public void setLightMode(Boolean lightMode) {
		this.lightMode = lightMode;
	}
}
