package Controller;

import Model.Item;
import View.ItemView;

public class ItemController {
    private Item model;
    private ItemView view;

    public ItemController(Item model, ItemView view) {
        this.model = model;
        this.view = view;
    }

    public void setName(String name) {
        model.setName(name);
        updateView();
    }

    public void setPrice(double price) {
        model.setPrice(price);
        updateView();
    }

    public void setQuantity(int quantity) {
        model.setQuantity(quantity);
        updateView();
    }

    public void updateView() {
        view.printItem(model);
    }
}