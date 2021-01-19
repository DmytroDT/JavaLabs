package transportSystem.train.railcar.railcarTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.junit.*;
import transportSystem.baggage.Cargo;
import transportSystem.train.railcar.CargoRailCar;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CargoRailCarTest {

    CargoRailCar cargoRailCar;

    Cargo cargoMock = mock(Cargo.class);
    Cargo specialCargo = mock(Cargo.class);


    List<Cargo> cargoList = new ArrayList<Cargo>();

    @Before
    public void setup() {
        when(specialCargo.getName()).thenReturn("special cargo");
        when(cargoMock.getVolume()).thenReturn(10.);
        when(cargoMock.getWeight()).thenReturn(10.);
    }

    @Test
    public void cargoRailCarShouldntFitCargoWithVolumeOverCapacity() {
        cargoRailCar = new CargoRailCar();

        for (int i = 0; i < 11; i++) {
            cargoRailCar.loadCargo(cargoMock);
        }

        assertEquals(false, cargoRailCar.loadCargo(cargoMock));
    }

    @Test
    public void CargoShouldCorrectlyComputeWeight() {

        cargoRailCar = new CargoRailCar();

        for (int i = 0; i < 11; i++) {
            cargoRailCar.loadCargo(cargoMock);
        }

        assertEquals(false, cargoRailCar.isOverloaded());

        assertEquals(25000 + 9 * 10., cargoRailCar.computeWeight(), 100);
    }


    @Test
    public void cargoRCShouldRetrieveSpecifiedCargo() {

        cargoRailCar = new CargoRailCar();

        for (int i = 0; i < 3; i++) {
            cargoRailCar.loadCargo(cargoMock);
        }

        assertEquals(true, cargoRailCar.loadCargo(specialCargo));

        assertEquals(specialCargo, cargoRailCar.retrieveCargo("special cargo"));

    }

}
