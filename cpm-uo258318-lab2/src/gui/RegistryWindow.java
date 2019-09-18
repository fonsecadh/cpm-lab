package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegistryWindow extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	
	// Variables
	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnNext;
	private JPanel customerInfoPanel;
	private JPanel orderPanel;
	private JLabel lblNameSurname;
	private JTextField txtNameSurname;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistryWindow frame = new RegistryWindow();
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
	public RegistryWindow() {
		setTitle("Customer Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 372);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		contentPane.add(getCustomerInfoPanel());
		contentPane.add(getOrderPanel());
	}
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 System.exit(0);
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(new Color(220, 20, 60));
			btnCancel.setBounds(570, 286, 89, 23);
		}
		return btnCancel;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNext.setBackground(new Color(46, 139, 87));
			btnNext.setBounds(471, 286, 89, 23);
		}
		return btnNext;
	}
	private JPanel getCustomerInfoPanel() {
		if (customerInfoPanel == null) {
			customerInfoPanel = new JPanel();
			customerInfoPanel.setBorder(new TitledBorder(null, "Customer Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			customerInfoPanel.setBackground(Color.WHITE);
			customerInfoPanel.setBounds(24, 21, 632, 191);
			customerInfoPanel.setLayout(null);
			customerInfoPanel.add(getLblNameSurname());
			customerInfoPanel.add(getTxtNameSurname());
		}
		return customerInfoPanel;
	}
	private JPanel getOrderPanel() {
		if (orderPanel == null) {
			orderPanel = new JPanel();
			orderPanel.setBackground(Color.WHITE);
			orderPanel.setBounds(24, 228, 375, 70);
		}
		return orderPanel;
	}
	private JLabel getLblNameSurname() {
		if (lblNameSurname == null) {
			lblNameSurname = new JLabel("Name and Surname:");
			lblNameSurname.setLabelFor(getTxtNameSurname());
			lblNameSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNameSurname.setBounds(10, 33, 132, 17);
		}
		return lblNameSurname;
	}
	private JTextField getTxtNameSurname() {
		if (txtNameSurname == null) {
			txtNameSurname = new JTextField();
			txtNameSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNameSurname.setBounds(185, 27, 418, 26);
			txtNameSurname.setColumns(10);
		}
		return txtNameSurname;
	}
}
