
import java.util.ArrayList;

public class Shelf {
	
	private ArrayList<Product> products;

	public Shelf() {
		products = new ArrayList<>();
	}
	
	public Shelf(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}