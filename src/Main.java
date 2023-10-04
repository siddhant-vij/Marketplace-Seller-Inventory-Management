import java.util.Scanner;

public class Main {

  private static Scanner scanner = new Scanner(System.in);

  public static void displayMenu() {
    System.out.println("\nMarketplace Seller Inventory Management\n");
    System.out.println("1. Add a product");
    System.out.println("2. Remove a product");
    System.out.println("3. Update a product");
    System.out.println("4. Simulate sale");
    System.out.println("5. Inventory report");
    System.out.println("6. Order report");
    System.out.println("0. Exit");
  }

  public static void run() {
    while (true) {
      displayMenu();
      System.out.print("\nEnter command: ");
      String command = scanner.nextLine();

      switch (command) {
        case "1":
          Controller.addProduct();
          break;
        case "2":
          Controller.removeProduct();
          break;
        case "3":
          Controller.updateProduct();
          break;
        case "4":
          Controller.simulateSale();
          break;
        case "5":
          Controller.report.generateSalesReport();
          break;
        case "6":
          Controller.viewOrders();
          break;
        case "0":
          System.out.println("Exiting...");
          scanner.close();
          return;
        default:
          System.out.println("Invalid command. Try again.");
      }
    }
  }

  public static void main(String[] args) {
    run();
  }
}
