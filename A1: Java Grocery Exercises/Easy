package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int num_of_c = scan.nextInt();
		int j = 0;
		int i = 0;
		while (i < num_of_c) {
			// get customer name (DONE)
			String customer_first = scan.next();
			String customer_last = scan.next();
			// now create a method to grab the first letter of string and add in period (DONE)
			String short_name = getfirstletter(customer_first);
			// make another method to concat with last (DONE)
			// store that entire name in a variable (DONE)
			String fullname = combinenames(short_name, customer_last);

			// get amount of items bought (DONE)
			int customer_order_num = scan.nextInt();
			double[] total = new double[customer_order_num];

			while (j < customer_order_num) {
				// snag all the items (DONE)
				int num_item = scan.nextInt();
				scan.next();
				double prices = scan.nextDouble();
				double total_item_price = (num_item * prices);
				total[j] = total_item_price;
				j++;
			}
			// total the items (DONE)
			double sum_price = calculateValueSum(total);
			// move on to the next customer(DONE)
			// add in print statement here(DONE)
			String cusandprice = fullname + ":" + " " + String.format("%.2f", sum_price);
			System.out.println(cusandprice);
			j = 0;
			i++;
		}

	}

	// initial method
	static String getfirstletter(String name) {

		return (name.charAt(0) + ".");
	}

	// combines last name to first name
	static String combinenames(String first, String lastname) {
		return (first + " " + lastname);
	}

	// price adding method
	static double calculateValueSum(double[] vals) {

		double sum = 0;

		for (int i = 0; i < vals.length; i++) {
			sum += vals[i];
		}

		return sum;
	}

}
