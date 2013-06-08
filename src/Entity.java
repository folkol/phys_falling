import java.awt.Graphics;
import java.util.List;

public abstract class Entity {
    public void update(List<Entity> entities) {

    }
    abstract void draw(Graphics g);

    public int x;
    public int y;
    public int dx;
    public int dy;

    public boolean collides(Entity e) {
        return false;
    }
}
