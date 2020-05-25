package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: RoomScheduler.java
* Date: March 26, 2020
*/

import cst8284.asgmt2.room.Room;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;

/** This class RoomScheduler is the control center for the entire program
* @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
*/
public class RoomScheduler {

	/** This variable is declared a room of type Room */
    private Room room;
    
    /** Object Scanner helps user input data */
    private static Scanner scan = new Scanner(System.in);
    
	/** This is an ArrayList that stores all room bookings */
    private ArrayList<RoomBooking> roomBookings = new ArrayList<>();

    /** This is the option to Display Room Information in the Menu */
    private static final int DISPLAY_ROOM_INFORMATION = 1;
    
    /** This is the option to Enter Room Booking in the Menu */
    private static final int ENTER_ROOM_BOOKING = 2;
    
    /** This is the option to Delete Room Booking in the Menu */
    private static final int DELETE_BOOKING = 3;
    
    /** This is the option to Change Room Booking in the Menu */
    private static final int CHANGE_BOOKING = 4;
    
    /** This is the option to Display Room Booking in the Menu */
    private static final int DISPLAY_BOOKING = 5;
    
    /** This is the option to Display Day Bookings in the Menu */
    private static final int DISPLAY_DAY_BOOKINGS = 6;
    
    /** This is the option to Save Bookings to File in the Menu */
    private static final int SAVE_BOOKINGS_TO_FILE = 7;
    
    /** This is the option to Load Bookings from File in the Menu */
    private static final int LOAD_BOOKINGS_FROM_FILE = 8;
    
    /** This is the option to Exit the program in the Menu */
    private static final int EXIT = 0;

    
    /** This is one-arg constructor RoomScheduler
     * @param room - This argument contains the room for this RoomScheduler object
     */
    public RoomScheduler(Room room) {
        setRoom(room);
    }

    
    /** This is setter method setRoom() to set name of Room, no return
     * @param room - This argument is to set name of Room
     * 
     */
    private void setRoom(Room room) {
        this.room = room;
    }


	/** This is getter method getRoom() to get information of Room
	 * @return Return Room which includes information about room
	 */
    private Room getRoom() {
        return room;
    }

 
	/** This void method launch() displays menu and execute it based on user's choice, return nothing
	 */
    public void launch() {
        int choice = 0;
        if (new File("CurrentRoomBookings.book").exists()) {
            loadBookingsFromFile();
        }
        do {
            choice = displayMenu();
            executeMenuItem(choice);
        } while (choice != EXIT);
    }


	/** This method displayMenu() displays menu of the program
	 * @return Return an integer that represents user's choice of the menu
	 */
    private int displayMenu() {
        System.out.println("Enter a selection from the following menu:");
        System.out.println(
                DISPLAY_ROOM_INFORMATION + ". Display room information\n"
                + ENTER_ROOM_BOOKING + ". Enter a room booking\n"
                + DELETE_BOOKING + ". Remove a room booking\n"
                + CHANGE_BOOKING + ". Change a room booking\n"
                + DISPLAY_BOOKING + ". Display a booking\n"
                + DISPLAY_DAY_BOOKINGS + ". Display room bookings for the whole day\n"
                + SAVE_BOOKINGS_TO_FILE + ". Backup current bookings to file\n"
                + LOAD_BOOKINGS_FROM_FILE + ". Load current bookings from file\n"
                + EXIT + ". Exit program");
        int ch = scan.nextInt();
        scan.nextLine();  // 'eat' the next line in the buffer
        System.out.println(); // add a space before next menu output
        return ch;
    }

    
    /** This void method executeMenuItem() is to execute the menu based on user's choice, return nothing
     * @param choice - This parameter is for the option that the user can choose from the menu
     */
    private void executeMenuItem(int choice) {
        switch (choice) {
            case DISPLAY_ROOM_INFORMATION:
                displayRoomInfo();
                break;
            case ENTER_ROOM_BOOKING:
                saveRoomBooking(makeBookingFromUserInput());
                break;
            case DELETE_BOOKING:
                System.out.println("Enter booking to delete");
                System.out.println(deleteBooking(makeCalendarFromUserInput(null, true))
                        ? "Booking deleted" : "Booking not deleted");
                break;
            case CHANGE_BOOKING:
                System.out.println("Enter booking to change");
                if (!changeBooking(makeCalendarFromUserInput(null, true))) {
                    System.out.println("Cannot change room booking");
                }
                break;
            case DISPLAY_BOOKING:
                displayBooking(makeCalendarFromUserInput(null, true));
                break;
            case DISPLAY_DAY_BOOKINGS:
                displayDayBookings(makeCalendarFromUserInput(null, false));
                break;
            case SAVE_BOOKINGS_TO_FILE:
                System.out.println(saveBookingsToFile()
                        ? "Current room bookings backed up to file"
                        : "Could not backup room bookings to file");
                break;
            case LOAD_BOOKINGS_FROM_FILE:
                System.out.println(loadBookingsFromFile() != null
                        ? "Current room bookings loaded from file"
                        : "Room bookings could not be loaded from file");
                break;
            case EXIT:
                System.out.println("Exiting Room Booking Application\n\n");
                break;
            default:
                System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
        }
        System.out.println();  // add blank line after each output
    }

    
    /** This is a getter method getResponseTo() print a String and returns a scanner to get input from user
     * @param s - This argument is a message that needs to be printed for users to see
     * @return Return a scanner to ask for input from user
     */
    private static String getResponseTo(String s) {
        System.out.print(s);
        return (scan.nextLine());
    }

