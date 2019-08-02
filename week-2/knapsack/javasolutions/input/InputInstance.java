package javasolutions.input;

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

  public InputInstance clone () {
    return new InputInstance(new ArrayList<Item>(items), capacity);
  }

  public static InputInstance fromFile (String fileName) throws IOException {
    ArrayList<Item> items = new ArrayList<Item>();
    int capacity = -1;

    BufferedReader input =  new BufferedReader(new FileReader(fileName));
    try {
        String line = null;
        int index = 0;
        while ((line = input.readLine()) != null) {
          if (index == 0) {
            String[] firstLine = line.split("\\s+");
            capacity = Integer.parseInt(firstLine[1]);
          } else {
            String[] parts = line.split("\\s+");
            Item item = new Item(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), index - 1);
            items.add(item);
          }
          index++;
        }
    }
    finally {
        input.close();
    }

    return new InputInstance(items, capacity);
  }
}
