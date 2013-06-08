import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;


public class Grass extends Entity {
    
    public static int GRASS_WIDTH = 35000;
    int SEGMENT_WIDTH = 100;
    int segments = GRASS_WIDTH / SEGMENT_WIDTH;
    int[] segmentX;
    int[] heights;
    Random random = new Random();
    
    public Grass() {
        segmentX = new int[segments];
        heights = new int[segments];
        heights[0] = 500;
        segmentX[0] = 0;
        heights[1] = 500;
        segmentX[1] = SEGMENT_WIDTH;
        heights[2] = 500;
        segmentX[2] = SEGMENT_WIDTH * 2;
        for (int i = 3; i < segments; i++) {
            segmentX[i] = i * SEGMENT_WIDTH;
            switch (random.nextInt(6)) {
            case 0:
                heights[i] = 500;
                break;
            case 1:
                heights[i] = heights[i - 1] - random.nextInt(SEGMENT_WIDTH / 4) - SEGMENT_WIDTH / 2;
                break;
            default:
                heights[i] = heights[i - 1] + random.nextInt(SEGMENT_WIDTH / 10) - SEGMENT_WIDTH / 5;
            }
        }
    }

    @Override
    void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(20));
        g2d.setColor(Color.GREEN);
        g2d.drawPolyline(segmentX, heights, segments);
    }

    public int getGrassHeight(int x) {
        if (x > GRASS_WIDTH - SEGMENT_WIDTH - 1) return 650;

        int segment = x / SEGMENT_WIDTH;
        float segmentOffset = (x % SEGMENT_WIDTH) / (float) SEGMENT_WIDTH;

        int y1 = heights[segment];
        int y2 = heights[segment + 1];

        int grassHeight = (int) (y1 + segmentOffset * (y2 - y1));

        return grassHeight - Wheel.WHEEL_SIZE / 2;
    }

}
