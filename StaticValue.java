
public class StaticValue {
	// add to cart
	public final static int OUT_OF_STOCK = -2;
	public final static int ADD_ERROR = -3;
	public final static int ADD_SUCCESS = 1;
	
	// owner/default id
	public final static int FAIL_ID = -1;
	public final static int OWNER_ID = 0;
	
	public final static Object[] MONTHS = {"Month", "01", "02", "03", "04" ,"05", "06", "07", "08", "09", "10", "11", "12"};
	public final static Object[] PRICE_RANGE = {"Price Range", "0-5", "5-10", "10-15", "15-20"};
	public final static Object[] DISCOUNT_STATUS = {"false", "true"};
	public final static Object[] TO_CHARITY = {"false", "true"};
	
	// account validation
	public final static int SYSTEM_ERROR = 0;
	public final static int PHONE_ERROR = -1;
	public final static int EMAIL_ERROR = -2;
	public final static int ADDRESS_ERROR = -3;
	public final static int BANKCARD_ERROR = -4;
	public final static int CVV_ERROR = -5;
	public final static int EXPIRYDATE_ERROR = -6;
	public final static int USERNAME_ERROR = -7;
	public final static int PASSWORD_ERROR = -8;
	public final static int ACCOUNT_SUCCESS = 1;
	
	// product validation
	public final static int PRODUCT_SUCCESS = 1;
	public final static int PRICE_ERROR = -1;
	public final static int QUANTITY_ERROR = -2;
	public final static int SHELFLIFE_ERROR = -3;
	public final static int PRODUCTNAME_ERROR = -4;
	public final static int PRICERANGE_ERROR = -5;
	
	// File name
	public final static String USER_FILE = "userInfo.txt";
	public final static String PAYMENT_FILE = "paymentInfo.txt";
	public final static String BANKCARD_FILE = "bankCardInfo.txt";
	public final static String PRODUCT_FILE = "productInfo.txt";
	public final static String TRANSACTION_FILE = "transactionInfo.txt";
	public final static String CART_FILE = "cartInfo.txt";
}
