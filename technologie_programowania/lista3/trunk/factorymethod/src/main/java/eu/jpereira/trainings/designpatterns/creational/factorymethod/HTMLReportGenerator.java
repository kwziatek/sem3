package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class HTMLReportGenerator extends ReportGenerator{
    @Override
    public Report createReport() {
        return new HTMLReport();
    }
}
