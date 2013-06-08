import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FallingCar extends JFrame {

    public int x, y, dx, dy;

    private Car wheel1;
    private Grass grass;
    private Car wheel2;
    private int centerX;
    private int centerY;

    private Car car;

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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Car.forward(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    initWheels();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Car.forward(true);
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
        car = new Car();

        grass = new Grass();
    }

    protected void updateWorld() {
        car.update(grass);
    }

    class ViewPort extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D graphics2d = (Graphics2D) g;

            g.drawString("ESCAPE= Restart", 10, 20);
            g.drawString("SPACE = Forward", 10, 40);

            AffineTransform scrolling = new AffineTransform();
            scrolling.translate(-car.getX() + 300, 0);
            graphics2d.setTransform(scrolling);

            graphics2d.setStroke(new BasicStroke(10));
            g.setColor(Color.GREEN);
            g.fillRect(Grass.GRASS_WIDTH, 650 + (Car.getWheelSize() / 2), 4500, 300);

            grass.draw(g);

            car.draw(g);
        }
    }

    public static void main(String args[]) {
        new FallingCar();
    }
}
