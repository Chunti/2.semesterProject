package dat.startcode.model.entities;

public class BoMItems {

    int orderId;
    int itemId;
    int amount;

    String name;
    int length;
    String description;
    String unit;
    int price;

    public BoMItems(int orderId, int itemId, int amount, String name, int length, String description, String unit, int price) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
        this.name = name;
        this.length = length;
        this.description = description;
        this.unit = unit;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }
}
