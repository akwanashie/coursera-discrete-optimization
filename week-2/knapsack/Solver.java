import java.io.IOException;
import java.util.List;

import javasolutions.input.InputInstance;
import javasolutions.solution.Solution;
import javasolutions.solution.SolutionChecker;
import javasolutions.solver.AllSolutionsSolver;
import javasolutions.solver.BestSolutionSolver;

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
			List<Solution> solutions = AllSolutionsSolver.solve(input.clone());
			for (Solution solution: solutions) {
				System.out.println(solution.title + ": " + solution);
			}

			String outputLine1 = String.format("%-40s %-20s %-20s", "Algorithm", "isCorrect", "OptimalValue");
			System.out.println(outputLine1);

			for (Solution solution: solutions) {
				boolean isSolutionCorrect = SolutionChecker.isCorrect(solution, input);
				String outputLine2 = String.format("%-40s %-20s %-20d", solution.title, isSolutionCorrect, solution.optimalValue);				System.out.println(outputLine2);
			}
		} else {
			Solution solution = BestSolutionSolver.solve(input.clone());
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