import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	JButton btn_maker_mode, btn_play_mode;

	CardLayout gameGroup;
	JPanel cardGame;
	CardLayout footerGroup;
	JPanel cardFooter;
	GameMaster GameMaster;
	GameSetting GameSetting;

	public GamePanel(CardLayout gameGroup, JPanel cardGame, CardLayout footerGroup, JPanel cardFooter, GameSetting GameSetting, GameMaster GameMaster) {

		this.GameMaster = GameMaster;
		this.gameGroup = gameGroup;
		this.cardGame = cardGame;
		this.footerGroup = footerGroup;
		this.cardFooter = cardFooter;
		this.GameSetting = GameSetting;

		this.setLayout(null);
		this.setBackground(Color.WHITE);

		btn_maker_mode = new JButton(new ImageIcon("btn_maker_mode.png"));
		btn_play_mode = new JButton(new ImageIcon("btn_play_mode.png"));

		btn_maker_mode.setBorderPainted(false);
		btn_maker_mode.setFocusPainted(false);
		btn_maker_mode.setContentAreaFilled(false);

		btn_play_mode.setBorderPainted(false);
		btn_play_mode.setFocusPainted(false);
		btn_play_mode.setContentAreaFilled(false);

		btn_maker_mode.setSize(200, 194);
		btn_maker_mode.setLayout(null);
		btn_maker_mode.setLocation(100, 53);

		btn_play_mode.setSize(200, 194);
		btn_play_mode.setLayout(null);
		btn_play_mode.setLocation(100, 353);

		btn_maker_mode.addActionListener(this);
		btn_play_mode.addActionListener(this);

		this.add(btn_maker_mode);
		this.add(btn_play_mode);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();

		// Maker Mode
		if (source == btn_maker_mode) {
			GameSetting.setGameMode("maker");
			gameGroup.show(cardGame, "Choice");
			footerGroup.show(cardFooter, "FooterBack");
		}

		// Play Mode
		else if (source == btn_play_mode) {
			GameSetting.setGameMode("play");
			gameGroup.show(cardGame, "Choice");
			footerGroup.show(cardFooter, "FooterBack");
		}
	}
}