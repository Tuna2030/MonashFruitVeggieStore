import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CustomerInterface {

	private JFrame frame;
	private JLabel main_title;
	private JLabel label;
	private JButton register;
	private JButton home;
	private JButton log_in;
	private JButton log_out;
	private JButton account;
	private JButton search;
	private JTextField search_area;
	private JComboBox price_range;
	private JTable pro_table;
	private JScrollPane pro_scroll;
	private JButton cart;
	private JButton purchase;
	private JButton trans;
	// GUI for transUI
	private JTable transTable;
	private JScrollPane trans_scroll;
	// GUI for cartUI
	private JTable cartTable;
	private JScrollPane cart_scroll;
	// GUI for accountUI
	private JLabel name_txt;
	private JTextField name_in;
	private JLabel name_s;
	private JLabel passwd_txt;
	private JPasswordField passwd_in;
	private JLabel phone_txt;
	private JTextField phone_in;
	private JLabel email_txt;
	private JTextField email_in;
	private JLabel address_txt;
	private JTextField address_in;
	private JLabel card_txt;
	private JTextField card_in;
	private JLabel cvv_txt;
	private JTextField cvv_in;
	private JLabel expiry_txt;
	private JComboBox expiry_m;
	private JLabel expiry_slash;
	private JComboBox expiry_y;
	private JButton update;
	private JButton regist;
	private JButton res_B;
	private JButton cancel;
	// purchase UI
	private JLabel username;
	private JTextField name_input;
	private JLabel phoneNo;
	private JTextField phoneNo_input;
	private JLabel email_add;
	private JTextField email_input;
	private JLabel addr;
	private JTextField addr_input;
	private JLabel cardNo;
	private JTextField cardNo_input;
	private JLabel cvvNo;
	private JTextField cvvNo_input;
	private JLabel expiry_date;
	private JComboBox month_input;
	private JLabel slash;
	private JComboBox year_input;
	private JLabel price;
	private JButton confirm;
	private JButton cancel_p;
	
	private int userID;
	private Object[] years;
	private DecimalFormat df;
	
	
	public CustomerInterface() {
		userID = StaticValue.FAIL_ID;
		df = new DecimalFormat("#.00");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int initWidth = (int) (screenSize.width * 0.8);
		int initHeight = (int) (screenSize.height * 0.8);
	
		frame = new JFrame();
		frame.setTitle("Monash Vege and Fruit Store");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		main_title = new JLabel("Monash Vege and Fruit Store");
		main_title.setFont(new Font("Arial", 1, 22));
		main_title.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.00), (int)(initWidth * 0.40), (int)(initHeight * 0.10));
		panel.add(main_title);
		
		
		account = new JButton("Account");
		account.setBounds((int)(initWidth * 0.80), (int)(initHeight * 0.02), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		account.setVisible(false);
		panel.add(account);
		
		register = new JButton("Register");
		register.setBounds((int)(initWidth * 0.80), (int)(initHeight * 0.02), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		panel.add(register);
		
		home = new JButton("Home");
		home.setBounds((int)(initWidth * 0.80), (int)(initHeight * 0.02), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		home.setVisible(false);
		panel.add(home);
		
		log_in = new JButton("Log in");
		log_in.setBounds((int)(initWidth * 0.89), (int)(initHeight * 0.02), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		panel.add(log_in);
		
		log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.89), (int)(initHeight * 0.02), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		log_out.setVisible(false);
		panel.add(log_out);
		
		label = new JLabel();
		label.setFont(new Font("Arial", 1, 17));
		label.setBounds((int)(initWidth * 0.70), (int)(initHeight * 0.02), (int)(initWidth * 0.10), (int)(initHeight * 0.04));
		label.setVisible(false);
		panel.add(label);
		
		search_area = new JTextField("Product Name");
		search_area.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.13), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		panel.add(search_area);
		
		search = new JButton("Search");
		search.setBounds((int)(initWidth * 0.26), (int)(initHeight * 0.13), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		panel.add(search);
		
		price_range = new JComboBox(StaticValue.PRICE_RANGE);
		price_range.setPreferredSize(new Dimension((int)(initWidth * 0.15), (int)(initHeight * 0.07)));
		price_range.setBounds((int)(initWidth * 0.37), (int)(initHeight * 0.13), (int)(initWidth * 0.15), (int)(initHeight * 0.05));
		price_range.setBackground(Color.WHITE);
		panel.add(price_range);
		
		String[] productInfo = new UserController().searchProduct("").split(";");
		Object[][] tableData = null;
		if (productInfo.length == 0)
			tableData = new Object[1][];
		else
			tableData = new Object[productInfo.length][]; 
		for (int i = 0; i < productInfo.length; i++) {
			String[] productTemp = productInfo[i].split(",");
			String id = productTemp[0];
			String name = productTemp[1];
			String price = productTemp[2];
			String quantityInStock = productTemp[4];
			String unitType = productTemp[5];
			String startDate = productTemp[6];
			String shelfLife = productTemp[7];
			String discountStatus = productTemp[8].equals("false") ? "No" : "Yes";
			tableData[i] = new Object[]{id, name, price, unitType, quantityInStock, startDate + " + " + shelfLife + "d", discountStatus};
		}
		Object[] columnTitle = {"Product ID", "Product Name" , "Price", "Unit", "Quantity In Stock", "Expiry Date", "Discount"};  
		pro_table = new JTable(new DefaultTableModel(tableData, columnTitle));
		pro_table.setRowHeight((int)(initHeight * 0.07));
		pro_table.setFont(new Font("Arial", 1, 13));
		pro_table.setRowSelectionAllowed(true);
		pro_table.setRowSorter(null);
		pro_table.setAutoscrolls(true);
		pro_table.getTableHeader().setFont(new Font("Arial", 1, 14));
		
		pro_scroll = new JScrollPane(pro_table);
		pro_scroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.22), (int)(initWidth * 0.90), (int)(initHeight * 0.63));
		panel.add(pro_scroll);
		
		cart = new JButton("My Cart");
		cart.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.87), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		cart.setVisible(false);
		panel.add(cart);
		
		purchase = new JButton("Purchase");
		purchase.setBounds((int)(initWidth * 0.45), (int)(initHeight * 0.87), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		purchase.setVisible(false);
		panel.add(purchase);
		
		trans = new JButton("My Transaction");
		trans.setBounds((int)(initWidth * 0.57), (int)(initHeight * 0.87), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		trans.setVisible(false);
		panel.add(trans);
		
		// transaction UI
		Object[][] transData = new Object[1][]; 
		Object[] transTitle = {"No.", "Date", "Status", "Total Price"};   
		transTable = new JTable(transData, transTitle);
		transTable.setRowHeight((int)(initHeight * 0.08));
		transTable.setFont(new Font("Arial", 1, 16));
		transTable.getTableHeader().setFont(new Font("Arial", 1, 14));
		
		trans_scroll = new JScrollPane(transTable);
		trans_scroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.22), (int)(initWidth * 0.90), (int)(initHeight * 0.63));
		trans_scroll.setVisible(false);
		panel.add(trans_scroll);
		
		// cart UI-------------------------------------------------------------------------------------------------------------------------------------------------------
		Object[][] cartData = new Object[1][]; 
		Object[] cartTitle = {"Product ID", "Product Name", "Price", "Sale Method", "Quantity", "Discount"};   
		cartTable = new JTable(new DefaultTableModel(cartData, cartTitle));
		cartTable.getTableHeader().setFont(new Font("Arial", 1, 14));
		
		cart_scroll = new JScrollPane(cartTable);
		cart_scroll.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.22), (int)(initWidth * 0.90), (int)(initHeight * 0.63));
		cart_scroll.setVisible(false);
		panel.add(cart_scroll);
		
		// account UI
		name_txt = new JLabel("Username");
		name_txt.setFont(new Font("Arial", 1, 17));
		name_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.22), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		name_txt.setVisible(false);
		panel.add(name_txt);
		
		name_in = new JTextField(30);
		name_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.22), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		name_in.setVisible(false);
		panel.add(name_in);
		
		name_s = new JLabel();
		name_s.setFont(new Font("Arial", 0, 17));
		name_s.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.22), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		name_s.setVisible(false);
		panel.add(name_s);
		
		passwd_txt = new JLabel("Password");
		passwd_txt.setFont(new Font("Arial", 1, 17));
		passwd_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.28), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		passwd_txt.setVisible(false);
		panel.add(passwd_txt);
		
		passwd_in = new JPasswordField(30);
		passwd_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.28), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		passwd_in.setVisible(false);
		panel.add(passwd_in);
		
		phone_txt = new JLabel("Phone No.");
		phone_txt.setFont(new Font("Arial", 1, 17));
		phone_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.34), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		phone_txt.setVisible(false);
		panel.add(phone_txt);
		
		phone_in = new JTextField();
		phone_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.34), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		phone_in.setVisible(false);
		panel.add(phone_in);
		
		email_txt = new JLabel("Email");
		email_txt.setFont(new Font("Arial", 1, 17));
		email_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.40), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		email_txt.setVisible(false);
		panel.add(email_txt);
		
		email_in = new JTextField();
		email_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.40), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		email_in.setVisible(false);
		panel.add(email_in);
		
		address_txt = new JLabel("Address");
		address_txt.setFont(new Font("Arial", 1, 17));
		address_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.46), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		address_txt.setVisible(false);
		panel.add(address_txt);
		
		address_in = new JTextField();
		address_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.46), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		address_in.setVisible(false);
		panel.add(address_in);
		
		card_txt = new JLabel("Bank Card No.");
		card_txt.setFont(new Font("Arial", 1, 17));
		card_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.52), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		card_txt.setVisible(false);
		panel.add(card_txt);
		
		card_in = new JTextField();
		card_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.52), (int)(initWidth * 0.23), (int)(initHeight * 0.05));
		card_in.setVisible(false);
		panel.add(card_in);
		
		cvv_txt = new JLabel("CVV");
		cvv_txt.setFont(new Font("Arial", 1, 17));
		cvv_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.58), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		cvv_txt.setVisible(false);
		panel.add(cvv_txt);
		
		cvv_in = new JTextField();
		cvv_in.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.58), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		cvv_in.setVisible(false);
		panel.add(cvv_in);
		
		expiry_txt = new JLabel("Expiry Date");
		expiry_txt.setFont(new Font("Arial", 1, 17));
		expiry_txt.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.64), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		expiry_txt.setVisible(false);
		panel.add(expiry_txt);
		
		expiry_m = new JComboBox(StaticValue.MONTHS);
		expiry_m.setFont(new Font("Arial", 1, 15));
		expiry_m.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.64), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		expiry_m.setVisible(false);
		panel.add(expiry_m);
		
		expiry_slash = new JLabel("/");
		expiry_slash.setFont(new Font("Arial", 1, 19));
		expiry_slash.setBounds((int)(initWidth * 0.57), (int)(initHeight * 0.64), (int)(initWidth * 0.01), (int)(initHeight * 0.05));
		expiry_slash.setVisible(false);
		panel.add(expiry_slash);
		
		years = new Object[11];
		years[0] = "Year";
		for (int i = 1; i < years.length; i++) {
			years[i] = (2017 + i) + "";
		}
		expiry_y = new JComboBox(years);
		expiry_y.setFont(new Font("Arial", 1, 15));
		expiry_y.setBounds((int)(initWidth * 0.58), (int)(initHeight * 0.64), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		expiry_y.setVisible(false);
		panel.add(expiry_y);
		
		update = new JButton("Update");
		update.setBounds((int)(initWidth * 0.34), (int)(initHeight * 0.78), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		update.setVisible(false);
		panel.add(update);
		
		regist = new JButton("Register");
		regist.setBounds((int)(initWidth * 0.34), (int)(initHeight * 0.78), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		regist.setVisible(false);
		panel.add(regist);
		
		res_B = new JButton("Reset");
		res_B.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.78), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		res_B.setVisible(false);
		panel.add(res_B);
		
		cancel = new JButton("Cancel");
		cancel.setBounds((int)(initWidth * 0.60), (int)(initHeight * 0.78), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		cancel.setVisible(false);
		panel.add(cancel);
		
		// purchase UI
		username = new JLabel("Username");
		username.setFont(new Font("Arial", 1, 16));
		username.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.22), (int)(initWidth * 0.12), (int)(initHeight * 0.05));
		username.setVisible(false);
		frame.add(username);
		
		name_input = new JTextField(30);
		name_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.22), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		name_input.setVisible(false);
		frame.add(name_input);
		
		phoneNo = new JLabel("Phone No.");
		phoneNo.setFont(new Font("Arial", 1, 16));
		phoneNo.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.29), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		phoneNo.setVisible(false);
		frame.add(phoneNo);
		
		phoneNo_input = new JTextField(10);
		phoneNo_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.29), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		phoneNo_input.setVisible(false);
		frame.add(phoneNo_input);
		
		email_add = new JLabel("Email");
		email_add.setFont(new Font("Arial", 1, 16));
		email_add.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.36), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		email_add.setVisible(false);
		frame.add(email_add);
		
		email_input = new JTextField();
		email_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.36), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		email_input.setVisible(false);
		frame.add(email_input);
		
		addr = new JLabel("Address");
		addr.setFont(new Font("Arial", 1, 16));
		addr.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.43), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		addr.setVisible(false);
		frame.add(addr);
		
		addr_input = new JTextField();
		addr_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.43), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		addr_input.setVisible(false);
		frame.add(addr_input);
		
		cardNo = new JLabel("Card No.");
		cardNo.setFont(new Font("Arial", 1, 16));
		cardNo.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.50), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		cardNo.setVisible(false);
		frame.add(cardNo);
		
		cardNo_input = new JTextField();
		cardNo_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.50), (int)(initWidth * 0.20), (int)(initHeight * 0.05));
		cardNo_input.setVisible(false);
		frame.add(cardNo_input);
		
		cvvNo = new JLabel("Card CVV");
		cvvNo.setFont(new Font("Arial", 1, 16));
		cvvNo.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.57), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		cvvNo.setVisible(false);
		frame.add(cvvNo);
		
		cvvNo_input = new JTextField(3);
		cvvNo_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.57), (int)(initWidth * 0.05), (int)(initHeight * 0.05));
		cvvNo_input.setVisible(false);
		frame.add(cvvNo_input);
		
		expiry_date = new JLabel("Expiry Date");
		expiry_date.setFont(new Font("Arial", 1, 16));
		expiry_date.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.64), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		expiry_date.setVisible(false);
		frame.add(expiry_date);
		
		month_input = new JComboBox(StaticValue.MONTHS);
		month_input.setFont(new Font("Arial", 0, 19));
		month_input.setBackground(Color.WHITE);
		month_input.setBounds((int)(initWidth * 0.47), (int)(initHeight * 0.64), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		month_input.setVisible(false);
		frame.add(month_input);
		
		slash = new JLabel("/");
		slash.setFont(new Font("Arial", 1, 19));
		slash.setBounds((int)(initWidth * 0.55), (int)(initHeight * 0.64), (int)(initWidth * 0.01), (int)(initHeight * 0.05));
		slash.setVisible(false);
		frame.add(slash);
		
		year_input = new JComboBox(years);
		year_input.setFont(new Font("Arial", 0, 16));
		year_input.setBackground(Color.WHITE);
		year_input.setBounds((int)(initWidth * 0.56), (int)(initHeight * 0.64), (int)(initWidth * 0.10), (int)(initHeight * 0.05));
		year_input.setVisible(false);
		frame.add(year_input);
		
		price = new JLabel();
		price.setFont(new Font("Arial", 1, 16));
		price.setBounds((int)(initWidth * 0.33), (int)(initHeight * 0.73), (int)(initWidth * 0.30), (int)(initHeight * 0.06));
		price.setVisible(false);
		frame.add(price);
		
		confirm = new JButton("Confirm");
		confirm.setBounds((int)(initWidth * 0.38), (int)(initHeight * 0.84), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		confirm.setVisible(false);
		frame.add(confirm);
		
		cancel_p = new JButton("Cancel");
		cancel_p.setBounds((int)(initWidth * 0.53), (int)(initHeight * 0.84), (int)(initWidth * 0.08), (int)(initHeight * 0.05));
		cancel_p.setVisible(false);
		frame.add(cancel_p);
		
		// add action listener to the buttons
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = name_input.getText();
				String phoneNum = phoneNo_input.getText();
				String email = email_input.getText();
				String adr = addr_input.getText();
				String cardNum = cardNo_input.getText();
				String cvvNum = cvvNo_input.getText();
				String mon = month_input.getSelectedItem().toString();
				String y = year_input.getSelectedItem().toString();
				double totalprice = Double.parseDouble(price.getText().split("D")[1].substring(1));
				
				CustomerController controller = new CustomerController();
				
				int result = controller.validateAccount(name, phoneNum, email, adr, cardNum, cvvNum, mon, y);
				if (result == StaticValue.ACCOUNT_SUCCESS) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date_p = dateFormat.format(new Date());
					boolean isSuccess = controller.generateTransaction(userID, date_p, false, totalprice);
					isSuccess = isSuccess && controller.clearCart(userID);
					// update transaction table
					if (isSuccess) {
						String[] transInfo = new UserController().viewTransaction(userID);
						Object[][] tableData = new Object[transInfo.length][]; 
						for (int i = 0; i < transInfo.length; i++) {
							String[] transTemp = transInfo[i].split(",");
							String date = transTemp[2];
							boolean status = Boolean.parseBoolean(transTemp[3]);
							String status_str = "";
							if (status)
								status_str = "Pending";
							else
								status_str = "Completed";
							String price = transTemp[4];
							tableData[i] = new Object[]{i+1, date, status_str, price};
						}
						Object[] transTitle = {"No.", "Date", "Status", "Total Price"};  
						transTable.setModel(new DefaultTableModel(tableData, transTitle));
						transTable.setVisible(true);
						transactionUI();
						JOptionPane.showMessageDialog(null, "Successful !");
					}
					else {
						JOptionPane.showMessageDialog(null, "System Error Occurs !");
					}
				}
				else if (result == StaticValue.USERNAME_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Username !");
				}
				else if (result == StaticValue.PHONE_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Phone Number !");
				}
				else if (result == StaticValue.EMAIL_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Email Address !");
				}
				else if (result == StaticValue.ADDRESS_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Address !");
				}
				else if (result == StaticValue.BANKCARD_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card Number !");
				}
				else if (result == StaticValue.CVV_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card CVV Number !");
				}
				else if (result == StaticValue.EXPIRYDATE_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card Expiry Date !");
				}
			}}); 
		cancel_p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cartUI();
			}});
		
		// add action listener to the buttons
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountUI();
				update.setVisible(false);
				regist.setVisible(true);
				
			}});
		
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				homeUI();
				
			}});
		
		log_in.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				loginUI((int)(initWidth * 0.4), (int)(initHeight * 0.4));
				
			}});
		
		log_out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				userID = StaticValue.FAIL_ID;
				homeUI();
				log_out.setVisible(false);
				log_in.setVisible(true);
				label.setText("");
				label.setVisible(false);
			}});
		
		account.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountUI();
				String[] user = new CustomerController().readAccount(userID).split(",");
				if (user.length != 0) {
					name_s.setText(user[1]);
					passwd_in.setText(user[2]);
					phone_in.setText(user[3]);
					email_in.setText(user[4]);
					address_in.setText(user[5]);
					card_in.setText(user[6]);
					cvv_in.setText(user[7]);
					expiry_m.setSelectedItem(user[8]);
					expiry_y.setSelectedItem(user[9]);
				}
				else {
					JOptionPane.showMessageDialog(null, "System Error!");
				}
				
			}});
		
		pro_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (userID != StaticValue.FAIL_ID) {
						String id = pro_table.getValueAt(pro_table.getSelectedRow(), 0).toString();
						String name = pro_table.getValueAt(pro_table.getSelectedRow(), 1).toString();
						String price = pro_table.getValueAt(pro_table.getSelectedRow(), 2).toString();
						String unit = pro_table.getValueAt(pro_table.getSelectedRow(), 3).toString();
						String quan = pro_table.getValueAt(pro_table.getSelectedRow(), 4).toString();
						String discount = pro_table.getValueAt(pro_table.getSelectedRow(), 6).toString();
						String product = id + "," + name + "," + price + "," + unit + "," + quan + "," + discount;
						manageCartUI((int)(initWidth * 0.50), (int)(initHeight * 0.80), label.getText(), product, true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Log in !");
					}
				}
				
			}
	    });
		
		
		cartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {					
					String id = cartTable.getValueAt(cartTable.getSelectedRow(), 0).toString();
					String name = cartTable.getValueAt(cartTable.getSelectedRow(), 1).toString();
					String price = cartTable.getValueAt(cartTable.getSelectedRow(), 2).toString();
					String unit = cartTable.getValueAt(cartTable.getSelectedRow(), 3).toString();
					String quan = cartTable.getValueAt(cartTable.getSelectedRow(), 4).toString();
					String discount = cartTable.getValueAt(cartTable.getSelectedRow(), 5).toString();
					String item = id + "," + name + "," + price + "," + unit + "," + quan + "," + discount;

					manageCartUI((int)(initWidth * 0.50), (int)(initHeight * 0.80),label.getText(), item, false);
				}
				
			}
	    });
		
		search_area.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				search_area.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (search_area.getText().trim().equals(""))
					search_area.setText("Product Name");
			}
			
		});
		
		price_range.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int index = price_range.getSelectedIndex();
					String[] productInfo = new UserController().searchProduct("").split(";");
					String temp = "";
					if (index != 0) {
						for (String product : productInfo) {
							if (product.split(",")[3].split(":")[0].equals(index + ""))
								temp += (product + ";");
						}
						if (!temp.equals(""))
							productInfo = temp.split(";");
						else
							productInfo = null;
					}
					Object[][] tableDataNew = null;
					if (productInfo == null)
						tableDataNew = new Object[1][];
					else {
						tableDataNew = new Object[productInfo.length][]; 
						for (int i = 0; i < productInfo.length; i++) {
							String[] productTemp = productInfo[i].split(",");
							String id = productTemp[0];
							String name = productTemp[1];
							String price = productTemp[2];
							String quantityInStock = productTemp[4];
							String unitType = productTemp[5];
							String startDate = productTemp[6];
							String shelfLife = productTemp[7];
							String discountStatus = productTemp[8].equals("false") ? "No" : "Yes";
							tableDataNew[i] = new Object[]{id, name, price, unitType, quantityInStock, startDate + " + " + shelfLife, discountStatus};
						}
					}
					Object[] columnTitle = {"Product ID", "Product Name" , "Price", "Unit", "Quantity In Stock", "Expiry Date", "Discount"};  
					TableModel dataModel = new DefaultTableModel(tableDataNew, columnTitle);
					pro_table.setModel(dataModel);
				}
			}});
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String keyword = search_area.getText().toString().toLowerCase();
				if (!new UserController().searchProduct(keyword).trim().equals("")) {
					String[] proInfo = new UserController().searchProduct(keyword).split(";");
					Object[][] shelfData = null;
					if (proInfo.length == 0)
						shelfData = new Object[1][];
					else
						shelfData = new Object[proInfo.length][]; 
					for (int i = 0; i < proInfo.length; i++) {
						String[] productTemp = proInfo[i].split(",");
						String id = productTemp[0];
						String name = productTemp[1];
						String price = productTemp[2];
						String quantityInStock = productTemp[4];
						String unitType = productTemp[5];
						String startDate = productTemp[6];
						String shelfLife = productTemp[7];
						String discountStatus = productTemp[8].equals("false") ? "No" : "Yes";
						shelfData[i] = new Object[]{id, name, price, unitType, quantityInStock, startDate + " + " + shelfLife, discountStatus};
					}
					pro_table.setModel(new DefaultTableModel(shelfData, columnTitle));
				}
			}});
		
		cart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cartUI();
			}});
		
		purchase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerController controller = new CustomerController();
				String user = new CustomerController().readAccount(userID);
				double totalprice = controller.calculatePrice(userID);
				purchaseUI(initWidth, initHeight, user, totalprice);
			}});
		
		trans.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transactionUI();
				String[] transInfo = new UserController().viewTransaction(userID);
				Object[][] tableData = new Object[transInfo.length][]; 
				for (int i = 0; i < transInfo.length; i++) {
					String[] transTemp = transInfo[i].split(",");
					String date = transTemp[2];
					boolean status = Boolean.parseBoolean(transTemp[3]);
					String status_str = "";
					if (status)
						status_str = "Pending";
					else
						status_str = "Completed";
					String price = transTemp[4];
					tableData[i] = new Object[]{i+1, date, status_str, price};
				}
				Object[] transTitle = {"No.", "Date", "Status", "Total Price"};  
				transTable.setModel(new DefaultTableModel(tableData, transTitle));
				transTable.setVisible(true);
			}});
		
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = name_s.getText();
				String passwd = passwd_in.getText();
				String phone = phone_in.getText();
				String email = email_in.getText();
				String address = address_in.getText();
				String card = card_in.getText();
				String cvv = cvv_in.getText();
				String month = expiry_m.getSelectedItem().toString();
				String year = expiry_y.getSelectedItem().toString();
				int isSuccess = 0;
				isSuccess = new CustomerController().updateAccount(userID, username, passwd, phone, email, address, card, cvv, month, year)[1];
				if (isSuccess == 1) {
					label.setText("Hi, " + username);
					label.setVisible(true);
					homeUI();
					JOptionPane.showMessageDialog(null, "Successful !");
				}
				else {
					JOptionPane.showMessageDialog(null, "Fail !");
				}
			}}); 
		
		regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = name_in.getText();
				String passwd = passwd_in.getText();
				String phone = phone_in.getText();
				String email = email_in.getText();
				String address = address_in.getText();
				String card = card_in.getText();
				String cvv = cvv_in.getText();
				String month = expiry_m.getSelectedItem().toString();
				String year = expiry_y.getSelectedItem().toString();
				int status = StaticValue.SYSTEM_ERROR;
				int[] res = new CustomerController().register(userID, username, passwd, phone, email, address, card, cvv, month, year);
				status = res[1];
				if (status == StaticValue.ACCOUNT_SUCCESS) {
					label.setText("Hi, " + username);
					label.setVisible(true);
					userID = res[0];
					homeUI();
				}
				else if (status == StaticValue.USERNAME_ERROR) {
					JOptionPane.showMessageDialog(null, "Invalid Username!");
				}
				else if (status == StaticValue.PASSWORD_ERROR){
					JOptionPane.showMessageDialog(null, "Illegal Password!");
				}
				else if (status == StaticValue.PHONE_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Phone Number !");
				}
				else if (status == StaticValue.EMAIL_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Email Address !");
				}
				else if (status == StaticValue.ADDRESS_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Address !");
				}
				else if (status == StaticValue.BANKCARD_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card Number !");
				}
				else if (status == StaticValue.CVV_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card CVV Number !");
				}
				else if (status == StaticValue.EXPIRYDATE_ERROR){
					JOptionPane.showMessageDialog(null, "Invalid Bank Card Expiry Date !");
				}
				else if (status == StaticValue.SYSTEM_ERROR){
					JOptionPane.showMessageDialog(null, "System Error Occurs!");
				}
			}});
		
		res_B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (userID == StaticValue.FAIL_ID)
					name_in.setText("");
				passwd_in.setText("");
				phone_in.setText("");
				email_in.setText("");
				address_in.setText("");
				card_in.setText("");
				cvv_in.setText("");
				expiry_m.setSelectedIndex(0);
				expiry_y.setSelectedIndex(0);
			}}); 
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				homeUI();
			}}); 
		
		frame.add(panel);
		frame.setSize(initWidth, initHeight);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	
	public void loginUI(int loginW, int loginH) {
		// initialize the login window
		JFrame loginWindow = new JFrame();
		loginWindow.setSize(loginW, loginH);
		loginWindow.setVisible(true);
		loginWindow.setResizable(false);
		loginWindow.setLayout(null);
		loginWindow.setLocationRelativeTo(frame);
		loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Arial", 1, 16));
		username.setBounds((int)(loginW * 0.19), (int)(loginH * 0.15), (int)(loginW * 0.2), (int)(loginH * 0.12));
		loginWindow.add(username);
		
		JTextField name_input = new JTextField(30);
		name_input.setFont(new Font("Arial", 0, 15));
		name_input.setBounds((int)(loginW * 0.4), (int)(loginH * 0.15), (int)(loginW * 0.40), (int)(loginH * 0.12));
		loginWindow.add(name_input);
		
		JLabel passwd = new JLabel("Password");
		passwd.setFont(new Font("Arial", 1, 16));
		passwd.setBounds((int)(loginW * 0.19), (int)(loginH * 0.35), (int)(loginW * 0.2), (int)(loginH * 0.12));
		loginWindow.add(passwd);
		
		JPasswordField passwd_input = new JPasswordField(30);
		passwd_input.setBounds((int)(loginW * 0.4), (int)(loginH * 0.35), (int)(loginW * 0.40), (int)(loginH * 0.12));
		loginWindow.add(passwd_input);
		
		JButton confirm = new JButton("Confirm");
		confirm.setBounds((int)(loginW * 0.25), (int)(loginH * 0.65), (int)(loginW * 0.2), (int)(loginH * 0.13));
		loginWindow.add(confirm);
		
		JButton reset = new JButton("Reset");
		reset.setBounds((int)(loginW * 0.55), (int)(loginH * 0.65), (int)(loginW * 0.2), (int)(loginH * 0.13));
		loginWindow.add(reset);
		// add action listener to the buttons
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = name_input.getText();
				String passwd = passwd_input.getText();
				userID = new UserController().login(username, passwd);
				if (userID != StaticValue.FAIL_ID) {
					if (userID == StaticValue.OWNER_ID) {
						new OwnerInterface(frame);
						frame.dispose();
					}
					else {
						label.setText("Hi, " + username);
						label.setVisible(true);
						homeUI();
					}
					loginWindow.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
					passwd_input.setText("");
				}
			}}); 
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				name_input.setText("");
				passwd_input.setText("");
			}}); 
		
	}
	
	public void accountUI() {
		// hide initial UI
		register.setVisible(false);
		account.setVisible(false);
		search_area.setVisible(false);
		price_range.setVisible(false);
		search.setVisible(false);
		pro_scroll.setVisible(false);
		cart.setVisible(false);
		purchase.setVisible(false);
		trans.setVisible(false);
		trans_scroll.setVisible(false);
		cart_scroll.setVisible(false);
		// show account UI
		if (userID == StaticValue.FAIL_ID) {
			name_in.setVisible(true);
			name_s.setVisible(false);
		}
		else {
			name_in.setVisible(false);
			name_s.setVisible(true);
		}
		home.setVisible(true);
		name_txt.setVisible(true);
		passwd_txt.setVisible(true);
		passwd_in.setVisible(true);
		phone_txt.setVisible(true);
		phone_in.setVisible(true);
		email_txt.setVisible(true);
		email_in.setVisible(true);
		address_txt.setVisible(true);
		address_in.setVisible(true);
		card_txt.setVisible(true);
		card_in.setVisible(true);
		cvv_txt.setVisible(true);
		cvv_in.setVisible(true);
		expiry_txt.setVisible(true);
		expiry_m.setVisible(true);
		expiry_slash.setVisible(true);
		expiry_y.setVisible(true);
		update.setVisible(true);
		res_B.setVisible(true);
		cancel.setVisible(true);
		// hide purchase UI
		username.setVisible(false);
		name_input.setVisible(false);
		phoneNo.setVisible(false);
		phoneNo_input.setVisible(false);
		email_add.setVisible(false);
		email_input.setVisible(false);
		addr.setVisible(false);
		addr_input.setVisible(false);
		cardNo.setVisible(false);
		cardNo_input.setVisible(false);
		cvvNo.setVisible(false);
		cvvNo_input.setVisible(false);
		expiry_date.setVisible(false);
		month_input.setVisible(false);
		slash.setVisible(false);
		year_input.setVisible(false);
		price.setVisible(false);
		confirm.setVisible(false);
		cancel_p.setVisible(false);
	}
	
	public void homeUI() {
		// show initial UI
		if (userID == StaticValue.FAIL_ID) {
			account.setVisible(false);
			register.setVisible(true);
			log_in.setVisible(true);
			log_out.setVisible(false);
			cart.setVisible(false);
			purchase.setVisible(false);
			trans.setVisible(false);
			label.setText("");
			label.setVisible(false);
		}
		else {
			account.setVisible(true);
			register.setVisible(false);
			log_in.setVisible(false);
			log_out.setVisible(true);
			cart.setVisible(true);
			purchase.setVisible(true);
			trans.setVisible(true);
		}
		search_area.setVisible(true);
		price_range.setVisible(true);
		search.setVisible(true);
		pro_scroll.setVisible(true);
		// hide account UI
		home.setVisible(false);
		name_txt.setVisible(false);
		name_in.setVisible(false);
		name_s.setVisible(false);
		passwd_txt.setVisible(false);
		passwd_in.setVisible(false);
		phone_txt.setVisible(false);
		phone_in.setVisible(false);
		email_txt.setVisible(false);
		email_in.setVisible(false);
		address_txt.setVisible(false);
		address_in.setVisible(false);
		card_txt.setVisible(false);
		card_in.setVisible(false);
		cvv_txt.setVisible(false);
		cvv_in.setVisible(false);
		expiry_txt.setVisible(false);
		expiry_m.setVisible(false);
		expiry_slash.setVisible(false);
		expiry_y.setVisible(false);
		update.setVisible(false);
		regist.setVisible(false);
		res_B.setVisible(false);
		cancel.setVisible(false);
		trans_scroll.setVisible(false);
		cart_scroll.setVisible(false);
		// hide purchase UI
		username.setVisible(false);
		name_input.setVisible(false);
		phoneNo.setVisible(false);
		phoneNo_input.setVisible(false);
		email_add.setVisible(false);
		email_input.setVisible(false);
		addr.setVisible(false);
		addr_input.setVisible(false);
		cardNo.setVisible(false);
		cardNo_input.setVisible(false);
		cvvNo.setVisible(false);
		cvvNo_input.setVisible(false);
		expiry_date.setVisible(false);
		month_input.setVisible(false);
		slash.setVisible(false);
		year_input.setVisible(false);
		price.setVisible(false);
		confirm.setVisible(false);
		cancel_p.setVisible(false);
		//determine whether purchase button is enable or not
		if (new CustomerController().readCart(userID).size() != 0)
			purchase.setEnabled(true);
		else
			purchase.setEnabled(false);
	}
	
	public void cartUI() {
		updateCartTable();
		// show cart UI
		if (userID == StaticValue.FAIL_ID) {
			account.setVisible(false);
			register.setVisible(true);
			log_in.setVisible(true);
			log_out.setVisible(false);
			cart.setVisible(false);
			purchase.setVisible(false);
			trans.setVisible(false);
		}
		else {
			account.setVisible(false);
			register.setVisible(false);
			log_in.setVisible(false);
			log_out.setVisible(true);
			cart.setVisible(false);
			cart_scroll.setVisible(true);
			purchase.setVisible(true);
			trans.setVisible(false);
		}
		// hide initial UI
		search_area.setVisible(false);
		price_range.setVisible(false);
		search.setVisible(false);
		pro_scroll.setVisible(false);
		home.setVisible(true);
		name_txt.setVisible(false);
		name_in.setVisible(false);
		name_s.setVisible(false);
		passwd_txt.setVisible(false);
		passwd_in.setVisible(false);
		phone_txt.setVisible(false);
		phone_in.setVisible(false);
		email_txt.setVisible(false);
		email_in.setVisible(false);
		address_txt.setVisible(false);
		address_in.setVisible(false);
		card_txt.setVisible(false);
		card_in.setVisible(false);
		cvv_txt.setVisible(false);
		cvv_in.setVisible(false);
		expiry_txt.setVisible(false);
		expiry_m.setVisible(false);
		expiry_slash.setVisible(false);
		expiry_y.setVisible(false);
		update.setVisible(false);
		regist.setVisible(false);
		res_B.setVisible(false);
		cancel.setVisible(false);
		trans_scroll.setVisible(false);
		// hide purchase UI
		username.setVisible(false);
		name_input.setVisible(false);
		phoneNo.setVisible(false);
		phoneNo_input.setVisible(false);
		email_add.setVisible(false);
		email_input.setVisible(false);
		addr.setVisible(false);
		addr_input.setVisible(false);
		cardNo.setVisible(false);
		cardNo_input.setVisible(false);
		cvvNo.setVisible(false);
		cvvNo_input.setVisible(false);
		expiry_date.setVisible(false);
		month_input.setVisible(false);
		slash.setVisible(false);
		year_input.setVisible(false);
		price.setVisible(false);
		confirm.setVisible(false);
		cancel_p.setVisible(false);
		//determine whether purchase button is enable or not
		if (new CustomerController().readCart(userID).size() != 0)
			purchase.setEnabled(true);
		else
			purchase.setEnabled(false);
	}
	
	public void purchaseUI(int initWidth, int initHeight, String userInfo, double totalprice) {
		// hide initial UI
		register.setVisible(false);
		log_in.setVisible(false);
		log_out.setVisible(true);
		account.setVisible(false);
		search_area.setVisible(false);
		price_range.setVisible(false);
		search.setVisible(false);
		pro_scroll.setVisible(false);
		cart.setVisible(false);
		purchase.setVisible(false);
		trans.setVisible(false);
		cart_scroll.setVisible(false);
		// hide transaction UI
		home.setVisible(true);
		trans_scroll.setVisible(false);
		// hide account UI
		name_txt.setVisible(false);
		name_in.setVisible(false);
		name_s.setVisible(false);
		passwd_txt.setVisible(false);
		passwd_in.setVisible(false);
		phone_txt.setVisible(false);
		phone_in.setVisible(false);
		email_txt.setVisible(false);
		email_in.setVisible(false);
		address_txt.setVisible(false);
		address_in.setVisible(false);
		card_txt.setVisible(false);
		card_in.setVisible(false);
		cvv_txt.setVisible(false);
		cvv_in.setVisible(false);
		expiry_txt.setVisible(false);
		expiry_m.setVisible(false);
		expiry_slash.setVisible(false);
		expiry_y.setVisible(false);
		update.setVisible(false);
		res_B.setVisible(false);
		cancel.setVisible(false);
		// show purchase UI
		username.setVisible(true);
		name_input.setVisible(true);
		phoneNo.setVisible(true);
		phoneNo_input.setVisible(true);
		email_add.setVisible(true);
		email_input.setVisible(true);
		addr.setVisible(true);
		addr_input.setVisible(true);
		cardNo.setVisible(true);
		cardNo_input.setVisible(true);
		cvvNo.setVisible(true);
		cvvNo_input.setVisible(true);
		expiry_date.setVisible(true);
		month_input.setVisible(true);
		slash.setVisible(true);
		year_input.setVisible(true);
		price.setVisible(true);
		confirm.setVisible(true);
		cancel_p.setVisible(true);
		// initial information
		price.setText("Total Price :            AUD$  " + totalprice);
		String[] user = userInfo.split(",");
		if (user.length != 0) {
			name_input.setText(user[1]);
			phoneNo_input.setText(user[3]);
			email_input.setText(user[4]);
			addr_input.setText(user[5]);
			cardNo_input.setText(user[6]);
			cvvNo_input.setText(user[7]);
			month_input.setSelectedItem(user[8]);
			year_input.setSelectedItem(user[9]);
		}
		else {
			JOptionPane.showMessageDialog(null, "System Error!");
		}
		
		
	}
	
	public void transactionUI() {
		// hide initial UI
		register.setVisible(false);
		log_in.setVisible(false);
		log_out.setVisible(true);
		account.setVisible(false);
		search_area.setVisible(false);
		price_range.setVisible(false);
		search.setVisible(false);
		pro_scroll.setVisible(false);
		cart.setVisible(false);
		purchase.setVisible(false);
		trans.setVisible(false);
		cart_scroll.setVisible(false);
		// show transaction UI
		home.setVisible(true);
		trans_scroll.setVisible(true);
		// hide account UI
		name_txt.setVisible(false);
		name_in.setVisible(false);
		name_s.setVisible(false);
		passwd_txt.setVisible(false);
		passwd_in.setVisible(false);
		phone_txt.setVisible(false);
		phone_in.setVisible(false);
		email_txt.setVisible(false);
		email_in.setVisible(false);
		address_txt.setVisible(false);
		address_in.setVisible(false);
		card_txt.setVisible(false);
		card_in.setVisible(false);
		cvv_txt.setVisible(false);
		cvv_in.setVisible(false);
		expiry_txt.setVisible(false);
		expiry_m.setVisible(false);
		expiry_slash.setVisible(false);
		expiry_y.setVisible(false);
		update.setVisible(false);
		res_B.setVisible(false);
		cancel.setVisible(false);
		// hide purchase UI
		username.setVisible(false);
		name_input.setVisible(false);
		phoneNo.setVisible(false);
		phoneNo_input.setVisible(false);
		email_add.setVisible(false);
		email_input.setVisible(false);
		addr.setVisible(false);
		addr_input.setVisible(false);
		cardNo.setVisible(false);
		cardNo_input.setVisible(false);
		cvvNo.setVisible(false);
		cvvNo_input.setVisible(false);
		expiry_date.setVisible(false);
		month_input.setVisible(false);
		slash.setVisible(false);
		year_input.setVisible(false);
		price.setVisible(false);
		confirm.setVisible(false);
		cancel_p.setVisible(false);
	}
	
	public void manageCartUI(int initWidth, int initHeight, String title, String product, boolean isAdd) {
		JFrame jf = new JFrame();
		jf.setTitle("Monash Vege and Fruit Store");
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JLabel wtitle = new JLabel("Monash Vege and Fruit Store");
		wtitle.setFont(new Font("Arial", 1, 20));
		wtitle.setBounds((int)(initWidth * 0.05), (int)(initHeight * 0.1), (int)(initWidth * 0.50), (int)(initHeight * 0.10));
		p.add(wtitle);
		
		JButton home = new JButton("Home");
		home.setBounds((int)(initWidth * 0.72), (int)(initHeight * 0.02), (int)(initWidth * 0.13), (int)(initHeight * 0.06));
		p.add(home);
		
		JButton log_out = new JButton("Log out");
		log_out.setBounds((int)(initWidth * 0.86), (int)(initHeight * 0.02), (int)(initWidth * 0.13), (int)(initHeight * 0.06));
		p.add(log_out);
		
		JLabel pro_id_t = new JLabel("Product ID");
		pro_id_t.setFont(new Font("Arial", 1, 17));
		pro_id_t.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.20), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		p.add(pro_id_t);
		
		JLabel pro_id_c = new JLabel();
		pro_id_c.setFont(new Font("Arial", 0, 17));
		pro_id_c.setBounds((int)(initWidth * 0.45), (int)(initHeight * 0.20), (int)(initWidth * 0.23), (int)(initHeight * 0.06));
		p.add(pro_id_c);
		
		JLabel pro_name_t = new JLabel("Product Name");
		pro_name_t.setFont(new Font("Arial", 1, 17));
		pro_name_t.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.29), (int)(initWidth * 0.70), (int)(initHeight * 0.06));
		p.add(pro_name_t);
		
		JLabel pro_name_c = new JLabel();
		pro_name_c.setFont(new Font("Arial", 0, 17));
		pro_name_c.setBounds((int)(initWidth * 0.45), (int)(initHeight * 0.29), (int)(initWidth * 0.23), (int)(initHeight * 0.06));
		p.add(pro_name_c);
		
		JLabel price_t = new JLabel("Price");
		price_t.setFont(new Font("Arial", 1, 17));
		price_t.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.38), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		p.add(price_t);
		
		JLabel price_c = new JLabel();
		price_c.setFont(new Font("Arial", 0, 17));
		price_c.setBounds((int)(initWidth * 0.45), (int)(initHeight * 0.38), (int)(initWidth * 0.23), (int)(initHeight * 0.06));
		p.add(price_c);
		
		JLabel quan_t = new JLabel("Quantity");
		quan_t.setFont(new Font("Arial", 1, 17));
		quan_t.setBounds((int)(initWidth * 0.20), (int)(initHeight * 0.47), (int)(initWidth * 0.20), (int)(initHeight * 0.06));
		p.add(quan_t);
		
		JTextField quan_c = new JTextField();
		quan_c.setFont(new Font("Arial", 0, 17));
		quan_c.setBounds((int)(initWidth * 0.45), (int)(initHeight * 0.47), (int)(initWidth * 0.23), (int)(initHeight * 0.06));
		p.add(quan_c);
		
		JButton add = new JButton("Add To Cart");
		add.setBounds((int)(initWidth * 0.40), (int)(initHeight * 0.70), (int)(initWidth * 0.20), (int)(initHeight * 0.07));
		p.add(add);
		
		JButton mUpdate = new JButton("Update");
		mUpdate.setBounds((int)(initWidth * 0.30), (int)(initHeight * 0.70), (int)(initWidth * 0.15), (int)(initHeight * 0.07));
		p.add(mUpdate);
		
		JButton mDelete = new JButton("Delete");
		mDelete.setBounds((int)(initWidth * 0.55), (int)(initHeight * 0.70), (int)(initWidth * 0.15), (int)(initHeight * 0.07));
		p.add(mDelete);
		
		if (isAdd) {
			add.setVisible(true);
			mDelete.setVisible(false);
			mUpdate.setVisible(false);
		}
		else {
			add.setVisible(false);
			mUpdate.setVisible(true);
			mDelete.setVisible(true);
		}
		
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				homeUI();
				jf.dispose();
				
			}});
		
		log_out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				userID = StaticValue.FAIL_ID;
				homeUI();
				jf.dispose();
				
			}});
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(pro_id_c.getText());
				int quan = Integer.parseInt(quan_c.getText());
				int isSuccess = new CustomerController().addToCart(userID, id, quan);
				if (isSuccess == StaticValue.ADD_SUCCESS) {
					cartUI();
					JOptionPane.showMessageDialog(null, "Successful !");
					jf.dispose();
				}
				else if (isSuccess == StaticValue.OUT_OF_STOCK) {
					JOptionPane.showMessageDialog(null, "Out of Stock !");
				}
				else {
					JOptionPane.showMessageDialog(null, "System Error !");
				}
				
			}});
		
		mUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(pro_id_c.getText());
				int quan = Integer.parseInt(quan_c.getText());
				boolean isSuccess = new CustomerController().updateCart(userID, id, quan);
				if (isSuccess) {
					cartUI();
					JOptionPane.showMessageDialog(null, "Successful !");
					jf.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "System Error !");
				}
				
			}});
		
		mDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(pro_id_c.getText());
				int quan = Integer.parseInt(quan_c.getText());
				boolean isSuccess = new CustomerController().deleteFromCart(userID, id, quan);
				if (isSuccess) {
					cartUI();
					JOptionPane.showMessageDialog(null, "Successful !");
					jf.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "System Error !");
				}
				
			}});
		
		jf.add(p);
		jf.setSize(initWidth, initHeight);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] pro = product.split(",");
		String id = pro[0];
		String name = pro[1];
		double discount = pro[5].equals("No") ? 1 : 0.6;
		String price = df.format(Double.parseDouble(pro[2]) * discount);
		String unit = pro[3];
		String quantity = pro[4];
		pro_id_c.setText(id);
		pro_name_c.setText(name);
		price_t.setText("Price/" + unit);
		price_c.setText(price);
		quan_c.setText(quantity);
	}
	
	public void updateCartTable() {
		String[] proInfo = new UserController().searchProduct("").split(";");
		HashMap<Integer, Integer> carts = new CustomerController().readCart(userID);
		String temp = "";
		if (carts.size() != 0) {
			for (Integer key : carts.keySet()) {
				int id = key.intValue();
				for (String product : proInfo) {
					if (id == Integer.parseInt(product.split(",")[0])) {
						temp = temp + product.split(",")[0] + "," + product.split(",")[1] + ",";
						if (product.split(",")[8].equals("false"))
							temp = temp + product.split(",")[2] + "," + product.split(",")[5] + ","+ carts.get(key).toString() + "," +product.split(",")[8] + ";";
						else
							temp = temp + df.format(Double.parseDouble(product.split(",")[2]) * 0.6) + ","  + product.split(",")[5] + "," + carts.get(key).toString() + "," + product.split(",")[8] + ";";
					}
				}
			}
		}
		Object[][] tableDataNew = null;
		if (temp.equals(""))
			tableDataNew = new Object[1][];
		else {
			tableDataNew = new Object[temp.split(";").length][]; 
			for (int i = 0; i < temp.split(";").length; i++) {
				String[] productTemp = temp.split(";")[i].split(",");
				String id = productTemp[0];
				String name = productTemp[1];
				String price = productTemp[2];
				String unit = productTemp[3];
				String quantity = productTemp[4];
				String discount = productTemp[5].equals("false") ? "No" : "Yes";
				tableDataNew[i] = new Object[]{id, name, price, unit, quantity, discount};
			}
		}
		Object[] columnTitle = {"Product ID", "Product Name" , "Price", "Sale Method", "Quantity", "Discount"};  
		TableModel dataModel = new DefaultTableModel(tableDataNew, columnTitle);
		cartTable.setModel(dataModel);
	}
	
	public static void main(String[] args) {
		new CustomerInterface();
	}
	
}
