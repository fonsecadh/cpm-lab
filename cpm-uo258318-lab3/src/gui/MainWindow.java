package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Menu;
import logic.Order;
import logic.Product;
import logic.ProductType;
import logic.adapter.discount.DiscountAdapter;
import logic.adapter.discount.McHappyDay;
import logic.adapter.products.OrderAdapter;
import logic.adapter.products.OrderManager;

public class MainWindow extends JFrame {
		
	// Constants
	private static final String LOOK_AND_FEEL = 
			"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	
	
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
	private JLabel lblDiscount;	
		
	/**
	 * Business class which manages the discount logic.
	 */
	private DiscountAdapter discountAdapter;
	
	/**
	 * Business class which expands internal order logic.
	 */
	private OrderAdapter orderAdapter;
	private JButton btnDelete;
	private JMenuBar menuBar;
	private JMenu mnOrder;
	private JMenuItem mntmNew;
	private JSeparator separator1Order;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmContents;
	private JSeparator separator1Help;
	private JMenuItem mntmAbout;
	private JLabel lblProductPicture;
	private JPanel panelFilter;
	private JButton btnHamburguers;
	private JButton btnDrinks;
	private JButton btnSides;
	private JButton btnDesserts;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					
					// Nimbus Look and Feel is not working on my GNU/Linux system				
					try {
						UIManager.setLookAndFeel(LOOK_AND_FEEL);
					} catch (Exception e) {
						System.err.println("Nimbus look and feel not available.");
					}
					
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
		setBounds(100, 100, 1128, 649);
		setJMenuBar(getMenuBar_1());
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
		contentPane.add(getSpCurrentOrder());
		contentPane.add(getLblCurrentOrder());
		contentPane.add(getLblDiscount());
		contentPane.add(getBtnDelete());		
		contentPane.add(getLblProductPicture());
		contentPane.add(getPanelFilter());
		
		this.getRootPane().setDefaultButton(getBtnNext());
		this.getRootPane().registerKeyboardAction(
				e -> initialize(), 
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		// We instantiate the McHappyDay logic class
		this.discountAdapter = new McHappyDay(order);
		
		// We instantiate the OrderManager logic class
		this.orderAdapter = new OrderManager(order);
		
		// We show the picture for the first product
		// at start.
		showPicture();
	}	
	
	public void initialize() {
		getCbProducts().setModel(new DefaultComboBoxModel<Product>(menu.getProducts()));
		getCbProducts().setSelectedIndex(0);
		getSpUnits().setValue(1);
		getTfOrderPrice().setText("");
		getBtnNext().setEnabled(false);
		getTaCurrentOrder().setText("");
		getLblDiscount().setVisible(false);
		
		orderAdapter.initialize();
	}

