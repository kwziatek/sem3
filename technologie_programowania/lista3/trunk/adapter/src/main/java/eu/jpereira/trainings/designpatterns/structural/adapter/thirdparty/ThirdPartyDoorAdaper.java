package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door {
    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try{
            super.unlock(code);
            setState(DoorState.OPEN);
        } catch (CannotUnlockDoorException | CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close(){
        try{
            super.setState(DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor e) {

        }
    }

    @Override
    public boolean isOpen() {
        return super.getState() == DoorState.OPEN;
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if (!newCodeConfirmation.equals(newCode)) {
            throw new CodeMismatchException();
        }
        try{
            super.unlock(oldCode);
            super.setNewLockCode(newCode);
        } catch (CannotUnlockDoorException | CannotChangeCodeForUnlockedDoor e){
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public boolean testCode(String code) {
        try {
            super.unlock(code);
            super.lock();
            return true;
        } catch (CannotUnlockDoorException e){
            return false;
        }
    }
}
