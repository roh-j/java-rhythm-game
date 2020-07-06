import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SidePanel extends JPanel {

	JPanel nodeDiffPanel;
	JTable nodeDiffTable;
	DefaultTableModel nodeDiffModel;
	Vector<String> nodeDiffTH = new Vector<String>();
	Vector<String> nodeDiffTR;

	JPanel gameScorePanel;
	JTable gameScoreTable;
	DefaultTableModel gameScoreModel;
	Vector<String> gameScoreTH = new Vector<String>();
	Vector<String> gameScoreTR;

	JPanel gameInfoPanel;
	JTable gameInfoTable;
	DefaultTableModel gameInfoModel;
	Vector<String> gameInfoTH = new Vector<String>();
	Vector<String> gameInfoTR;

	JPanel musicInfoPanel;
	JTable musicInfoTable;
	DefaultTableModel musicInfoModel;
	Vector<String> musicInfoTH = new Vector<String>();
	Vector<String> musicInfoTR;

	GameSetting GameSetting;

	public SidePanel(GameSetting GameSetting) {
		this.setBackground(Color.decode("#D2D2D2"));
		this.GameSetting = GameSetting;

		// TH
		nodeDiffTH.addElement("Node #");
		nodeDiffTH.addElement("ms");

		nodeDiffPanel = new JPanel(new BorderLayout());
		nodeDiffModel = new DefaultTableModel(nodeDiffTH, 0);
		nodeDiffTable = new JTable(nodeDiffModel);

		// TR 입력
		nodeDiffTR = new Vector<String>();
		nodeDiffTR.addElement("1");
		nodeDiffTR.addElement("");
		nodeDiffModel.addRow(nodeDiffTR);
		nodeDiffTR = new Vector<String>();
		nodeDiffTR.addElement("2");
		nodeDiffTR.addElement("");
		nodeDiffModel.addRow(nodeDiffTR);
		nodeDiffTR = new Vector<String>();
		nodeDiffTR.addElement("3");
		nodeDiffTR.addElement("");
		nodeDiffModel.addRow(nodeDiffTR);
		nodeDiffTR = new Vector<String>();
		nodeDiffTR.addElement("4");
		nodeDiffTR.addElement("");
		nodeDiffModel.addRow(nodeDiffTR);

		// nodeDiffTable 표시
		nodeDiffPanel.add(nodeDiffTable, BorderLayout.CENTER);
		nodeDiffPanel.add(nodeDiffTable.getTableHeader(), BorderLayout.NORTH);
		nodeDiffPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Difference")));
		nodeDiffPanel.setBackground(Color.WHITE);

		// TH
		gameScoreTH.addElement("Node #");
		gameScoreTH.addElement("Score");

		gameScorePanel = new JPanel(new BorderLayout());
		gameScoreModel = new DefaultTableModel(gameScoreTH, 0);
		gameScoreTable = new JTable(gameScoreModel);

		// TR 입력
		gameScoreTR = new Vector<String>();
		gameScoreTR.addElement("1");
		gameScoreTR.addElement("");
		gameScoreModel.addRow(gameScoreTR);
		gameScoreTR = new Vector<String>();
		gameScoreTR.addElement("2");
		gameScoreTR.addElement("");
		gameScoreModel.addRow(gameScoreTR);
		gameScoreTR = new Vector<String>();
		gameScoreTR.addElement("3");
		gameScoreTR.addElement("");
		gameScoreModel.addRow(gameScoreTR);
		gameScoreTR = new Vector<String>();
		gameScoreTR.addElement("4");
		gameScoreTR.addElement("");
		gameScoreModel.addRow(gameScoreTR);

		// nodeDiffTable 표시
		gameScorePanel.add(gameScoreTable, BorderLayout.CENTER);
		gameScorePanel.add(gameScoreTable.getTableHeader(), BorderLayout.NORTH);
		gameScorePanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Game Score")));
		gameScorePanel.setBackground(Color.WHITE);

		// TH
		gameInfoTH.addElement("Type");
		gameInfoTH.addElement("Value");

		gameInfoPanel = new JPanel(new BorderLayout());
		gameInfoModel = new DefaultTableModel(gameInfoTH, 0);
		gameInfoTable = new JTable(gameInfoModel);

		// TR 입력
		gameInfoTR = new Vector<String>();
		gameInfoTR.addElement("runtime");
		gameInfoTR.addElement("");
		gameInfoModel.addRow(gameInfoTR);

		// nodeDiffTable 표시
		gameInfoPanel.add(gameInfoTable, BorderLayout.CENTER);
		gameInfoPanel.add(gameInfoTable.getTableHeader(), BorderLayout.NORTH);
		gameInfoPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Game Info")));
		gameInfoPanel.setBackground(Color.WHITE);

		// TH
		musicInfoTH.addElement("Artist");
		musicInfoTH.addElement("Name");

		musicInfoPanel = new JPanel(new BorderLayout());
		musicInfoModel = new DefaultTableModel(musicInfoTH, 0);
		musicInfoTable = new JTable(musicInfoModel);

		// TR 입력
		musicInfoTR = new Vector<String>();
		musicInfoTR.addElement("");
		musicInfoTR.addElement("");
		musicInfoModel.addRow(musicInfoTR);

		// nodeDiffTable 표시
		musicInfoPanel.add(musicInfoTable, BorderLayout.CENTER);
		musicInfoPanel.add(musicInfoTable.getTableHeader(), BorderLayout.NORTH);
		musicInfoPanel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Music Info")));
		musicInfoPanel.setBackground(Color.WHITE);

		this.add(nodeDiffPanel);
		this.add(gameScorePanel);
		this.add(gameInfoPanel);
		this.add(musicInfoPanel);

		this.GameSetting.setNodeDiff(nodeDiffModel);
		this.GameSetting.setGameScore(gameScoreModel);
		this.GameSetting.setMusicInfo(musicInfoModel);
		this.GameSetting.setGameInfo(gameInfoModel);
	}
}