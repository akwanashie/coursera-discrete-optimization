package javasolutions.solution;

public class Solution {
  public int optimalValue;
  public int[] variableValues;
  public String title;

  public Solution (int optimalValue, int[] variableValues, String title) {
    this.optimalValue = optimalValue;
    this.variableValues = variableValues;
    this.title = title;
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
    return new Solution(0, new int[0], "Empty");
  }
}