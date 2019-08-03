package javasolutions.solver;

import java.util.List;
import javasolutions.input.InputInstance;
import javasolutions.input.Item;
import javasolutions.solution.Solution;

public class DynamicProgrammingSolver implements GenericSolver {
  @Override
  public Solution solve (InputInstance instance) throws InappropriateSolverError {
    if (instance.capacity > 100000) {
      throw new InappropriateSolverError("Capacity " + instance.capacity + " is greater than 1000");
    }

    int[] variableValues = new int[instance.items.size()];
    int cumulatedCost = 0;
    int numberOfVariables = instance.items.size();
    List<Item> items = instance.items;
    int[][] grid = new int[numberOfVariables + 1][instance.capacity + 1];

    for (int i = 1; i < grid.length; i++) {
      for (int j = 1; j < grid[i].length; j++) {
        Item currentItem =  items.get(i - 1);
        int previousCost = grid[i - 1][j];

        if (j < currentItem.weight) {
          grid[i][j] = previousCost;
        } else {
          int leftOverCapacity = j - currentItem.weight;
          int leftOverValue = grid[i-1][leftOverCapacity];
          int addedCost = currentItem.cost + leftOverValue;

          if (addedCost > previousCost) {
            grid[i][j] = addedCost;
          } else {
            grid[i][j] = previousCost;
          }
        }
      }
    }

    int j = instance.capacity;
    for (int i = grid.length - 1; i > 0; i--) {
      if (grid[i][j] > grid[i-1][j]) {
        variableValues[i - 1] = 1;
        cumulatedCost += items.get(i - 1).cost;
        j -= items.get(i - 1).weight;
      }
    }

    return new Solution(cumulatedCost, variableValues, "DynamicProgrammingSolver");
  }

  private void printGrid (int[][] grid) {
    for (int i = 0; i < grid[0].length; i++) {
      for (int j = 0; j < grid.length; j++) {
        System.out.print(String.format("%-4d", grid[j][i]));
      }
      System.out.println();
    }
  }
}