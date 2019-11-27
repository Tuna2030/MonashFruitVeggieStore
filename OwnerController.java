
public class OwnerController {
	
	public boolean deleteAccount(int userID, String username) {
		boolean isSuccess = false;
		FileOperator operator = new FileOperator();
		isSuccess = operator.writeUserFile(userID, username);
		return isSuccess;
	}
	
	public boolean deleteProduct(int proID) {
		boolean isSuccess = false;
		isSuccess = new FileOperator().deleteProductFile(proID);
		return isSuccess;
	}
	
	public boolean manageProduct(String product) {
		boolean isSuccess = false;
		isSuccess = new FileOperator().updateProductFile(product);
		return isSuccess;
	}
	
	public boolean addProduct(String product) {
		boolean isSuccess = false;
		isSuccess = new FileOperator().insertProductFile(product);
		return isSuccess;
	}
	
	public int validateProduct(String name, double price, int rangeKey, int quantity, int shelfLife) {
		String[] priceRange = StaticValue.PRICE_RANGE[rangeKey].toString().split("-");
	    double minR = Double.parseDouble(priceRange[0]);
	    double maxR = Double.parseDouble(priceRange[1]);
	    
	    if (name.length() == 0 || name.length() > 25) {
	    	return StaticValue.PRODUCTNAME_ERROR;
	    }
	    if (price < 0) {
	    	return StaticValue.PRICE_ERROR;
	    }
	    if (quantity < 0) {
	    	return StaticValue.QUANTITY_ERROR;
	    }
	    if (shelfLife <= 0) {
	    	return StaticValue.SHELFLIFE_ERROR;
	    }
	    if (price < minR || price > maxR) {
	    	return StaticValue.PRICERANGE_ERROR;
	    }
	    return StaticValue.PRODUCT_SUCCESS;
	}
}
