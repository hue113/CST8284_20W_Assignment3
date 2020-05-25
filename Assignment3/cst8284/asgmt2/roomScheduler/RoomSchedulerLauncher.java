package cst8284.asgmt2.roomScheduler;
import cst8284.asgmt2.room.*;

/**  Course Name: CST8284
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: RoomSchedulerLauncher.java
* Date: March 26, 2020
*/

/** This is main class RoomSchedulerLauncher
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public class RoomSchedulerLauncher {

	/** This is main method to launch program
	 * @param args - This argument that passes to the Java compile 
	 */
	public static void main(String[] args) {
		Room room = new ComputerLab();
		room.setRoomNumber("B119");
		new RoomScheduler(room).launch();
	}
	
}
