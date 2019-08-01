package javasolutions;

import java.util.Comparator;
import java.util.List;

public class GreedySolverSortedValues {
  public static Solution solve (InputInstance instance) {
    int usedCapacity = 0;
    int cumulatedValue = 0;
    int[] variableValues = new int[instance.items.size()];

    List<Item> items = instance.items;
    items.sort(new Comparator<Item>() {
      @Override
      public int compare(Item o1, Item o2) {
        return o1.value - o2.value;
      }
    });

    for(int i = 0; i < items.size(); i++) {
      usedCapacity += items.get(i).weight;

      if (usedCapacity > instance.capacity) {
        break;
      }

      cumulatedValue += items.get(i).value;
      variableValues[i] = 1;
    }

    return new Solution(cumulatedValue, variableValues, "Greedy");
  }
}