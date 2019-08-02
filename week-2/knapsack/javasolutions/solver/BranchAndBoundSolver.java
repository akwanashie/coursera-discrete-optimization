package javasolutions.solver;

import javasolutions.solution.Solution;
import javasolutions.input.InputInstance;

public class BranchAndBoundSolver implements GenericSolver {
  public Solution solve (InputInstance instance) {
    Node rootNode = Node.create(instance.items.size());
    Node bestNode = search(rootNode, instance);
    int bestNodeValue = nodeValue(bestNode, instance);
    return new Solution(bestNodeValue, bestNode.getVariableValues(), "BranchAndBoundSolver");
  }

  private Node search (Node node, InputInstance instance) {
    if (node.isEdge()) {
      return node;
    } else {
      Node leftBranchNode = Node.branch(node, 0);
      Node leftBranchEdge = search(leftBranchNode, instance);

      Node rightBranchNode = Node.branch(node, 1);
      Node rightBranchEdge = null;
      if (nodeWeight(rightBranchNode, instance) > instance.capacity) {
        rightBranchEdge = Node.toEdge(node);
      } else {
        rightBranchEdge = search(rightBranchNode, instance);
      }

      return nodeValue(leftBranchEdge, instance) > nodeValue(rightBranchEdge, instance) ? leftBranchEdge : rightBranchEdge;
    }
  }

  private int nodeValue (Node node, InputInstance instance) {
    int totalValue = 0;
    int[] nodeVariables = node.getVariableValues();
    for (int i = 0; i <= node.getLevel(); i++) {
      totalValue += nodeVariables[i] * instance.items.get(i).value;
    }
    return totalValue;
  }

  private int nodeWeight (Node node, InputInstance instance) {
    int totalWeight = 0;
    int[] nodeVariables = node.getVariableValues();
    for (int i = 0; i <= node.getLevel(); i++) {
      totalWeight += nodeVariables[i] * instance.items.get(i).weight;
    }
    return totalWeight;
  }
}

class Node {
  private int[] variableValues;
  private int level;
  private Node parent;

  public Node (int[] variableValues, int level, Node parent) {
    this.variableValues = variableValues;
    this.level = level;
    this.parent = parent;
  }

  public int[] getVariableValues () {
    return this.variableValues;
  }

  public int getLevel () {
    return this.level;
  }

  public Node getParent () {
    return this.parent;
  }

  public boolean isEdge () {
    return this.level == this.variableValues.length - 1;
  }

  public static Node create (int numberOfVariables) {
    int[] variableValues = new int[numberOfVariables];
    for (int i = 0; i < numberOfVariables; i++) {
      variableValues[i] = -1;
    }
    return new Node(variableValues, -1, null);
  }

  public static Node branch (Node node, int value) {
    int[] variableValues = node.getVariableValues().clone();
    int level = node.getLevel() + 1;
    variableValues[level] = value;
    return new Node(variableValues, level, node);
  }

  public static Node toEdge (Node node) {
    int[] variableValues = node.getVariableValues().clone();
    for (int i = 0; i < variableValues.length; i++) {
      if (variableValues[i] == -1) {
        variableValues[i] = 0;
      }
    }
    return new Node(variableValues, variableValues.length - 1, node);
  }
}