package dat.startcode.model.entities;

public class Carport {

    int length;
    int width;
    String material;
    int userId;

    public Carport(int length, int width,String material, int userId) {
        this.length = length;
        this.width = width;
        this.material = material;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Carport{" +
                "length=" + length +
                ", width=" + width +
                ", material='" + material + '\'' +
                ", userId=" + userId +
                '}';
    }
}
