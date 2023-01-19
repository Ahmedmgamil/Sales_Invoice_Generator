package Model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceLines {
    private InvoiceHeader header;

    private List<Item> items;
    private double totalAmount;

    public InvoiceLines(InvoiceHeader header ) {
        this.header = header;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotalAmount() {
        double totalAmount = 0;
        for (Item item : items) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
        return totalAmount;
    }

}


