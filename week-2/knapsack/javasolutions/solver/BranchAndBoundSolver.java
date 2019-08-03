package javasolutions.solver;

import javasolutions.solution.Solution;
import javasolutions.input.InputInstance;
import javasolutions.input.Item;

public class BranchAndBoundSolver implements GenericSolver {
  private Node currentBest;
  private int currentIterations = 0;
  private int MAX_ITERATIONS = 10000000;

  public Solution solve (InputInstance instance) {
    Node rootNode = Node.create(instance);
    this.currentBest = rootNode;

    Node bestNode = search(rootNode);
    return new Solution(bestNode.totalCost(), bestNode.instance.values(), "BranchAndBoundSolver");
  }

  private boolean iterationsComplete () {
    return this.currentIterations >= this.MAX_ITERATIONS;
  }

  private Node search (Node node) {
    this.currentIterations++;
    if (node.isEdge() || node.capacityRelaxation() < currentBest.totalCost() || iterationsComplete()) {
      return node;
    } else {
      Node leftBranchNode = node.branch(0);
      Node leftBranchEdge = search(leftBranchNode);

      Node rightBranchNode = node.branch(1);
      Node rightBranchEdge = null;
      if (rightBranchNode.totalWeight() > node.instance.capacity) {
        rightBranchEdge = node.toEdge();
      } else {
        rightBranchEdge = search(rightBranchNode);
      }

      Node betterNode = leftBranchEdge.totalCost() > rightBranchEdge.totalCost() ? leftBranchEdge : rightBranchEdge;
      this.currentBest = betterNode.totalCost() > this.currentBest.totalCost() ? betterNode : this.currentBest;
      return betterNode;
    }
  }
}

class Node {
  public InputInstance instance;
  private int level;

  public Node (InputInstance instance, int level) {
    this.instance = instance;
    this.level = level;
  }

  public boolean isEdge () {
    return this.level == this.instance.size() - 1;
  }

  public Node toEdge () {
    return new Node(this.instance.clone(), this.instance.size() - 1);
  }

  public Node branch (int value) {
    InputInstance clonedInstance = this.instance.clone();
    int level = this.level + 1;
    clonedInstance.items.get(level).value = value;
    return new Node(clonedInstance, level);
  }

  public int totalCost () {
    int totalCost = 0;
    for (Item item : this.instance.items) {
      totalCost += item.value * item.cost;
    }
    return totalCost;
  }

  public int totalWeight () {
    int totalWeight = 0;
    for (Item item : this.instance.items) {
      totalWeight += item.value * item.weight;
    }
    return totalWeight;
  }

  public int capacityRelaxation () {
    InputInstance clonedInstance = this.instance.clone();
    for (int i = this.level + 1; i < this.instance.size(); i++) {
      clonedInstance.items.get(i).value = 1;
    }
    return new Node(clonedInstance, clonedInstance.size()).totalCost();
  }

  public static Node create (InputInstance instance) {
    return new Node(instance.clone(), -1);
  }
}