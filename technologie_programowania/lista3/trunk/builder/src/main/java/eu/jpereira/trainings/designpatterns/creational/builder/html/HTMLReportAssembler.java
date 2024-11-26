package eu.jpereira.trainings.designpatterns.creational.builder.html;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Reportable;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.awt.image.RescaleOp;
import java.util.Iterator;

public class HTMLReportAssembler implements Reportable {
    @Override
    public Report getReport(SaleEntry saleEntry){
        Report report = new Report();

        HTMLReportBody reportBody = new HTMLReportBody();
        reportBody.putContent("<span class=\"customerName\">");
        reportBody.putContent(saleEntry.getCustomer().getName());
        reportBody.putContent("</span><span class=\"customerPhone\">");
        reportBody.putContent(saleEntry.getCustomer().getPhone());
        reportBody.putContent("</span>");

        reportBody.putContent("<items>");

        Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
        while ( it.hasNext() ) {
            SoldItem soldEntry= it.next();
            reportBody.putContent("<item><name>");
            reportBody.putContent(soldEntry.getName());
            reportBody.putContent("</name><quantity>");
            reportBody.putContent(soldEntry.getQuantity());
            reportBody.putContent("</quantity><price>");
            reportBody.putContent(soldEntry.getUnitPrice());
            reportBody.putContent("</price></item>");
        }
        reportBody.putContent("</items>");
        report.setReportBody(reportBody);

        return report;
    }

}
