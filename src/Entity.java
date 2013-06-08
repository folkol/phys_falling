import java.awt.Graphics;

public abstract class Entity {
    public void update() {

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
