package gui;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConfirmationWindow extends JDialog {
	
	// Attributes
	private JLabel lblOk;
	private JLabel lblProcessing;
	private JLabel lblYourOrderCode;
	private JTextField txtCode;
	private JButton btnFinish;

	/**
	 * Create the dialog.
	 */
	public ConfirmationWindow() {
		setBackground(Color.WHITE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmationWindow.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's Spain: Confirmation Window");
		setBounds(100, 100, 556, 259);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOk());
		getContentPane().add(getLblProcessing());
		getContentPane().add(getLblYourOrderCode());
		getContentPane().add(getTxtCode());
		getContentPane().add(getBtnFinish());

	}

	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("");
			lblOk.setIcon(new ImageIcon(ConfirmationWindow.class.getResource("/img/ok.png")));
			lblOk.setBounds(41, 33, 72, 43);
		}
		return lblOk;
	}
	private JLabel getLblProcessing() {
		if (lblProcessing == null) {
			lblProcessing = new JLabel("We are processing your order");
			lblProcessing.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblProcessing.setBounds(123, 48, 394, 28);
		}
		return lblProcessing;
	}
	private JLabel getLblYourOrderCode() {
		if (lblYourOrderCode == null) {
			lblYourOrderCode = new JLabel("Your order code is:");
			lblYourOrderCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblYourOrderCode.setBounds(80, 118, 135, 34);
		}
		return lblYourOrderCode;
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtCode.setEditable(false);
			txtCode.setBounds(225, 122, 161, 27);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.setMnemonic('f');
			btnFinish.setForeground(Color.WHITE);
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnFinish.setBackground(new Color(46, 139, 87));
			btnFinish.setBounds(428, 196, 89, 23);
		}
		return btnFinish;
	}
}
