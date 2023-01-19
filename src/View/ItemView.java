package View;

import Model.Item;

public class ItemView {
    public void printItem(Item model) {
        System.out.println("Name: " + model.getName());
        System.out.println("Price: " + model.getPrice());
        System.out.println("Quantity: " + model.getQuantity());
    }
}