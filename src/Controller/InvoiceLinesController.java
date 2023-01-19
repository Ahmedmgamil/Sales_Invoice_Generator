package Controller;

import Model.InvoiceLines;
import View.InvoiceLinesView;
import Model.Item;

public class InvoiceLinesController {
    private InvoiceLines model;
    private InvoiceLinesView view;

    public InvoiceLinesController(InvoiceLines model, InvoiceLinesView view) {
        this.model = model;
        this.view = view;
    }
    public void addItem(Item item) {
        model.addItem(item);
        updateView();
    }

    public void removeItem(Item item) {
        model.removeItem(item);
        updateView();
    }

    public void updateView() {
        view.printInvoice(model);
    }
}