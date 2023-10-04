import java.util.List;

public class Order {
  private String orderId;
  private List<Product> products;

  // Constructor
  public Order(String orderId, List<Product> products) {
    this.orderId = orderId;
    this.products = products;
  }

  // Getter method for products
  public List<Product> getProducts() {
    return products;
  }

  // Getter method for orderId
  public String getOrderId() {
    return orderId;
  }
}