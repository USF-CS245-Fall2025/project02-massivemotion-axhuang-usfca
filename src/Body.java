import java.awt.Color;

public class Body {
    protected int x;
    protected int y;
    protected int x_velocity;
    protected int y_velocity;
    protected int size;
    protected Color color;

    public Body(int x_value, int y_value, int x_speed, int y_speed, int size_value, Color color) {
        this.x = x_value;
        this.y = y_value;
        this.x_velocity = x_speed;
        this.y_velocity = y_speed;
        this.size = size_value;
        this.color = color;
    }

    /** move method changes the x and y values of the body
    * using the given velocities.
    * @param none
    * @return none
    */
    public void move() {
        this.x += this.x_velocity;
        this.y += this.y_velocity;
    }
}
