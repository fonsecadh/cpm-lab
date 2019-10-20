package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistryWindow extends JDialog {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final int MIN_PASSWD_LENGTH = 8;
	
	
	// Attributes
	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnNext;
	private JPanel customerInfoPanel;
	private JPanel orderPanel;
	private JLabel lblNameSurname;
	private JTextField txtNameSurname;
	private JLabel lblBirthrate;
	private JComboBox cbBirthrate;
	private JLabel lblPasswd;
	private JPasswordField pfPasswd;
	private JLabel lblPasswdRep;
	private JPasswordField pfPasswdRep;
	private JRadioButton rdbtnOnSite;
	private JRadioButton rdbtnTakeAway;
	private final ButtonGroup btnGrOrder = new ButtonGroup();
	private ConfirmationWindow confirmationWindow = null;
	
	private MainWindow mainWindow = new MainWindow();
	
	
	
	/**
	 * Create the frame.
	 * 
	 * @param mainWindow 
	 * 			The main window.
	 */
	public RegistryWindow(MainWindow mainWindow) {
		setTitle("McDonald's Spain: Customer Information");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 355);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		contentPane.add(getCustomerInfoPanel());
		contentPane.add(getOrderPanel());
		
		this.getRootPane().setDefaultButton(getBtnNext());
		this.getRootPane().registerKeyboardAction(
				e -> { dispose(); mainWindow.initialize(); }, 
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		this.mainWindow = mainWindow;
	}
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('c');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					mainWindow.initialize();
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(new Color(220, 20, 60));
			btnCancel.setBounds(544, 286, 89, 23);
		}
		return btnCancel;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					if (checkComponents()) {
						openConfirmationWindow();
					}
				}				
			});
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNext.setBackground(new Color(46, 139, 87));
			btnNext.setBounds(443, 286, 89, 23);
		}
		return btnNext;
	}
	
	private void openConfirmationWindow() {
		confirmationWindow = new ConfirmationWindow(this);
		confirmationWindow.setModal(true);
		confirmationWindow.setLocationRelativeTo(this);
		confirmationWindow.setVisible(true);		
	}				
	
	private boolean checkComponents() {
		// We define a boolean variable for the result
		boolean isEverythingOk = true;		
		
		// We retrieve the components we need to check
		JTextField theTxtNameSurname = getTxtNameSurname();
		JPasswordField thePfPasswd = getPfPasswd();
		JPasswordField thePfPasswdRep = getPfPasswdRep();					
		
		// We check that none of the text fields is empty
		if (theTxtNameSurname.getText().equals("") 
				|| String.valueOf(thePfPasswd.getPassword()).equals("") 
				|| String.valueOf(thePfPasswdRep.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(
					null, "Some fields are empty", "Unable to continue", 
					JOptionPane.ERROR_MESSAGE);
			isEverythingOk = false;
		}					
		
		// Both password fields match
		if (!String.valueOf(thePfPasswd.getPassword())
				.equals(String.valueOf(thePfPasswdRep.getPassword()))) {
			JOptionPane.showMessageDialog(
					null, "Passwords do not match", "Unable to continue", 
					JOptionPane.ERROR_MESSAGE);
			isEverythingOk = false;
		}
		
		return isEverythingOk;
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
			customerInfoPanel.add(getLblBirthrate());
			customerInfoPanel.add(getCbBirthrate());
			customerInfoPanel.add(getLblPasswd());
			customerInfoPanel.add(getPfPasswd());
			customerInfoPanel.add(getLblPasswdRep());
			customerInfoPanel.add(getPfPasswdRep());
		}
		return customerInfoPanel;
	}
	private JPanel getOrderPanel() {
		if (orderPanel == null) {
			orderPanel = new JPanel();
			orderPanel.setBorder(new TitledBorder(null, "Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			orderPanel.setBackground(Color.WHITE);
			orderPanel.setBounds(24, 228, 375, 70);
			orderPanel.setLayout(null);
			orderPanel.add(getRdbtnOnSite());
			orderPanel.add(getRdbtnTakeAway());
		}
		return orderPanel;
	}
	private JLabel getLblNameSurname() {
		if (lblNameSurname == null) {
			lblNameSurname = new JLabel("Name and Surname:");
			lblNameSurname.setDisplayedMnemonic(KeyEvent.VK_N);
			lblNameSurname.setLabelFor(getTxtNameSurname());
			lblNameSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNameSurname.setBounds(10, 33, 157, 17);
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
	private JLabel getLblBirthrate() {
		if (lblBirthrate == null) {
			lblBirthrate = new JLabel("Birthrate:");
			lblBirthrate.setDisplayedMnemonic(KeyEvent.VK_B);
			lblBirthrate.setLabelFor(getCbBirthrate());
			lblBirthrate.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblBirthrate.setBounds(10, 67, 122, 21);
		}
		return lblBirthrate;
	}
	private JComboBox getCbBirthrate() {
		if (cbBirthrate == null) {
			cbBirthrate = new JComboBox();
			IntStream
				.range(1900, Calendar.getInstance().get(Calendar.YEAR) + 1)
				.forEach(date -> cbBirthrate.addItem(date));
			cbBirthrate.setFont(new Font("Dialog", Font.PLAIN, 14));
			cbBirthrate.setBounds(185, 65, 418, 24);
		}
		return cbBirthrate;
	}
	private JLabel getLblPasswd() {
		if (lblPasswd == null) {
			lblPasswd = new JLabel("Password:");
			lblPasswd.setDisplayedMnemonic(KeyEvent.VK_P);
			lblPasswd.setLabelFor(getPfPasswd());
			lblPasswd.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblPasswd.setBounds(10, 106, 122, 21);
		}
		return lblPasswd;
	}
	private JPasswordField getPfPasswd() {
		if (pfPasswd == null) {
			pfPasswd = new JPasswordField();
			pfPasswd.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (getPfPasswd().getPassword().length < MIN_PASSWD_LENGTH) {
						JOptionPane.showMessageDialog(contentPane, "The password must be at least " + MIN_PASSWD_LENGTH + " characters");
						getPfPasswd().grabFocus();
					}
				}
			});
			pfPasswd.setFont(new Font("Dialog", Font.PLAIN, 14));
			pfPasswd.setBounds(185, 104, 418, 26);
		}
		return pfPasswd;
	}
	private JLabel getLblPasswdRep() {
		if (lblPasswdRep == null) {
			lblPasswdRep = new JLabel("Repeat password:");
			lblPasswdRep.setDisplayedMnemonic(KeyEvent.VK_R);
			lblPasswdRep.setLabelFor(getPfPasswdRep());
			lblPasswdRep.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblPasswdRep.setBounds(10, 145, 145, 21);
		}
		return lblPasswdRep;
	}
	private JPasswordField getPfPasswdRep() {
		if (pfPasswdRep == null) {
			pfPasswdRep = new JPasswordField();
			pfPasswdRep.setFont(new Font("Dialog", Font.PLAIN, 14));
			pfPasswdRep.setBounds(185, 142, 418, 26);
		}
		return pfPasswdRep;
	}
	private JRadioButton getRdbtnOnSite() {
		if (rdbtnOnSite == null) {
			rdbtnOnSite = new JRadioButton("On site");
			rdbtnOnSite.setMnemonic('s');
			rdbtnOnSite.setSelected(true);
			btnGrOrder.add(rdbtnOnSite);
			rdbtnOnSite.setBackground(Color.WHITE);
			rdbtnOnSite.setFont(new Font("Dialog", Font.PLAIN, 14));
			rdbtnOnSite.setBounds(53, 39, 114, 23);
		}
		return rdbtnOnSite;
	}
	private JRadioButton getRdbtnTakeAway() {
		if (rdbtnTakeAway == null) {
			rdbtnTakeAway = new JRadioButton("Take away");
			rdbtnTakeAway.setMnemonic('t');
			btnGrOrder.add(rdbtnTakeAway);
			rdbtnTakeAway.setFont(new Font("Dialog", Font.PLAIN, 14));
			rdbtnTakeAway.setBackground(Color.WHITE);
			rdbtnTakeAway.setBounds(171, 39, 149, 23);
		}
		return rdbtnTakeAway;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public JTextField getNameAndSurname() {
		return this.txtNameSurname;
	}
	
	public JComboBox getBirthDate() {
		return this.cbBirthrate;
	}
}
