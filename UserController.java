
public class UserController {
	
	public UserController() {}
	
	public int login(String userName, String password) {
		int userID = StaticValue.FAIL_ID;
		String[] userInfo = new FileOperator().readUserFile(userID).split(";");
		for (String user : userInfo) {
			if (user.split(",")[1].equals(userName)) {
				if (user.split(",")[2].equals(password)) {
					userID = Integer.parseInt(user.split(",")[0]);
					break;
				}
				else
					break;
			}
		}
		return userID;
	}
	
	public String[] viewTransaction(int userID) {
		String[] transInfo = new FileOperator().readTransFile(userID).split(";");
		return transInfo;
	}
	
	public String searchProduct(String keyword) {
		String pros = "";
		Shelf products = null;
		products = new FileOperator().readProductFile(keyword);
		for (Product product : products.getProducts()) {
			int id = product.getProId();
			pros = pros + id;
			String name = product.getProName();
			pros = pros + "," + name;
			double price = product.getPrice();
			pros = pros + "," + price;
			int priceRange = -1;
			for (Integer key : product.getPriceRange().keySet()) {
				priceRange = key.intValue();
				break;
			}
			pros = pros + "," + priceRange;
			int quantityInStock = product.getQuantityInStock();
			pros = pros + "," + quantityInStock;
			String unitType = product.getUnitType();
			pros = pros + "," + unitType;
			String startDate = product.getStartDate();
			pros = pros + "," + startDate;
			int shelfLife = product.getShelfLife();
			pros = pros + "," + shelfLife;
			boolean discountStatus = product.isDiscountStatus();
			pros = pros + "," + discountStatus;
			boolean toCharity = product.isToCharity();
			pros = pros + "," + toCharity + ";";
		}
		return pros;
	}
	
	public String[] viewAccounts(int userID) {
		String[] userInfo = new FileOperator().readUserFile(userID).split(";");
		if (userID == StaticValue.OWNER_ID)
			return userInfo;
		else {
			String u = "";
			for (String user : userInfo) {
				String Id = user.split(",")[0];
				if (userID == Integer.parseInt(Id) && Integer.parseInt(Id) != StaticValue.OWNER_ID) {
					u = u + user + ";";
				}
			}
			return u.split(";");
		}
	}
	
}
