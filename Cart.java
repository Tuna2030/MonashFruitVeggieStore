
import java.util.HashMap;

public class Cart {
	
	private int cusID;
	private HashMap<Integer, Integer> cartMap;    // productID, quantity
	
	public Cart() {}
	
	public Cart(int cusID, HashMap<Integer, Integer> cartMap) {
		this.cusID = cusID;
		this.cartMap = cartMap;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public HashMap<Integer, Integer> getCartMap() {
		return cartMap;
	}

	public void setCartMap(HashMap<Integer, Integer> cartMap) {
		this.cartMap = cartMap;
	}
	
}
