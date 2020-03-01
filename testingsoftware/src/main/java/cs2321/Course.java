package cs2321;

import java.util.Arrays;
import java.util.Collections;

/**
 * solves the course problem with a brute force solution that is more efficient then dynamic programming
 * 
 * Course: CS2321 Section ALL
 * Assignment: #8
 * @author Grant Walker
 */

/**
 * @author Ruihong Zhang
 * Reference: Text book: R14.17 on page 678
 *
 */
public class Course {

	HashMap<String,String[]> register = new HashMap<String,String[]>();
	HashMap<String,Integer> accounting = new HashMap<String,Integer>();
	
	/**
	 * @param courses: An array of course information. Each element in the array is also array:
	 * 				starts with the course name, followed by a list (0 or more) of prerequisite course names.
	 * 
	 */
	public Course(String courses[][]) {
		for(int i = 0; i < courses.length; i++) {
			if(courses[i].length>1) {
//				if the class has prereqs seperate the clas from the prereqs and put them in the hash map
				register.put(courses[i][0], Arrays.copyOfRange(courses[i], 1, courses[i].length));
			} else {
//				if it doesnt have any prereqs then just put it in the hash map
				register.put(courses[i][0], new String[0]);
			}
		}
	}
	
	/**
	 * @param course
	 * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites. 
	 */
	@TimeComplexity("O(n)")
	public int whichSemester(String course) {
		/* TCJ
		 * the true time complexity of this algorithm is somewhere between 1 and n since it remembers it previous results and reuses them
		 * so once it has calculated a semesters result it never has to do so again. 
		 */
//		if we have already calculated this class just go get its value
		if(accounting.get(course)!=null) {
			return accounting.get(course);
		}
//		if this class cant be calculated further just record its vlaue and reutrn it
		if(register.get(course).length==0) {
			accounting.put(course, 1);
			return 1;
		}
//		creates a list to store the values
		Integer[] holding = new Integer[register.get(course).length];
//		calculates the value of each of the prereqs
		for(int i = 0; i < holding.length; i++) {
			holding[i] = whichSemester(register.get(course)[i]);
		}
//		stores the value of the class
		accounting.put(course, Collections.max(Arrays.asList(holding))+1);
//		returns the max value of the prereqs plus one
		return Collections.max(Arrays.asList(holding))+1;
	}
			
}
