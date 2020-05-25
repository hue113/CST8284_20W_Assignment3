package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: RoomBooking.java
* Date: March 26, 2020
*/

/** This is class RoomBooking which implements interface Serializable
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public class RoomBooking implements java.io.Serializable {
	
	/** Version identifier for a Serializable class */
	public static final long serialVersionUID = 1L;
	
	/** This variable is declared for contact information of client */
	private ContactInfo contactInfo;
	
	/** This variable is declared for activity of a booking */
	private Activity activity;
	
	/** This variable is declared for time block of a booking */
	private TimeBlock timeBlock;
	
	
    /** This is three-arg constructor RoomBooking takes objects ContactInfo, Activity, TimeBlock as parameters
     * @param contactInfo - This parameter contains contact information of client who booked this room booking 
     * @param activity - This parameter contains booking's activity details
     * @param timeBlock - This parameter contains time of a booking
     */  
	public RoomBooking(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		setContactInfo(contactInfo); setActivity(activity); setTimeBlock(timeBlock);
	}
	
	/** This is no-arg constructor RoomBooking()
	 * Initializes a new instance of a RoomBooking
	 */
	public RoomBooking() {}


	/** This setter method setContactInfo() is to set contact information of client who booked a room booking, return nothing
	 * @param contactInfo - The client’s contact information
	 */
	public void setContactInfo(ContactInfo contactInfo) {this.contactInfo = contactInfo;}
	
	/** This getter method is getContactInfo() to get contact information of client who booked a room booking
	 * @return Return a ContactInfo object that contains information about client who booked a room
	 */
	public ContactInfo getContactInfo() {return contactInfo;}
	

	/** This setter method setActivity() is to set activity of client (return nothing)
	 * @param activity This argument is to set activity of client 
	 */
	public void setActivity(Activity activity) {this.activity = activity;}
	
	
	/** This getter method is getActivity() to get activity details of a booking
	 * @return Return an Activity that user entered
	 */
	public Activity getActivity() {return activity;}
	

	/** This setter method setTimeBlock() is to set time block for a booking (return nothing)
	 * @param timeBlock - This argument is to set time block for a booking
	 */
	public void setTimeBlock(TimeBlock timeBlock) {this.timeBlock = timeBlock;}
	
	
	/** This is getter method getRoomNumber() to get room number of room
	 * @return timeBlock - Return an object TimeBlock
	 */
	public TimeBlock getTimeBlock() {return timeBlock;}

	
	/** This method is to override Object superclass method
	 * @return Return a String of time (starting and ending time), activity (Event, Description), and contact information (Name, Phone, Organization)
	 */
	@Override public String toString() {
		return (getTimeBlock().toString() + getActivity().toString() +  getContactInfo().toString());
	}

}
