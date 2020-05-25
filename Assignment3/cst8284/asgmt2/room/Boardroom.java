package cst8284.asgmt2.room;
/**  Course Name: CST8284
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: Boardroom.java
* Date: March 26, 2020
*/


/** This is class BoardRoom which inherits from Room class
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public final class Boardroom extends Room{

	/** The number of seats of class room */ 
	private int seats;
	
	
	/** This is no-arg constructor Boardroom()
	 * Initializes a new instance of a board room
	 */
	public Boardroom() {seats = 16;}
	
	
	/** This is getter method getSeats() to get number of seats of board room
	 * @return seats - Return an integer represents number of seats of board room
	 */
	protected int getSeats() {return seats;}
	
	
	/** This is getter method getRoomType() to get room type of board room
	 * @return Return a String represents room type of board room
	 */
	protected String getRoomType(){return "board room";}
	
	
	/** This is getter method getDetails() to get room details of board room
	 * @return Return a String represents details of board room
	 */
	protected String getDetails(){return "conference call enabled";}
}
