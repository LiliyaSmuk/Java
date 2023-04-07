import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    private DrawCanvas canvas;

    public Main(double st) {
        this.canvas = new DrawCanvas(st);
        this.canvas.setPreferredSize(new Dimension(400, 400));
        Container cp = this.getContentPane();
        cp.add(this.canvas);
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setTitle("Спидометр");
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main(Math.random() * 265.0 - 40.0);
            }
        });
    }

    private class DrawCanvas extends JPanel {
        final int centerX = 200;
        final int centerY = 200;
        double st = 0.0;

        public DrawCanvas(double st) {
            this.st = st;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            //g2.draw(new Ellipse2D.Float(40, 30, 320, 320)); // обводка
            g2.draw(new Ellipse2D.Float(192, 192, 16, 16)); // центр

            double anglex;
            g2.setStroke(new BasicStroke(1.5F));

            //Мелкие Засечки
            for(anglex = 220.0; anglex >= -40.0; anglex -= 260.0/90) {
                if(anglex <= 20){
                    g2.setColor(Color.red);
                } else {
                    g2.setColor(Color.black);
                }
                this.drawLine(g2, anglex, 130, 140);
            }

            //Средние Засечки
            for(anglex = 220.0; anglex >= -40.0; anglex -= 260.0/18) {
                if(anglex <= 20){
                    g2.setColor(Color.red);
                } else {
                    g2.setColor(Color.black);
                }
                this.drawLine(g2, anglex, 124, 140);
            }

            //Длинные Засечки
            for(anglex = 220.0; anglex >= -40.0; anglex -= 260.0/9) {
                if(anglex <= 20){
                    g2.setColor(Color.red);
                } else {
                    g2.setColor(Color.black);
                }
                g2.setStroke(new BasicStroke(1.8F));
                this.drawLine(g2, anglex, 118, 140);
            }


            g2.setFont(new Font("Arial", 1, 17));
            int ciphDistance = 95;

            double angle = 220.0;

            // Цифры
            for(int c = 0; c <= 180; c += 20) {
                if(angle <= 30){
                    g2.setColor(Color.red);
                } else {
                    g2.setColor(Color.black);
                }
                g2.drawString(Integer.toString(c),
                        187 + (int)((double)ciphDistance * Math.cos(angle * Math.PI / 180.0)),
                        205 - (int)((double)ciphDistance * Math.sin(angle * Math.PI / 180.0)));

                angle -= 30.0;
            }

            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(1.8F));
            g2.draw(new Ellipse2D.Float(40, 30, 320, 320)); // обводка

        }

        private void drawLine(Graphics2D g2, double angle, int start, int end) {
            g2.drawLine(200 + (int)((double)start * Math.cos(angle * Math.PI / 180.0)), 200 - (int)((double)start * Math.sin(angle * Math.PI / 180.0)), 200 + (int)((double)end * Math.cos(angle * Math.PI / 180.0)), 200 - (int)((double)end * Math.sin(angle * Math.PI / 180.0)));
        }
    }
}
