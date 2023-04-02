
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public Main() {

        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                g2.draw(new Arc2D.Float(70, 100, 350, 200, -75, 330, Arc2D.OPEN));
                g2.draw(new Arc2D.Float(120, 120, 250, 110, -50, 280, Arc2D.OPEN));

                g2.draw(new Line2D.Double(166, 217, 245, 135));
                g2.draw(new Line2D.Double(325, 217, 245, 135));

                g2.draw(new Line2D.Double(200, 297, 245, 165));
                g2.draw(new Line2D.Double(290, 297, 245, 165));


                Font font = new Font("Calibri", Font.PLAIN, 60);
                TextLayout textLayout = new TextLayout("I   N   F   I   N   I   T   I", font, g2.getFontRenderContext());
                textLayout.draw(g2, 5, 380);
            }
        };
        setTitle("INFINITI");
        this.getContentPane().add(p);
    }

    public static void main(String arg[]) {
        new Main();
    }
}
