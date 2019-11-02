package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logic.Board;
import logic.Game;
import logic.SpaceInvadersUtils;
import logic.gmelement.GameElement;
import logic.gmelement.factory.GameElementFactory;
import logic.gmelement.factory.GameElementFactoryImpl;

public class MainWindow extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	
	// Attributes
	private JPanel contentPane;
	private JButton btnDice;
	private JLabel lblSpaceShip;
	private JLabel lblScore;
	private JLabel lblEarth;
	private JTextField txtScore;
	private JPanel pnShots;
	private JPanel pnBoard;
	private JMenuBar menuBarGame;
	private JMenu mnGame;
	private JMenuItem mntmNewGame;
	private JSeparator separator;
	private JSeparator separator_1;
	private JMenuItem mntmExit;
	private JMenuItem mntmShots;
	
	private ProcessDrag pd = new ProcessDrag();
	private ProcessButton pb = new ProcessButton();
	
	/**
	 * Business class that manages the logic of the game.
	 */
	private Game game;
	
	/**
	 * Game elements.
	 */
	private GameElement[] gameElements;

	/**
	 * Current max shots of the game.
	 */
	private int currentMaxShots;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameElementFactory factory = new GameElementFactoryImpl();
					MainWindow frame = new MainWindow(
							factory.createInvader(), 
							factory.createMeteorite(), 
							factory.createShield());
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param gameElement3 
	 * @param gameElement2 
	 * @param gameElement 
	 */
	public MainWindow(GameElement... gameElements) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/invader.jpg")));
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 508);
		setJMenuBar(getMenuBarGame());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnDice());
		contentPane.add(getLblSpaceShip());
		contentPane.add(getLblScore());
		contentPane.add(getLblEarth());
		contentPane.add(getTxtScore());
		contentPane.add(getPnShots());
		contentPane.add(getPnBoard());
		
		// We create the game elements
		this.gameElements = gameElements;
		
		// We create the game
		this.game = new Game(this.gameElements);
		
		enableBoard(false);
		txtScore.setText(String.valueOf(game.getScore()));
		
		createButtons();
	}

	private void createButtons() {
		// Adding the buttons to the pnBoard
		for (int i = 0; i < Board.DIM; i++) {
			pnBoard.add(createButton(i));
		}
	}
	
	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
			btnDice.setDisabledIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initGame();
				}
			});
			btnDice.setToolTipText("Throws the dice");
			btnDice.setBorder(null);
			btnDice.setIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setBounds(10, 11, 107, 108);
		}
		return btnDice;
	}
	private JLabel getLblSpaceShip() {
		if (lblSpaceShip == null) {
			lblSpaceShip = new JLabel("");
			lblSpaceShip.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spaceship.png")));
			lblSpaceShip.setBounds(216, 11, 145, 108);
		}
		return lblSpaceShip;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setLabelFor(getTxtScore());
			lblScore.setFont(new Font("Tahoma", Font.PLAIN, 28));
			lblScore.setBackground(Color.BLACK);
			lblScore.setForeground(Color.WHITE);
			lblScore.setBounds(450, 31, 81, 34);
		}
		return lblScore;
	}
	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(MainWindow.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(630, 11, 190, 183);
		}
		return lblEarth;
	}
	private JTextField getTxtScore() {
		if (txtScore == null) {
			txtScore = new JTextField();
			txtScore.setHorizontalAlignment(SwingConstants.CENTER);
			txtScore.setText("0");
			txtScore.setFont(new Font("Tahoma", Font.PLAIN, 32));
			txtScore.setEditable(false);
			txtScore.setBackground(Color.BLACK);
			txtScore.setForeground(Color.GREEN);
			txtScore.setBounds(428, 76, 126, 54);
			txtScore.setColumns(10);
		}
		return txtScore;
	}
	private JPanel getPnShots() {
		if (pnShots == null) {
			pnShots = new JPanel();
			pnShots.setBackground(Color.BLACK);
			pnShots.setBounds(54, 158, 512, 95);
		}
		return pnShots;
	}
	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBorder(new LineBorder(Color.BLUE));
			pnBoard.setBackground(Color.BLUE);
			pnBoard.setBounds(10, 309, 810, 128);
			pnBoard.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnBoard;
	}
	
	private JMenuBar getMenuBarGame() {
		if (menuBarGame == null) {
			menuBarGame = new JMenuBar();
			menuBarGame.add(getMnGame());
		}
		return menuBarGame;
	}
	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("Game");
			mnGame.add(getMntmNewGame());
			mnGame.add(getSeparator());
			mnGame.add(getMntmShots());
			mnGame.add(getSeparator_1());
			mnGame.add(getMntmExit());
		}
		return mnGame;
	}
	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New");
			mntmNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			mntmNewGame.setMnemonic('n');
		}
		return mntmNewGame;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmExit.setMnemonic('e');
		}
		return mntmExit;
	}

	private JMenuItem getMntmShots() {
		if (mntmShots == null) {
			mntmShots = new JMenuItem("Shots");
			mntmShots.setMnemonic('s');
			mntmShots.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					maxShotsInput();
				}
			});
		}
		return mntmShots;
	}
	
	
	private void enableBoard(boolean state) {
		for (Component c : pnBoard.getComponents()) {
			c.setEnabled(state);
		}
	}
	
	private JLabel newShot() {
		JLabel shot = new JLabel();
		shot.setBorder(new LineBorder(Color.GREEN));
		shot.setIcon(ImageFactory.getImage());
		shot.addMouseListener(pd);
		shot.setTransferHandler(new TransferHandler("foreground"));
		shot.setForeground(Color.BLUE);
		return shot;
	}
	
	private void paintShots() {
		for (int i = 0; i < game.getShots(); i++) {
			pnShots.add(newShot());
		}
		validate();
	}
	
	private void initGame() {
		game.launch();
		paintShots();
		btnDice.setEnabled(false);
		enableBoard(true);
	}
	
	private void removeShot() {
		pnShots.remove(0);
		repaint();
	}
	
	private void paint(int position) {
		ImageIcon icon = ImageFactory.getImagen(game.getBoard().getCells()[position]);
		((JButton) pnBoard.getComponent(position)).setIcon(icon);
		((JButton) pnBoard.getComponent(position)).setDisabledIcon(icon);
	}
	
	private void updateStateOfTheGame(int position) {
		txtScore.setText(String.valueOf(game.getScore()));
		removeShot();
		paint(position);
		
		if (game.isGameOver()) {
			StringBuilder sb = new StringBuilder();
			
			if (game.getScore() < 3000) {
				sb.append("You lost!\n");
				sb.append("Game Over!");
				JOptionPane.showMessageDialog(this, sb.toString(), "Space Invaders", 
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				sb.append("You won!\n");
				sb.append("Game Over!");
				JOptionPane.showMessageDialog(this, sb.toString(), "Space Invaders", 
						JOptionPane.INFORMATION_MESSAGE);
			}
			enableBoard(false);
			discoverBoard();
		}
	}
	
	private void discoverBoard() {
		for (int i = 0; i < getPnBoard().getComponents().length; i++) {
			paint(i);
		}
	}

	private void shoot(int position) {
		game.shoot(position);
		updateStateOfTheGame(position);
	}	

	private void initialize() {
		game.initialize(gameElements);
		game.setMaxShots(currentMaxShots);
		pnBoard.removeAll();
		createButtons();
		enableBoard(false);
		btnDice.setEnabled(true);
		pnShots.removeAll();
		txtScore.setText(String.valueOf(game.getScore()));
		hideBoard();
		repaint();
	}
	
	private void hideBoard() {
		for (int i = 0; i < getPnBoard().getComponents().length; i++) {
			hide(i);
		}
	}	
	
	private void hide(int position) {
		((JButton) pnBoard.getComponent(position)).setIcon(null);
		((JButton) pnBoard.getComponent(position)).setDisabledIcon(null);
	}
	
	private JButton createButton(int position) {
		JButton btn = new JButton("");
		btn.setBackground(Color.BLACK);	
		btn.setBorder(new LineBorder(Color.BLUE, 2));
		btn.setActionCommand(String.valueOf(position));		
		btn.addPropertyChangeListener(pb);
		btn.setTransferHandler(new TransferHandler("foreground"));
		return btn;
	}

	private void maxShotsInput() {
		String input = getMaxShotsFromUser();
		
		if (input != null) {
			// Restart game to reflect changes
			initialize();

			// Process input
			game.setMaxShots(Integer.valueOf(input));
			
			// Save max shots for future games
			this.currentMaxShots = Integer.valueOf(input);
		}
	}

	private String getMaxShotsFromUser() {
		StringBuilder sb = new StringBuilder();
		sb.append("Please, select the maximum number of shots for the game.\n");
		sb.append("Value must be between 2 and 6.");
		String input = null;
		
		// Input validation
		boolean validInput = false;
		
		while (!validInput) {			
			input = JOptionPane.showInputDialog(this, sb.toString(), "Space Invaders", 
				JOptionPane.QUESTION_MESSAGE);
			
			if (input == null) {
				break;
			}
			
			if (input.isEmpty() || !SpaceInvadersUtils.isNumeric(input)) {
				JOptionPane.showMessageDialog(this, "Input must be a positive number!", 
						"Space Invaders", 
						JOptionPane.ERROR_MESSAGE);
			} else if (Integer.valueOf(input) > 6 || Integer.valueOf(input) < 2) {
				JOptionPane.showMessageDialog(this, "Input must be in range!", 
						"Space Invaders", 
						JOptionPane.ERROR_MESSAGE);
			} else {
				validInput = true;
			}
		}
		return input;
	}



	// Custom event handlers
	
	@SuppressWarnings("unused")
	private class CellActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton cell = (JButton) e.getSource();
			shoot(Integer.valueOf(cell.getActionCommand()));
		}
		
	}
	
	private class ProcessDrag extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}
	
	private class ProcessButton implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if (e.getPropertyName().equals("foreground")) {
				JButton btn = (JButton) e.getSource();
				Integer position = Integer.parseInt(btn.getActionCommand());
				shoot(position);				
			}
		}
	}
	
}
