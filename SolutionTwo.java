/**
 * Title: SolutionOne
 * Author: Justin Siu
 * Date: March 1st, 2021
 * Purpose: To recursively determine the optimal solution to the grocery problem. The algorithm works backwards, starting with a bag full of every item and then removing items until the conditions are met. This algorithm has a run time of O(nPr).
 */

public class SolutionTwo {
	
	private static final double MAX_WEIGHT = 8.5; // the maximum weight allowed in the bag
	
	private static int [] bestAnswer = null; // the number of each item used in the optimal answer
	private static double startCost = 0; // the cost of the bag if you placed every item in it
	private static double startWeight = 0; // the weight of the bag if you placed every item in it
	private static double maxCost = 0; // the maximum cost possible to obtain
	private static double bestWeight = 0; // the weight of the best bag
	private static int counter = 0; // counts the number of method calls

	// The types of Grocery items that you could purchase
	private static final GroceryItem [] ITEMS = { 
		new GroceryItem(2.0, 15.0, "chicken"), 
		new GroceryItem(1.0, 2.50, "flour"),
		new GroceryItem(0.25, 2.40, "beans"), 
		new GroceryItem(0.5, 1.20, "cereal"), 
		new GroceryItem(0.3, 3.99, "cookies"),
		new GroceryItem(1.0, 0.99, "soda"), 
		new GroceryItem(0.3, 4.99, "spam"), 
	};

	public static void main(String[] args) {

		int[] used = new int[ITEMS.length]; // the items used in the bag
		bestAnswer = new int[ITEMS.length];

		// our solution first fills the bag with every item, and then removes items
		// until the bag weighs less than or equal to the maximum weight
		for (int i = 0; i < bestAnswer.length; i++) {
			used[i] = 2;
		} // for

		// calculates the starting weight and cost of a full bag
		startWeight = calculateStartWeight();
		startCost = calculateStartCost();

		long startTime = System.nanoTime();

		// the recursive algorithm
		find(used, startWeight, startCost);

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		// print data
		System.out.println("Time: " + duration / 1000 + " us");
		System.out.println("# method calls = " + counter);
		System.out.println("# items = " + countItems());
		System.out.println("weight of items = " + bestWeight + " kg");
		System.out.println("cost of items = $" + maxCost);

		// print items
		System.out.println();
		System.out.println("Items in bag:");

		// prints the best answer
		for (int i = 0; i < bestAnswer.length; i++) {
			System.out.println("   " + bestAnswer[i] + " x " + ITEMS[i].getName());
		} // for
	} // main

	/**
	 * counts the number of items in the final bag
	 * 
	 * @return returns the number of items in the bag
	 */
	public static int countItems() {
		int total = 0;

		for (int i = 0; i < bestAnswer.length; i++) {
			total += bestAnswer[i];
		} // for

		return total;
	} // countItems

	/**
	 * calculates the cost of a bag with two of every item in it
	 * 
	 * @return returns the total cost of the bag
	 */
	public static double calculateStartCost() {
		double total = 0;

		for (int i = 0; i < ITEMS.length; i++) {
			total += 2 * ITEMS[i].getCost();
		} // for

		return total;
	} // calculateStartCost

	/**
	 * calculates the weight of a bag with two of every item in it
	 * 
	 * @return returns the total weight of the bag
	 */
	public static double calculateStartWeight() {
		double total = 0;

		for (int i = 0; i < ITEMS.length; i++) {
			total += 2 * ITEMS[i].getWeight();
		} // for

		return total;
	} // calculateStartWeight

	/**
	 * recursively removes items from the bag until the bag ways less than or equal
	 * to the maximum bag weight allowed.
	 * 
	 * @param used
	 *            the number of each item currently in the bag
	 * @param weight
	 *            the current weight of the bag
	 * @param cost
	 *            the current cost of all items in the bag
	 */
	public static void find(int[] used, double weight, double cost) {

		counter++;

		// if the cost of the bag is less than the maximum cost, saves time by exiting
		if (cost > maxCost) {

			// if the current weight is less than or equal to the allowed weight, than this
			// bag is our new best answer;
			if (weight <= MAX_WEIGHT) {

				// copies the current bag into the answer
				for (int i = 0; i < used.length; i++) {
					bestAnswer[i] = used[i];
				} // for

				maxCost = cost;
				bestWeight = weight;
				return;
			} // if

			int[] copy = new int[used.length];

			// copies our used array to pass into later method calls
			for (int i = 0; i < used.length; i++) {
				copy[i] = used[i];
			} // for

			// removes an item, attempts all possible ways
			for (int i = 0; i < 2 * copy.length; i++) {

				// checks that there is an item to remove
				if (copy[i / 2] != 0) {

					// recalculates weight, cost, and removes the item
					copy[i / 2]--;
					weight -= ITEMS[i / 2].getWeight();
					cost -= ITEMS[i / 2].getCost();

					find(copy, weight, cost);

					// recalculates weight, cost and adds the item back
					copy[i / 2]++;
					weight += ITEMS[i / 2].getWeight();
					cost += ITEMS[i / 2].getCost();
				} // if
			} // for
		} // if
	} // find

} // Groceries