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
 * Sync Node 생성 클래스
 * Create 모드에서 사용됨.
 */

class CreatePanel extends JPanel implements KeyListener, databaseInit {

	// 노드 그래픽 처리를 위한 배열
	ArrayList syncNodeList1 = new ArrayList();
	ArrayList syncNodeList2 = new ArrayList();
	ArrayList syncNodeList3 = new ArrayList();
	ArrayList syncNodeList4 = new ArrayList();

	// 노드 이미지 buffer
	BufferedImage node_img_1 = null;
	BufferedImage node_img_2 = null;
	BufferedImage node_img_3 = null;
	BufferedImage node_img_4 = null;

	SyncCreater SyncCreater;
	Graphics g;

	long startTime = System.currentTimeMillis();

	Connection con = null;
	Statement stmt;
	ResultSet rs;
	GameSetting GameSetting;
	nodeSaveInit nodeSaveInit;

	String artist = null;
	String name = null;

	public CreatePanel(GameSetting GameSetting, nodeSaveInit nodeSaveInit) throws SQLException {

		this.GameSetting = GameSetting;
		this.nodeSaveInit = nodeSaveInit;
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				CreatePanel.this.requestFocusInWindow();
			}
		});

		try {
			// Database Connect
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
		rs = stmt.executeQuery("SELECT * FROM rhythm.music where id = "+this.GameSetting.getGameMusic()+"");

		while (rs.next()) {
			artist = rs.getString("artist");
			name = rs.getString("name");
		}
		this.GameSetting.getMusicInfo().setValueAt(artist, 0, 0);
		this.GameSetting.getMusicInfo().setValueAt(name, 0, 1);

		try {
			node_img_1 = ImageIO.read(new File("node_img_1.png"));
			node_img_2 = ImageIO.read(new File("node_img_2.png"));
			node_img_3 = ImageIO.read(new File("node_img_3.png"));
			node_img_4 = ImageIO.read(new File("node_img_4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		class MyThread extends Thread {
			public void run() {

				while(true) {
					// 노드 그래픽 처리
					repaint();
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
	}

	public void drawSync(Graphics g) {

		// node space 1
		for (int i=0; i < syncNodeList1.size(); i++) {
			SyncCreater = (SyncCreater) (syncNodeList1.get(i)); // 생성자 배열 불러오기
			long endTime = System.currentTimeMillis(); // 현재 타임스탬프

			if (SyncCreater.pos.y >= 700) {
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

			if (SyncCreater.pos.y >= 700) {
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

			if (SyncCreater.pos.y >= 700) {
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

			if (SyncCreater.pos.y >= 700) {
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

		SyncCreater SyncCreater;
		long currentTime; // 노드 시작 timestamp 계산을 위한 변수

		switch (e.getKeyCode()) {
			case KeyEvent.VK_D:

				currentTime = System.currentTimeMillis();
				nodeSaveInit.setNodeSaveList1(currentTime - startTime);

				SyncCreater = new SyncCreater(0, 0);
				syncNodeList1.add(SyncCreater);
				SyncCreater.timer();

				break;
			case KeyEvent.VK_F:

				currentTime = System.currentTimeMillis();
				nodeSaveInit.setNodeSaveList2(currentTime - startTime);

				SyncCreater = new SyncCreater(100, 0);
				syncNodeList2.add(SyncCreater);
				SyncCreater.timer();

				break;
			case KeyEvent.VK_J:

				currentTime = System.currentTimeMillis();
				nodeSaveInit.setNodeSaveList3(currentTime - startTime);

				SyncCreater = new SyncCreater(200, 0);
				syncNodeList3.add(SyncCreater);
				SyncCreater.timer();

				break;
			case KeyEvent.VK_K:

				currentTime = System.currentTimeMillis();
				nodeSaveInit.setNodeSaveList4(currentTime - startTime);

				SyncCreater = new SyncCreater(300, 0);
				syncNodeList4.add(SyncCreater);
				SyncCreater.timer();

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