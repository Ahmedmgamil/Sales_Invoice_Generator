package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class InvoiceHeader {
   private String InvoiceNumber;
   private String invoicedate;

    private String customerName;

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getCustomername() {
        return customerName;
    }

    public void setCustomername(String customername) {
        customerName = customername;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public InvoiceHeader()
    {

    }

    public String gettimenow()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }

    public void setDate(String date) {
        this.invoicedate = date;

    }
}
