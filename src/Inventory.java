import java.util.HashMap;
import java.util.Map;

public class Inventory {
  Map<String, Product> products;

  // Constructor
  public Inventory() {
    this.products = new HashMap<>(); // Treemap?
  }

  // Method to add a new product
  public void addProduct(Product product) {
    products.put(product.getId(), product);
  }

  // Method to get a product by ID
  public Product getProduct(String id) {
    return products.get(id);
  }

  // Method to remove a product by ID
  public void removeProduct(String id) {
    products.remove(id);
  }

  // Method to update product details
  public void updateProduct(String id, String name, double price, int quantity) {
    Product product = products.get(id);
    if (product != null) {
      product.setName(name);
      product.setPrice(price);
      product.setQuantity(quantity);
    }
  }
}