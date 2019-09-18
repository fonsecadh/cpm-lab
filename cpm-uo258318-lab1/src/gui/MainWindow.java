package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
		
	// Variables
	private JPanel contentPane = null;
	private JButton btnOk = null;
	
	
	public MainWindow() {
		this.setSize(400, 300);
		this.setTitle("Hello World!");
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		this.setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		btnOk = new JButton();
		btnOk.setBackground(Color.YELLOW);
		btnOk.setText("Kill revisionists");
		btnOk.setBounds(170, 220, 200, 30);
		
		contentPane.add(btnOk);
		
	}
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
