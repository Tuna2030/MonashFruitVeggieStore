
public class Transaction {
	
	private int transId;
	private int cusID;
	private String date;
	private boolean status;
	private double totalPrice;
	
	public Transaction() {}
	
	public Transaction(String date, boolean status, double totalPrice) {
		this.date = date;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Transaction(int transId, int cusID, String date, boolean status, double totalPrice) {
		this.transId = transId;
		this.cusID = cusID;
		this.date = date;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
