
package colormixer;

/**
 *
 * @author evgenylukyanov
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ColorMixer extends JFrame implements ChangeListener {

    private final JSlider sliderRed;
    private final JSlider sliderGreen;
    private final JSlider sliderBlu;
    private final JPanel viewColor;
    private final JLabel label;

    public ColorMixer() {
        setTitle("Color Mixer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        setBounds(d.width/2-500,d.height/2-250,500,500);

        sliderRed = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        sliderGreen = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        sliderBlu = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        

        sliderRed.addChangeListener(this);
        sliderGreen.addChangeListener(this);
        sliderBlu.addChangeListener(this);

        viewColor = new JPanel();
        viewColor.setPreferredSize(new Dimension(200, 200));

        label = new JLabel("#000000");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.add(sliderRed);
        panel.add(sliderGreen);
        panel.add(sliderBlu);
        panel.add(viewColor);
        panel.add(label);

        add(panel);

        pack();
        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int r = sliderRed.getValue();
        int g = sliderGreen.getValue();
        int b = sliderBlu.getValue();

        Color color = new Color(r, g, b);
        viewColor.setBackground(color);

        String hex = String.format("#%02x%02x%02x", r, g, b);
        label.setText(hex);
    }

    public void shuffle() {
        Random rand = new Random();
        sliderRed.setValue(rand.nextInt(256));
        sliderGreen.setValue(rand.nextInt(256));
        sliderBlu.setValue(rand.nextInt(256));
        stateChanged(null);
    }

    public static void main(String[] args) {
        ColorMixer mixer = new ColorMixer();
    }
}
