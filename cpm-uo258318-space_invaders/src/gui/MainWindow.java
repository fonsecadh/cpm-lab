package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logic.Game;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JButton btnDice;
	private JLabel lblSpaceShip;
	private JLabel lblScore;
	private JLabel lblEarth;
	private JTextField txtScore;
	private JPanel pnShots;
	private JPanel pnBoard;
	private JButton btnCell01;
	private JButton btnCell02;
	private JButton btnCell03;
	private JButton btnCell04;
	private JButton btnCell05;
	private JButton btnCell06;
	private JButton btnCell07;
	private JButton btnCell08;
	
	/**
	 * Business class that manages the logic of the game.
	 */
	private Game game = new Game();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	 */
	public MainWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/invader.jpg")));
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 508);
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
		
		enableBoard(false);
		txtScore.setText(String.valueOf(game.getScore()));
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
			pnShots.setBounds(113, 158, 368, 95);
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
			pnBoard.add(getBtnCell01());
			pnBoard.add(getBtnCell02());
			pnBoard.add(getBtnCell03());
			pnBoard.add(getBtnCell04());
			pnBoard.add(getBtnCell05());
			pnBoard.add(getBtnCell06());
			pnBoard.add(getBtnCell07());
			pnBoard.add(getBtnCell08());
		}
		return pnBoard;
	}
	private JButton getBtnCell01() {
		if (btnCell01 == null) {
			btnCell01 = new JButton("");
			btnCell01.setBackground(Color.BLACK);
			btnCell01.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell01;
	}
	private JButton getBtnCell02() {
		if (btnCell02 == null) {
			btnCell02 = new JButton("");
			btnCell02.setBackground(Color.BLACK);
			btnCell02.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell02;
	}
	private JButton getBtnCell03() {
		if (btnCell03 == null) {
			btnCell03 = new JButton("");
			btnCell03.setBackground(Color.BLACK);
			btnCell03.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell03;
	}
	private JButton getBtnCell04() {
		if (btnCell04 == null) {
			btnCell04 = new JButton("");
			btnCell04.setBackground(Color.BLACK);
			btnCell04.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell04;
	}
	private JButton getBtnCell05() {
		if (btnCell05 == null) {
			btnCell05 = new JButton("");
			btnCell05.setBackground(Color.BLACK);
			btnCell05.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell05;
	}
	private JButton getBtnCell06() {
		if (btnCell06 == null) {
			btnCell06 = new JButton("");
			btnCell06.setBackground(Color.BLACK);
			btnCell06.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell06;
	}
	private JButton getBtnCell07() {
		if (btnCell07 == null) {
			btnCell07 = new JButton("");
			btnCell07.setBackground(Color.BLACK);
			btnCell07.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell07;
	}
	private JButton getBtnCell08() {
		if (btnCell08 == null) {
			btnCell08 = new JButton("");
			btnCell08.setBackground(Color.BLACK);
			btnCell08.setBorder(new LineBorder(Color.BLUE, 2));
		}
		return btnCell08;
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
			JOptionPane.showMessageDialog(this, "Game Over!", "Space Invaders", 
					JOptionPane.INFORMATION_MESSAGE);
			enableBoard(false);
		}
	}
	
	private void shoot(int position) {
		game.shoot(position);
	}	
	
}
