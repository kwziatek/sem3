package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportGenerator extends ReportGenerator{
    @Override
    public Report createReport() {
        return new JSONReport();
    }
}
