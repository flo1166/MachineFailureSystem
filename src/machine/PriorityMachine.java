package machine;

import java.util.Random;

import org.apache.commons.math3.distribution.TriangularDistribution;

/** 
 * This class represents the priority machine, which extends the machine object
 * @author Florian Korn
 *
 */

public class PriorityMachine extends Machine {

	/**
	 * Super parameter constructor.
	 * @param name of machine
	 * @param f the probability to fail at the beginning of each period, if it was running at the end of the period before
	 * @param r the probability to be repaired at the beginning of the period
	 */
	public PriorityMachine(String name, double f, double r) {
		
		super(name, f, r);
	}
	
	/**
	 * Random generator for priority machines
	 */
	public double[] RandomGenerator(long seed, int T) {
		
		// initialize random
		Random randomObj = new Random(seed);
		
		// initialize variables
		double[] randomSeq = new double[T];
		double a = 0;
		double c = 0.2;
		double b = 1;
		
		// Triangular distribution
		TriangularDistribution triangular = new TriangularDistribution(a, c, b);
	    
	    // calculate
		for (int i = 0; i < T; i++) {
			randomSeq[i] = triangular.cumulativeProbability(randomObj.nextDouble());
		}
		
		// return
		return randomSeq;
	}
}
