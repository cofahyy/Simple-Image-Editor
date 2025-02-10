import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PixelateMenuItem extends JMenuItem implements ActionListener {

    private final ImageEditor MAIN_PANEL;

    public PixelateMenuItem(ImageEditor mainPanel) {
        super("Pixelate");
        this.MAIN_PANEL = mainPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of times to pixelate the image:"));
        this.MAIN_PANEL.addImage(ImageOperations.pixelate(MAIN_PANEL.getImage(), n));
        this.MAIN_PANEL.repaint();
    }
}
