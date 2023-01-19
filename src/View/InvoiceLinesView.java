package View;

import Model.InvoiceLines;
import Model.Item;

public class InvoiceLinesView {
    public void printInvoice(InvoiceLines model) {
        System.out.println("Invoice #: " + model.getHeader().getInvoiceNumber());
        System.out.println("Date: " + model.getHeader().gettimenow());
        System.out.println("Customer: " + model.getHeader().getCustomername());
        System.out.println("Items:");
        for (Item item : model.getItems()) {
            System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getPrice() + ", Total: " + item.getPrice()*item.getQuantity());
        }
        System.out.println("Total Amount: " + model.calculateTotalAmount());
    }
}