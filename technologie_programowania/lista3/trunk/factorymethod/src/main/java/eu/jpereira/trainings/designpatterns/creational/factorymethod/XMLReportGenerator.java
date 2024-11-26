package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportGenerator extends ReportGenerator{
    @Override
    public Report createReport() {
        return new XMLReport();
    }
}
