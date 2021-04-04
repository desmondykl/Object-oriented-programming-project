package Class;

import java.io.Serializable;

/**
 * Item parent class for AlaCarteItem and Promotion
 * It implements serializable to allow us to serialize and deserialize to a file
 * @author Yeo Kok Leong Desmond
 * @version 9.0
 * @since 2019/04/01
 */
public class Item implements Serializable{
    /**
     * This is the name of the item
     */
    public String name;
    /**
     * This is the description of the item 
     */
    public String desc;
    /**
     * This is the ID for AlacarteItem/Promotion
     */
    public int ItemID;

    /**
     * ID that uniquely identify AlacarteItem/Promotion objects
     * @return return the ItemID
     */
    public int getItemID() {
        return ItemID;
    }
    /**
     * ID that uniquely identify AlacarteItem/Promotion objects
     * @param itemID Set the itemID for AlacarteItem/Promotion objects
     */
    public void setItemID(int itemID) {
        ItemID = itemID;
    }
    /**
     * 
     * @return get the Name for AlacarteItem/Promotion objects
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @param name set the name AlacarteItem/Promotion objects
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @return get the description AlacarteItem/Promotion objects
     */
    public String getDesc() {
        return desc;
    }
    /**
     * 
     * @param desc set the description AlacarteItem/Promotion objects
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Item Constructor to create an item object
     * @param ItemID unique identifier for the item
     * @param name the item's name 
     * @param desc the item's description 
     */
    public Item(int ItemID, String name, String desc){
        this.desc=desc;
        this.ItemID=ItemID;
        this.name=name;

    }
    /**
     * Default Constructor
     */
    public Item() {}
}