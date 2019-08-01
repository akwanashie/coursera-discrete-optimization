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
        double diff = (o2.value / (1.0 * o2.weight)) - (o1.value / (1.0 * o1.weight));
        if (diff < 0) return -1;
        else if (diff > 0) return 1;
        else return 0;
      }
    });

    for(int i = 0; i < items.size(); i++) {
      Item item = items.get(i);
      usedCapacity += item.weight;

      if (usedCapacity > instance.capacity) {
        break;
      }

      cumulatedValue += item.value;
      variableValues[item.index] = 1;
    }

    return new Solution(cumulatedValue, variableValues, "GreedySolverSortedValues");
  }
}