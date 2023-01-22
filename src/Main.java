import Controller.InvoiceHeaderController;
import Controller.InvoiceLinesController;

import Controller.Invoices_Reading;
import Model.InvoiceHeader;
import Model.InvoiceLines;
import Model.Item;
import View.InvoiceHeaderView;
import View.InvoiceLinesView;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add invoice");
            System.out.println("2. Read invoices");
            System.out.println("3. Exit");
            int choice = s.nextInt();
            if (choice == 1) {
                boolean createAnotherInvoice;

                do {


                    InvoiceHeader headerModel = new InvoiceHeader();
                    InvoiceHeaderView headerView = new InvoiceHeaderView();
                    InvoiceHeaderController headerController = new InvoiceHeaderController(headerModel, headerView);

                    Scanner scanner = new Scanner(System.in);


                    System.out.println("Enter invoice number:");
                    String invoiceNumber = scanner.next();
                    headerController.setInvoiceNumber(invoiceNumber);
                    headerController.setDate();

                    System.out.println("Enter customer name:");
                    String customerName = scanner.next();
                    headerController.setCustomername(customerName);


                    InvoiceLines model = new InvoiceLines(headerModel);
                    InvoiceLinesView view = new InvoiceLinesView();
                    InvoiceLinesController controller = new InvoiceLinesController(model, view);

                    boolean done = false;
                    while (!done) {

                        System.out.println("Enter item name or 'done' to finish:");
                        String itemName = scanner.next();
                        if (itemName.equalsIgnoreCase("done")) {
                            done = true;
                            break;
                        }


                        System.out.println("Enter item price:");
                        double itemPrice = scanner.nextDouble();


                        System.out.println("Enter item quantity:");
                        int itemQuantity = scanner.nextInt();

                        Item itemModel = new Item(itemName, itemPrice, itemQuantity);


                        controller.addItem(itemModel);
                    }

                    controller.updateView();

                    saveInvoiceHeaderToCSV(headerModel);
                    // save the invoice data to a CSV file
                    saveInvoiceToCSV(model, headerModel);
                    System.out.println("Do you want to create another invoice? (y/n)");
                    String response = scanner.next();
                    createAnotherInvoice = response.equalsIgnoreCase("y");
                } while (createAnotherInvoice);

            } else if (choice == 2) {

                Invoices_Reading invoices_reading = new Invoices_Reading();
                HashMap<String, InvoiceHeader> headers = invoices_reading.readInvoiceHeaders();
                ArrayList<InvoiceLines> lines = invoices_reading.readInvoiceLines(headers);

                for (InvoiceLines line : lines) {

                    System.out.println("Invoice Number: " + line.getHeader().getInvoiceNumber());
                    System.out.println("Invoice Date: " + line.getHeader().getInvoicedate());
                    System.out.println("Customer Name: " + line.getHeader().getCustomername());
                    System.out.println("Invoice Items:");
                    for (Item item : line.getItems()) {
                        System.out.println("Item Name: " + item.getName());
                        System.out.println("Price: " + item.getPrice());
                        System.out.println("Quantity: " + item.getQuantity());
                    }
                    System.out.println("------------------");
                }
            }

            else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

        public static void saveInvoiceHeaderToCSV (InvoiceHeader headerModel){
            try {
                FileWriter writer = new FileWriter("invoice_header.csv", true);

                writer.append(headerModel.getInvoiceNumber());
                writer.append(",");
                writer.append(headerModel.getInvoicedate());
                writer.append(",");
                writer.append(headerModel.getCustomername());
                writer.append("\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void saveInvoiceToCSV (InvoiceLines model, InvoiceHeader headerModel){
            try {
                FileWriter writer = new FileWriter("invoice.csv", true);

                for (Item item : model.getItems()) {
                    writer.append(headerModel.getInvoiceNumber());
                    writer.append(",");
                    writer.append(item.getName());
                    writer.append(",");
                    writer.append(Double.toString(item.getPrice()));
                    writer.append(",");
                    writer.append(Integer.toString(item.getQuantity()));
                    writer.append("\n");
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}




