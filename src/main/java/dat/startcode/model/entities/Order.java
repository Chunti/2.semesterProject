package dat.startcode.model.entities;

public class Order {

    int orderId;
    String fullName;
    int phone;
    int length;
    int width;
    String material;
    int shedLength;
    int shedWidth;

    public Order(int orderId, String fullName, int phone, int length, int width, String material) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.phone = phone;
        this.length = length;
        this.width = width;
        this.material = material;
        shedLength = 0;
    }

    public void setShed(int shedLength,int shedWidth) {
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPhone() {
        return phone;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getMaterial() {
        return material;
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", fullName='" + fullName + '\'' +
                ", phone=" + phone +
                ", length=" + length +
                ", width=" + width +
                ", material=" + material +
                ", shedLength=" + shedLength +
                ", shedWidth=" + shedWidth +
                '}';
    }
}