	public JPanel getContentPane() {
		return contentPane;
	}
	
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.PNG")));
			lblLogo.setBounds(323, 11, 204, 160);
		}
		return lblLogo;
	}
	private JLabel getLblMcdonalds() {
		if (lblMcdonalds == null) {
			lblMcdonalds = new JLabel("McDonald's");
			lblMcdonalds.setFont(new Font("Arial", Font.BOLD, 36));
			lblMcdonalds.setBounds(560, 57, 245, 90);
		}
		return lblMcdonalds;
	}
	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setDisplayedMnemonic('r');
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblProducts.setBounds(310, 240, 85, 26);
		}
		return lblProducts;
	}
	private JComboBox<Product> getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox<Product>();
			cbProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showPicture();
					isPossibleToDelete();
				}
			});
			cbProducts.setModel(new DefaultComboBoxModel<Product>(menu.getProducts()));
			cbProducts.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbProducts.setBounds(310, 277, 297, 21);
		}
		return cbProducts;
	}
	
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('u');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblUnits.setBounds(658, 240, 85, 26);
		}
		return lblUnits;
	}
	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					isPossibleToDelete();
				}
			});
			spUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnits.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spUnits.setBounds(658, 271, 55, 31);
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
					isPossibleToDelete();
					resetSpinner();
				}
			});
			btnAdd.setMnemonic('a');
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(new Color(46, 139, 87));
			btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAdd.setBounds(725, 271, 101, 31);
		}
		return btnAdd;
	}

	private void addProductToOrder() {
		Product selectedProduct = (Product) cbProducts.getSelectedItem();
		int units = (int) spUnits.getValue();
		orderAdapter.addProduct(selectedProduct, units);
	}	
	
	private void updateShownOrderPrize() {		
		float total = discountAdapter.calculateTotalPrize();
		
		if (discountAdapter.needToApplyDiscount()) {			
			showDiscountLabel();
		} 
		
		getTfOrderPrice().setText(String.valueOf(BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP)));
		
	}

	private void showDiscountLabel() {
		getLblDiscount().setVisible(true);
	}
	
	private void updateCurrentOrderInfo() {		
		getTaCurrentOrder().setText(orderAdapter.getAllProductInformation());
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
			lblOrderPrice.setBounds(658, 318, 85, 26);
		}
		return lblOrderPrice;
	}
	private JTextField getTfOrderPrice() {
		if (tfOrderPrice == null) {
			tfOrderPrice = new JTextField();
			tfOrderPrice.setToolTipText("This is the total prize of the order.");
			tfOrderPrice.setEditable(false);
			tfOrderPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tfOrderPrice.setBounds(658, 355, 118, 26);
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
			btnNext.setBounds(912, 550, 89, 23);
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
					initialize();
				}
			});
			btnCancel.setMnemonic('c');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCancel.setBackground(new Color(220, 20, 60));
			btnCancel.setBounds(1008, 550, 89, 23);
		}
		return btnCancel;
	}
	private JScrollPane getSpCurrentOrder() {
		if (spCurrentOrder == null) {
			spCurrentOrder = new JScrollPane();
			spCurrentOrder.setBounds(310, 344, 297, 114);
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
			lblCurrentOrder.setBounds(310, 310, 159, 26);
		}
		return lblCurrentOrder;
	}
	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("10% Discount!");
			lblDiscount.setVisible(false);
			lblDiscount.setFont(new Font("Dialog", Font.BOLD, 14));
			lblDiscount.setBounds(794, 355, 142, 26);
		}
		return lblDiscount;
	}	
	
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.setEnabled(false);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					deleteFromProduct();
					updateShownOrderPrize();
					updateCurrentOrderInfo();
					isPossibleToDelete();
					resetSpinner();
				}			
			});
			btnDelete.setMnemonic('l');
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDelete.setBackground(new Color(46, 139, 87));
			btnDelete.setBounds(836, 271, 101, 31);
		}
		return btnDelete;
	}
	
	private void deleteFromProduct() {
		Product selectedProduct = (Product) cbProducts.getSelectedItem();
		int units = (int) spUnits.getValue();		
		orderAdapter.deleteProduct(selectedProduct, units);			
	}


	public Order getOrder() {
		return order;
	}

	public DiscountAdapter getDiscountAdapter() {
		return discountAdapter;
	}

	private void isPossibleToDelete() {
		Product selectedProduct = (Product) cbProducts.getSelectedItem();
		int units = (int) spUnits.getValue();
		
		if (orderAdapter.canDeleteFromProduct(selectedProduct, units)) {
			getBtnDelete().setEnabled(true);
		} else {
			getBtnDelete().setEnabled(false);
		}
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnOrder());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnOrder() {
		if (mnOrder == null) {
			mnOrder = new JMenu("Order");
			mnOrder.setMnemonic('o');
			mnOrder.add(getMntmNew());
			mnOrder.add(getSeparator1Order());
			mnOrder.add(getMntmExit());
		}
		return mnOrder;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("New");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					initialize();
				}
			});
			mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mntmNew.setMnemonic('n');
		}
		return mntmNew;
	}
	private JSeparator getSeparator1Order() {
		if (separator1Order == null) {
			separator1Order = new JSeparator();
		}
		return separator1Order;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmExit.setMnemonic('x');
		}
		return mntmExit;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('h');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator1Help());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "Not currently available", "Contents", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mntmContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContents.setMnemonic('t');
		}
		return mntmContents;
	}
	private JSeparator getSeparator1Help() {
		if (separator1Help == null) {
			separator1Help = new JSeparator();
		}
		return separator1Help;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, 
							"Developed for the Human-Computer Interaction subject. "
							+ "\n\nApplication name: McDonald's Spain "
							+ "\nAuthor: Hugo Fonseca Díaz (UO258318) "
							+ "\nLicense: GPLv3", 
							"Contents", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	private JLabel getLblProductPicture() {
		if (lblProductPicture == null) {
			lblProductPicture = new JLabel("");
			lblProductPicture.setBounds(625, 403, 245, 139);
		}
		return lblProductPicture;
	}
	
	private void showPicture() {
		spUnits.setValue(((SpinnerNumberModel) spUnits.getModel()).getMinimum());
		String pictureName = "/img/" + ((Product) cbProducts.getSelectedItem()).getCode() + ".png";
		lblProductPicture.setIcon(new ImageIcon(MainWindow.class.getResource(pictureName)));
	}
	
	private JPanel getPanelFilter() {
		if (panelFilter == null) {
			panelFilter = new JPanel();
			panelFilter.setBackground(Color.WHITE);
			panelFilter.setBounds(10, 21, 159, 536);
			panelFilter.setLayout(null);
			panelFilter.add(getBtnHamburguers());
			panelFilter.add(getBtnDrinks());
			panelFilter.add(getBtnSides());
			panelFilter.add(getBtnDesserts());
		}
		return panelFilter;
	}
	private JButton getBtnHamburguers() {
		if (btnHamburguers == null) {
			btnHamburguers = new JButton("Hamburguers");
			btnHamburguers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filteringProducts(ProductType.BURGER);
				}
			});
			btnHamburguers.setMnemonic('h');
			btnHamburguers.setBackground(Color.WHITE);
			btnHamburguers.setHorizontalTextPosition(AbstractButton.CENTER);
			btnHamburguers.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnHamburguers.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Hamburguesa.png")));
			btnHamburguers.setBounds(10, 11, 136, 121);
		}
		return btnHamburguers;
	}
	private JButton getBtnDrinks() {
		if (btnDrinks == null) {
			btnDrinks = new JButton("Drinks");
			btnDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filteringProducts(ProductType.DRINK);
				}
			});
			btnDrinks.setMnemonic('d');
			btnDrinks.setBackground(Color.WHITE);
			btnDrinks.setHorizontalTextPosition(AbstractButton.CENTER);
			btnDrinks.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnDrinks.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Bebida.png")));
			btnDrinks.setBounds(10, 143, 136, 121);
		}
		return btnDrinks;
	}
	private JButton getBtnSides() {
		if (btnSides == null) {
			btnSides = new JButton("Sides");
			btnSides.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filteringProducts(ProductType.SIDE);
				}
			});
			btnSides.setMnemonic('i');
			btnSides.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Complemento.png")));
			btnSides.setHorizontalTextPosition(AbstractButton.CENTER);
			btnSides.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnSides.setBackground(Color.WHITE);
			btnSides.setBounds(10, 272, 136, 121);
		}
		return btnSides;
	}
	private JButton getBtnDesserts() {
		if (btnDesserts == null) {
			btnDesserts = new JButton("Desserts");
			btnDesserts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filteringProducts(ProductType.DESSERT);
				}
			});
			btnDesserts.setMnemonic('s');
			btnDesserts.setBackground(Color.WHITE);
			btnDesserts.setHorizontalTextPosition(AbstractButton.CENTER);
			btnDesserts.setVerticalTextPosition(AbstractButton.BOTTOM);
			btnDesserts.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Postre.png")));
			btnDesserts.setBounds(10, 404, 136, 121);
		}
		return btnDesserts;
	}
	

	/**
	 * Refills the combo box filtering the products 
	 * by means of the type given.
	 *
	 * @param type
	 * 			The product type acting as a filter.
	 */
	private void filteringProducts(ProductType type) {
		// We convert the array of products to a List<Product>
		List<Product> products = Arrays.asList(menu.getProducts());
		
		// We filter and collect a List<Product> with the filtered products
		List<Product> filteredProducts = products
			.parallelStream()
			.filter(p -> p.getType().toLowerCase().equals(
					type.toString().toLowerCase()))
			.collect(Collectors.toList());
		
		// We make an auxiliary array with size of the filtered products list
		// so we can convert that list to an array in the next line of code
		Product[] aux = new Product[filteredProducts.size()];	
		
		// We set the filtered products to the combo box model
		cbProducts.setModel(new DefaultComboBoxModel<Product>(
				filteredProducts.toArray(aux)));
		
		// We reset the spinner
		spUnits.setValue(1);
		
		// We show the picture for the first element of the new model
		showPicture();
	}
}
