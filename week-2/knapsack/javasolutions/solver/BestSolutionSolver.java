package javasolutions.solver;

import java.util.ArrayList;
import java.util.List;
import javasolutions.input.InputInstance;
import javasolutions.solution.Solution;
import javasolutions.solver.GenericSolver;

public class BestSolutionSolver {
  public static Solution solve (InputInstance instance) {
    List<GenericSolver> solvers = new ArrayList<GenericSolver>();
    solvers.add(new BranchAndBoundSolver());
    solvers.add(new DynamicProgrammingSolver());
    solvers.add(new GreedySolverSortedValues());
    solvers.add(new GreedySolver());

    List<Solution> solutions = new ArrayList<Solution>();
    for (GenericSolver solver : solvers) {
      try {
        solutions.add(solver.solve(instance.clone()));
      } catch (Error ex) { }
    }

    Solution best = solutions.get(0);
    for (Solution solution : solutions) {
      if (solution.optimalValue > best.optimalValue) {
        best = solution;
      }
    }

    return best;
  }
}