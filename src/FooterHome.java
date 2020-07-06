import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FooterHome extends JPanel implements ActionListener {

	GameSetting GameSetting;
	PlayMaster PlayMaster;
	MusicPlayer player;

	public FooterHome(GameSetting GameSetting, MusicPlayer player, PlayMaster PlayMaster) {

		this.GameSetting = GameSetting;
		this.PlayMaster = PlayMaster;
		this.player = player;
		this.setBackground(Color.decode("#293545"));

		JButton home = new JButton(new ImageIcon("btn_home.png"));

		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusPainted(false);

		this.add(home);
		home.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		PlayMaster.dispose();
		player.pause();
		GameMaster GM = new GameMaster();
	}
}