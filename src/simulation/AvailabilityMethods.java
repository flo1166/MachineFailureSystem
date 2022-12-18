package simulation;

import machine.Machine;

/**
 * This class implements the different methods to calculate the availability of machines.
 * @author Florian Korn
 *
 */
public class AvailabilityMethods {

	/**
	 * This method calculates the availability of a single machine.
	 * @param r the probability to be repaired at the beginning of the period
	 * @param f the probability to fail at the beginning of each period, if it was running at the end of the period before
	 * @return the availability of a single machine
	 */
	public static double calcAvailOneMachine(Machine machines) {
		
		double availability = machines.getR() / (machines.getR() + machines.getF());
		
		return availability;
	}
	
	/**
	 * This method calculates the availability of a set of machines.
	 * @param machines is a set of machines to calculate with
	 * @return the availability of all machines in a set
	 */
	public static double calcAvailAllMachines(Machine[] machines) {
		double availability = 1;
		
		for (int i = 0; i < machines.length; i++) {
			availability = availability * calcAvailOneMachine(machines[i]);
		}
		
		return availability;
	}
	
	/**
	 * This method calculates the probability of at minimum one machine available.
	 * @param machines is a set of machines to calculate with
	 * @return the availability of at least one machine available
	 */
	public static double calcAvailOnePlus(Machine[] machines) {
		double availability = 1;
		
		for (int i = 0; i < machines.length; i++) {
			availability = availability * (1 - calcAvailOneMachine(machines[i]));
		}
		
		availability = 1 - availability;
				
		return availability;
	}
}
