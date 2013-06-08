import java.awt.Graphics;

public class Wheel extends Entity {

    private static final double BOUNCYNESS = 0.3;
    private static final double GRAVITY = 0.5;
    public float ACCELERATION = 0.8f;
    public float MAX_SPEED = 15;
    public float SPEED = 0;
    public static final int WHEEL_SIZE = 75;
    private static final float RETARDATION = 0.2f;
    public boolean forward = false;

    public Wheel() {
        x = 0;
        y = 500;
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(x - (WHEEL_SIZE / 2), y - (WHEEL_SIZE / 2), WHEEL_SIZE, WHEEL_SIZE);
    }

    public void update(Grass grass) {
        if (SPEED > MAX_SPEED) SPEED = MAX_SPEED;
        x += SPEED;
        dy += GRAVITY;
        y += dy;
        int grassHeight = grass.getGrassHeight(x);
        if (dy > 0 && y > grassHeight) {
            if (forward) {
                SPEED += ACCELERATION;
            } else {
                SPEED -= RETARDATION;
            }
            dy = (int) -(dy * BOUNCYNESS);
            // if (dy < 0.5) dy = 0;
            y = grassHeight;
        }
    }
}
