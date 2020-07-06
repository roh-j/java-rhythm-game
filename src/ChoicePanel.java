import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChoicePanel extends JPanel implements ActionListener {

	JButton music_album_1, music_album_2, music_album_3;

	CardLayout gameGroup;
	JPanel cardGame;
	CardLayout footerGroup;
	JPanel cardFooter;
	GameSetting GameSetting;
	GameMaster GameMaster;
	MusicPlayer player;

	public ChoicePanel(CardLayout gameGroup, JPanel cardGame, CardLayout footerGroup, JPanel cardFooter, GameSetting GameSetting, GameMaster GameMaster) {

		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.gameGroup = gameGroup;
		this.cardGame = cardGame;
		this.footerGroup = footerGroup;
		this.cardFooter = cardFooter;
		this.GameSetting = GameSetting;
		this.GameMaster = GameMaster;

		music_album_1 = new JButton(new ImageIcon("music_album_1.png"));
		music_album_2 = new JButton(new ImageIcon("music_album_2.png"));
		music_album_3 = new JButton(new ImageIcon("music_album_3.png"));

		music_album_1.setBorderPainted(false);
		music_album_1.setFocusPainted(false);
		music_album_1.setContentAreaFilled(false);
		music_album_1.addActionListener(this);

		music_album_2.setBorderPainted(false);
		music_album_2.setFocusPainted(false);
		music_album_2.setContentAreaFilled(false);
		music_album_2.addActionListener(this);

		music_album_3.setBorderPainted(false);
		music_album_3.setFocusPainted(false);
		music_album_3.setContentAreaFilled(false);
		music_album_3.addActionListener(this);

		music_album_1.setSize(300, 150);
		music_album_1.setLayout(null);
		music_album_1.setLocation(50, 30);

		music_album_2.setSize(300, 150);
		music_album_2.setLayout(null);
		music_album_2.setLocation(50, 225);

		music_album_3.setSize(300, 150);
		music_album_3.setLayout(null);
		music_album_3.setLocation(50, 420);

		this.add(music_album_1);
		this.add(music_album_2);
		this.add(music_album_3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton) e.getSource();

		player = new MusicPlayer();

		if (source == music_album_1) {

			player.setPath("game_music_1.mp3");
			GameSetting.setGameMusic(1);

			switch (GameSetting.getGameMode()) {
				case "maker":
					GameMaster.dispose();
					player.play(-1);
					CreateMaster CM = new CreateMaster(GameSetting, player);
					break;
				case "play":
					if (GameSetting.getMusicSaveList1() == 0) {
						GameAlert GameAlert = new GameAlert("저장된 게임이 없습니다.");
					} else {
						GameMaster.dispose();
						player.play(-1);
						PlayMaster PM = new PlayMaster(GameSetting, player);
					}
					break;
				default:
			}
		}
		else if (source == music_album_2) {

			player.setPath("game_music_2.mp3");
			GameSetting.setGameMusic(2);

			switch (GameSetting.getGameMode()) {
				case "maker":
					GameMaster.dispose();
					player.play(-1);
					CreateMaster CM = new CreateMaster(GameSetting, player);
					break;
				case "play":
					if (GameSetting.getMusicSaveList2() == 0) {
						GameAlert GameAlert = new GameAlert("저장된 게임이 없습니다.");
					} else {
						GameMaster.dispose();
						player.play(-1);
						PlayMaster PM = new PlayMaster(GameSetting, player);
					}
					break;
				default:
			}
		}
		else if (source == music_album_3) {

			player.setPath("game_music_3.mp3");
			GameSetting.setGameMusic(3);

			switch (GameSetting.getGameMode()) {
				case "maker":
					GameMaster.dispose();
					player.play(-1);
					CreateMaster CM = new CreateMaster(GameSetting, player);
					break;
				case "play":
					if (GameSetting.getMusicSaveList3() == 0) {
						GameAlert GameAlert = new GameAlert("저장된 게임이 없습니다.");
					} else {
						GameMaster.dispose();
						player.play(-1);
						PlayMaster PM = new PlayMaster(GameSetting, player);
					}
					break;
				default:
			}
		}
	}
}