public class Report {
  private Inventory inventory;

  public Report(Inventory inventory) {
    this.inventory = inventory;
  }

  public void generateSalesReport() {
    // Simple Inventory Report generation (expand as needed)
    for (Product product : inventory.products.values()) {
      System.out.println(product.getId() + ". " + product.getName() + " - " + product.getQuantity());
    }
  }
}