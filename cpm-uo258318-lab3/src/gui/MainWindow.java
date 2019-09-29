package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	
	// Attributes
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblMcdonalds;
	private JLabel lblProducts;
	private JComboBox cbProducts;
	private JLabel lblUnits;
	private JSpinner spUnits;
	private JButton btnAdd;
	private JLabel lblOrderPrice;
	private JTextField tfOrderPrice;
	private JButton btnNext;
	private JButton btnCancel;
	private RegistryWindow registryWindow = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's Spain");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblMcdonalds());
		contentPane.add(getLblProducts());
		contentPane.add(getCbProducts());
		contentPane.add(getLblUnits());
		contentPane.add(getSpUnits());
		contentPane.add(getBtnAdd());
		contentPane.add(getLblOrderPrice());
		contentPane.add(getTfOrderPrice());
		contentPane.add(getBtnNext());
		contentPane.add(getBtnCancel());
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.PNG")));
			lblLogo.setBounds(10, 11, 204, 160);
		}
		return lblLogo;
	}
	private JLabel getLblMcdonalds() {
		if (lblMcdonalds == null) {
			lblMcdonalds = new JLabel("McDonald's");
			lblMcdonalds.setFont(new Font("Arial", Font.BOLD, 36));
			lblMcdonalds.setBounds(257, 56, 245, 90);
		}
		return lblMcdonalds;
	}
	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setDisplayedMnemonic('r');
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblProducts.setBounds(34, 209, 85, 26);
		}
		return lblProducts;
	}
	private JComboBox getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox();
			cbProducts.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbProducts.setBounds(34, 246, 297, 21);
		}
		return cbProducts;
	}
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('u');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblUnits.setBounds(382, 209, 85, 26);
		}
		return lblUnits;
	}
	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spUnits.setBounds(382, 240, 55, 31);
		}
		return spUnits;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setMnemonic('a');
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(new Color(46, 139, 87));
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAdd.setBounds(449, 240, 101, 31);
		}
		return btnAdd;
	}
	private JLabel getLblOrderPrice() {
		if (lblOrderPrice == null) {
			lblOrderPrice = new JLabel("Order price:");
			lblOrderPrice.setLabelFor(getTfOrderPrice());
			lblOrderPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblOrderPrice.setBounds(382, 287, 85, 26);
		}
		return lblOrderPrice;
	}
	private JTextField getTfOrderPrice() {
		if (tfOrderPrice == null) {
			tfOrderPrice = new JTextField();
			tfOrderPrice.setEditable(false);
			tfOrderPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tfOrderPrice.setBounds(382, 324, 118, 26);
			tfOrderPrice.setColumns(10);
		}
		return tfOrderPrice;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openRegistryWindow();
				}
			});
			btnNext.setMnemonic('n');
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNext.setBackground(new Color(46, 139, 87));
			btnNext.setBounds(483, 399, 89, 23);
		}
		return btnNext;
	}
	
	private void openRegistryWindow() {
		registryWindow = new RegistryWindow();
		registryWindow.setModal(true);
		registryWindow.setLocationRelativeTo(this);
		registryWindow.setVisible(true);
	}	
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btnCancel.setMnemonic('c');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setBackground(new Color(220, 20, 60));
			btnCancel.setBounds(579, 399, 89, 23);
		}
		return btnCancel;
	}
}
