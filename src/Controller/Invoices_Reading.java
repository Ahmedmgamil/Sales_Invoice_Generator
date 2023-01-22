package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLines;
import Model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Invoices_Reading {
    public HashMap<String, InvoiceHeader> readInvoiceHeaders() throws FileNotFoundException {
        HashMap<String, InvoiceHeader> headers = new HashMap<String, InvoiceHeader>();
        Scanner scanner = new Scanner(new File("invoice_header.csv"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String invoiceNumber = values[0];
            String invoiceDate = values[1];
            String customerName = values[2];
            InvoiceHeader header = new InvoiceHeader();
            header.setInvoiceNumber(invoiceNumber);
            header.setDate(invoiceDate);
            header.setCustomername(customerName);
            headers.put(invoiceNumber, header);
        }
        scanner.close();
        return headers;
    }

    public ArrayList<InvoiceLines> readInvoiceLines(HashMap<String, InvoiceHeader> headers) throws FileNotFoundException {
        ArrayList<InvoiceLines> lines = new ArrayList<InvoiceLines>();
        Scanner scanner = new Scanner(new File("invoice.csv"));
        scanner.nextLine(); //skip the header row
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            String invoiceNumber = values[0];
            String itemName = values[1];
            double price = Double.parseDouble(values[2]);
            int quantity = Integer.parseInt(values[3]);
            InvoiceLines invoiceLines = new InvoiceLines();
            ArrayList<Item> items = new ArrayList<>();
            Item item = new Item();
            item.setName(itemName);
            item.setPrice(price);
            item.setQuantity(quantity);
            items.add(item);

            InvoiceHeader header = headers.get(invoiceNumber);
            invoiceLines.setHeader(header);
            invoiceLines.setItems(items);
            lines.add(invoiceLines);
        }
        scanner.close();
        return lines;
    }

}

