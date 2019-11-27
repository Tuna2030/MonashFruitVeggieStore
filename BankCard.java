
public class BankCard {
	
	private String cardNo;
	private int cvv;
	private String expiryDate;
	
	public BankCard() {}

	public BankCard(String cardNo, int cvv, String expiryDate) {
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
