package javasolutions;

public class GreedySolver {
  public static Solution solve (int[] values, int[] weights, int maxCapacity) {
    int usedCapacity = 0;
    int[] variableValues = new int[weights.length];
    int cumulatedValue = 0;

    for (int i = 0; i < weights.length; i++) {
      usedCapacity += weights[i];

      if (usedCapacity > maxCapacity) {
        break;
      }

      cumulatedValue += values[i];
      variableValues[i] = 1;
    }

    return new Solution(cumulatedValue, variableValues);
  }
}