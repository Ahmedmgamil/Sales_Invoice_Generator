import Controller.InvoiceHeaderController;
import Controller.InvoiceLinesController;

import Model.InvoiceHeader;
import Model.InvoiceLines;
import Model.Item;
import View.InvoiceHeaderView;
import View.InvoiceLinesView;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
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
        s.close();
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

