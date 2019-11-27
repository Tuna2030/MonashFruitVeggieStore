import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class CustomerController {
	
	public CustomerController() {}
	
	public int[] register(int userID, String username, String passwd, String phone, String email, String address, String card, String cvv, String month, String year) {
		int result = validateRegisterAccount(username, passwd, phone, email, address, card, cvv, month, year);
		
		if (result == StaticValue.ACCOUNT_SUCCESS) {
			FileOperator operator = new FileOperator();
			String[] userInfo = operator.readUserFile(userID).split(";");
			for (String user : userInfo) {
				if (user.split(",")[1].toLowerCase().equals(username.toLowerCase()))
					return new int[]{userID, StaticValue.USERNAME_ERROR};
			}
			int[] res = updateAccount(userID, username, passwd, phone, email, address, card, cvv, month, year);
			result = res[1];
			if (result == StaticValue.SYSTEM_ERROR)
				return new int[]{userID, result};
			else
				return new int[] {res[0], StaticValue.ACCOUNT_SUCCESS};
		}
		else {
			return new int[]{userID, result};
		}
	}
	
	public boolean unRegister(int userID,String name) {
		boolean isSuccess = false;
		isSuccess = new FileOperator().writeUserFile(userID, name);
		return isSuccess;
	}
	
	public String readAccount(int userID) {
		FileOperator operator = new FileOperator();
		String userInfo = operator.readUserFile(userID);
		if (!userInfo.equals("")) {
			String payInfo = operator.readPaymentFile(userID);
			if (!payInfo.equals("")) {
				String cardInfo = operator.readCardFile(userID);
				if (!cardInfo.equals(""))
				    userInfo = userInfo + payInfo.substring(payInfo.split(",")[0].length() + 1) + cardInfo.substring(cardInfo.split(",")[0].length() + 1);
				return userInfo;
			}
		}
		return null;
	}
	
	public int[] updateAccount(int userID, String username, String passwd, String phone, String email, String address, String card, String cvv, String month, String year) {
		Customer customer = new Customer(userID, username, passwd);
		BankCard bankcard = new BankCard(card, Integer.parseInt(cvv), month + "/" + year);
		Payment payment = new Payment(phone, email, address, bankcard);
		if (userID == StaticValue.FAIL_ID) {
			return new FileOperator().insertUserFile(userID, customer, payment);
		}
		else {
			if (new FileOperator().updateUserFile(userID, customer, payment).get(userID).booleanValue()) {
				return new int[] {userID, StaticValue.ACCOUNT_SUCCESS};
			}
			return new int[] {userID, StaticValue.SYSTEM_ERROR};
		}
	}
	
	public HashMap<Integer, Integer> readCart(int userID) {
		HashMap<Integer, Integer> cartMap = new HashMap<>();
		ArrayList<Cart> carts = new FileOperator().readCartFile(userID);
		if (carts.size() != 0) {
			for (Cart cart : carts) {
				if (cart.getCusID() == userID)
					cartMap = cart.getCartMap();
			}
		}
		return cartMap;
	}
	
	public int addToCart(int userID, int proId,int quantity) {
		int isSuccess = 0;
		HashMap<Integer, Integer> cartMap = null;
		FileOperator operator = new FileOperator();
		try {
			Shelf shelf = operator.readProductFile("");
			ArrayList<Cart> carts = operator.readCartFile(userID);
			for (Cart cart : carts) {
				if (cart.getCusID() == userID) {
					cartMap = cart.getCartMap();
					break;
				}
			}
			for (Product product: shelf.getProducts()) {
				if (product.getProId() == proId) {
					if (product.getQuantityInStock() >= quantity) {
						product.setQuantityInStock(product.getQuantityInStock() - quantity);
						if (cartMap != null) {
							for (Integer key : cartMap.keySet()) {
								if (key.intValue() == proId) {
									cartMap.replace(proId, cartMap.get(proId).intValue() + quantity);
									break;
								}
							}
							cartMap.put(proId, quantity);
						}
						else {
							cartMap = new HashMap<>();
							cartMap.put(proId, quantity);
						}
						Cart c = new Cart(userID, cartMap);
						String pro = product.getProId() + "," + product.getProName() + "," + product.getPrice() + ","; 
						for (Integer key : product.getPriceRange().keySet()) {
							pro = pro + key.toString() + ":" + product.getPriceRange().get(key) + "," 
									+ product.getQuantityInStock() + "," + product.getUnitType() + ","
									+ product.getStartDate() + "," + product.getShelfLife() + "," 
									+ product.isDiscountStatus() + "," + product.isToCharity();
						}
						boolean pre = operator.updateCartFile(c) && operator.updateProductFile(pro);
						if (pre)
							isSuccess = StaticValue.ADD_SUCCESS;
						else
							isSuccess = StaticValue.ADD_ERROR;
					}
					else
						isSuccess = StaticValue.OUT_OF_STOCK;
					break;
				}
			}
		}
		catch (Exception e) {
			System.out.println("The product quantity is not enough.");
		}
		return isSuccess;
	}
	
    public boolean deleteFromCart(int userID, int proId, int quantity) {
    	boolean isSuccess = false;
    	HashMap<Integer, Integer> cartMap = null;
    	FileOperator operator = new FileOperator();
    	try {
    		ArrayList<Cart> carts = operator.readCartFile(userID);
			for (Cart cart : carts) {
				if (cart.getCusID() == userID) {
					cartMap = cart.getCartMap();
					break;
				}
			}
    		if (cartMap != null) {
    			cartMap.remove(proId);
    			Cart cart = new Cart(userID, cartMap);
    			isSuccess = operator.updateCartFile(cart);
    			if (isSuccess) {
    				Shelf shelf = operator.readProductFile("");
    				for (Product product: shelf.getProducts()) {
    					if (product.getProId() == proId) {
    						product.setQuantityInStock(product.getQuantityInStock() + quantity);
    						String pro = product.getProId() + "," + product.getProName() + "," + product.getPrice() + ","; 
    						for (Integer key : product.getPriceRange().keySet()) {
    							pro = pro + key.toString() + ":" + product.getPriceRange().get(key) + "," 
    									+ product.getQuantityInStock() + "," + product.getUnitType() + ","
    									+ product.getStartDate() + "," + product.getShelfLife() + "," 
    									+ product.isDiscountStatus() + "," + product.isToCharity();
    							break;
    						}
    						isSuccess = operator.updateProductFile(pro);
    						break;
    					}
    				}
    				
    			}
    		}
    	}
    	catch (Exception e) {
    		System.out.println("The product doesn't exist.");
    	}
    	return isSuccess;
    }
    
    public boolean updateCart(int userID, int proId,int quantity) {
    	boolean isSuccess = false;
    	HashMap<Integer, Integer> cartMap = null;
    	FileOperator operator = new FileOperator();
    	try {
    		ArrayList<Cart> carts = operator.readCartFile(userID);
			for (Cart cart : carts) {
				if (cart.getCusID() == userID) {
					cartMap = cart.getCartMap();
					break;
				}
			}
			if (cartMap != null) {
				int oldQuan = cartMap.get(proId).intValue();
	    		cartMap.replace(proId,quantity);
	    		Cart cart = new Cart(userID, cartMap);
	    		isSuccess = new FileOperator().updateCartFile(cart);
	    		if (isSuccess) {
    				Shelf shelf = operator.readProductFile("");
    				for (Product product: shelf.getProducts()) {
    					int diff = oldQuan - quantity;
    					if (product.getProId() == proId) {
    						product.setQuantityInStock(product.getQuantityInStock() + diff);
    						String pro = product.getProId() + "," + product.getProName() + "," + product.getPrice() + ","; 
    						for (Integer key : product.getPriceRange().keySet()) {
    							pro = pro + key.toString() + ":" + product.getPriceRange().get(key) + "," 
    									+ product.getQuantityInStock() + "," + product.getUnitType() + ","
    									+ product.getStartDate() + "," + product.getShelfLife() + "," 
    									+ product.isDiscountStatus() + "," + product.isToCharity();
    							break;
    						}
    						isSuccess = operator.updateProductFile(pro);
    						break;
    					}
    				}
    				
    			}
			}
    	}
    	catch (Exception e) {
    		System.out.println("The product doesn't exist.");
    	}
    	return isSuccess;
    }
    
    public boolean clearCart(int userID) {
    	boolean isSuccess = false;
    	Cart cart = new Cart(userID, new HashMap<Integer, Integer>());
    	isSuccess = new FileOperator().updateCartFile(cart);
    	return isSuccess;
    }
    
    public double calculatePrice(int userID) {
    	double totalprice = 0.0;
    	HashMap<Integer, Integer> cartMap = null;
    	FileOperator operator = new FileOperator();
    	ArrayList<Cart> carts = operator.readCartFile(userID);
		for (Cart cart : carts) {
			if (cart.getCusID() == userID) {
				cartMap = cart.getCartMap();
				break;
			}
		}
		if (cartMap != null) {
	    	Shelf shelf = operator.readProductFile("");
	    	for (Integer key : cartMap.keySet()) {
	    		int proID = key.intValue();
	    		for (Product shelfPro : shelf.getProducts()) {
	    			if (proID == shelfPro.getProId()) {
	    				if (shelfPro.isDiscountStatus()) {
	    					totalprice = totalprice + shelfPro.getPrice() * cartMap.get(key).intValue() * Product.DISCOUNT;
	    				}
	    				else {
	    					totalprice = totalprice + shelfPro.getPrice() * cartMap.get(key).intValue();
	    				}
	    			}
	    		}
	    	}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		totalprice = Double.parseDouble(decimalFormat.format(totalprice));
    	return totalprice;
    }
    
    public int validateAccount(String name, String phone, String email, 
    		String address, String card, String cvv, String month, String year) {
    	
		String nameFormat = "^[a-zA-Z]{1,25}$";
		String phoneFormat = "^0[0-9]{9}$";
		String emailFormat = "^[a-zA-Z0-9_.]{1,}@[a-z0-9]{1,}.[a-z]{1,}$";
		String cardFormat = "^[0-9]{16}$";
		String cvvFormat = "^[0-9]{3}$";
		if (!Pattern.matches(nameFormat, name)) {
			return StaticValue.USERNAME_ERROR;
		}
		if (!Pattern.matches(phoneFormat, phone)) {
			return StaticValue.PHONE_ERROR;
		}
		if (!Pattern.matches(emailFormat, email)) {
			return StaticValue.EMAIL_ERROR;
		}
		if (address.length() > 70 || address.length() == 0) {
			return StaticValue.ADDRESS_ERROR;
		}
		if (!Pattern.matches(cardFormat, card)) {
			return StaticValue.BANKCARD_ERROR;
		}
		if (!Pattern.matches(cvvFormat, cvv)) {
			return StaticValue.CVV_ERROR;
		}
		if (month.equals("Month") || year.equals("Year")) {
			return StaticValue.EXPIRYDATE_ERROR;
		}
		return StaticValue.ACCOUNT_SUCCESS;
	}
    
    public int validateRegisterAccount(String name, String passwd, String phone, String email, 
    		String address, String card, String cvv, String month, String year) {
    	
    	int result = validateAccount(name, phone, email, address, card, cvv, month, year);
    	if (result == StaticValue.ACCOUNT_SUCCESS) {
	    	String passwdFormat = "^[a-zA-Z0-9]{1,16}$";
	        if (!Pattern.matches(passwdFormat, passwd)) {
	        	return StaticValue.PASSWORD_ERROR;
	        }
	        else
	        	return StaticValue.ACCOUNT_SUCCESS;
    	}
    	else {
    		return result;
    	}
    }
    
    public boolean generateTransaction(int userID, String date, boolean status, double totalprice) {
    	boolean isSuccess = false;
    	Transaction trans = new Transaction(date, status, totalprice);
    	isSuccess = new FileOperator().insertTransFile(userID, trans);
    	return isSuccess;
    }

}
