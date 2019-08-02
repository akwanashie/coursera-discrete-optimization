package javasolutions.solver;

import javasolutions.input.InputInstance;
import javasolutions.solution.Solution;
import javasolutions.solver.GenericSolver;

public class GreedySolver implements GenericSolver {
  @Override
  public Solution solve (InputInstance instance) throws InappropriateSolverError {
    int usedCapacity = 0;
    int[] variableValues = new int[instance.items.size()];
    int cumulatedValue = 0;

    for (int i = 0; i < instance.items.size(); i++) {
      usedCapacity += instance.items.get(i).weight;

      if (usedCapacity > instance.capacity) {
        break;
      }

      cumulatedValue += instance.items.get(i).value;
      variableValues[i] = 1;
    }

    return new Solution(cumulatedValue, variableValues, "GreedySolver");
  }
}