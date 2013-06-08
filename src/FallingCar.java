import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FallingCar extends JFrame {

    List<Entity> balls = new ArrayList<Entity>();
    private final Wheel wheel1;
    private final Grass grass;
    private final Wheel wheel2;

    public FallingCar() {
        super("Physics test");
        setContentPane(new ViewPort());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 850);
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
                wheel1.dy = -20;
            }
        });

        wheel1 = new Wheel();
        wheel2 = new Wheel();
        wheel1.x += 200;
        balls.add(wheel1);
        balls.add(wheel2);
        grass = new Grass();

        new Timer(1000 / 30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWorld();
                repaint();
            }
        }).start();
    }

    protected void updateWorld() {
        restrictDistance(wheel1, wheel2);
        for (Entity e : balls) {
            e.update(balls);
            if (e.dy > 0 && e.y > grass.getGrassHeight(e.x)) {
                e.dy = (int) -(e.dy * 0.5);
                e.y = grass.getGrassHeight(e.x);
            }
        }
    }

    private void restrictDistance(Wheel wheel1, Wheel wheel2) {
        double carLength = 200;
        double distance = Math.sqrt((wheel1.x - wheel2.x) * (wheel1.x - wheel2.x) + (wheel1.y - wheel2.y) * (wheel1.y - wheel2.y));
        System.out.println(distance);

        double nudge = Math.abs(carLength - distance);
        // wheel1.x -= nudge / 2;
        // wheel2.x += nudge / 2;
    }

    class ViewPort extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            grass.draw(g);
            for (Entity e : balls) {
                e.draw(g);
            }
        }
    }

    public static void main(String args[]) {
        new FallingCar();
    }
}
