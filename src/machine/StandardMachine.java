package machine;

import java.util.Random;

/** 
 * This class represents the standard machine, which extends the machine object
 * @author Florian Korn
 *
 */

public class StandardMachine extends Machine {

	/**
	 * Super parameter constructor.
	 * @param name of machine
	 * @param f the probability to fail at the beginning of each period, if it was running at the end of the period before
	 * @param r the probability to be repaired at the beginning of the period
	 */
	public StandardMachine(String name, double f, double r) {

		super(name, f, r);
	}
	
	/**
	 * Random generator for standard machines
	 */
	public double[] RandomGenerator(int T) {
		
		long seed = 1234554321;
		Random randomObj = new Random(seed);
		double[] randomSeq = new double[T];
		
		for (int i = 0; i < T; i++) {
			randomSeq[i] = randomObj.nextDouble(1) + 0.01;
		}
		
		return randomSeq;
	}
}
