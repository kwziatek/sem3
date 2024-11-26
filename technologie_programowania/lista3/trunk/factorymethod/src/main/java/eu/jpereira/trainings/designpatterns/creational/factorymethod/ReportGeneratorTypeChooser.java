package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class ReportGeneratorTypeChooser {
    public static Report reportOfSpecificType(ReportData data, String type) {
        Report report = null;
        switch (type) {
            case "HTML":
                report = new HTMLReportGenerator().createReport();
                break;
            case "JSON":
                report = new JSONReportGenerator().createReport();
                break;
            case "PDF":
                report = new PDFReportGenerator().createReport();
                break;
            case "XML":
                report = new XMLReportGenerator().createReport();
                break;
        }
        report.generateReport(data);
        return report;
    }
}
