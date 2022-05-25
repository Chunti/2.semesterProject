package dat.startcode.model.entities;

public class Shed {

    int length;
    String material;
    int userId;

    public Shed(int length, String material, int userId) {
        this.length = length;
        this.material = material;
        this.userId = userId;
    }

    public int getLength() {
        return length;
    }

    public String getMaterial() {
        return material;
    }


    @Override
    public String toString() {
        return "Shed{" +
                "length=" + length +
                ", material='" + material + '\'' +
                ", userId=" + userId +
                '}';
    }
}
