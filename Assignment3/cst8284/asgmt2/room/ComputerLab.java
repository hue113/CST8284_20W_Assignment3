package cst8284.asgmt2.room;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0 
* Class name: ComputerLab.java
* Date: March 26, 2020
*/

/** This is class ComputerLab which inherits from Room class
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public final class ComputerLab extends Room{

    /** The default number of seats of computer lab */ 
	private static final int DEFAULT_SEATS=30;
	
    /** The number of seats of computer lab */ 
	private int seats;
	
	
	/** This is no-arg constructor ComputerLab()
	 * Initializes a new instance of a computer lab
	 */
	public ComputerLab() {seats = DEFAULT_SEATS;}


	/** This is getter method getSeats() to get number of seats of computer lab
	 * @return seats - Return an integer represents number of seats of computer lab
	 */
	protected int getSeats(){return seats;}
	
	
	/** This is getter method getRoomType() to get room type of computer lab
	 * @return Return a String represents room type of computer lab
	 */
	protected String getRoomType(){return "computer lab";}
	
	
	/** This is getter method getDetails() to get room details of computer lab
	 * @return Return a String represents details of computer lab
	 */
	protected String getDetails(){return "contains outlets for 30 laptops";}

}
