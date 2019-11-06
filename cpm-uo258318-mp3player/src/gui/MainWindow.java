package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

public class MainWindow extends JFrame {
	
	// Attributes
	private JPanel contentPane;
	private JPanel pnNorth;
	private JLabel lblLogo;
	private JSlider slVolume;
	private JPanel pnVol;
	private JLabel lblVol;
	private JTextField txtField;
	private JPanel pnCenter;
	private JPanel pnLibrary;
	private JLabel lblLibrary;
	private JScrollPane spLibrary;
	private JList listlLibrary;
	private JPanel pnLibButtons;
	private JButton btnAddLibrary;
	private JButton btnDelLibrary;
	private JPanel pnPlayList;
	private JLabel lblPlaylist;
	private JScrollPane spPlaylist;
	private JList listPlaylist;
	private JPanel pnPlayButtons;
	private JButton btnRewind;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnForward;
	private JButton btnDelete;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmNext;
	private JMenuItem mntmRandom;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;
	
	private DefaultListModel model1 = null;
	private DefaultListModel model2 = null;
	
	private JFileChooser selector = null;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties props = new Properties();
					props.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Mp3 Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1110, 639);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorth(), BorderLayout.NORTH);
		contentPane.add(getPnCenter(), BorderLayout.CENTER);
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new GridLayout(1, 0, 0, 0));
			pnNorth.add(getLblLogo());
			pnNorth.add(getSlVolume());
			pnNorth.add(getPnVol());
		}
		return pnNorth;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblLogo;
	}
	private JSlider getSlVolume() {
		if (slVolume == null) {
			slVolume = new JSlider();
			slVolume.setPaintTicks(true);
			slVolume.setPaintLabels(true);
			slVolume.setMinorTickSpacing(10);
			slVolume.setMajorTickSpacing(20);
		}
		return slVolume;
	}
	private JPanel getPnVol() {
		if (pnVol == null) {
			pnVol = new JPanel();
			pnVol.add(getLblVol());
			pnVol.add(getTxtField());
		}
		return pnVol;
	}
	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setFont(new Font("Tahoma", Font.PLAIN, 33));
		}
		return lblVol;
	}
	private JTextField getTxtField() {
		if (txtField == null) {
			txtField = new JTextField();
			txtField.setFont(new Font("Tahoma", Font.PLAIN, 40));
			txtField.setEditable(false);
			txtField.setColumns(2);
		}
		return txtField;
	}
	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(new GridLayout(1, 0, 0, 0));
			pnCenter.add(getPnLibrary());
			pnCenter.add(getPnPlayList());
		}
		return pnCenter;
	}
	private JPanel getPnLibrary() {
		if (pnLibrary == null) {
			pnLibrary = new JPanel();
			pnLibrary.setLayout(new BorderLayout(0, 0));
			pnLibrary.add(getLblLibrary(), BorderLayout.NORTH);
			pnLibrary.add(getSpLibrary(), BorderLayout.CENTER);
			pnLibrary.add(getPnLibButtons(), BorderLayout.SOUTH);
		}
		return pnLibrary;
	}
	private JLabel getLblLibrary() {
		if (lblLibrary == null) {
			lblLibrary = new JLabel("♪♫ Library:");
			lblLibrary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblLibrary;
	}
	private JScrollPane getSpLibrary() {
		if (spLibrary == null) {
			spLibrary = new JScrollPane();
			spLibrary.setViewportView(getListlLibrary());
		}
		return spLibrary;
	}
	private JList getListlLibrary() {
		if (listlLibrary == null) {
			model1 = new DefaultListModel();
			listlLibrary = new JList(model1);
		}
		return listlLibrary;
	}
	private JPanel getPnLibButtons() {
		if (pnLibButtons == null) {
			pnLibButtons = new JPanel();
			pnLibButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnLibButtons.add(getBtnAddLibrary());
			pnLibButtons.add(getBtnDelLibrary());
		}
		return pnLibButtons;
	}
	private JButton getBtnAddLibrary() {
		if (btnAddLibrary == null) {
			btnAddLibrary = new JButton("Add to Playlist");
			btnAddLibrary.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAddLibrary.setMnemonic('d');
		}
		return btnAddLibrary;
	}
	private JButton getBtnDelLibrary() {
		if (btnDelLibrary == null) {
			btnDelLibrary = new JButton("Delete");
			btnDelLibrary.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDelLibrary.setMnemonic('l');
		}
		return btnDelLibrary;
	}
	private JPanel getPnPlayList() {
		if (pnPlayList == null) {
			pnPlayList = new JPanel();
			pnPlayList.setLayout(new BorderLayout(0, 0));
			pnPlayList.add(getLblPlaylist(), BorderLayout.NORTH);
			pnPlayList.add(getSpPlaylist(), BorderLayout.CENTER);
			pnPlayList.add(getPnPlayButtons(), BorderLayout.SOUTH);
		}
		return pnPlayList;
	}
	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("♪♫ Playlist:");
			lblPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblPlaylist;
	}
	private JScrollPane getSpPlaylist() {
		if (spPlaylist == null) {
			spPlaylist = new JScrollPane();
			spPlaylist.setViewportView(getListPlaylist());
		}
		return spPlaylist;
	}
	private JList getListPlaylist() {
		if (listPlaylist == null) {
			model2 = new DefaultListModel();
			listPlaylist = new JList(model2);
		}
		return listPlaylist;
	}
	private JPanel getPnPlayButtons() {
		if (pnPlayButtons == null) {
			pnPlayButtons = new JPanel();
			pnPlayButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnPlayButtons.add(getBtnRewind());
			pnPlayButtons.add(getBtnPlay());
			pnPlayButtons.add(getBtnStop());
			pnPlayButtons.add(getBtnForward());
			pnPlayButtons.add(getBtnDelete());
		}
		return pnPlayButtons;
	}
	private JButton getBtnRewind() {
		if (btnRewind == null) {
			btnRewind = new JButton("◄◄");
			btnRewind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnRewind;
	}
	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("►");
			btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnPlay;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("■");
			btnStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnStop;
	}
	private JButton getBtnForward() {
		if (btnForward == null) {
			btnForward = new JButton("►►");
			btnForward.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnForward;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Del");
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDelete.setMnemonic('e');
		}
		return btnDelete;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('f');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('p');
			mnPlay.add(getMntmNext());
		}
		return mnPlay;
	}
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('o');
			mnOptions.add(getMntmRandom());
		}
		return mnOptions;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('h');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open...");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					whenPressedOpen();
				}
			});
			mntmOpen.setMnemonic('n');
		}
		return mntmOpen;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setMnemonic('e');
		}
		return mntmExit;
	}
	private JMenuItem getMntmNext() {
		if (mntmNext == null) {
			mntmNext = new JMenuItem("Next");
			mntmNext.setMnemonic('n');
		}
		return mntmNext;
	}
	private JMenuItem getMntmRandom() {
		if (mntmRandom == null) {
			mntmRandom = new JMenuItem("Random");
			mntmRandom.setMnemonic('r');
		}
		return mntmRandom;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setMnemonic('c');
		}
		return mntmContents;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('a');
		}
		return mntmAbout;
	}

	public JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
		}		
		return selector;
	}
	


	private void whenPressedOpen() {
		int response = getSelector().showOpenDialog(this);
		if (response == JFileChooser.APPROVE_OPTION) {
			for (File f : getSelector().getSelectedFiles()) {
				model1.addElement(f);
			}
		}
	}
		
}
