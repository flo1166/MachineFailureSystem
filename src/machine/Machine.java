package machine;

/**
 * This class represents a machine in the machine failure simulation problem
 * @author Florian Korn
 */
public abstract class Machine {
	
	/**
	 * the name of a machine
	 */
	protected String name;
	
	/**
	 * the probability to fail at the beginning of each period, if it was running at the end of the period before
	 */
	private double f;
	
	/**
	 * the probability to be repaired at the beginning of the period
	 */
	private double r;
	
	/**
	 * A boolean to decide if machine is running (true) or not (false)
	 */
	public boolean running;
	
	/**
	 * This is a full parameter constructor.
	 * @param f the probability to fail at the beginning of each period, if it was running at the end of the period before
	 * @param r the probability to be repaired at the beginning of the period
	 */
	public Machine(String name, double f, double r) {
		super();
		this.name = name;
		this.f = f;
		this.r = r;
		this.running = true;
	}
	
	/**
	 * 
	 * @return the probability to fail (if it was running at the end of the previous period)
	 */
	public double getF() {
		return f;
	}
	
	/**
	 * 
	 * @return the probability to be repaired (at the beginning of the period, if failed at the end of the previous period)
	 */
	public double getR() {
		return r;
	}
	
	/**
	 * 
	 * @return if machine is running (true) or not (false)
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * 
	 * @return the name of the machine
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method calculates the overall availability of a machine during a simulation.
	 * @param countUpA is the count of the {@link Machine}, which it was avaialable through the simulation.
	 * @param T is the count of repetition for this simulation.
	 * @return the overall availability of a machine during a simulation
	 */
	public static double calcAvailMachines(double countUpA, int T) {
		
		double availability = countUpA / T;
		
		return availability;
	}
	
	/**
	 * This method checks if all machines are running.
	 * @param machines set of machines to calculate with
	 * @return true if all are running, false if at least one is not running
	 */
	public static boolean checkAllUp(Machine[] machines) {
		
		boolean running = true;
		
		for (Machine machine : machines) {
			if (!machine.running) {
				return false;
			}
		}
		
		return running;
	}
	
	/**
	 * Check if at least one is up.
	 * @param machines set of machines to calculate with
	 * @return true if at least one is up, false if not
	 */
	public static boolean checkOneUp(Machine[] machines) {
		
		boolean running = false;
		
		for (Machine machine : machines) {
			if (machine.running) {
				return true;
			}
		}
		
		return running;
	}
	
	/**
	 * This is a abstract class to change the random generator depending on class object
	 * @param T the count of repetition for the simulation
	 * @return a sequence with random variables
	 */
	public abstract double[] RandomGenerator(int T);
}
