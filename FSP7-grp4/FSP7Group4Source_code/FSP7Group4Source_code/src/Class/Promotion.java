package Class;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represent a promotion in the restuarant.
 * A promotion item can consist of many alacarte.
 * It implements serializable to allow us to serialize and deserialize to a file (PromoItem.dat)
 * @author Sam Jian Shen
 * @version 9.0
 * @since 2019/04/01
 */
public class Promotion extends Item implements Serializable{
	
	/**
	 * The alaCartList of the promotion.
	 */
	private ArrayList<AlaCarteItem> alaCartList;
	
	/**
	 * This price of the promotion.
	 */
	private double price;
	
	/**
	 * Create a new promotion with given alacarte item/s, promotion name, promotion ID, 
	 * promotion description and promotion price.
	 * @param alaCartList This is promotion's alaCarte items.
	 * @param name This is promotion's name.
	 * @param promoID This is promotion's ID.
	 * @param promoDesc This is promotion's description.
	 * @param price This is promotion's price.
	 */
	public Promotion(ArrayList<AlaCarteItem> alaCartList, String name, int promoID, String promoDesc, double price){
		super(promoID,name,promoDesc); 
		this.alaCartList = alaCartList;
		this.name = name;  
		this.price = price;
	}
	/**
	 * Default constructor for Promotion
	 */
	public Promotion(){}
	
	/**
	 * Get the promotion's alaCarte item/s.
	 * @return this current promotion's alacarte item/s.
	 */
	public ArrayList<AlaCarteItem> alaCartList() {
		return alaCartList;
	}
	
	/**
	 * Set promotion's alacarte item/s.
	 * @param alaCartList This is set new promotion's alacarte item/s.
	 */
	public void setMenuItem(ArrayList<AlaCarteItem> alaCartList) {
		this.alaCartList = alaCartList;
	}
	
	/**
	 * Get the promotion's ID.
	 * @return promoID This is current promotion's ID.
	 */
	public int getPromoID() {
		return super.ItemID;
	}
	
	/**
	 * Set the promotion's ID.
	 * @param promoID This is new promotion's ID.
	 */
	public void setPromoID(int promoID) {
		super.ItemID = promoID;
	}
	
	/**
	 * Get the promotion's Name.
	 * @return name This promotion's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the promotion's Name.
	 * @param name This is new promotion's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the promotion's description.
	 * @return super.desc This is current promotion's description.
	 */
	public String getDesc() {
		return super.desc;
	}
	
	/**
	 * Set the promotion's description.
	 * @param promoDesc This is new promotion's description.
	 */
	public void setDesc(String promoDesc) {
		super.desc = promoDesc;
	}
	
	/**
	 * Get the promotion's price.
	 * @return this promotion's price.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set the promotion's price.
	 * @param price This is new promotion's price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}