package cst8284.asgmt2.roomScheduler;

/**  Course Name: CST8284
* @author: Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
* @version 1.0
* Class name: ContactInfo.java
* Date: March 26, 2020
*/

/** This is class ContactInfo which implements interface Serializable
 * @author Pham Thanh Hue, based on code supplied by Prof. Dave Houtman
 * @version 1.0
 */
public class ContactInfo implements java.io.Serializable{
	/** The first name, last name, phone number and organization of client */ 
	private String firstName, lastName, phoneNumber, organization;
	
    /** This is three-arg constructor ContactInfo
	 * Initializes a new instance of a ContactInfo with specified client's first name, last name, and phone number
     * @param firstName - The client’s first name
     * @param lastName - The client’s last name
     * @param phoneNumber - The client’s phone number
     */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}	
	

	/** This is three-arg constructor ContactInfo
	 * Initializes a new instance of a ContactInfo with specified client's first name, last name, phone number, and organization
	 * @param firstName - The client’s first name
	 * @param lastName - The client’s last name
	 * @param phoneNumber - The client’s phone number
	 * @param organization - The client’s organization
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
		setFirstName(firstName); setLastName(lastName); 
		setPhoneNumber(phoneNumber); setOrganization(organization);
	}	
	
    /** This is setter method setFirstName() to set first name of client, return nothing
     * @param firstName - The client’s first name
     */
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
	/** This is getter method getFirstName() to get first name of client
	 * @return firstName - Return a String represents first name of client
	 */
	public String getFirstName() {return firstName;}


	/** This is setter method setLastName() to set last name of client, return nothing
     * @param lastName - The client’s last name
     */
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	/** This is getter method getLastName() to get last name of client
	 * @return lastName - Return a String represents last name of client
	 */
	public String getLastName() {return lastName;}

	/** This is setter method setPhoneNumber() to set phone number of client, return nothing
     * @param phoneNumber - The client’s phone number
     */
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
	/** This is getter method getPhoneNumber() to get phone number of client
	 * @return phoneNumber - Return a String represents phone number of client
	 */
	public String getPhoneNumber() {return phoneNumber;}

    /** This is setter method setOrganization() to set organization of client, return nothing
     * @param organization - The client’s organization
     */
	public void setOrganization(String organization) {this.organization = organization;}
	
	/** This is getter method getOrganization() to get organization of client
	 * @return organization - Return a String represents organization of client
	 */
	public String getOrganization() {return organization;}
	
	/** This method is to override Object superclass method
	 * @return Return a String of Contact Information, Phone, and Organization
	 */
	@Override
	public String toString() {
		return "Contact Information: " +
			((getFirstName()!="")?(getFirstName() + " " + getLastName()):"") + "\n" +
			"Phone: " + getPhoneNumber() +  
			((getOrganization().equals(""))?"":("\n" +getOrganization() + "\n"));
	}
}