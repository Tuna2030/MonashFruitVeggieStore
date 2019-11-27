
public class Payment {
	
	private String phoneNo;
	private String email;
	private String address;
	private BankCard cardInfo;
	
	public Payment() {}

	public Payment(String phoneNo, String email, String address, BankCard cardInfo) {
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.cardInfo = cardInfo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BankCard getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(BankCard cardInfo) {
		this.cardInfo = cardInfo;
	}
	
}
