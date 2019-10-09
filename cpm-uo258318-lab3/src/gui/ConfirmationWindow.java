package gui;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import logic.FileUtil;
import logic.Order;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ConfirmationWindow extends JDialog {
	
	// Attributes
	private JLabel lblOk;
	private JLabel lblProcessing;
	private JLabel lblYourOrderCode;
	private JTextField txtCode;
	private JButton btnFinish;
	
	private String code;
	private Order order;
	private JLabel lblPrize;
	private JTextField tfPrize;
	private RegistryWindow registryWindow;
	
	

	/**
	 * Create the dialog.
	 * 
	 * @param registryWindow 
	 * 			The registry window.
	 * 
	 */
	public ConfirmationWindow(RegistryWindow registryWindow) {
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
		
		this.getRootPane().setDefaultButton(getBtnFinish());
		getContentPane().add(getLblPrize());
		getContentPane().add(getTfPrize());
		
		// We initialize the code and order attributes
		this.code = FileUtil.setFileName();		
		this.order = registryWindow.getMainWindow().getOrder();	
		
		showCode();		
		showTotalPrize(registryWindow.getMainWindow().getDiscountAdapter().calculateTotalPrize());
		
		this.registryWindow = registryWindow;
	}

	private void showTotalPrize(float prize) {
		getTfPrize().setText(String.valueOf(BigDecimal.valueOf(prize).setScale(2, BigDecimal.ROUND_HALF_UP)));
	}

	private void showCode() {
		getTxtCode().setText(code);
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
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					saveOrder();
					registryWindow.dispose();
					registryWindow.getMainWindow().initialize();
					dispose();
				}
			});
			btnFinish.setMnemonic('f');
			btnFinish.setForeground(Color.WHITE);
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnFinish.setBackground(new Color(46, 139, 87));
			btnFinish.setBounds(428, 196, 89, 23);
		}
		return btnFinish;
	}

	private void saveOrder() {
		order.saveOrder(code);
	}
	
	private JLabel getLblPrize() {
		if (lblPrize == null) {
			lblPrize = new JLabel("Total prize:");
			lblPrize.setLabelFor(getTfPrize());
			lblPrize.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblPrize.setBounds(80, 163, 135, 34);
		}
		return lblPrize;
	}
	private JTextField getTfPrize() {
		if (tfPrize == null) {
			tfPrize = new JTextField();
			tfPrize.setFont(new Font("Dialog", Font.PLAIN, 14));
			tfPrize.setEditable(false);
			tfPrize.setColumns(10);
			tfPrize.setBounds(181, 167, 205, 27);
		}
		return tfPrize;
	}
}
