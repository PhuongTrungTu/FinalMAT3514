package Service.components;

public class Color{
    int r = 0;
    int b = 0;
    int g = 0;

    public Color(int red , int blue , int green) {
        this.r = red;
        this.b = blue;
        this.g = green;
    }

    public Color() {
    }

    public int[] color(){
        return new int[]{ r , b , g };
    }
}
