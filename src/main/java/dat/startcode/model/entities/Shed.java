package dat.startcode.model.entities;

public class Shed {

    int length;
    int width;
    int height;
    String material;
    int userId;

    public Shed(int length, int width, int height, String material, int userId) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.material = material;
        this.userId = userId;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMaterial() {
        return material;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Shed{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", material='" + material + '\'' +
                ", userId=" + userId +
                '}';
    }
}
