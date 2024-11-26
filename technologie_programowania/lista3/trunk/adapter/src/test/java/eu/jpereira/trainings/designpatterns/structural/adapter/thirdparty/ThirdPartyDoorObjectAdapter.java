package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;

public class ThirdPartyDoorObjectAdapter implements Door {
    ThirdPartyDoorAdaper thirdPartyDoorAdaper = new ThirdPartyDoorAdaper();
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        thirdPartyDoorAdaper.open(code);
    }

    @Override
    public void close() {
        thirdPartyDoorAdaper.close();
    }

    @Override
    public boolean isOpen() {
        return thirdPartyDoorAdaper.isOpen();
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        thirdPartyDoorAdaper.changeCode(oldCode, newCode, newCodeConfirmation);
    }

    @Override
    public boolean testCode(String code) {
        return thirdPartyDoorAdaper.testCode(code);
    }
}