    /** This method check whether the value passed on parameter is number or not
     * and catch exception
     * @param item This argument contain a char that needs to be check whether it's number or not
     * @return Return true or false (true if the parameter is a number)
     */
    private static boolean checkIsNumber(char item) {
        try {
            Integer.decode(item + "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    /** This method makeBookingFromUserInput() makes booking based on user input
     * It catches errors from user's input if input is not in correct format
     * @return Return a new RoomBooking which contains information of client (contact information, booking's activity, and booking's time)
     */
    private static RoomBooking makeBookingFromUserInput() {
        String[] fullName;
        List<String> list;
        boolean check = false;
        do {
            check = false;
            fullName = getResponseTo("Enter Client Name (as FirstName LastName): ").split(" ");
            list = Arrays.asList(fullName);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).length()<1||list.get(i).contains("*") || list.get(i).contains("-") || list.get(i).contains(".") || fullName.length > 60) {
                    check = true;
                    BadRoomBookingException nameException = new BadRoomBookingException("Name contains illegal characters", "A name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (‘)");
                    System.out.println(nameException.getMessage());
                    break;
                }
            }
        } while (check);
        String phoneNumber;
        do {
            check = false;
            phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
            if (phoneNumber.length() != 12) {
                check = true;

            }
            if (check != true) {
                for (int i = 0; i < phoneNumber.length(); i++) {
                    if (RoomScheduler.checkIsNumber(phoneNumber.charAt(i))) {

                    } else {
                        if (i != 3 && i != 7) {
                            check = true;
                        } else if (phoneNumber.charAt(i) != '-') {
                            {
                                check = true;
                            }
                        }
                    }
                }
            }
            if (check == true) {
                BadRoomBookingException nameException = new BadRoomBookingException("Bad telephone number", "The telephone number must be a 10-digit number,\n" + "separated by ‘-‘ in the form, e.g. 613-555-1212.");
                System.out.println(nameException.getMessage());
            }
        } while (check);

        String organization, category, description;
        do {
            check = false;
            organization = getResponseTo("Organization (optional): ");
            if (organization.length()<1) {
                check = true;
                BadRoomBookingException nameException = new BadRoomBookingException("Missing value", "Missing an input value");
                System.out.println(nameException.getMessage());
            }
        } while (check);
        do {
            check = false;
            category = getResponseTo("Enter event category: ");
            if (category.length()<1) {
                check = true;
                BadRoomBookingException nameException = new BadRoomBookingException("Missing value", "Missing an input value");
                System.out.println(nameException.getMessage());
            }
        } while (check);
        do {
            check = false;
            description = getResponseTo("Enter detailed description of event: ");
            if (description.length()<1) {
                check = true;
                BadRoomBookingException nameException = new BadRoomBookingException("Missing value", "Missing an input value");
                System.out.println(nameException.getMessage());
            }
        } while (check);

        Calendar startCal = makeCalendarFromUserInput(null, true);
        Calendar endCal = makeCalendarFromUserInput(startCal, true);
        while (startCal.get(Calendar.HOUR_OF_DAY) >= endCal.get(Calendar.HOUR_OF_DAY)) {

            if (startCal.get(Calendar.HOUR_OF_DAY) > endCal.get(Calendar.HOUR_OF_DAY)) {
                BadRoomBookingException nameException = new BadRoomBookingException("End time precedes start time", "The room booking start time must occur before the end time of the room booking.");
                System.out.println(nameException.getMessage());
            } else {
                BadRoomBookingException nameException = new BadRoomBookingException("Zero time room booking", "Start and end time of the room booking are the same. The minimum time for a room booking is one hour.");
                System.out.println(nameException.getMessage());
            }
            endCal = makeCalendarFromUserInput(startCal, true);
        }

        ContactInfo contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber, organization);
        Activity activity = new Activity(category, description);
        TimeBlock timeBlock = new TimeBlock(startCal, endCal);
        return (new RoomBooking(contactInfo, activity, timeBlock));
    }

    
    /** This method makeCalendarFromUserInput() create a booking (Calendar) based on input information from users
     * Throw BadRoomBookingException if date format is not correct
     * @param initCal - This argument describes the date that user input
     * @param requestHour - This argument if true, will ask users to enter time
     * @return Return a Calendar date that contains DD MM YYYY from user input
     */
    private static Calendar makeCalendarFromUserInput(Calendar initCal, boolean requestHour) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        String date = "";
        int hour = 0;
        boolean needNewCal = (initCal == null);

        if (needNewCal) {
            do {
                date = getResponseTo("Event Date (entered as DDMMYYYY): ");
                if (date.length() > 8) {
                    BadRoomBookingException nameException = new BadRoomBookingException("Bad Calendar format", "Bad calendar date was entered. The correct format is DDMMYYYY.");
                    System.out.println(nameException.getMessage());
                }
            } while (date.length() > 8);
        }
        int day = needNewCal ? Integer.parseInt(date.substring(0, 2)) : initCal.get(Calendar.DAY_OF_MONTH);
        int month = needNewCal ? Integer.parseInt(date.substring(2, 4)) - 1 : initCal.get(Calendar.MONTH);
        int year = needNewCal ? Integer.parseInt(date.substring(4, 8)) : initCal.get(Calendar.YEAR);
        int yearNow = Calendar.getInstance().get(Calendar.YEAR);
        if (needNewCal) {
            boolean check = false;
            if ((day == 31 && month == 4) || (yearNow + 10 <= year) || (month > 12) || (year < yearNow) || date.length() > 8) {
                check = true;
                BadRoomBookingException nameException = new BadRoomBookingException("Bad Calendar format", "Bad calendar date was entered. The correct format is DDMMYYYY.");
                System.out.println(nameException.getMessage());
            }
            while (check) {
                check = false;
                date = getResponseTo("Event Date (entered as DDMMYYYY): ");
                if (date.length() <= 8) {
                    day = needNewCal ? Integer.parseInt(date.substring(0, 2)) : initCal.get(Calendar.DAY_OF_MONTH);
                    month = needNewCal ? Integer.parseInt(date.substring(2, 4)) - 1 : initCal.get(Calendar.MONTH);
                    year = needNewCal ? Integer.parseInt(date.substring(4, 8)) : initCal.get(Calendar.YEAR);
                    if ((day == 31 && month == 4) || (yearNow + 10 <= year) || (month > 12) || (year < yearNow)) {
                        check = true;
                        BadRoomBookingException nameException = new BadRoomBookingException("Bad Calendar format", "Bad calendar date was entered. The correct format is DDMMYYYY.");
                        System.out.println(nameException.getMessage());
                    }
                } else {
                    check = true;
                    BadRoomBookingException nameException = new BadRoomBookingException("Bad Calendar format", "Bad calendar date was entered. The correct format is DDMMYYYY.");
                    System.out.println(nameException.getMessage());
                }
            };
        }
        if (requestHour) {
            hour = processTimeString(getResponseTo((needNewCal ? "Start" : "End") + " Time: "));
        }
        cal.set(year, month, day, hour, 0);
        return (cal);
    }

    
    /** This method processTimeString() is to process time input from user: time format of 12 hours or 24 hours
     * @param t - This is original time that user entered
     * @return Return an Integer represents time that has been processed
     */
    private static int processTimeString(String t) {
        int hour = 0;
        t = t.trim();
        if (t.contains("pm") || (t.contains("p.m."))) {
            hour = Integer.parseInt(t.split(" ")[0]) + 12;
        }
        if (t.contains("am") || t.contains("a.m.")) {
            hour = Integer.parseInt(t.split(" ")[0]);
        }
        if (t.contains(":")) {
            hour = Integer.parseInt(t.split(":")[0]);
        }
        return hour;
    }


    
    /** This method findBooking() find booking by date
     * creates a new class SortRoomBookingByCalendar that implements the Comparator interface
     * @param cal - This argument contains date and time for a booking to be looked for
     * @return Return a Room Booking that matches calendar inside parameter
     */
    private RoomBooking findBooking(Calendar cal) {
        Calendar oneHourLater = Calendar.getInstance();
        oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
        TimeBlock findTB = new TimeBlock(cal, oneHourLater);
        RoomBooking rb = new RoomBooking();
        rb.setTimeBlock(findTB);
//        for (RoomBooking rb : getRoomBookings()) {
//            if (rb.getTimeBlock().overlaps(findTB)) {
//                return rb;
//            }
//        }
        int index = Collections.binarySearch(getRoomBookings(),
                rb, new SortRoomBookingsByCalendar());
        if (index != -1) {
            return getRoomBookings().get(index);
        }
        return null;
    }

    /** This method displayRoomInfo() display Room information, return nothing
     * It prints a String of room information
     */
    private void displayRoomInfo() {
        System.out.println(getRoom().toString());
    }
    

    /** This method saveRoomBooking() check whether a booking is saved successfully
     * @param roomBooking - This argument describes the room booking that needs to be saved
     * @return Return true or false (true if it was saved successfully)
     */
    private boolean saveRoomBooking(RoomBooking roomBooking) {
        TimeBlock tb = roomBooking.getTimeBlock();  // Check this TimeBlock to see if already booked
        Calendar cal = (Calendar) tb.getStartTime().clone(); // use its Calendar
        int hour = cal.get(Calendar.HOUR_OF_DAY);//Get first hour of block
        for (; hour < tb.getEndTime().get(Calendar.HOUR_OF_DAY); hour++) { //Loop through each hour in TimeBlock
            cal.set(Calendar.HOUR_OF_DAY, hour); // set next hour
            if (findBooking(cal) != null) {  // TimeBlock already booked at that hour, can't add appointment
                System.out.println("Cannot save booking; that time is already booked");
                return false;
            }
        }  // else time slot still available; continue loop to next hour
        getRoomBookings().add(roomBooking);
        Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());
        System.out.println("Booking time and date saved.");
        return true;
    }

    /** This method displayBooking() displays a room booking based on date and time from the parameter
     * @param cal - This argument contains date and time for a booking that need to be displayed
     * @return booking - Return a RoomBooking
     */
    private RoomBooking displayBooking(Calendar cal) {
        RoomBooking booking = findBooking(cal);
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        System.out.print((booking != null)
                ? "---------------\n" + booking.toString() + "---------------\n"
                : "No booking scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n"
        );
        return booking;
    }

    /** This method deleteBooking() allow users to delete a booking 
     * @param cal - This argument contains date and time for a booking to be deleted
     * @return Return true or false (true if booking is deleted successfully)
     */
    private boolean deleteBooking(Calendar cal) {
        RoomBooking rb = displayBooking(cal);
        if (rb != null) {
            if (getResponseTo("Press 'Y' to confirm deletion, "
                    + "any other key to abort: ").toUpperCase().equals("Y")) {
                getRoomBookings().remove(rb);
                return true;
            }
        }
        return false;
    }
    
    
    /** This method changeBooking() allow users to change a booking 
     * @param cal - This argument contains date and time for a booking to be changed
     * @return Return true or false (true if booking is changed successfully)
     */
    private boolean changeBooking(Calendar cal) {
        RoomBooking rb = displayBooking(cal);  // existing RoomBooking
        if (rb == null) {
            return false;
        }
        Calendar startCal = makeCalendarFromUserInput(cal, false);
        startCal.set(Calendar.HOUR_OF_DAY, processTimeString(getResponseTo("Enter New Start Time: ")));
        Calendar endCal = makeCalendarFromUserInput(cal, false);
        endCal.set(Calendar.HOUR_OF_DAY, processTimeString(getResponseTo("Enter New End Time: ")));
        TimeBlock tb = new TimeBlock(startCal, endCal);  // new TimeBlock
        for (RoomBooking rbook : getRoomBookings()) // check this won't collide with existing TimeBlock
        {
            if (!rbook.equals(rb)
                    && // ignore rb already in ArrayList
                    (rbook.getTimeBlock().overlaps(tb))) {
                return false;
            }
        }
        System.out.println("Booking has been changed to:");
        rb.setTimeBlock(tb);
        return (displayBooking(startCal) != null);
    }
    

    /**  This method displayDayBookings() displays booking for the whole day for users, return nothing
     * @param cal - This argument take a Calendar date from user input
     */
    private void displayDayBookings(Calendar cal) {
        for (int hrCtr = 8; hrCtr < 24; hrCtr++) {
            cal.set(Calendar.HOUR_OF_DAY, hrCtr);
            RoomBooking rb = displayBooking(cal);
            if (rb != null) {
                hrCtr = rb.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY) - 1;
            }
        }
    }

    
	/** This method saveBookingsToFile() saves booking from users to file
	 * @return Return true or false (true if booking is saved successfully)
	 */
    private boolean saveBookingsToFile() {
        try (FileOutputStream fos = new FileOutputStream("CurrentRoomBookings.book");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            for (RoomBooking rb : getRoomBookings()) {
                oos.writeObject(rb);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /** This method loadBookingsFromFile() loads room bookings from file
	 * @return ArrayList of RoomBooking
	 */
    private ArrayList<RoomBooking> loadBookingsFromFile() {
        ArrayList<RoomBooking> ar = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("CurrentRoomBookings.book");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            while (true) {
                ar.add((RoomBooking) (ois.readObject()));
            }
        } catch (EOFException ex) {
            return ar;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    
    /** ArrayList of RoomBooking is to add bookings into ArrayList
	 * @return roomBookings - Return room bookings from the this RoomScheduler object
	 */
    private ArrayList<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

}
