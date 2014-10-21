package com.cs308.metro;
/*Main class exists to prevent the Boston Metro System to become abstract.
 *Needed to avoid issues involving references to static variables in the
 *BostonMetroSystem class.
 */
public class Main {

	public static void main(String[] args) {
		BostonMetroSystem bms = new BostonMetroSystem();
		bms.execute();

	}

}
