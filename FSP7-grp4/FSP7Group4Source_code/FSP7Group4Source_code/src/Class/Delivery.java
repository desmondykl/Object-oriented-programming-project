package Class;

/**
 * The program will have a new function called Order Delivery.
 * To implement this function, we will create a new class called delivery.java.
 * Delivery extends order and it will inherit all its attributes. 
 * However, delivery has its own attribute called address. 
 * 
 */
public class Delivery extends Ordering{
	/**
	 * The address of the particular customer
	 * @return address The address of the customer
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * The address of the particular customer
	 * @param address The address of the customer
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	String address; 
}


