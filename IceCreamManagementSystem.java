import java.util.Scanner;

public class IceCreamManagementSystem {

    
    String[] iceCreamTypes = {"Vanilla", "Chocolate", "Strawberry"};
    int[] iceCreamPrices = {50, 60, 70}; 
    int[] iceCreamStock = {100, 80, 60}; 

    String[] customerNames = new String[100];
    String[] customerOrders = new String[100];
    int[] customerQuantities = new int[100];

    int totalCustomers = 0; 
    int totalAmount = 0;
    Scanner sc = new Scanner(System.in);
    final String ADMIN_PASSWORD = "siws"; // Admin password

   
    public void displayMenu() {
        System.out.println("********** Welcome to Ice Cream Palace **********");
        System.out.println("==================================================");
        for (int i = 0; i < iceCreamTypes.length; i++) {
            System.out.println((i + 1) + ". " + iceCreamTypes[i] + " - Rs " + iceCreamPrices[i] + " (Stock: " + iceCreamStock[i] + ")");
        }
        System.out.println("4. Exit");
        System.out.println("==================================================");
        System.out.println("What would you like to order?");
    }

   
    public void placeOrder() {
        if (totalCustomers >= 100) {
            System.out.println("Sorry, we can't accept more customers at the moment.");
            return;
        }
        sc.nextLine(); 
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 4) {
                generateBill();
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please select a valid ice cream.");
                continue;
            }

            System.out.println("You have selected " + iceCreamTypes[choice - 1]);
            System.out.print("Enter the quantity: ");
            int quantity = sc.nextInt();

            if (iceCreamStock[choice - 1] < quantity) {
                System.out.println("Sorry, we only have " + iceCreamStock[choice - 1] + " left in stock.");
                continue;
            }

            
            iceCreamStock[choice - 1] -= quantity;
            totalAmount += iceCreamPrices[choice - 1] * quantity;

            
            customerNames[totalCustomers] = name;
            customerOrders[totalCustomers] = iceCreamTypes[choice - 1];
            customerQuantities[totalCustomers] = quantity;
            totalCustomers++;

            System.out.print("Do you want to order more ice creams? (Y/N): ");
            String moreOrder = sc.next();
            if (moreOrder.equalsIgnoreCase("N")) {
                generateBill();
                break;
            }
        }
    }

   
    public void adminMode() {
        System.out.print("Enter admin password: ");
        String enteredPassword = sc.next();

        if (!enteredPassword.equals(ADMIN_PASSWORD)) {
            System.out.println("Incorrect password. Access denied.");
            return;
        }

        System.out.println("Access granted to Admin Mode.");
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Customers and Orders");
            System.out.println("2. Update Prices");
            System.out.println("3. Update Stock");
            System.out.println("4. View Stock and Prices");
            System.out.println("5. Exit Admin Mode");
            System.out.print("Enter your choice: ");
            int adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1:
                    viewCustomersAndOrders();
                    break;
                case 2:
                    updatePrices();
                    break;
                case 3:
                    updateStock();
                    break;
                case 4:
                    viewStockAndPrices();
                    break;
                case 5:
                    System.out.println("Exiting Admin Mode...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    
    public void viewCustomersAndOrders() {
        System.out.println("********** Customer Orders **********");
        for (int i = 0; i < totalCustomers; i++) {
            System.out.println("Customer: " + customerNames[i] + " | Ordered: " + customerOrders[i] + " | Quantity: " + customerQuantities[i]);
        }
        if (totalCustomers == 0) {
            System.out.println("No customers yet.");
        }
    }

    
    public void updatePrices() {
        System.out.println("Select the ice cream to update price:");
        for (int i = 0; i < iceCreamTypes.length; i++) {
            System.out.println((i + 1) + ". " + iceCreamTypes[i] + " - Rs " + iceCreamPrices[i]);
        }
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= iceCreamTypes.length) {
            System.out.print("Enter new price for " + iceCreamTypes[choice - 1] + ": ");
            iceCreamPrices[choice - 1] = sc.nextInt();
            System.out.println("Price updated successfully!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    
    public void updateStock() {
        System.out.println("Select the ice cream to update stock:");
        for (int i = 0; i < iceCreamTypes.length; i++) {
            System.out.println((i + 1) + ". " + iceCreamTypes[i] + " - Stock: " + iceCreamStock[i]);
        }
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= iceCreamTypes.length) {
            System.out.print("Enter new stock for " + iceCreamTypes[choice - 1] + ": ");
            iceCreamStock[choice - 1] = sc.nextInt();
            System.out.println("Stock updated successfully!");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    
    public void viewStockAndPrices() {
        System.out.println("Current Stock and Prices:");
        for (int i = 0; i < iceCreamTypes.length; i++) {
            System.out.println(iceCreamTypes[i] + " - Price: Rs " + iceCreamPrices[i] + " | Stock: " + iceCreamStock[i]);
        }
    }

    
    public void generateBill() {
        System.out.println();
        System.out.println("********** Thank you for ordering from Ice Cream Palace **********");
        System.out.println("Your total bill is: Rs " + totalAmount);
    }

    
    public static void main(String[] args) {
        IceCreamManagementSystem system = new IceCreamManagementSystem();  

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select Mode: ");
            System.out.println("1. Customer Mode");
            System.out.println("2. Admin Mode");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mode = sc.nextInt();

            switch (mode) {
                case 1:
                    system.placeOrder(); 
                    break;
                case 2:
                    system.adminMode();  
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please choose a valid mode.");
            }
        }
    }
}
