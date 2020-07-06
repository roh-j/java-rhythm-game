import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * Play Panel
 * 게임 플레이(시뮬레이션)을 위한 Panel
 */

public class PlayPanel extends JPanel implements KeyListener, databaseInit {

	// 노드 그래픽 배열
	ArrayList syncNodeList1 = new ArrayList();
	ArrayList syncNodeList2 = new ArrayList();
	ArrayList syncNodeList3 = new ArrayList();
	ArrayList syncNodeList4 = new ArrayList();

	ArrayList effectNode = new ArrayList();

	BufferedImage node_img_1 = null;
	BufferedImage node_img_2 = null;
	BufferedImage node_img_3 = null;
	BufferedImage node_img_4 = null;

	BufferedImage node_bg_1 = null;
	BufferedImage node_bg_2 = null;
	BufferedImage node_bg_3 = null;
	BufferedImage node_bg_4 = null;

	SyncCreater SyncCreater;
	EffectCreater EffectCreater;
	Graphics g;

	long startTime = System.currentTimeMillis();

	Connection con = null;
	Statement stmt;
	ResultSet rs;

	// 노드 카운터
	int timeCounter1, timeCounter2, timeCounter3, timeCounter4 = 0;

	// 노드 타임 split 배열
	String[] nodeTime1;
	String[] nodeTime2;
	String[] nodeTime3;
	String[] nodeTime4;

	String artist = null;
	String name = null;

	GameSetting GameSetting;

