package javasolutions;

public class Solution {
  public int optimalValue;
  public int[] variableValues;

  public Solution (int optimalValue, int[] variableValues) {
    this.optimalValue = optimalValue;
    this.variableValues = variableValues;
  }

  public String toString() {
    StringBuilder outputBuilder = new StringBuilder();
    outputBuilder.append(optimalValue + " 0 \r\n");

    for(int i=0; i < variableValues.length; i++){
      outputBuilder.append(variableValues[i] + " ");
    }
    outputBuilder.append("");

    return outputBuilder.toString();
  }

  public static Solution empty() {
    return new Solution(0, new int[0]);
  }
}