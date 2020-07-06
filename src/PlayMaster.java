import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

public class PlayMaster extends JFrame {

	public PlayMaster(GameSetting GameSetting, MusicPlayer player) {

		this.setTitle("리듬게임 | 플레이모드");
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		try {
			SidePanel SidePanel = new SidePanel(GameSetting);
			SidePanel.setPreferredSize(new Dimension(200, 600));
			PlayPanel PlayPanel = new PlayPanel(GameSetting);
			PlayPanel.setPreferredSize(new Dimension(400, 600));
			FooterHome FooterHome = new FooterHome(GameSetting, player, this);
			FooterHome.setPreferredSize(new Dimension(600, 100));

			this.add(PlayPanel, BorderLayout.CENTER);
			this.add(SidePanel, BorderLayout.EAST);
			this.add(FooterHome, BorderLayout.SOUTH);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}