import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FallingCar extends JFrame {

    private Wheel wheel1;
    private Grass grass;
    private Wheel wheel2;
    private int centerX;
    private int centerY;

    public FallingCar() {
        super("Physics test");
        setContentPane(new ViewPort());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);
        setVisible(true);
        requestFocus();
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    initWheels();
                }
            }
        });

        initWheels();

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWorld();
                repaint();
            }
        }).start();
    }

    private void initWheels() {
        wheel1 = new Wheel();
        wheel2 = new Wheel();
        wheel1.x += 200;
        wheel1.y = 300;
        wheel2.y = 300;
        grass = new Grass();
    }

    protected void updateWorld() {
        wheel1.update(grass);
        wheel2.update(grass);
        restrictWheelDistance(wheel1, wheel2);
    }

    private void restrictWheelDistance(Wheel wheel1, Wheel wheel2) {

        centerX = (wheel1.x + wheel2.x) / 2;
        centerY = (wheel1.y + wheel2.y) / 2;

        double carLength = 200;
        double distance = Math.sqrt((wheel1.x - wheel2.x) * (wheel1.x - wheel2.x) + (wheel1.y - wheel2.y) * (wheel1.y - wheel2.y));
        int distanceX = Math.abs(wheel1.x - wheel2.x);
        if (distanceX == 0) distanceX = 1;
        int distanceY = Math.abs(wheel1.y - wheel2.y);
        if (distanceY == 0) distanceY = 1;

        double delta = distance - carLength;
        double deltaX = delta * distanceX / distance;
        double deltaY = delta * distanceY / distance;

        wheel1.x -= deltaX / 2;
        wheel2.x += deltaX / 2;
        wheel1.y -= deltaY / 2;
        wheel2.y += deltaY / 2;
        System.out.println(deltaY);
    }

    class ViewPort extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.setColor(Color.GREEN);
            g.fillRect(Grass.GRASS_WIDTH, 650 + (Wheel.WHEEL_SIZE / 2), 2000, 300);

            grass.draw(g);

            g.setColor(Color.GRAY);
            ((Graphics2D) g).setStroke(new BasicStroke(10));
            g.drawLine(wheel1.x, wheel1.y, wheel2.x, wheel2.y);
            g.setColor(Color.RED);
            wheel1.draw(g);
            g.setColor(Color.BLUE);
            wheel2.draw(g);

            g.setColor(Color.BLACK);
            g.fillOval(centerX, centerY, 5, 5);
            g.fillOval(wheel1.x, wheel1.y, 5, 5);
            g.fillOval(wheel2.x, wheel2.y, 5, 5);

            g.drawString("ESC = Restart", 10, 20);
        }
    }

    public static void main(String args[]) {
        new FallingCar();
    }
}
