import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * 생성된 Node를 저장하기 위한 Class
 * 데이버베이스에 배열로 저장.
 */

class FooterSave extends JPanel implements ActionListener, databaseInit {

	Connection con = null;
	Statement stmt;
	ResultSet rs;

	GameSetting GameSetting;
	CreateMaster CreateMaster;
	MusicPlayer player;
	nodeSaveInit nodeSaveInit;

	JButton save;
	JButton home;

	public FooterSave(GameSetting GameSetting, MusicPlayer player, nodeSaveInit nodeSaveInit, CreateMaster CreateMaster) throws SQLException {

		this.GameSetting = GameSetting;
		this.CreateMaster = CreateMaster;
		this.nodeSaveInit = nodeSaveInit;
		this.player = player;
		this.setBackground(Color.decode("#293545"));

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,id,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stmt = con.createStatement();

		home = new JButton(new ImageIcon("btn_home.png"));
		save = new JButton(new ImageIcon("btn_save.png"));

		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		home.setFocusPainted(false);

		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		save.setFocusPainted(false);

		this.add(home);
		this.add(save);
		home.addActionListener(this);
		save.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		// TODO Auto-generated method stub
		if (source == save) {
			try {
				stmt.executeUpdate("insert into rhythm.rhythm_node1(music_id, rhythm_time) values('"+GameSetting.getGameMusic()+"', '"+nodeSaveInit.getNodeSaveList1()+"')");
				stmt.executeUpdate("insert into rhythm.rhythm_node2(music_id, rhythm_time) values('"+GameSetting.getGameMusic()+"', '"+nodeSaveInit.getNodeSaveList2()+"')");
				stmt.executeUpdate("insert into rhythm.rhythm_node3(music_id, rhythm_time) values('"+GameSetting.getGameMusic()+"', '"+nodeSaveInit.getNodeSaveList3()+"')");
				stmt.executeUpdate("insert into rhythm.rhythm_node4(music_id, rhythm_time) values('"+GameSetting.getGameMusic()+"', '"+nodeSaveInit.getNodeSaveList4()+"')");
				GameAlert GameAlert = new GameAlert("저장에 성공하였습니다.");
			} catch (SQLException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
		}
		else if (source == home) {
			CreateMaster.dispose();
			player.pause();
			GameMaster GM = new GameMaster();
		}
	}
}