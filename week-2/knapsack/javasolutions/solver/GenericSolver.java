package javasolutions.solver;

import javasolutions.solution.Solution;
import javasolutions.input.InputInstance;

public interface GenericSolver {
  public Solution solve (InputInstance instance);
}

class InappropriateSolverError extends Error {
  public InappropriateSolverError (String message) {
    super(message);
  }
}