package javasolutions.solver;

import java.util.ArrayList;
import java.util.List;
import javasolutions.input.InputInstance;
import javasolutions.solution.Solution;
import javasolutions.solver.GenericSolver;

public class MultipleSolver {
  public static Solution solve (InputInstance instance) {
    List<GenericSolver> solvers = new ArrayList<GenericSolver>();
    solvers.add(new DynamicProgrammingSolver());
    solvers.add(new GreedySolverSortedValues());
    solvers.add(new GreedySolver());

    for (GenericSolver solver : solvers) {
      try {
        return solver.solve(instance.clone());
      } catch (InappropriateSolverError ex) { }
    }

    return Solution.empty();
  }
}