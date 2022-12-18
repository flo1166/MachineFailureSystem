package simulation;

import java.util.Random;

import machine.Machine;
import machine.StandardMachine;

/**
 * This class simulates a given problem.
 * @author Florian Korn
 *
 */
public class Simulation {

	/**
	 * The main method.
	 * @param args
	 */
	public static void main(String[] args) {
		Machine[] machines = new Machine[3];
		// standard machine
		machines[0] = new StandardMachine("A", 0.2, 0.3);
		machines[1] = new StandardMachine("B", 0.3, 0.6);
		machines[2] = new StandardMachine("C", 0.8, 0.8);
		
		// priority machine
//		machines[0] = new PriorityMachine("A", 0.2, 0.3);
//		machines[1] = new PriorityMachine("B", 0.3, 0.6);
//		machines[2] = new PriorityMachine("C", 0.8, 0.8);
		
		// count of replications for simulation
		int T = 10000;
		
		simulation(T, machines);   
	}
	
	/**
	 * This method initializes random series of numbers and returns an array filled with them
	 * @return a sequence with random probabilities bounded between 0 and 1
	 */
	public static double[] randomDouble(int T) {
		long seed = 1234554321;
		Random randomObj = new Random(seed);
		double[] randomSeq = new double[T];
		
		for (int i = 0; i < T; i++) {
			randomSeq[i] = randomObj.nextDouble(1) + 0.01;
		}
		
		return randomSeq;
	}
	
	/**
	 * This method simulates a given problem.
	 * @param T repetition of simulation
	 * @param machines set of machines to solve the problem
	 */
	public static void simulation(int T, Machine[] machines) {
		
		// initialize counters
		double countAllUp = 0;
		double countOne = 0;
		double countUpA = 0;
		double countUpB = 0;
		double countUpC = 0;
		
		double[] ranDouble = randomDouble(T * machines.length);
		
		// solve problem
		for (int i = 0; i < T; i ++) {
			for (int j = 0; j < machines.length; j++) {
				
				// check if running or not
				if (machines[j].isRunning() && ranDouble[i * 3 + j] < machines[j].getF()) {
					machines[j].running = false;
				} else if (!machines[j].isRunning() && ranDouble[i * 3 + j] < machines[j].getR()){
					machines[j].running = true;
				}
				
				// set counters for one machine
				if (machines[j].running) {
					if (machines[j].getName() == "A") {
							countUpA += 1;
						} else if (machines[j].getName() == "B") {
							countUpB += 1;
						} else {
							countUpC += 1;
						}
				}
			}
			
			// set counters for all machines
			if(Machine.checkAllUp(machines)) {
				countAllUp += 1;
			}
			
			if(Machine.checkOneUp(machines)) {
				countOne += 1;
			}
		}
		
		// print everything
		printOut(countUpA, countUpB, countUpC, countAllUp, countOne, T);
		printCheck(machines);
	}
	
	/**
	 * The print function of all availabilities.
	 * @param countUpA the count of available machines A
	 * @param countUpB the count of available machines B
	 * @param countUpC the count of available machines C
	 * @param countAllUp the count of all machines available
	 * @param countOne the count of one machine available
	 * @param T the count of simulation repetitions
	 */
	public static void printOut(double countUpA, double countUpB, double countUpC, double countAllUp, double countOne, int T) {
		System.out.println("------------------Simulation:------------------");
		System.out.println("Availability of machine A: " + Machine.calcAvailMachines(countUpA, T));
		System.out.println("Availability of machine B: " + Machine.calcAvailMachines(countUpB, T));
		System.out.println("Availability of machine C: " + Machine.calcAvailMachines(countUpC, T));
		System.out.println("Availability of all machines: " + Machine.calcAvailMachines(countAllUp, T));
		System.out.println("Availability of at least one machine: " + Machine.calcAvailMachines(countOne, T));
	}
	
	/**
	 * This method prints the check for the simulation.
	 * @param machines a set of machines to check the simulation
	 */
	public static void printCheck(Machine[] machines) {
		System.out.println("------------------Check:------------------");
		for (Machine machine : machines) {
			System.out.println("Check availability machine " + machine.getName() + ": " + AvailabilityMethods.calcAvailOneMachine(machine));
		}
		System.out.println("Check availability machine: " + AvailabilityMethods.calcAvailAllMachines(machines));
		System.out.println("Check availability of at least one machine: " + AvailabilityMethods.calcAvailOnePlus(machines));
	}
}
