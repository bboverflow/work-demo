package cn.trusteye.work;

/**
 * @Author: rayman
 * @Description:
 * @Date: Create in 2017/12/25 18:10
 * @Modified By:
 */
public class Apple {
    private String color ;
    private int weight;
    private String style;


    public Apple(String color, int weight, String style) {
        this.color = color;
        this.weight = weight;
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }


    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", style='" + style + '\'' +
                '}';
    }
}
