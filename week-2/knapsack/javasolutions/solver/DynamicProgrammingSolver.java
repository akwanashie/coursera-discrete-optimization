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
    int cumulatedValue = 0;
    int numberOfVariables = instance.items.size();
    List<Item> items = instance.items;
    int[][] grid = new int[numberOfVariables + 1][instance.capacity + 1];

    for (int i = 1; i < grid.length; i++) {
      for (int j = 1; j < grid[i].length; j++) {
        Item currentItem =  items.get(i - 1);
        int previousValue = grid[i - 1][j];

        if (j < currentItem.weight) {
          grid[i][j] = previousValue;
        } else {
          int leftOverCapacity = j - currentItem.weight;
          int leftOverValue = grid[i-1][leftOverCapacity];
          int addedValue = currentItem.value + leftOverValue;

          if (addedValue > previousValue) {
            grid[i][j] = addedValue;
          } else {
            grid[i][j] = previousValue;
          }
        }
      }
    }

    int j = instance.capacity;
    for (int i = grid.length - 1; i > 0; i--) {
      if (grid[i][j] > grid[i-1][j]) {
        variableValues[i - 1] = 1;
        cumulatedValue += items.get(i - 1).value;
        j -= items.get(i - 1).weight;
      }
    }

    return new Solution(cumulatedValue, variableValues, "DynamicProgrammingSolver");
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