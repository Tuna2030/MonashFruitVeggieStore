import java.util.HashMap;

public class Product {
	
	private int proId;
	private String proName;
	private double price;
	private HashMap<Integer, String> priceRange;
	private int quantityInStock;
	private String unitType;
	private String startDate;
	private int shelfLife;
	private boolean discountStatus;
	private boolean toCharity;
	
	public final static double DISCOUNT = 0.6;
	
	public Product() {}
	
	public Product(int proId, String proName, double price, int quantityInStock, boolean discountStatus) {
		this.proId = proId;
		this.proName = proName;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.discountStatus = discountStatus;
	}	

	public Product(int proId, String proName, double price, HashMap<Integer, String> priceRange, int quantityInStock,
			String startDate, int shelfLife, boolean discountStatus) {
		this.proId = proId;
		this.proName = proName;
		this.price = price;
		this.priceRange = priceRange;
		this.quantityInStock = quantityInStock;
		this.startDate = startDate;
		this.shelfLife = shelfLife;
		this.discountStatus = discountStatus;
	}

	public Product(int proId, String proName, double price, HashMap<Integer, String> priceRange, int quantityInStock, boolean discountStatus) {
		this.proId = proId;
		this.proName = proName;
		this.price = price;
		this.priceRange = priceRange;
		this.quantityInStock = quantityInStock;
		this.discountStatus = discountStatus;
	}

	public Product(int proId, String proName, double price, HashMap<Integer, String> priceRange, int quantityInStock,
			String unitType, String startDate, int shelfLife, boolean discountStatus, boolean toCharity) {
		this.proId = proId;
		this.proName = proName;
		this.price = price;
		this.priceRange = priceRange;
		this.quantityInStock = quantityInStock;
		this.unitType = unitType;
		this.startDate = startDate;
		this.shelfLife = shelfLife;
		this.discountStatus = discountStatus;
		this.toCharity = toCharity;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public HashMap<Integer, String> getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(HashMap<Integer, String> priceRange) {
		this.priceRange = priceRange;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}

	public boolean isDiscountStatus() {
		return discountStatus;
	}

	public void setDiscountStatus(boolean discountStatus) {
		this.discountStatus = discountStatus;
	}

	public boolean isToCharity() {
		return toCharity;
	}

	public void setToCharity(boolean toCharity) {
		this.toCharity = toCharity;
	}
	
}

