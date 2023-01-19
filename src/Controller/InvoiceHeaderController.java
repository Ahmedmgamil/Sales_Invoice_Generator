package Controller;

import Model.InvoiceHeader;
import View.InvoiceHeaderView;

public class InvoiceHeaderController {
    private InvoiceHeader model;
    private InvoiceHeaderView view;

    public InvoiceHeaderController(InvoiceHeader model, InvoiceHeaderView view) {
        this.model = model;
        this.view = view;
    }

    public void setCustomername (String customerName)
    {
        model.setCustomername(customerName);
    }
    public void setInvoiceNumber(String invoiceNumber) {
        model.setInvoiceNumber(invoiceNumber);
    }

    public void setDate() {
        String date;
        date = model.gettimenow();
        model.gettimenow();
        model.setInvoicedate(date);
    }



    public void updateView() {
        view.printInvoiceHeader(model);
    }
}

