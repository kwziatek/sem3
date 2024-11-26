package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReport;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReport;

public abstract class AbstractFactory {

    public abstract ReportBody createReportBody();

    public abstract ReportFooter createReportFooter();

    public abstract ReportHeader createReportHeader();

    public static void handleReport(String string, Report report) {
        AbstractFactory abstractFactory = null;
        if(string.equals("JSON")) {
            abstractFactory = new JSONReport();
        } else if(string.equals("XML")){
            abstractFactory = new XMLReport();
        }
        report.setBody(abstractFactory.createReportBody());
        report.setFooter(abstractFactory.createReportFooter());
        report.setHeader(abstractFactory.createReportHeader());
    }
}