	public PlayPanel(GameSetting GameSetting) throws SQLException {

		this.GameSetting = GameSetting;

		this.addKeyListener(this);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
		this.requestFocusInWindow();

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				PlayPanel.this.requestFocusInWindow();
			}
		});

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stmt = con.createStatement();

		rs = stmt.executeQuery("SELECT * FROM rhythm.music where id = "+this.GameSetting.getGameMusic());

		while (rs.next()) {
			artist = rs.getString("artist");
			name = rs.getString("name");
		}
		this.GameSetting.getMusicInfo().setValueAt(artist, 0, 0);
		this.GameSetting.getMusicInfo().setValueAt(name, 0, 1);

		rs = stmt.executeQuery("SELECT * FROM rhythm.rhythm_node1 where music_id = "+Integer.toString(this.GameSetting.getGameMusic())+" order by id desc limit 0, 1");

		while (rs.next()) {
			String timeListNode1 = rs.getString("rhythm_time").replace("[", "").replace("]", "");
			nodeTime1 = timeListNode1.split(", ");
		}

		rs = stmt.executeQuery("SELECT * FROM rhythm.rhythm_node2 where music_id = "+Integer.toString(this.GameSetting.getGameMusic())+" order by id desc limit 0, 1");

		while (rs.next()) {
			String timeListNode2 = rs.getString("rhythm_time").replace("[", "").replace("]", "");
			nodeTime2 = timeListNode2.split(", ");
		}

		rs = stmt.executeQuery("SELECT * FROM rhythm.rhythm_node3 where music_id = "+Integer.toString(this.GameSetting.getGameMusic())+" order by id desc limit 0, 1");

		while (rs.next()) {
			String timeListNode3 = rs.getString("rhythm_time").replace("[", "").replace("]", "");
			nodeTime3 = timeListNode3.split(", ");
		}

		rs = stmt.executeQuery("SELECT * FROM rhythm.rhythm_node4 where music_id = "+Integer.toString(this.GameSetting.getGameMusic())+" order by id desc limit 0, 1");

		while (rs.next()) {
			String timeListNode4 = rs.getString("rhythm_time").replace("[", "").replace("]", "");
			nodeTime4 = timeListNode4.split(", ");
		}

		try {
			node_img_1 = ImageIO.read(new File("node_img_1.png"));
			node_img_2 = ImageIO.read(new File("node_img_2.png"));
			node_img_3 = ImageIO.read(new File("node_img_3.png"));
			node_img_4 = ImageIO.read(new File("node_img_4.png"));

			node_bg_1 = ImageIO.read(new File("node_bg_1.png"));
			node_bg_2 = ImageIO.read(new File("node_bg_2.png"));
			node_bg_3 = ImageIO.read(new File("node_bg_3.png"));
			node_bg_4 = ImageIO.read(new File("node_bg_4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		class MyThread extends Thread {
			public void run() {

				while(true) {
					repaint();
					GameSetting.getGameInfo().setValueAt((System.currentTimeMillis() - startTime)+" ms", 0, 1);

					if (timeCounter1 < nodeTime1.length) {
						if (Long.parseLong(nodeTime1[timeCounter1]) <= System.currentTimeMillis() - startTime) {
							SyncCreater = new SyncCreater(0, 0);
							syncNodeList1.add(SyncCreater);
							GameSetting.getNodeDiff().setValueAt((System.currentTimeMillis() - startTime)-Long.parseLong(nodeTime1[timeCounter1]), 0, 1);
							timeCounter1++;
						}
					}
					if (timeCounter2 < nodeTime2.length) {
						if (Long.parseLong(nodeTime2[timeCounter2]) <= System.currentTimeMillis() - startTime) {
							SyncCreater = new SyncCreater(100, 0);
							syncNodeList2.add(SyncCreater);
							GameSetting.getNodeDiff().setValueAt((System.currentTimeMillis() - startTime)-Long.parseLong(nodeTime2[timeCounter2]), 1, 1);
							timeCounter2++;
						}
					}
					if (timeCounter3 < nodeTime3.length) {
						if (Long.parseLong(nodeTime3[timeCounter3]) <= System.currentTimeMillis() - startTime) {
							SyncCreater = new SyncCreater(200, 0);
							syncNodeList3.add(SyncCreater);
							GameSetting.getNodeDiff().setValueAt((System.currentTimeMillis() - startTime)-Long.parseLong(nodeTime3[timeCounter3]), 2, 1);
							timeCounter3++;
						}
					}
					if (timeCounter4 < nodeTime4.length) {
						if (Long.parseLong(nodeTime4[timeCounter4]) <= System.currentTimeMillis() - startTime) {
							SyncCreater = new SyncCreater(300, 0);
							syncNodeList4.add(SyncCreater);
							GameSetting.getNodeDiff().setValueAt((System.currentTimeMillis() - startTime)-Long.parseLong(nodeTime4[timeCounter4]), 3, 1);
							timeCounter4++;
						}
					}

					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		Thread t = new MyThread();
		t.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawSync(g);
		drawEffect(g);
	}

	public void drawEffect(Graphics g) {
		for (int i=0; i < effectNode.size(); i++) {
			EffectCreater = (EffectCreater) (effectNode.get(i));
			switch (EffectCreater.checkId()) {
				case 1:
					g.drawImage(node_bg_1, EffectCreater.pos.x, EffectCreater.pos.y, null);
					break;
				case 2:
					g.drawImage(node_bg_2, EffectCreater.pos.x, EffectCreater.pos.y, null);
					break;
				case 3:
					g.drawImage(node_bg_3, EffectCreater.pos.x, EffectCreater.pos.y, null);
					break;
				case 4:
					g.drawImage(node_bg_4, EffectCreater.pos.x, EffectCreater.pos.y, null);
					break;
			}

			EffectCreater.lifeTime();

			if (EffectCreater.checkTime() <= 0) {
				effectNode.remove(i);
			}
		}
	}

	public void drawSync(Graphics g) {

		// node space 1
		for (int i=0; i < syncNodeList1.size(); i++) {
			SyncCreater = (SyncCreater) (syncNodeList1.get(i)); // 생성자 배열 불러오기
			long endTime = System.currentTimeMillis(); // 현재 타임스탬프

			if (SyncCreater.pos.y >= 640) {
				syncNodeList1.remove(i); // 노드가 화면에서 사라졌다면 배열에서 제거
			}
			else {
				SyncCreater.move(); // y값 업데이트
				g.drawImage(node_img_1, SyncCreater.pos.x, SyncCreater.pos.y, null);
			}
		}

		// node space 2
		for (int i=0; i < syncNodeList2.size(); i++) {
			SyncCreater = (SyncCreater) (syncNodeList2.get(i)); // 생성자 배열 불러오기
			long endTime = System.currentTimeMillis(); // 현재 타임스탬프

			if (SyncCreater.pos.y >= 640) {
				syncNodeList2.remove(i); // 노드가 화면에서 사라졌다면 배열에서 제거
			}
			else {
				SyncCreater.move(); // y값 업데이트
				g.drawImage(node_img_2, SyncCreater.pos.x, SyncCreater.pos.y, null);
			}
		}

		// node space 3
		for (int i=0; i < syncNodeList3.size(); i++) {
			SyncCreater = (SyncCreater) (syncNodeList3.get(i)); // 생성자 배열 불러오기
			long endTime = System.currentTimeMillis(); // 현재 타임스탬프

			if (SyncCreater.pos.y >= 640) {
				syncNodeList3.remove(i); // 노드가 화면에서 사라졌다면 배열에서 제거
			}
			else {
				SyncCreater.move(); // y값 업데이트
				g.drawImage(node_img_3, SyncCreater.pos.x, SyncCreater.pos.y, null);
			}
		}

		// node space 4
		for (int i=0; i < syncNodeList4.size(); i++) {
			SyncCreater = (SyncCreater) (syncNodeList4.get(i)); // 생성자 배열 불러오기
			long endTime = System.currentTimeMillis(); // 현재 타임스탬프

			if (SyncCreater.pos.y >= 640) {
				syncNodeList4.remove(i); // 노드가 화면에서 사라졌다면 배열에서 제거
			}
			else {
				SyncCreater.move(); // y값 업데이트
				g.drawImage(node_img_4, SyncCreater.pos.x, SyncCreater.pos.y, null);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_D:

				EffectCreater = new EffectCreater(0, 0);
				effectNode.add(EffectCreater);
				EffectCreater.setId(1);

				if (syncNodeList1.size() != 0) {
					SyncCreater = (SyncCreater) (syncNodeList1.get(0));
					if (SyncCreater.pos.y >= 520 && SyncCreater.pos.y <= 640) {
						this.GameSetting.getGameScore().setValueAt((SyncCreater.pos.y-600)+" Cool", 0, 1);
						syncNodeList1.remove(0);
					} else {
						this.GameSetting.getGameScore().setValueAt("Oops!", 0, 1);
					}
				}
				break;
			case KeyEvent.VK_F:

				EffectCreater = new EffectCreater(100, 0);
				effectNode.add(EffectCreater);
				EffectCreater.setId(2);

				if (syncNodeList2.size() != 0) {
					SyncCreater = (SyncCreater) (syncNodeList2.get(0));
					if (SyncCreater.pos.y >= 520 && SyncCreater.pos.y <= 640) {
						this.GameSetting.getGameScore().setValueAt((SyncCreater.pos.y-600)+" Cool", 1, 1);
						syncNodeList2.remove(0);
					} else {
						this.GameSetting.getGameScore().setValueAt("Oops!", 1, 1);
					}
				}
				break;
			case KeyEvent.VK_J:

				EffectCreater = new EffectCreater(200, 0);
				effectNode.add(EffectCreater);
				EffectCreater.setId(3);

				if (syncNodeList3.size() != 0) {
					SyncCreater = (SyncCreater) (syncNodeList3.get(0));
					if (SyncCreater.pos.y >= 520 && SyncCreater.pos.y <= 640) {
						this.GameSetting.getGameScore().setValueAt((SyncCreater.pos.y-600)+" Cool", 2, 1);
						syncNodeList3.remove(0);
					} else {
						this.GameSetting.getGameScore().setValueAt("Oops!", 2, 1);
					}
				}
				break;
			case KeyEvent.VK_K:

				EffectCreater = new EffectCreater(300, 0);
				effectNode.add(EffectCreater);
				EffectCreater.setId(4);

				if (syncNodeList4.size() != 0) {
					SyncCreater = (SyncCreater) (syncNodeList4.get(0));
					if (SyncCreater.pos.y >= 520 && SyncCreater.pos.y <= 640) {
						this.GameSetting.getGameScore().setValueAt((SyncCreater.pos.y-600)+" Cool", 3, 1);
						syncNodeList4.remove(0);
					} else {
						this.GameSetting.getGameScore().setValueAt("Oops!", 3, 1);
					}
				}
				break;
			default:
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}