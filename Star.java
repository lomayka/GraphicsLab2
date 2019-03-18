package sample;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Star extends JPanel implements ActionListener {
    // Масштабування відбувається відносно центру координат,
// тому малювати фігуру бажано також симетрично центру
    double points[][] = {
            {-5,-25},
            {-15,-115},
            {-90,-100},
            {-80,-5},
            {-15,-15},
            {-65,45},
            {-30,115},
            {35,80},
            {-5,-15},
            {60,25},
            {110,-55},
            {10,-108}
    };
    Timer timer;
    // Для анімації масштабування
    private double scale = 1;
    private double delta = 0.01;
    // Для анімації руху
    private double dx = 1;
    private double tx = 0;
    private double dy = 1;

    private double ty = 6;
    private static int maxWidth;
    private static int maxHeight;
    private int right = 0;
    private int down = -1;

    public Star() {
// Таймер генеруватиме подію що 10 мс
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
// Встановлюємо кольори
        g2d.setBackground(Color.black);
        g2d.setColor(Color.YELLOW);
        g2d.clearRect(0, 0, 1000, 1000);

        Rectangle rect  = new Rectangle(600, 600, 150, 200);
        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);
        g2d.fill(rect);

        Polygon p = new Polygon();
        p.addPoint(100, 600);
        p.addPoint(200, 700);
        p.addPoint(300, 650);
        g2d.fill(p);
        g2d.draw(p);
        g2d.draw(rect);

        Polygon polygon = new Polygon();
        int[][] d = new int[][]{
            {145,100},
            {135,10},
            {60,25},
            {70,120},
            {135,110},
            {85,170},
            {120,240},
            {185,205},
            {145,110},
            {210,150},
            {260,70},
            {160,17}
        };
        for (int[] arr: d
             ) {
            polygon.addPoint(arr[0],arr[1]);
        }

        g2d.translate(600, 0);

        Rectangle rectangle  = new Rectangle(0, 0, 300, 250);
        g2d.setColor(Color.RED);
        g2d.fill(rectangle);
        g2d.draw(rectangle);
        g2d.setColor(Color.GREEN);
        g2d.fill(polygon);
        g2d.draw(polygon);



        g2d.translate(-600, 0);
        g2d.setColor(Color.RED);

        BasicStroke bs1 = new BasicStroke(4, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER);
        g2d.setStroke(bs1);
        Rectangle rectangle1 = new Rectangle(0, 0, maxWidth+2, maxHeight+2);
        g2d.fill(rectangle1);




        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
        // Встановлюємо параметри рендерингу
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
// Зсовуємо центр координат в центр вікна
        g2d.translate(maxWidth/2, maxHeight/2);
// Перетворення для анімації руху.
        g2d.translate(tx, ty);
// Створення зірки
        g2d.setColor(Color.GREEN);
        GeneralPath star = new GeneralPath();
        star.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            star.lineTo(points[k][0], points[k][1]);
        star.closePath();
// Перетворення для анімації зміни прозорості
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float)scale));
// Далі йдуть всі ті методи, що необхідні для власне малювання малюнку
        g2d.fill(star);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Приклад анімації");
        frame.add(new Star());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = 500;
        maxHeight = 500;
    }

    public void actionPerformed(ActionEvent e) {
        if ( tx < -500/3.5 && right != 0) {
            dx = 0;
            dy = 1;
            right = 0;
            down = 1;
        } else if ( tx > maxWidth/3.5 && right != 0) {
            dx = 0;
            dy = -1;
            right = 0;
            down = -1;
        } else if ( ty < -maxHeight/4 && down != 0) {
            dy = 0;
            dx = -1;
            right = 1;
            down = 0;
        } else if ( ty > maxHeight/4 && down != 0) {
            dy = 0;
            dx = 1;
            right = -1;
            down = 0;
        }
        if(scale > 0.99) {
            delta = -delta;
        } else if(scale < 0.01) {
            delta = -delta;
        }


        scale += delta;
        ty += dy;
        tx += dx;

        repaint();
    }
}