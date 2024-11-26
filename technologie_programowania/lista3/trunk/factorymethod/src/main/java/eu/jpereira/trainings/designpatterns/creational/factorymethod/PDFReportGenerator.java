package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFReportGenerator extends ReportGenerator{
    @Override
    public Report createReport() {
        return new PDFReport();
    }
}
