import javasolutions.InputInstance;
import javasolutions.GreedySolver;
import javasolutions.GreedySolverSortedValues;
import javasolutions.Solution;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
		if (fileName == null) return;

		InputInstance input = InputInstance.fromFile(fileName);

		if (debugEnabled()) {
			List<Solution> solutions = new ArrayList<Solution>();
			solutions.add(GreedySolver.solve(input.clone()));
			solutions.add(GreedySolverSortedValues.solve(input.clone()));

			for (Solution solution: solutions) {
				System.out.println(solution.title + ": " + solution);
			}
		} else {
			Solution solution = GreedySolverSortedValues.solve(input.clone());
			System.out.println(solution);
		}
	}

	private static boolean debugEnabled() {
		return System.getenv("DEBUG") != null && System.getenv("DEBUG").equals("true");
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