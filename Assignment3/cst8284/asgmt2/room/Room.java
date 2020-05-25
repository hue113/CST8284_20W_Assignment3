package cst8284.asgmt2.room;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: Room.java
* Date: March 26, 2020
*/

/** This is super class Room which will be inherited by its subclasses
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public abstract class Room {
	
	/** Version identifier for a Serializable class */
	public static final long serialVersionUID=1L;
	
	/** The default room number of a room */ 
	private static final String DEFAULT_ROOM = "unknown room number";
	
	/** The room number of a room */ 
	private String roomNumber;
	
	
	/** This is no-arg constructor Room()
	 * Initializes a new instance of a room
	 */
	protected Room() {this(DEFAULT_ROOM);}
	
	
	/** This is one-arg constructor Room
	 * @param roomNum - This is room number of this room
	 */
	protected Room(String roomNum) {setRoomNumber(roomNum);}
	

	/** This is setter method setName to set room number of room, no return
	 * @param roomNum - This is room number of room that need to be set
	 */
	public void setRoomNumber(String roomNum) {roomNumber = roomNum;}
	
	
	/** This is getter method getRoomNumber() to get room number of room
	 * @return roomNumber - Return a String represents room number of room
	 */
	public String getRoomNumber() {return roomNumber;}
	

	/** This is an abstract method getSeats() to get number of seats of room
	 * This method will be overridden in subclasses 
	 * @return Return an integer represents number of seats of room
	 */
    protected abstract int getSeats();
    
    
	/** This is an abstract method getRoomType() to get type of room
	 * This method will be overridden in subclasses 
	 * @return Return a String represents type of room
	 */
    protected abstract String getRoomType();
    
    
	/** This is an abstract method getDetails() to get details of room
	 * This method will be overridden in subclasses 
	 * @return Return a String represents details of room
	 */
	protected abstract String getDetails();
	
	
	/** This method is to override Object superclass method
	 * @return Return a String of room number, room type, number of seats, and details of that room
	 */
	public String toString( ){return getRoomNumber() + " is a " +
		getRoomType() + " with " + getSeats() + " seats; " + getDetails();}
}