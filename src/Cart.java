import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
  private Map<String, Integer> products; // Changed to Map to hold quantities
  private Inventory inventory;

  public Cart(Inventory inventory) {
    this.products = new HashMap<>();
    this.inventory = inventory;
  }

  public void addProduct(String productId, int quantity) {
    this.products.put(productId, this.products.getOrDefault(productId, 0) + quantity);
  }

  public void removeProduct(String productId, int quantity) {
    this.products.put(productId, Math.max(0, this.products.getOrDefault(productId, 0) - quantity));
    if (this.products.get(productId) == 0) {
      this.products.remove(productId);
    }
  }

  public Order checkout(String orderId) {
    List<Product> orderProducts = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : this.products.entrySet()) {
      Product product = inventory.getProduct(entry.getKey());
      if (product != null && product.getQuantity() >= entry.getValue()) {
        product.setQuantity(product.getQuantity() - entry.getValue());
        orderProducts.add(new Product(product.getId(), product.getName(), product.getPrice(), entry.getValue()));
      }
    }
    this.products.clear();
    return new Order(orderId, orderProducts);
  }

  public int getProductQuantity(String id) {
    return this.products.getOrDefault(id, 0);
  }
}