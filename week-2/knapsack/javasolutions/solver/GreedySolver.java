package javasolutions.solver;

import javasolutions.input.InputInstance;
import javasolutions.solution.Solution;
import javasolutions.solver.GenericSolver;

public class GreedySolver implements GenericSolver {
  @Override
  public Solution solve (InputInstance instance) throws InappropriateSolverError {
    int usedCapacity = 0;
    int[] variableValues = new int[instance.items.size()];
    int cumulatedCost = 0;

    for (int i = 0; i < instance.items.size(); i++) {
      usedCapacity += instance.items.get(i).weight;

      if (usedCapacity > instance.capacity) {
        break;
      }

      cumulatedCost += instance.items.get(i).cost;
      variableValues[i] = 1;
    }

    return new Solution(cumulatedCost, variableValues, "GreedySolver");
  }
}