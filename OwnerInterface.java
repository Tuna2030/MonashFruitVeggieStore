
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class OwnerInterface {
	
	private JFrame frame;
	private JTable accountTable;
	private JScrollPane accountScroll;
	private JTable productTable;
	private JScrollPane productScroll;
	private Component parent;
	
	private Object[][] prodData;
	
	public OwnerInterface(Component parent) {
		this.parent = parent;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.25);
		int initHeight = (int) (screenSize.height * 0.35);
	
		frame = new JFrame();
		frame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel("Administrator");
		label.setFont(new Font("Arial", 1, 16));
		label.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.03), (int)(initWidth * 0.40), (int)(initHeight * 0.10));
		panel.add(label);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.75), (int)(initHeight * 0.02), (int)(initWidth * 0.23), (int)(initHeight * 0.11));
		panel.add(log_out);
		
		JButton viewPro = new JButton("View Products");
		viewPro.setFont(new Font("Arial", 0, 16));
		viewPro.setBounds((int)(initWidth * 0.25), (int)(initHeight * 0.23), (int)(initWidth * 0.50), (int)(initHeight * 0.15));
		panel.add(viewPro);
		
		JButton manageCus = new JButton("Manage Accounts");
		manageCus.setFont(new Font("Arial", 0, 16));
		manageCus.setBounds((int)(initWidth * 0.25), (int)(initHeight * 0.43), (int)(initWidth * 0.50), (int)(initHeight * 0.15));
		panel.add(manageCus);
		
		JButton viewTrans = new JButton("View Transactions");
		viewTrans.setFont(new Font("Arial", 0, 16));
		viewTrans.setBounds((int)(initWidth * 0.25), (int)(initHeight * 0.63), (int)(initWidth * 0.50), (int)(initHeight * 0.15));
		panel.add(viewTrans);
		
		log_out.addActionListener(new LogoutListener());
		
		
		
		viewPro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewProductUI();
					
			}});
		
		manageCus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewAccountsUI();
					
			}});
		
		viewTrans.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				viewTransactionsUI();
					
			}

		});
		
		frame.add(panel);
		frame.setSize(initWidth, initHeight);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean viewAccountsUI() {
		boolean isSccuess = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.4);
		int initHeight = (int) (screenSize.height * 0.5);
	
		JFrame aFrame = new JFrame();
		aFrame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setText("View & Manage User Accounts");
		label.setFont(new Font("Arial", 1, 16));
		label.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.02), (int)(initWidth * 0.60), (int)(initHeight * 0.08));
		panel.add(label);
		
		JButton go_back_a = new JButton("Go Back");
		go_back_a.setBounds((int)(initWidth * 0.69), (int)(initHeight * 0.03), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(go_back_a);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.84), (int)(initHeight * 0.03), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(log_out);
		
		log_out.addActionListener(new LogoutListener()); 
		
		go_back_a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				aFrame.dispose();	
			}});
		
		JTextField input = new JTextField("User Name");
		input.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.13), (int)(initWidth * 0.25), (int)(initHeight * 0.08));
		panel.add(input);
		
		input.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				input.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				input.setText("User Name");
			}
			
		});
		
		JButton search = new JButton("Search");
		search.setBounds((int)(initWidth * 0.31), (int)(initHeight * 0.13), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(search);
		
		JButton delete = new JButton("Delete");
		delete.setBounds((int)(initWidth * 0.46), (int)(initHeight * 0.13), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(delete);
		
		Object[][] tableData = initUserData(""); 
		Object[] columnTitle = {"Customer ID" , "User Name"};  
		accountTable = new JTable(new DefaultTableModel(tableData, columnTitle));
		accountTable.getTableHeader().setFont(new Font("Arial", 1, 14));
		
		accountScroll = new JScrollPane(accountTable);
		accountScroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.22), (int)(initWidth * 0.90), (int)(initHeight * 0.66));				
		panel.add(accountScroll);
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in = input.getText().toString().toLowerCase();
				if (!in.equals("") && !in.equals("user name")) {
					Object[][] tableDataNew = initUserData(input.getText().toString().toLowerCase());
					TableModel dataModel = new DefaultTableModel(tableDataNew, columnTitle);
					accountTable.setModel(dataModel);
				}
			}
			
		});
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					Object[][] tableDataNew = initUserData(input.getText().toString().toLowerCase());
					accountTable.setModel(new DefaultTableModel(tableDataNew, columnTitle));
					input.setText("User Name");
			}
			
		});
		
		aFrame.add(panel);
		aFrame.setSize(initWidth, initHeight);
		aFrame.setVisible(true);
		aFrame.setResizable(false);
		aFrame.setLocationRelativeTo(null);
		aFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		return isSccuess;
	}
	
	public boolean manageProductUI(String product, Component parent) {
		boolean isSuccess = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.4);
		int initHeight = (int) (screenSize.height * 0.6);
	
		JFrame mFrame = new JFrame();
		mFrame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setText("View & Manage Products");
		label.setFont(new Font("Arial", 1, 16));
		label.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.02), (int)(initWidth * 0.60), (int)(initHeight * 0.08));
		panel.add(label);
		
		JButton go_back_p = new JButton("Go Back");
		go_back_p.setBounds((int)(initWidth * 0.71), (int)(initHeight * 0.02), (int)(initWidth * 0.13), (int)(initHeight * 0.07));
		panel.add(go_back_p);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.85), (int)(initHeight * 0.02), (int)(initWidth * 0.13), (int)(initHeight * 0.07));
		panel.add(log_out);
		
		log_out.addActionListener(new LogoutListener()); 
		
		go_back_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				mFrame.dispose();	
			}});
		
		JLabel name = new JLabel("Product Name");
		name.setFont(new Font("Arial", 1, 14));
		name.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.17), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(name);
		
		JTextField name_in = new JTextField();
		name_in.setFont(new Font("Arial", 0, 15));
		name_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.17), (int)(initWidth * 0.35), (int)(initHeight * 0.06));
		panel.add(name_in);
		
		JLabel price = new JLabel("Price(AUD)");
		price.setFont(new Font("Arial", 1, 14));
		price.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.25), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(price);
		
		JTextField price_in = new JTextField();
		price_in.setFont(new Font("Arial", 0, 15));
		price_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.25), (int)(initWidth * 0.25), (int)(initHeight * 0.06));
		panel.add(price_in);
		
		JLabel range = new JLabel("Price Range");
		range.setFont(new Font("Arial", 1, 14));
		range.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.33), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(range);
		
		JComboBox range_in = new JComboBox(StaticValue.PRICE_RANGE);
		range_in.setFont(new Font("Arial", 0, 16));
		range_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.33), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		panel.add(range_in);
		
		JLabel unit = new JLabel("Sale Method");
		unit.setFont(new Font("Arial", 1, 14));
		unit.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.41), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(unit);
		
		JTextField unit_in = new JTextField();
		unit_in.setFont(new Font("Arial", 0, 15));
		unit_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.41), (int)(initWidth * 0.25), (int)(initHeight * 0.06));
		panel.add(unit_in);
		
		JLabel quantity = new JLabel("Quantity");
		quantity.setFont(new Font("Arial", 1, 14));
		quantity.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.49), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(quantity);
		
		JTextField quan_in = new JTextField();
		quan_in.setFont(new Font("Arial", 0, 15));
		quan_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.49), (int)(initWidth * 0.25), (int)(initHeight * 0.06));
		panel.add(quan_in);
		
		JLabel shelfLife = new JLabel("Shelf Life(Day)");
		shelfLife.setFont(new Font("Arial", 1, 14));
		shelfLife.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.57), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(shelfLife);
		
		JTextField shelf_in = new JTextField();
		shelf_in.setFont(new Font("Arial", 0, 15));
		shelf_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.57), (int)(initWidth * 0.25), (int)(initHeight * 0.06));
		panel.add(shelf_in);
		
		JLabel status = new JLabel("Discount");
		status.setFont(new Font("Arial", 1, 14));
		status.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.65), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(status);
		
		JComboBox status_in = new JComboBox(StaticValue.DISCOUNT_STATUS);
		status_in.setFont(new Font("Arial", 0, 15));
		status_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.65), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		panel.add(status_in);
		
		JLabel charity = new JLabel("To Charity");
		charity.setFont(new Font("Arial", 1, 14));
		charity.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.73), (int)(initWidth * 0.18), (int)(initHeight * 0.06));
		panel.add(charity);
		
		JComboBox charity_in = new JComboBox(StaticValue.TO_CHARITY);
		charity_in.setFont(new Font("Arial", 0, 15));
		charity_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.73), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		panel.add(charity_in); 
		
		JButton insert = new JButton("Insert");
		insert.setBounds((int)(initWidth * 0.43), (int)(initHeight * 0.83), (int)(initWidth * 0.15), (int)(initHeight * 0.07));
		panel.add(insert);
		
		JButton update = new JButton("Update");
		update.setBounds((int)(initWidth * 0.35), (int)(initHeight * 0.83), (int)(initWidth * 0.15), (int)(initHeight * 0.07));
		panel.add(update);
		
		JButton delete = new JButton("Delete");
		delete.setBounds((int)(initWidth * 0.58), (int)(initHeight * 0.83), (int)(initWidth * 0.15), (int)(initHeight * 0.07));
		panel.add(delete);
		
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = name_in.getText();
				String price = price_in.getText();
				String priceRange = range_in.getSelectedIndex() + ":" + range_in.getSelectedItem().toString();
				String quantityInStock = quan_in.getText();
				String unitType = unit_in.getText();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = dateFormat.format(new Date());
				String shelfLife = shelf_in.getText();
				String status = status_in.getSelectedItem().toString();
				String toCharity = charity_in.getSelectedItem().toString();
				String product = name + "," + price + "," + priceRange + "," + quantityInStock + "," 
						+ unitType + "," + startDate + "," + shelfLife + "," + status + "," + toCharity;
				boolean isSuccess = new OwnerController().addProduct(product);
				if (isSuccess) {
					JOptionPane.showMessageDialog(null, "Successful !");
					mFrame.dispose();
					parent.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Fail !");
				}
				
			}});
	
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { 
				String id = product.split(",")[0];
				String name = product.split(",")[1];
				String price = price_in.getText();
				String priceRange = range_in.getSelectedIndex() + ":" + range_in.getSelectedItem().toString();
				String quantityInStock = quan_in.getText();
				String unitType = unit_in.getText();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = dateFormat.format(new Date());
				String shelfLife = shelf_in.getText();
				String status = status_in.getSelectedItem().toString();
				String toCharity = charity_in.getSelectedItem().toString();
				String p = id + "," + name + "," + price + "," + priceRange + "," + quantityInStock + "," 
						+ unitType + "," + startDate + "," + shelfLife + "," + status + "," + toCharity;
				boolean isSuccess = new OwnerController().manageProduct(p);
				if (isSuccess) {
					JOptionPane.showMessageDialog(null, "Successful !");
					mFrame.dispose();
					parent.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Fail !");
				}

			}});
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { 
				int id = Integer.parseInt(product.split(",")[0]);
				boolean isSuccess = new OwnerController().deleteProduct(id);
				if (isSuccess) {
					JOptionPane.showMessageDialog(null, "Successful !");
					mFrame.dispose();
					parent.show();
				}
				else {
					JOptionPane.showMessageDialog(null, "Fail !");
				}

			}});
		
		mFrame.add(panel);
		mFrame.setSize(initWidth, initHeight);
		mFrame.setVisible(true);
		mFrame.setResizable(false);
		mFrame.setLocationRelativeTo(null);
		mFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// initial
		if (!product.equals("")) {
			update.setVisible(true);
			delete.setVisible(true);
			insert.setVisible(false);
			String[] temp = product.split(",");
			String name_s = temp[1];
			String price_s = temp[2];
			String priceRange = temp[3].split(":")[0];
			String quan = temp[4];
			String unitType = temp[5];
			String shelflife = temp[7];
			String stat = temp[8];
			String toCharity = temp[9];
			
			name_in.setText(name_s);
			price_in.setText(price_s);
			range_in.setSelectedIndex(Integer.parseInt(priceRange));
			unit_in.setText(unitType);
			quan_in.setText(quan);
			shelf_in.setText(shelflife);
			status_in.setSelectedItem(stat);
			charity_in.setSelectedItem(toCharity);
		}
		else {
			insert.setVisible(true);
			update.setVisible(false);
			delete.setVisible(false);
		}
		
		return isSuccess;
	}
	
	public boolean viewProductUI() {
		boolean isSuccess = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.6);
		int initHeight = (int) (screenSize.height * 0.7);
	
		JFrame pFrame = new JFrame();
		pFrame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setText("View & Manage Products");
		label.setFont(new Font("Arial", 1, 16));
		label.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.02), (int)(initWidth * 0.60), (int)(initHeight * 0.08));
		panel.add(label);
		
		JButton go_back_p = new JButton("Go Back");
		go_back_p.setBounds((int)(initWidth * 0.76), (int)(initHeight * 0.03), (int)(initWidth * 0.10), (int)(initHeight * 0.06));
		panel.add(go_back_p);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.87), (int)(initHeight * 0.03), (int)(initWidth * 0.10), (int)(initHeight * 0.06));
		panel.add(log_out);
		
		log_out.addActionListener(new LogoutListener()); 
		
		go_back_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.show();
				pFrame.dispose();	
			}});
		
		JTextField input = new JTextField("Product ID/Name");
		input.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.12), (int)(initWidth * 0.30), (int)(initHeight * 0.06));
		panel.add(input);
		
		input.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				input.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {}
			
		});
		
		JButton search = new JButton("Search");
		search.setBounds((int)(initWidth * 0.37), (int)(initHeight * 0.12), (int)(initWidth * 0.13), (int)(initHeight * 0.06));
		panel.add(search);
		
		String[] productInfo = new UserController().searchProduct("").split(";");
		if (productInfo.length == 0)
			prodData = new Object[1][];
		else
			prodData = new Object[productInfo.length][]; 
		for (int i = 0; i < productInfo.length; i++) {
			String[] productTemp = productInfo[i].split(",");
			String id = productTemp[0];
			String name = productTemp[1];
			String price = productTemp[2];
			String priceRange = productTemp[3];
			String quantityInStock = productTemp[4];
			String unitType = productTemp[5];
			String startDate = productTemp[6];
			String shelfLife = productTemp[7];
			String discountStatus = productTemp[8];
			String toCharity = productTemp[9];
			prodData[i] = new Object[]{id, name, price, priceRange, unitType, quantityInStock, startDate + " + " + shelfLife, discountStatus, toCharity};
		}
		Object[] columnTitle = {"Product ID" , "Product Name" , "Price", "Price Range", "Unit", "Quantity", "Expiry Date", "Discount", "To Charity"};  
		productTable = new JTable(new DefaultTableModel(prodData, columnTitle));
		productTable.setRowSelectionAllowed(true);
		productTable.setRowSorter(null);
		productTable.getTableHeader().setFont(new Font("Arial", 1, 13));
		
		productScroll = new JScrollPane(productTable);
		productScroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.21), (int)(initWidth * 0.90), (int)(initHeight * 0.60));
		panel.add(productScroll);
		
		JButton insert = new JButton("Insert");
		insert.setFont(new Font("Arial", 0, 16));
		insert.setBounds((int)(initWidth * 0.44), (int)(initHeight * 0.85), (int)(initWidth * 0.12), (int)(initHeight * 0.06));
		panel.add(insert);
		
		productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				String id = productTable.getValueAt(productTable.getSelectedRow(), 0).toString();
				for (String product : productInfo) {
					if (product.split(",")[0].equals(id)) {
						manageProductUI(product, pFrame);
						pFrame.dispose();
						break;
					}
				}
				
			}
	    });
		
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manageProductUI("", pFrame);
				
			}});
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in = input.getText().toLowerCase();
				String res = "";
				if (!in.equals("") && !in.equals("product id/name")) {
					for (String p : productInfo) {
						if ((p.split(",")[0] + p.split(",")[1]).contains(in))
							res = res + p;
					}
					if (!res.equals("")) {
						Object[][] tableDataNew = new Object[1][]; 
						String[] productTemp = res.split(",");
						String id = productTemp[0];
						String name = productTemp[1];
						String price = productTemp[2];
						String priceRange = productTemp[3];
						String quantityInStock = productTemp[4];
						String unitType = productTemp[5];
						String startDate = productTemp[6];
						String shelfLife = productTemp[7];
						String discountStatus = productTemp[8];
						String toCharity = productTemp[9];
						prodData[1] = new Object[]{id, name, price, priceRange, unitType, quantityInStock, startDate + " + " + shelfLife, discountStatus, toCharity};
					
						TableModel dataModel = new DefaultTableModel(tableDataNew, columnTitle);
						productTable.setModel(dataModel);
					}
				}
			}});
		
		pFrame.add(panel);
		pFrame.setSize(initWidth, initHeight);
		pFrame.setVisible(true);
		pFrame.setResizable(false);
		pFrame.setLocationRelativeTo(null);
		pFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return isSuccess;
	}
	
	public boolean viewTransactionsUI() {
		boolean isSuccess = false;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.4);
		int initHeight = (int) (screenSize.height * 0.5);
	
		JFrame pFrame = new JFrame();
		pFrame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel label = new JLabel();
		label.setText("View Transactions");
		label.setFont(new Font("Arial", 1, 16));
		label.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.02), (int)(initWidth * 0.60), (int)(initHeight * 0.08));
		panel.add(label);
		
		JButton go_back_t = new JButton("Go Back");
		go_back_t.setBounds((int)(initWidth * 0.69), (int)(initHeight * 0.03), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(go_back_t);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.84), (int)(initHeight * 0.03), (int)(initWidth * 0.14), (int)(initHeight * 0.08));
		panel.add(log_out);
		
		log_out.addActionListener(new LogoutListener()); 
		
		go_back_t.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.show();;
				pFrame.dispose();	
			}});
		
		JTable transTable = null;
		String[] transInfo = new UserController().viewTransaction(StaticValue.OWNER_ID);
		String[] userInfo = new UserController().viewAccounts(StaticValue.OWNER_ID);
		Object[][] tableData = new Object[transInfo.length][]; 
		for (int i = 0; i < transInfo.length; i++) {
			String[] transTemp = transInfo[i].split(",");
			String userId = transTemp[1];
			String username = "";
			for (String user : userInfo) {
				if (user.split(",")[0].equals(userId)) {
					username = user.split(",")[1];
				}
			}
			String date = transTemp[2];
			boolean status = Boolean.parseBoolean(transTemp[3]);
			String status_str = "";
			if (status)
				status_str = "Pending";
			else
				status_str = "Completed";
			String price = transTemp[4];
			tableData[i] = new Object[]{username, date, status_str, price};
		}
		Object[] columnTitle = {"Customer Name", "Date", "Status", "Total Price"};  
		transTable = new JTable(tableData, columnTitle);
		transTable.getTableHeader().setFont(new Font("Arial", 1, 13));
		
		JScrollPane tranScroll = new JScrollPane(transTable);
		tranScroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.15), (int)(initWidth * 0.90), (int)(initHeight * 0.75));
		panel.add(tranScroll);
		
		pFrame.add(panel);
		pFrame.setSize(initWidth, initHeight);
		pFrame.setVisible(true);
		pFrame.setResizable(false);
		pFrame.setLocationRelativeTo(null);
		pFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		return isSuccess;	
	}
	
	public Object[][] initUserData(String username) {
		UserController uc = new UserController();
		boolean isSuccess = new OwnerController().deleteAccount(StaticValue.OWNER_ID, username);
		if (isSuccess) {
			String[] users = uc.viewAccounts(StaticValue.OWNER_ID);
			Object[][] tableData = new Object[users.length-1][]; 
			for (int i = 1; i < users.length; i++) {
				String userID = users[i].split(",")[0];
				String userName = users[i].split(",")[1];
				if (username.equals("") || username.equals("user name"))
					tableData[i-1] = new Object[]{userID, userName};
				else{
					if (username.equals(userName.toLowerCase())) {
						tableData[0] = new Object[]{userID, userName};
						break;
					}
				}
			} 
			return tableData;
		}
		else {
			JOptionPane.showMessageDialog(null, "Fail!");
		}
		return null;
	}
	
	class LogoutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			parent.show();
			frame.dispose();	
		}
		
	}

}
