import java.awt.Graphics;

public class Wheel extends Entity {

    public Wheel() {
        x = 100;
        y = 300;
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(x - 25, y - 25, 50, 50);
    }

    public void update(Grass grass) {
        x += 10;
        dy++;
        y += 0.5 * dy;
        if (dy > 0 && y > grass.getGrassHeight(x)) {
            dy = (int) -(dy * 0.5);
            y = grass.getGrassHeight(x);
        }
    }
}
