import java.awt.Graphics;

public class Wheel extends Entity {

    @Override
    public void draw(Graphics g) {
        g.fillOval(x - 25, y - 25, 50, 50);
    }

    @Override
    public void update() {
        x += 10;
        dy++;
        y += dy;
    }

}
