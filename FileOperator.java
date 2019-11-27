
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class FileOperator {
	
	private String path;
	
	public FileOperator() {
		path = "./src/MVFSData/";
	}
	
	public FileOperator(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public String readUserFile(int userID)
	{
		String filename = StaticValue.USER_FILE;
		String userInf = "";
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					String user = parser.nextLine();
					if (userID == StaticValue.FAIL_ID || userID == StaticValue.OWNER_ID)
						userInf = userInf + user + ";";
					else {
						if (user.split(",")[0].equals(userID + ""))
							userInf = user.substring(0, user.length() - 1 - user.split(",")[user.split(",").length - 1].length()) + ",";
					}
					
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return userInf;
	}
	
	public String readPaymentFile(int userID) {
		String filename = StaticValue.PAYMENT_FILE;
		String payInf = "";
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					if (userID == StaticValue.FAIL_ID || userID == StaticValue.OWNER_ID)
						payInf = payInf + parser.nextLine() + ";";
					else {
						String pay = parser.nextLine();
						if (Integer.parseInt(pay.split(",")[0]) == userID) {
							payInf = pay + ",";
							break;
						}
					}
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return payInf;
	}
	
	public String readCardFile(int userID) {
		String filename = StaticValue.BANKCARD_FILE;
		String cardInf = "";
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					String card = parser.nextLine();
					if (userID == StaticValue.FAIL_ID || userID == StaticValue.OWNER_ID)
						cardInf = cardInf + card + ";";
					else {
						if (card.split(",")[0].equals(userID + "")) {
							cardInf = card;
							break;
						}
					}
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return cardInf;
	}
	
	public ArrayList<Cart> readCartFile(int userID) {
		String filename = StaticValue.CART_FILE;
		ArrayList<Cart> carts = new ArrayList<>();
		HashMap<Integer, Integer> cartMap = null;
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					String t = parser.nextLine();
					String[] cart = t.split(",");
					if (userID == StaticValue.FAIL_ID || userID == StaticValue.OWNER_ID) {
						cartMap = new HashMap<>();
						if (cart.length > 1) {
							for (int i = 1; i < cart.length; i++) {
								int key = Integer.parseInt(cart[i].split(":")[0]);
								int value = Integer.parseInt(cart[i].split(":")[1]);
								cartMap.put(key, value);
							}
						}
						Cart c = new Cart(Integer.parseInt(cart[0]), cartMap);
						carts.add(c);
					}
					else {
						if (Integer.parseInt(cart[0]) == userID) {
							cartMap = new HashMap<>();
							if (cart.length > 1) {
								for (int i = 1; i < cart.length; i++) {
									int key = Integer.parseInt(cart[i].split(":")[0]);
									int value = Integer.parseInt(cart[i].split(":")[1]);
									cartMap.put(key, value);
								}
							}
							Cart c = new Cart(userID, cartMap);
							carts.add(c);
							break;
						}
					}
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		} catch (NumberFormatException e) {
			System.out.println("Unexpected error occured...");
		}
		return carts;
	}
	
	public Shelf readProductFile(String keyword)
	{
		String filename = StaticValue.PRODUCT_FILE;
		Shelf shelf = new Shelf();
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					String[] product = parser.nextLine().split(",");
					if (product[1].toLowerCase().contains(keyword)) {
						int proID = Integer.parseInt(product[0]);
						String proName = product[1];
						double price = Double.parseDouble(product[2]);
						HashMap<Integer, String> priceRange = new HashMap<>();
						int key = Integer.parseInt(product[3].split(":")[0]);
						String value = product[3].split(":")[1];
						priceRange.put(key, value);
						int quantity = Integer.parseInt(product[4]);
						String unit = product[5];
						String date = product[6];
						int shelflife = Integer.parseInt(product[7]);
						boolean discountStatus = Boolean.parseBoolean(product[8]);
						boolean charity = Boolean.parseBoolean(product[9]);
						Product p = new Product(proID, proName, price, priceRange, quantity, unit, date, shelflife, discountStatus, charity);
						shelf.getProducts().add(p);
					}
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return shelf;
	}
	
	public String readTransFile(int userID)
	{
		String filename = StaticValue.TRANSACTION_FILE;
		String transInf = "";
		FileReader inputFile = null;
		try {
			inputFile = new FileReader(path + filename);
			try
			{
				Scanner parser = new Scanner(inputFile);
				while (parser.hasNextLine()) {
					String trans = parser.nextLine();
					if (userID == StaticValue.FAIL_ID || userID == StaticValue.OWNER_ID) {
						transInf = transInf + trans + ";";	
					}
					else {
						if (trans.split(",")[1].equals(userID + "")) {
							transInf = transInf + trans + ";";
						}
					}
				}
			}
			finally
			{
				inputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return transInf;
	}
	
	public boolean writeUserFile(int userID, String username)
	{
		boolean isSuccess = false;
		String filename = StaticValue.USER_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] userInfo = readUserFile(userID).split(";");
			
			if (userInfo.length != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String user : userInfo) {
						if (!user.split(",")[1].toLowerCase().equals(username))
							outputFile.println(user);
					}
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public HashMap<Integer, Boolean> updateUserFile(int userID, Customer customer, Payment payment)
	{
		String filename = StaticValue.USER_FILE;
		boolean isSuccess = false;
		HashMap<Integer, Boolean> res = new HashMap<>();  // userID, isSuccess
		res.put(userID, isSuccess);
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			boolean pre = updatePaymentFile(userID, payment);
			String[] userInfo = readUserFile(StaticValue.FAIL_ID).split(";");
			
			if (userInfo.length != 0 && pre) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String user : userInfo) {
						if (user.split(",")[0].equals(userID + "")) {
							String c = customer.getUserID() + "," + customer.getUserName() + "," + customer.getPassword() + "," + customer.getAuthority();
							outputFile.println(c);
						}
						else {
							outputFile.println(user);
						}
					}
					res.replace(userID, isSuccess, true);
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return res;
	}
	
	public boolean updatePaymentFile(int cusID, Payment payment) {
		boolean isSuccess = false;
		String filename = StaticValue.PAYMENT_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] paymentInfo = readPaymentFile(StaticValue.FAIL_ID).split(";");
			boolean pre = updateCardFile(cusID, payment.getCardInfo());
			
			if (paymentInfo.length != 0 && pre) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String payInfo : paymentInfo) {
						if (Integer.parseInt(payInfo.split(",")[0]) == cusID) {
							String p = cusID + "," + payment.getPhoneNo() + "," + payment.getEmail() + "," + payment.getAddress();
							outputFile.println(p);
						}
						else {
							outputFile.println(payInfo);
						}
					}
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean updateCardFile(int cusID, BankCard card) {
		boolean isSuccess = false;
		String filename = StaticValue.BANKCARD_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] cardInfo = readCardFile(StaticValue.FAIL_ID).split(";");
			
			if (cardInfo.length != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String c : cardInfo) {
						if (c.split(",")[0].equals(cusID + "")) {
							String month = card.getExpiryDate().split("/")[0];
							String year = card.getExpiryDate().split("/")[1];
							String temp = cusID + "," + card.getCardNo() + "," + card.getCvv() + "," + month + "," + year;
							outputFile.println(temp);
						}
						else {
							outputFile.println(c);
						}
					}
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean updateCartFile(Cart cart) {
		boolean isSuccess = false;
		String filename = StaticValue.CART_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			ArrayList<Cart> cartInfo = readCartFile(StaticValue.FAIL_ID);
			
			PrintWriter outputFile = new PrintWriter(path + filename);
			try
			{
				for (Cart ca : cartInfo) {
					String temp = ca.getCusID() + "";
					if (ca.getCusID() == cart.getCusID()) {
						if (cart.getCartMap().size() != 0) {
							for (Integer key : cart.getCartMap().keySet()) {
									temp = temp + "," + key.intValue() + ":" + cart.getCartMap().get(key).intValue();	
							}
						}
						else {
							temp = temp + ",";
						}
					}
					else {
						if (ca.getCartMap().size() != 0) {
							for (Integer key : ca.getCartMap().keySet()) {
									temp = temp + "," + key.intValue() + ":" + ca.getCartMap().get(key).intValue();	
							}
						}
						else {
							temp = temp + ",";
						}
					}
					outputFile.println(temp);
				}
				isSuccess = true;
			}
			finally
			{
				outputFile.close();
			}
//			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean updateProductFile(String product) {
		boolean isSuccess = false;
		String filename = StaticValue.PRODUCT_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			Shelf products = readProductFile("");
			
			if (products.getProducts().size() != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (Product p : products.getProducts()) {
						if (p.getProId() == Integer.parseInt(product.split(",")[0])) {
							outputFile.println(product);
						}
						else {
							String id = p.getProId() + "";
							String name = p.getProName();
							String price = p.getPrice() + "";
							String range = "";
							for (Integer key : p.getPriceRange().keySet()) {
								range = key.toString() + ":" + p.getPriceRange().get(key);
							}
							String quantity = p.getQuantityInStock() + "";
							String unit = p.getUnitType();
							String date = p.getStartDate();
							String shelflife = p.getShelfLife() + "";
							String status = p.isDiscountStatus() +"";
							String charity = p.isToCharity() + "";
							String temp = id + "," + name + "," + price + "," + range + "," + quantity + "," 
									+ unit + "," + date + "," + shelflife + "," + status + "," + charity;
							outputFile.println(temp);
						}
					}
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}

	public int[] insertUserFile(int userID, Customer customer, Payment payment)
	{
		String filename = StaticValue.USER_FILE;
		int isSuccess = 0;
		int[] res = new int[2];
		res[0] = userID;
		res[1] = isSuccess;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] userInfo = readUserFile(StaticValue.FAIL_ID).split(";");
			
			if (userInfo.length != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					int maxIndex = 0;
					for (String user : userInfo) {
						if (Integer.parseInt(user.split(",")[0]) >= maxIndex)
							maxIndex = Integer.parseInt(user.split(",")[0]);
						outputFile.println(user);
					}
					boolean pre = insertPaymentFile(maxIndex + 1, payment) && insertCartFile(maxIndex + 1);
					if (pre) {
						String c = (maxIndex + 1) + "," + customer.getUserName() + "," + customer.getPassword() + "," + customer.getAuthority();
						outputFile.println(c);
						res[0] = maxIndex + 1;
						res[1] = 1;
					}
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return res;
	}
	
	public boolean insertPaymentFile(int userID, Payment payment)
	{
		String filename = StaticValue.PAYMENT_FILE;
		boolean isSuccess = false;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			boolean pre = insertCardFile(userID, payment.getCardInfo());
			String[] payInfo = readPaymentFile(StaticValue.FAIL_ID).split(";");
			
			if (payInfo.length != 0 && pre) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String pay : payInfo)
						outputFile.println(pay);
					
					String p = userID + "," + payment.getPhoneNo() + "," + payment.getEmail() + "," + payment.getAddress();
					outputFile.println(p);	
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean insertCardFile(int userID, BankCard card)
	{
		String filename = StaticValue.PAYMENT_FILE;
		boolean isSuccess = false;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] cardInfo = readCardFile(StaticValue.FAIL_ID).split(";");
			
			if (cardInfo.length != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (String c : cardInfo)
						outputFile.println(c);
					
					String month = card.getExpiryDate().split("/")[0];
					String year = card.getExpiryDate().split("/")[1];
					String temp = userID + "," + card.getCardNo() + "," + card.getCvv() + "," + month + "," + year;
					outputFile.println(temp);	
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean insertCartFile(int userID)
	{
		String filename = StaticValue.CART_FILE;
		ArrayList<Cart> cartInfo = new ArrayList<>();
		boolean isSuccess = false;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			cartInfo = readCartFile(StaticValue.FAIL_ID);
			
			
			PrintWriter outputFile = new PrintWriter(path + filename);
			try
			{
				for (Cart cart : cartInfo) {
					if (cart.getCusID() != userID) {
						String c = cart.getCusID() + "";
						for (Integer key : cart.getCartMap().keySet()) {
							c = c + "," + key.intValue() + ":" + cart.getCartMap().get(key).toString();
						}
						outputFile.println(c);
					}
				}	
				outputFile.println(userID + ",");
				isSuccess = true;
			}
			finally
			{
				outputFile.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean insertProductFile(String product) {
		boolean isSuccess = false;
		String filename = StaticValue.PRODUCT_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			Shelf products = readProductFile("");
			
			if (products.getProducts().size() != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					int maxIndex = 0;
					for (Product p : products.getProducts()) {
						if (p.getProId() >= maxIndex) {
							maxIndex = p.getProId();
						}
						String id = p.getProId() + "";
						String name = p.getProName();
						String price = p.getPrice() + "";
						String priceRange = "";
						for (Integer key : p.getPriceRange().keySet()) {
							priceRange = priceRange + key.intValue() + ":" + p.getPriceRange().get(key);
							break;
						}
						String quantityInStock = p.getQuantityInStock() + "";
						String unitType = p.getUnitType();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String startDate = dateFormat.format(new Date());
						String shelfLife = p.getShelfLife() + "";
						String status = p.isDiscountStatus() + "";
						String toCharity = p.isToCharity() + "";
						String prod = id + "," + name + "," + price + "," + priceRange + "," + quantityInStock + "," 
								+ unitType + "," + startDate + "," + shelfLife + "," + status + "," + toCharity;
						outputFile.println(prod);
					}
					outputFile.println((maxIndex + 1) + "," + product);
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean insertTransFile(int userID, Transaction trans)
	{
		boolean isSuccess = false;
		String filename = StaticValue.TRANSACTION_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			String[] transInfo = readTransFile(StaticValue.FAIL_ID).split(";");
			
			PrintWriter outputFile = new PrintWriter(path + filename);
			try
			{
				int maxIndex = 0;
				for (String tran : transInfo) {
					if (!tran.equals("")) {
						outputFile.println(tran);
						if (Integer.parseInt(tran.split(",")[0]) >= maxIndex)
							maxIndex = Integer.parseInt(tran.split(",")[0]);
					}
				}
				String t = (maxIndex + 1) + "," + userID + "," + trans.getDate() + "," + trans.isStatus() + "," + trans.getTotalPrice();
				outputFile.println(t);
				isSuccess = true;
			}
			finally
			{
				outputFile.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}
	
	public boolean deleteProductFile(int proID) {
		boolean isSuccess = false;
		String filename = StaticValue.PRODUCT_FILE;
		try {
			File file = new File(path + filename);
			
			if (!file.exists())
				file.createNewFile();
			
			Shelf products = readProductFile("");
			
			if (products.getProducts().size() != 0) {
				PrintWriter outputFile = new PrintWriter(path + filename);
				try
				{
					for (Product p : products.getProducts()) {
						if (p.getProId() != proID) {
							String id = p.getProId() + "";
							String name = p.getProName();
							String price = p.getPrice() + "";
							String range = "";
							for (Integer key : p.getPriceRange().keySet()) {
								range = key.toString() + ":" + p.getPriceRange().get(key);
							}
							String quantity = p.getQuantityInStock() + "";
							String unit = p.getUnitType();
							String date = p.getStartDate();
							String shelflife = p.getShelfLife() + "";
							String status = p.isDiscountStatus() +"";
							String charity = p.isToCharity() + "";
							String temp = id + "," + name + "," + price + "," + range + "," + quantity + "," 
									+ unit + "," + date + "," + shelflife + "," + status + "," + charity;
							outputFile.println(temp);
						}
					}
					isSuccess = true;
				}
				finally
				{
					outputFile.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(filename + " not found...");
		} catch (IOException e) {
			System.out.println("Unexpected I/O error occured...");
		}
		return isSuccess;
	}

}
