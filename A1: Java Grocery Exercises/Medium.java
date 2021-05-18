package a1;

import java.util.HashMap;
import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		HashMap<String, Double>itemsinstore = new HashMap<String, Double>();


		int num_of_item = scan.nextInt();
		int i = 0;
		int j = 0;
		int z = 0;

		while (i<num_of_item) {
			String item = scan.next();
			Double price = scan.nextDouble();
			itemsinstore.put(item, price);
			i++;
		}

		int num_of_c = scan.nextInt();
		// create an array here for customer names so we can go back and attach their total order sum to their name based on index
		String[] customernames = new String[num_of_c];
		double[] allordercosts = new double[num_of_c];

		while (j<num_of_c) {
			String customer_first = scan.next();
			String customer_last = scan.next();
			customernames[j] = (customer_first + " " + customer_last);
			int itemsbought = scan.nextInt();
			double[] totalordercost = new double[itemsbought];
			while (z<itemsbought) {
				// grab the values from the keys entered previously and perform calcvaluesum, then add this into totalordercost array
				double numofpurchase = scan.nextDouble();
				String purchase = scan.next();
				double spent = (itemsinstore.get(purchase)) * (numofpurchase);
				totalordercost[z] = spent;
				z++;
				// every time we grab the item name, we will look up its key and add it to the total order cost
				// when the loop is done we will perform calcvaluesum on total order cost
				// we will then create a third array to store all customers totalordercosts.
			}
			double sumprice = calculateValueSum(totalordercost);
			allordercosts[j] = sumprice;
			z = 0;
			j++;
		}
		double littlespender = findValueMin(allordercosts);
		double bigspender = findValueMax(allordercosts);
		// find the index of big and little spender and then index customer names to find
		int indexofmin = searcher(allordercosts, littlespender);
		int indexofmax = searcher(allordercosts, bigspender);
		System.out.println("Biggest: " + (customernames[indexofmax]) + " " + "(" +  String.format("%.2f", bigspender) + ")");
		System.out.println("Smallest: " + customernames[indexofmin] + " " + "(" +  String.format("%.2f",littlespender) + ")");
		System.out.println("Average: " + String.format("%.2f", calculateValueAvg(allordercosts)));
	}
	static double calculateValueSum(double[] vals) {

		double sum = 0;

		for (int i = 0; i < vals.length; i++) {
			sum += vals[i];
		}

		return sum;
	}

	static double findValueMin(double[] vals) {

		// Initialize current minimum to first value in array.
		double cur_min = vals[0];

		// Starting with second value (if any), compare each value
		// in array with current minimum and replace if smaller.

		for (int i=1; i < vals.length; i++) {
			if (vals[i] < cur_min) {
				cur_min = vals[i];
			}
		}

		return cur_min;
	}

	static double findValueMax(double[] vals) {

		// Initialize current minimum to first value in array.
		double cur_max = vals[0];

		// Starting with second value (if any), compare each value
		// in array with current minimum and replace if smaller.

		for (int i=1; i < vals.length; i++) {
			if (vals[i] > cur_max) {
				cur_max = vals[i];
			}
		}

		return cur_max;
	}
	static int searcher(double[] customers, double money)
	{
		for (int i = 0; i < customers.length; i++)
			if (customers[i] == money)
				return i;
		return 0;
	}
	static double calculateValueAvg(double[] vals) {

		double sum = 0;
		double listlength = vals.length;

		for (int i=0; i<vals.length; i++) {
			sum += vals[i];
		}

		return (sum/listlength);
	}

}
