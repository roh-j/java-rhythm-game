import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FooterBack extends JPanel implements ActionListener {

	CardLayout gameGroup;
	JPanel cardGame;
	CardLayout footerGroup;
	JPanel cardFooter;
	GameSetting GameSetting;

	public FooterBack(CardLayout gameGroup, JPanel cardGame, CardLayout footerGroup, JPanel cardFooter, GameSetting GameSetting) {

		this.gameGroup = gameGroup;
		this.cardGame = cardGame;
		this.footerGroup = footerGroup;
		this.cardFooter = cardFooter;
		this.GameSetting = GameSetting;
		this.setBackground(Color.decode("#293545"));

		JButton back = new JButton(new ImageIcon("btn_back.png"));
		
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);

		this.add(back);
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameSetting.setGameMode(null);
		gameGroup.show(cardGame, "Game");
		footerGroup.show(cardFooter, "FooterMain");
	}
}