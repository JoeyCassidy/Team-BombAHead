package teamsoftware;
import java.util.ArrayList;

import cs2321.HashMap;

public class studyGroup {
	String identifier; // this has to be implemented
	HashMap<String,student> listOfStudents;
	Boolean availability;
	Boolean visibility;
	HashMap<String,post> listOfMessages;

	/**
	 * This method adds a post to the specified chat log
	 * @param newPost - the new post being added to the chat log
	 * @return the new post
	 */
	public post addToChatLog(post newPost) {
		return null;
	}

	/**
	 * This method will add a student to the chat study group chat log
	 * @param newStudent - the new student being added to the chat log
	 * @return the new student that was added
	 */
	public student addStudent(student newStudent) {
		return null;
	}

	/**
	 * This method will delete a specified student from the chat log
	 * @param delStudent - student being deleted
	 * @return the deleted student
	 */
	public student deleteStudent(student delStudent) {
		return null;
	}

	/**
	 * This method deletes a specified post from a chat log
	 * @param delPost - the post being deleted from the chat log
	 * @return the deleted post
	 */
	public post deleteChatLog(post delPost) {
		return null;
	}
}
