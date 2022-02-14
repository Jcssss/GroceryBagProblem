/**
 * Title: SolutionOne
 * Author: Justin Siu
 * Date: March 1st, 2021
 * Purpose: To represent items available for purchase at the store
 */

public class GroceryItem {
	private double weight; // the weight of the item in kilograms
	private double cost; // the cost of the items in 
	private String name;

	/**
	 * creates a new GroceryItem with an assigned weight, cost, and name
	 * @param w
	 * 		the weight of the item
	 * @param c
	 * 		the cost of the item
	 * @param n
	 * 		the name of the item
	 */
	public GroceryItem(double w, double c, String n) {
		weight = w;
		cost = c;
		name = n;
	} // constructor

	/**
	 * @return
	 * 		returns the weight of the item
	 */
	public double getWeight() {
		return this.weight;
	} // getWeight

	/**
	 * @return
	 * 		returns the cost of the item
	 */
	public double getCost() {
		return this.cost;
	} // getCost

	/**
	 * @return
	 * 		returns the name of the item
	 */
	public String getName() {
		return this.name;
	} // getName
} // GroceryItem
