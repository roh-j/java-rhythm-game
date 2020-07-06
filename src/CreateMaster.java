import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;

public class CreateMaster extends JFrame {

	public CreateMaster(GameSetting GameSetting, MusicPlayer player) {

		this.setTitle("리듬게임 | 제작모드");
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		try {
			nodeSaveInit nodeSaveInit = new nodeSaveInit();

			SidePanel SidePanel = new SidePanel(GameSetting);
			SidePanel.setPreferredSize(new Dimension(200, 600));
			CreatePanel CreatePanel = new CreatePanel(GameSetting, nodeSaveInit);
			CreatePanel.setPreferredSize(new Dimension(400, 600));
			FooterSave FooterSave = new FooterSave(GameSetting, player, nodeSaveInit, this);
			FooterSave.setPreferredSize(new Dimension(600, 100));

			this.add(CreatePanel, BorderLayout.CENTER);
			this.add(SidePanel, BorderLayout.EAST);
			this.add(FooterSave, BorderLayout.SOUTH);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}