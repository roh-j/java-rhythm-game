import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FooterMain extends JPanel {

	public FooterMain() {

		this.setBackground(Color.decode("#293545"));
		this.setLayout(null);

		JLabel copyright = new JLabel(new ImageIcon("copyright.png"));

		copyright.setSize(250, 50);
		copyright.setLayout(null);
		copyright.setLocation(350, 50);

		this.add(copyright);
	}
}