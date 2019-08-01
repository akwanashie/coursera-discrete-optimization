import javasolutions.*;
import java.io.*;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to
 * solve the knapsack problem.
 *
 */
public class Solver {

	/**
	 * The main class
	 */
	public static void main(String[] args) {
		try {
			solve(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read the instance, solve it, and print the solution in the standard output
	 */
	public static void solve(String[] args) throws IOException {
		String fileName = getFileName(args);
		if (fileName == null)
			return;

		InputInstance input = InputInstance.fromFile(fileName);
		// Solution solution = GreedySolver.solve(input);
		Solution solution = GreedySolverSortedValues.solve(input);
		System.out.println(solution);
	}

	private static String getFileName(String[] args) {
		String fileName = null;

		// get the temp file name
		for (String arg : args) {
			if (arg.startsWith("-file=")) {
				fileName = arg.substring(6);
			}
		}

		return fileName;
	}
}