package cst8284.asgmt2.room;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: Classroom.java
* Date: March 26, 2020
*/

/** This is class Classroom which inherits from Room class
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public final class Classroom extends Room{

	/** The default number of seats of the classroom */ 
	private static final int DEFAULT_SEATS=120;
	
	/** The number of seats of class room */ 
	private int seats;
	
	
	/** This is no-arg constructor Classroom()
	 * Initializes a new instance of a class room
	 */
	public Classroom(){seats = DEFAULT_SEATS;}
	
	
	/** This is getter method getSeats() to get number of seats of class room
	 * @return seats - Return an integer represents number of seats of class room
	 */
	protected int getSeats(){return seats;}
	
	
	/** This is getter method getRoomType() to get room type of class room
	 * @return Return a String represents room type of class room
	 */
	protected String getRoomType(){return "classroom";}
	
	
	/** This is getter method getDetails() to get room details of class room
	 * @return Return a String represents details of class room
	 */
	protected String getDetails(){return "contains overhead projector";}
}
