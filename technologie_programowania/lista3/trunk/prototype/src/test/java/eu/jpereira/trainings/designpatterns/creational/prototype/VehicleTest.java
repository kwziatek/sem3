package eu.jpereira.trainings.designpatterns.creational.prototype;

import eu.jpereira.trainings.designpatterns.creational.prototype.exception.VehicleDoesNotHavePartsException;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class VehicleTest {
    List<VehiclePart> listOfParts;
    Vehicle vehicle;
    Vehicle newVehicle;

    public VehicleTest() throws CloneNotSupportedException {
        listOfParts = new ArrayList<>();
        vehicle = new Vehicle();
        newVehicle = new Vehicle();
        creationMethod(listOfParts);
    }
    private void creationMethod(List<VehiclePart> listOfParts) throws CloneNotSupportedException {
        listOfParts.add(new Window());
        vehicle.setParts(listOfParts);
        newVehicle = vehicle.myClone();
    }
    @Test
    public void checkTheSizeOfParts() throws VehicleDoesNotHavePartsException {
        assertEquals(vehicle.countParts(), newVehicle.countParts());
    }
    @Test
    public void checkTheEqualityOfTheWindowItems() throws VehicleDoesNotHavePartsException {
        assertEquals(vehicle.getParts(VehiclePartEnumeration.WINDOW), newVehicle.getParts(VehiclePartEnumeration.WINDOW));

    }
}
