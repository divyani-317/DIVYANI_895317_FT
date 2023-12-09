import java.util.ArrayList;
import java.util.List;

public class Box extends Item {
    private List<Item> objectItems;
    private int size;

    public Box(int _size, int _number) {
        super("Box " + _number);
        this.size = _size;
        objectItems = new ArrayList<>();
    }

    public List<Item> getItems() {
        return objectItems;
    }

    public void addItem(Item item) {
        if (objectItems.size() < size) {
            objectItems.add(item);
        } else {
            System.out.println("No space remaining!");
        }
    }
}
