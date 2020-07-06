import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

// Game Initialization
interface gameInit {
	int gameMode = 12;
}

// database Initialization
interface databaseInit {
	String url = "jdbc:mysql://localhost/rhythm";
	String id = "root";
	String password = "onlyroot";
}

//node save Initialization
class nodeSaveInit {

	ArrayList nodeSaveList1 = new ArrayList();
	ArrayList nodeSaveList2 = new ArrayList();
	ArrayList nodeSaveList3 = new ArrayList();
	ArrayList nodeSaveList4 = new ArrayList();

	public nodeSaveInit() {
		// init
	}

	// Setter
	public void setNodeSaveList1(long item) {
		this.nodeSaveList1.add(item);
	}
	public void setNodeSaveList2(long item) {
		this.nodeSaveList2.add(item);
	}
	public void setNodeSaveList3(long item) {
		this.nodeSaveList3.add(item);
	}
	public void setNodeSaveList4(long item) {
		this.nodeSaveList4.add(item);
	}

	// Getter
	public ArrayList getNodeSaveList1() {
		return this.nodeSaveList1;
	}
	public ArrayList getNodeSaveList2() {
		return this.nodeSaveList2;
	}
	public ArrayList getNodeSaveList3() {
		return this.nodeSaveList3;
	}
	public ArrayList getNodeSaveList4() {
		return this.nodeSaveList4;
	}
}

// Node 생성 Class
class SyncCreater {

	Point pos; // 노드 좌표
	long timeStamp; // 노드 타임스탭프
	int dx = 10; // 이동 좌표 크기

	public SyncCreater(int x, int y) {
		pos = new Point(x, y);
	}

	public void move() {
		pos.y += dx;
	}

	public void timer() {
		timeStamp = System.currentTimeMillis();
	}
}

// Node Effect Class
class EffectCreater {

	Point pos;
	int timeStamp = 5;
	int nodeId;

	public EffectCreater(int x, int y) {
		pos = new Point(x, y);
	}

	public void setId(int id) {
		nodeId = id;
	}

	public int checkId() {
		return nodeId;
	}

	public void lifeTime() {
		timeStamp--;
	}

	public int checkTime() {
		return timeStamp;
	}
}

class GameSetting implements databaseInit {

	String gameMode = null;
	int gameMusic; // 1: 임재범, 2: 이문세, 3: 전인권

	DefaultTableModel nodeDiffModel;
	DefaultTableModel gameScoreModel;
	DefaultTableModel gameInfoModel;
	DefaultTableModel musicInfoModel;

	Connection con = null;
	Statement stmt;
	ResultSet rs;

	int music_savelist_1;
	int music_savelist_2;
	int music_savelist_3;

	public GameSetting() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);

			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM rhythm.rhythm_node1 where music_id = 1");
			while (rs.next()) {
				music_savelist_1 = rs.getInt("count(*)");
			}
			rs = stmt.executeQuery("SELECT count(*) FROM rhythm.rhythm_node1 where music_id = 2");
			while (rs.next()) {
				music_savelist_2 = rs.getInt("count(*)");
			}
			rs = stmt.executeQuery("SELECT count(*) FROM rhythm.rhythm_node1 where music_id = 3");
			while (rs.next()) {
				music_savelist_3 = rs.getInt("count(*)");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getMusicSaveList1() {
		return music_savelist_1;
	}

	public int getMusicSaveList2() {
		return music_savelist_2;
	}

	public int getMusicSaveList3() {
		return music_savelist_3;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getGameMode() {
		return this.gameMode;
	}

	public void setGameMusic(int gameMusic) {
		this.gameMusic = gameMusic;
	}

	public int getGameMusic() {
		return this.gameMusic;
	}

	// 사이드 패널을 위한 변수
	public void setNodeDiff(DefaultTableModel nodeDiffModel) {
		this.nodeDiffModel = nodeDiffModel;
	}

	public DefaultTableModel getNodeDiff() {
		return nodeDiffModel;
	}

	public void setGameScore(DefaultTableModel gameScoreModel) {
		this.gameScoreModel = gameScoreModel;
	}

	public DefaultTableModel getGameScore() {
		return gameScoreModel;
	}

	public void setGameInfo(DefaultTableModel gameInfoModel) {
		this.gameInfoModel = gameInfoModel;
	}

	public DefaultTableModel getGameInfo() {
		return gameInfoModel;
	}

	public void setMusicInfo(DefaultTableModel musicInfoModel) {
		this.musicInfoModel = musicInfoModel;
	}

	public DefaultTableModel getMusicInfo() {
		return musicInfoModel;
	}
}

class GameAlert extends JDialog {
	JLabel alert;
	public GameAlert(String message) {
		alert = new JLabel(message, SwingConstants.CENTER);
		this.setTitle("알림");
		this.setSize(200, 100);
		this.add(alert);
		this.setVisible(true);
	}
}

/*
 * SyncMaster Class
 * 게임 전체 레이아웃 구성 Class
 * 전역 변수 컨트롤을 위한 Interface가 선언되어 있음.
 */

public class GameMaster extends JFrame {

	CardLayout gameGroup = new CardLayout();
	JPanel cardGame = new JPanel();

	CardLayout footerGroup = new CardLayout();
	JPanel cardFooter = new JPanel();

	CardLayout sideGroup = new CardLayout();
	JPanel cardSide = new JPanel();

	GameSetting GameSetting;

	public GameMaster() {

		this.setTitle("리듬게임 | 메인");
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		cardGame.setLayout(gameGroup);
		cardFooter.setLayout(footerGroup);

		GameSetting = new GameSetting();

		GamePanel GamePanel = new GamePanel(gameGroup, cardGame, footerGroup, cardFooter, GameSetting, this);
		GamePanel.setPreferredSize(new Dimension(400, 600));

		ChoicePanel ChoicePanel = new ChoicePanel(gameGroup, cardGame, footerGroup, cardFooter, GameSetting, this);
		ChoicePanel.setPreferredSize(new Dimension(400, 600));

		SidePanel SidePanel = new SidePanel(GameSetting);
		SidePanel.setPreferredSize(new Dimension(200, 600));

		FooterMain FooterMain = new FooterMain();
		FooterMain.setPreferredSize(new Dimension(600, 100));

		FooterBack FooterBack = new FooterBack(gameGroup, cardGame, footerGroup, cardFooter, GameSetting);
		FooterBack.setPreferredSize(new Dimension(600, 100));

		cardGame.add(GamePanel, "Game");
		cardGame.add(ChoicePanel, "Choice");

		cardFooter.add(FooterMain, "FooterMain");
		cardFooter.add(FooterBack, "FooterBack");

		this.add(cardGame, BorderLayout.CENTER);
		this.add(SidePanel, BorderLayout.EAST);
		this.add(cardFooter, BorderLayout.SOUTH);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMaster GM = new GameMaster();
	}
}