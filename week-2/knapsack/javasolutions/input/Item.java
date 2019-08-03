package javasolutions.input;

public class Item {
  public int weight;
  public int cost;
  public int index;
  public int value;

  public Item (int cost, int weight, int index, int value) {
    this.weight = weight;
    this.cost = cost;
    this.index = index;
    this.value = value;
  }

  public Item clone () {
    return new Item(this.cost, this.weight, this.index, this.value);
  }
}