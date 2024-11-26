package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.html.HTMLReportAssembler;
import eu.jpereira.trainings.designpatterns.creational.builder.json.JSONReportAssembler;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Reportable;
import eu.jpereira.trainings.designpatterns.creational.builder.xml.XMLReportAssembler;

public class ReportAssemblerTypeChooser {

    public Reportable getAssemblerType(String type){
        Reportable reportable = null;
        if (type.equals("JSON")) {
            reportable = new JSONReportAssembler();
        } else if(type.equals("XML")){
            reportable = new XMLReportAssembler();
        } else if(type.equals("HTML")){
            reportable = new HTMLReportAssembler();
        }
        return reportable;
    }
}
