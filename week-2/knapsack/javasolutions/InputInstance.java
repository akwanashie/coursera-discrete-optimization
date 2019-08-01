package javasolutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputInstance {
  public List<Item> items;
  public int capacity;

  public InputInstance (List<Item> items, int capacity) {
    this.items = items;
    this.capacity = capacity;
  }

  public static InputInstance fromFile (String fileName) throws IOException {
    ArrayList<Item> items = new ArrayList<Item>();
    int capacity = -1;

    BufferedReader input =  new BufferedReader(new FileReader(fileName));
    try {
        String line = null;
        while ((line = input.readLine()) != null) {
          if (capacity == -1) {
            String[] firstLine = line.split("\\s+");
            capacity = Integer.parseInt(firstLine[1]);
          } else {
            String[] parts = line.split("\\s+");
            Item item = new Item(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            items.add(item);
          }
        }
    }
    finally {
        input.close();
    }

    return new InputInstance(items, capacity);
  }
}

class Item {
  public int weight;
  public int value;

  public Item (int value, int weight) {
    this.weight = weight;
    this.value = value;
  }
}
