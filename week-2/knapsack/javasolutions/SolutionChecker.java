package javasolutions;

public class SolutionChecker {
  public static boolean isCorrect(Solution solution, InputInstance input) {
    int totalUsedWeight = 0;
    int totalCapacity = 0;
    for (int i = 0; i < solution.variableValues.length; i++) {
      totalUsedWeight += solution.variableValues[i] * input.items.get(i).weight;
      totalCapacity += solution.variableValues[i] * input.items.get(i).value;
    }

    return (totalUsedWeight <= input.capacity) && (totalCapacity == solution.optimalValue);
  }
}