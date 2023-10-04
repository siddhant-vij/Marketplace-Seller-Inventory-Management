import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
  static Inventory inventory = new Inventory();
  static Cart cart = new Cart(inventory);
  static Report report = new Report(inventory);
  static Scanner scanner = new Scanner(System.in);
  static List<Order> orders = new ArrayList<>();

  public static void addProduct() {
    System.out.print("Enter product ID: ");
    String id = scanner.nextLine();
    if (inventory.getProduct(id) != null) {
      System.out.println("\nProduct ID already exists.\n");
      return;
    }
    System.out.print("Enter product name: ");
    String name = scanner.nextLine();
    System.out.print("Enter product price: ");
    double price = scanner.nextDouble();
    System.out.print("Enter product quantity: ");
    int quantity = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    Product product = new Product(id, name, price, quantity);
    inventory.addProduct(product);

    System.out.println("\nProduct added successfully.\n");
  }

  public static void removeProduct() {
    System.out.print("Enter product ID to remove: ");
    String id = scanner.nextLine();

    if (inventory.getProduct(id) != null) {
      inventory.removeProduct(id);
      System.out.println("\nProduct removed successfully.\n");
    } else {
      System.out.println("\nProduct not found.\n");
    }
  }

  public static void updateProduct() {
    System.out.print("Enter product ID to update: ");
    String id = scanner.nextLine();
    Product product = inventory.getProduct(id);

    if (product != null) {
      System.out.print("Enter new product name (or press enter to keep current): ");
      String name = scanner.nextLine();
      System.out.print("Enter new product price (or enter -1 to keep current): ");
      double price = scanner.nextDouble();
      System.out.print("Enter new product quantity (or enter -1 to keep current): ");
      int quantity = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      // Update values if new ones are provided
      if (!name.isEmpty())
        product.setName(name);
      if (price >= 0)
        product.setPrice(price);
      if (quantity >= 0)
        product.setQuantity(quantity);

      System.out.println("\nProduct updated successfully.\n");
    } else {
      System.out.println("\nProduct not found.\n");
    }
  }

  public static void simulateSale() {
    while (true) {
      System.out.println("Cart Menu:");
      System.out.println("1. Add to Cart");
      System.out.println("2. Remove from Cart");
      System.out.println("3. Checkout");
      System.out.println("4. Exit to Main Menu");
      System.out.print("Enter choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          addToCart();
          break;
        case 2:
          removeFromCart();
          break;
        case 3:
          checkout();
          break;
        case 4:
          return; // Exit to main menu
        default:
          System.out.println("Invalid choice. Try again.");
      }
    }
  }

  public static void viewOrders() {
    for (Order order : orders) {
      System.out.println("Order ID: " + order.getOrderId());
      for (Product product : order.getProducts()) {
        System.out.println(product.getId() + ". " + product.getName() + " - " + product.getQuantity());
      }
      System.out.println();
    }
  }

  private static void addToCart() {
    System.out.print("Enter product ID to add to cart: ");
    String id = scanner.nextLine();
    if (inventory.getProduct(id) == null) {
      System.out.println("\nProduct ID not found.\n");
      return;
    }
    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();
    if (quantity <= 0) {
      System.out.println("\nQuantity must be greater than 0.\n");
      return;
    } else if (inventory.getProduct(id).getQuantity() < quantity) {
      System.out.println("\nInsufficient quantity in stock.\n");
      return;
    }
    scanner.nextLine(); // Consume newline

    cart.addProduct(id, quantity);
    System.out.println(
        "\nSuccessfully added " + quantity + " units of product " + inventory.getProduct(id).getName()
            + " to the cart.\n");
  }

  private static void removeFromCart() {
    System.out.print("Enter product ID to remove from cart: ");
    String id = scanner.nextLine();
    if (inventory.getProduct(id) == null) {
      System.out.println("\nProduct ID not found.\n");
      return;
    }
    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();
    if (quantity <= 0) {
      System.out.println("\nQuantity must be greater than 0.\n");
      return;
    }
    if (quantity > cart.getProductQuantity(id)) {
      System.out.println("\nInsufficient quantity in cart.\n");
      return;
    }
    scanner.nextLine(); // Consume newline

    cart.removeProduct(id, quantity);
    System.out.println("\nSuccessfully removed " + quantity + " units of product " + inventory.getProduct(id).getName()
        + " from the cart.\n");
  }

  private static void checkout() {
    System.out.print("Enter order ID: ");
    String orderId = scanner.nextLine();

    Order order = cart.checkout(orderId);
    orders.add(order); // Assuming orders is a List<Order> to hold all orders
    System.out.println("\nCheckout successful. Order ID: " + order.getOrderId() + "\n");
  }
}