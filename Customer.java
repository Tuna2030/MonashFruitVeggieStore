
public class Customer extends User {
	
	public Payment paymentInfo;
	
	public Customer() {
		super();
	}
	
	public Customer(int userID, String userName, String password) {
		super(userID, userName, password);
	}

	public Customer(Payment paymentInfo) {
		super();
		this.paymentInfo = paymentInfo;
	}

	public Payment getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(Payment paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
}
