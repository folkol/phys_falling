import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Tile extends Entity {
    Random random = new Random();

    public Tile() {
        x = random.nextInt(1000);
        y = 800 - Math.min(random.nextInt(300), random.nextInt(300));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 100, 100);
    }
}
