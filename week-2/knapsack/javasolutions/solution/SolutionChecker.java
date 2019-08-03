package javasolutions.solution;

import javasolutions.input.InputInstance;

public class SolutionChecker {
  public static boolean isCorrect(Solution solution, InputInstance input) {
    int totalUsedWeight = 0;
    int totalCost = 0;
    for (int i = 0; i < solution.variableValues.length; i++) {
      totalUsedWeight += solution.variableValues[i] * input.items.get(i).weight;
      totalCost += solution.variableValues[i] * input.items.get(i).cost;
    }

    return (totalUsedWeight <= input.capacity) && (totalCost == solution.optimalValue);
  }
}