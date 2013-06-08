import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Wheel extends Entity {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y - 50, 50, 50);
    }

    @Override
    public void update(List<Entity> entities) {
        x += 10;
        dy++;
        y += dy;
    }

}
