package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logic.Menu;
import logic.Order;
import logic.Product;

public class MainWindow extends JFrame {

	
	// Attributes
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblMcdonalds;
	private JLabel lblProducts;
	private JComboBox<Product> cbProducts;
	private JLabel lblUnits;
	private JSpinner spUnits;
	private JButton btnAdd;
	private JLabel lblOrderPrice;
	private JTextField tfOrderPrice;
	private JButton btnNext;
	private JButton btnCancel;
	private RegistryWindow registryWindow = null;
	
	private Menu menu = new Menu();
	private Order order = new Order();
	private JScrollPane spCurrentOrder;
	private JTextArea taCurrentOrder;
	private JLabel lblCurrentOrder;
	private HashMap<Product, Integer> orderedProducts = new HashMap<Product, Integer>();
	private JLabel lblDiscount;
	

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
		
		this.getRootPane().setDefaultButton(getBtnNext());
		contentPane.add(getSpCurrentOrder());
		contentPane.add(getLblCurrentOrder());
		contentPane.add(getLblDiscount());
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
	private JComboBox<Product> getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox<Product>();
			cbProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getSpUnits().setValue(1);
				}
			});
			cbProducts.setModel(new DefaultComboBoxModel<Product>(menu.getProducts()));
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
			spUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spUnits.setBounds(382, 240, 55, 31);
		}
		return spUnits;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addProductToOrder();
					updateShownOrderPrize();
					updateCurrentOrderInfo();
					enableNextButton();
					resetSpinner();
				}
			});
			btnAdd.setMnemonic('a');
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(new Color(46, 139, 87));
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAdd.setBounds(449, 240, 101, 31);
		}
		return btnAdd;
	}

	private void addProductToOrder() {
		Product selectedProduct = (Product) cbProducts.getSelectedItem();
		int units = (int) spUnits.getValue();
		order.add(selectedProduct, units);	
		
		Integer currentUnits = orderedProducts.get(selectedProduct);
		
		if (currentUnits != null) { // If the product is already in the order
			// We update the units
			orderedProducts.replace(selectedProduct, currentUnits, currentUnits + units);
		} else {
			// We add the new product to the dictionary
			orderedProducts.put(selectedProduct, units);
		}
		
	}	
	
	private void updateShownOrderPrize() {
		float total = order.calcTotal();
		
		if (checkIfDiscount() == true) {
			total *= 0.9;
			showDiscountLabel();
		} 
		
		getTfOrderPrice().setText(String.valueOf(BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP)));
		
	}

	private void showDiscountLabel() {
		getLblDiscount().setVisible(true);
	}

	private boolean checkIfDiscount() {
		if (order.calcTotal() >= 50.0) {
			return true;
		}
		
		return false;
	}
	
	private void updateCurrentOrderInfo() {
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("This is the information regarding your order:\n");
		sbInfo.append("Type - Name - Prize - Units\n");
		
		// We append the elements of the dictionary
		orderedProducts.entrySet().forEach(p -> sbInfo.append(p.getKey() + " - " + p.getValue() + "\n"));
		
		getTaCurrentOrder().setText(sbInfo.toString());
	}
	
	private void enableNextButton() {
		getBtnNext().setEnabled(true);
	}			

	private void resetSpinner() {
		getSpUnits().setValue(1);
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
			tfOrderPrice.setToolTipText("This is the total prize of the order.");
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
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openRegistryWindow();
				}
			});
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNext.setBackground(new Color(46, 139, 87));
			btnNext.setBounds(483, 399, 89, 23);
		}
		return btnNext;
	}
	
	private void openRegistryWindow() {
		registryWindow = new RegistryWindow(this);
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

	public Order getOrder() {
		return order;
	}
	private JScrollPane getSpCurrentOrder() {
		if (spCurrentOrder == null) {
			spCurrentOrder = new JScrollPane();
			spCurrentOrder.setBounds(34, 313, 297, 114);
			spCurrentOrder.setViewportView(getTaCurrentOrder());
		}
		return spCurrentOrder;
	}
	private JTextArea getTaCurrentOrder() {
		if (taCurrentOrder == null) {
			taCurrentOrder = new JTextArea();
			taCurrentOrder.setEditable(false);
		}
		return taCurrentOrder;
	}
	private JLabel getLblCurrentOrder() {
		if (lblCurrentOrder == null) {
			lblCurrentOrder = new JLabel("Current order:");
			lblCurrentOrder.setLabelFor(getSpCurrentOrder());
			lblCurrentOrder.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblCurrentOrder.setDisplayedMnemonic('n');
			lblCurrentOrder.setBounds(34, 279, 159, 26);
		}
		return lblCurrentOrder;
	}
	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("10% Discount!");
			lblDiscount.setVisible(false);
			lblDiscount.setFont(new Font("Dialog", Font.BOLD, 14));
			lblDiscount.setBounds(518, 324, 142, 26);
		}
		return lblDiscount;
	}
}
