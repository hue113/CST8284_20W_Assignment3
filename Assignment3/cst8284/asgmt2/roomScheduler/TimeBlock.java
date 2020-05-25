package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: TimeBlock.java
* Date: March 26, 2020
*/

import java.util.Calendar;

/** This is class TimeBlock which implements interface Serializable
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public class TimeBlock implements java.io.Serializable{
	
	/** The start time and end time of a room booking */
	private Calendar startTime, endTime;
	
	
	/** This is no-arg constructor TimeBlock()
	 * Initializes a new instance of a TimeBlock
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
				new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 24).build());
	}
	
	
	/** This is two-arg constructor TimeBlock
     * @param start - The starting time of TimeBlock
     * @param end - The ending time of TimeBlock
     * Creates a time block with the specified starting time and ending time.
     */ 
	public TimeBlock(Calendar start, Calendar end) {
		setStartTime(start); setEndTime(end);
	}
	

	/** This method is to override Object superclass method
	 * @return Return a String of start time and end time (Eg: 15:00 - 17:00)
	 */
	@Override
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00\n";
	}

	
    /** This is setter method setStartTime() to set starting time of a time block, return nothing
     * @param startTime - The starting time of TimeBlock
     */
	public void setStartTime(Calendar startTime) {this.startTime = startTime;}
	

	/** This is getter method getStartTime() to get starting time of a time block
	 * @return Return a Calendar represents starting time
	 */
	public Calendar getStartTime() {return startTime;}

	
    /** This is setter method setEndTime() to set ending time of a time block, return nothing
     * @param endTime - The ending time of TimeBlock
     */
	public void setEndTime(Calendar endTime) {this.endTime = endTime;}
	
	
	/** This is getter method getEndTime() to get ending time of a time block
	 * @return Return a Calendar represents ending time
	 */
	public Calendar getEndTime() {return endTime;}
	
	
    /** This method duration() calculates duration or length of each booking
     * @return Return an integer value represents duration of each booking
     */
	public int duration() {
		return (getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY));
	}
	
	
	/** This method overlaps() check whether a booking's time (that users wish to use) overlaps with existing bookings
	 * @param tb - This argument contains date and time about a booking that needs to be checked whether it overlaps with existing bookings
	 * @return Return true or false (true if it overlaps)
	 */
	public boolean overlaps(TimeBlock tb) {
		if ((tb.getStartTime().get(Calendar.DAY_OF_YEAR) != this.getStartTime().get(Calendar.DAY_OF_YEAR))
				|| (tb.getStartTime().get(Calendar.YEAR) != this.getStartTime().get(Calendar.YEAR))) 
			return false;  // can't overlap; not on same date
	    return ((tb.getStartTime().get(Calendar.HOUR_OF_DAY) < getEndTime().get(Calendar.HOUR_OF_DAY))
				&& (tb.getEndTime().get(Calendar.HOUR_OF_DAY) > getStartTime().get(Calendar.HOUR_OF_DAY))) ;
	    // same date, but do the two timeblocks overlap?
	}
	
}