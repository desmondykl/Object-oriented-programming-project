package Class;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represent a Alacarte item in the restuarant.
 * It implements serializable to allow us to serialize and deserialize to a file (MenuItem.dat)
 * @author Sam Jian Shen
 * @version 9.0
 * @since 2019/04/01
 */
public class AlaCarteItem extends Item implements Serializable {
	/**
	 * The Alacarte item's price in the restaurant.
	 */
	private double price; 

	/**
	 * The Alacarte item's category in the restaurant.
	 */
	private Type cat;
	
	/**
	 * Create a new alacarte item with given alacarte item's name, price, ID and category.
	 * @param name This is alacarte item's name.
	 * @param price This is alacarte item's price.
	 * @param alaID This is alacarte item's ID.
	 * @param desc This is alacarte item's description.
	 * @param cat This is alacarte item's category.
	 */ 
	public AlaCarteItem(String name, double price, int alaID, String desc, Type cat) {
		super(alaID,name,desc);
		this.price = price;
		this.cat = cat;
	}
	
	/**
	 * Create a new alacarte item object
	 */
	public AlaCarteItem() {}
	
	/**
	 * Get the alacarte item's name.
	 * @return This alacarte item's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the alacarte item's name.
	 * @param name This is the new alacarte item's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the alacarte item's price.
	 * @return this alacarte item's price.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set the alacarte item's price.
	 * @param price This is the new alacarte item's price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Get the alacarte item's ID.
	 * @return this alacarte item's ID.
	 */
	public int getMenuID() {
		return super.ItemID;
	}
	
	/**
	 * Set the alacarte item's ID.
	 * @param alaID This is the new alacarte item's ID.
	 */
	public void setMenuID(int alaID) {
		super.ItemID = alaID;
	}
	
	/**
	 * Get the alacarte item's description.
	 * @return this alacarte item's description.
	 */ 
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Set the alacarte item's description.
	 * @param desc This is the new alacarte item's description.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Get the alacarte item's category.
	 * @return This alacarte item's category.
	 */
	public Type getCat() {
		return cat;
	}
	
	/**
	 * Set the alacarte item's category.
	 * @param cat This is the new alacarte item's category.
	 */
	public void setCat(Type cat) {
		this.cat = cat;
	} 
		
}
