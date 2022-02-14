/**
 * Title: SolutionOne
 * Author: Justin Siu
 * Date: March 1st, 2021
 * Purpose: To use the greedy algorithm to solve the grocery problem. This algroithm has a run time of O(n).
 */

import java.util.Arrays;

public class SolutionOne {
	
	private static final double MAX_WEIGHT = 8.5; // the maximum weight allowed in the bag
	
	private static int [] bestAnswer = null; // the number of each item used in the optimal answer
	private static double [][] dollarPerKilo = null; // the value of each item
	private static double bestCost = 0; // the maximum cost possible to obtain
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

		// runs the algorithm once prematurely in order to give effective results for
		// run times
		init();
		find();

		// actual run times calculated from here
		init();

		long startTime = System.nanoTime();

		find();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		// prints results
		System.out.println("Time: " + duration / 1000 + " us");
		System.out.println("# method calls = " + counter);
		System.out.println("# items = " + countItems());
		System.out.println("weight of items = " + bestWeight + " kg");
		System.out.println("cost of items: $" + bestCost);

		// prints items in bag
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
	 * initializes all non constant variables needed for the algorithm
	 */
	public static void init() {
		dollarPerKilo = new double[ITEMS.length][2];
		bestAnswer = new int[ITEMS.length];
		bestCost = 0;
		bestWeight = 0;
		counter = 0;
	} // init

	/**
	 * finds the optimal bag.
	 */
	public static void find() {
		// increments the number of times this method has been called
		counter++;

		// initializes the array of item costs in $ per kg, used so that we can tell
		// which items are most cost effective
		for (int i = 0; i < ITEMS.length; i++) {
			dollarPerKilo[i][0] = i;
			dollarPerKilo[i][1] = ITEMS[i].getCost() / ITEMS[i].getWeight();
		} // for

		// sorts the array based on the cost effectiveness of items
		Arrays.sort(dollarPerKilo, (a, b) -> Double.compare(a[1], b[1]));

		// starts with the most cost effective item and adds as many as possible
		for (int i = dollarPerKilo.length - 1; i >= 0; i--) {

			// adds maximum two of each item
			for (int j = 0; j < 2; j++) {
				int index = (int) dollarPerKilo[i][0];
				bestWeight += ITEMS[index].getWeight();

				// if the weight is greater than the maximum weight, undoes changes
				if (bestWeight > MAX_WEIGHT) {
					bestWeight -= ITEMS[index].getWeight();
					return;
				} // if

				// adds the item to the bag
				bestCost += ITEMS[index].getCost();
				bestAnswer[(int) dollarPerKilo[i][0]]++;
			} // for
		} // for
	} // find
} // main
