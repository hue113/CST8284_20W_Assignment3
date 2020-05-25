/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: SortRoomBookingsByCalendar.java
* Date: March 26, 2020
*/

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Comparator;

/** This is class SortRoomBookingsByCalendar which implements interface Comparator
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public class SortRoomBookingsByCalendar implements Comparator<RoomBooking> {

 
	/** This method compare() is to override superclass method
	 * @return Return an integer represents the difference between the Calendar’s of the two booking’s input in parameters
	 */
    @Override
    public int compare(RoomBooking o1, RoomBooking o2) {
       return  (int) ChronoUnit.HOURS.between(o1.getTimeBlock().getStartTime().toInstant(), o2.getTimeBlock().getStartTime().toInstant());
    }

  
}
