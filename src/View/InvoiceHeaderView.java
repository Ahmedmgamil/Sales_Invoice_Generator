package View;

import Model.InvoiceHeader;



public class InvoiceHeaderView {
    public void printInvoiceHeader(InvoiceHeader model) {
        System.out.println("Invoice #: " + model.getInvoiceNumber());
        System.out.println("Date: " + model.getInvoicedate());
        System.out.println("Customer Name: " + model.getCustomername());
    }
}