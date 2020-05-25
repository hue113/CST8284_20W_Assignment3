/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: BadRoomBookingException.java
* Date: March 26, 2020
*/

/** This is class BadRoomBookingException which extends from RuntimeException
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
*/
public class BadRoomBookingException extends RuntimeException {

	/** This variable is declared header for BadRoomBookingException */
    private String header;

	/** This is no-arg constructor BadRoomBookingException()
	 * Initializes a new instance of BadRoomBookingException with default message
	 */
    public BadRoomBookingException() {
        this("Bad room rooking entered", "Please try again");
    }


    /** This is two-arg constructor BadRoomBookingException
     * @param header - The second String is stored to a private String header
     * @param message - Use super() to pass the String to the superclass
     */    
    public BadRoomBookingException(String header, String message) {
        super(message);
        this.header = header;
    }

	/** This is setter method setHeader to set header of BadAppointmentDataException, return nothing 
	 * @param header - This argument is to describe the header of BadAppointmentDataException
	 */
    public void setHeader(String header) {
        this.header = header;
    }
    
	/** This is getter method getHeader() to get header of BadAppointmentDataException
	 * @return header - Return a String represents header of BadAppointmentDataException
	 */
    public String getHeader() {
        return header;
    }
    
    
}
