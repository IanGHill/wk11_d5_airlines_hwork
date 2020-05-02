import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightManagerTest {

  private Flight eb419;
  private Flight eb420;
  private Passenger dave;
  private Passenger clarissa;
  private Passenger hammy;
  private FlightManager flightManager;
  private Date departureTime;
  private ArrayList<Integer> testAllocatedSeats;

  @Before
  public void before(){
    departureTime = new Date(2020,05, 15, 18, 30  );
    eb419 = new Flight(PlaneType.BOEING747, "EB419", "GLA", "EDI", departureTime);
    eb420 = new Flight(PlaneType.SMALLPLANE100, "EB419", "GLA", "EDI", departureTime);
    dave = new Passenger("Dave", 2);
    clarissa = new Passenger("Clarissa", 1);
    hammy = new Passenger("Hammy", 1);
    flightManager = new FlightManager();
    testAllocatedSeats = new ArrayList<Integer>();
  }

  @Test
  public void canManageFlight(){
    flightManager.manageFlight(eb419);
    assertEquals(1, flightManager.getFlightCount());
  }

  @Test
  public void canGetBaggageWeightReserved(){
    eb419.bookPassenger(dave);
    assertEquals(2500, flightManager.getFlightBaggageWeightReserved(eb419), 0.1);
  }

  @Test
  public void canGetBaggageWeightBooked(){
    eb419.bookPassenger(dave);
    eb419.bookPassenger(clarissa);
    assertEquals(37.5, flightManager.getFlightBaggageWeightBooked(eb419), 0.1);
  }

  @Test
  public void canGetBaggageWeightRemaining(){
    eb419.bookPassenger(dave);
    eb419.bookPassenger(clarissa);
    assertEquals(2462.5, flightManager.getBaggageWeightRemaining(eb419), 0.1);
  }

  @Test
  public void canGetAllocatedSeats(){
    eb419.bookPassenger(dave);
    eb419.bookPassenger(clarissa);
    eb419.bookPassenger(hammy);
    testAllocatedSeats.add(dave.getSeatNumber());
    testAllocatedSeats.add(clarissa.getSeatNumber());
    testAllocatedSeats.add(hammy.getSeatNumber());
    assertEquals(testAllocatedSeats, flightManager.getFlightAllocatedSeats(eb419));
  }

  @Test
  public void canSortAllocatedSeats(){
    eb420.bookPassenger(dave);
    eb420.bookPassenger(clarissa);
    eb420.bookPassenger(hammy);
    testAllocatedSeats.add(1);
    testAllocatedSeats.add(2);
    testAllocatedSeats.add(3);
    assertEquals(testAllocatedSeats, flightManager.getSortedAllocatedSeats(eb420));
//    assertEquals(testAllocatedSeats, flightManager.getSortedAllocatedSeats(eb420));
  }

  @Test
  public void canFindPassengerBySeatNumber(){
    eb420.bookPassenger(dave);
    eb420.bookPassenger(clarissa);
    eb420.bookPassenger(hammy);
    assertEquals(dave, eb420.getPassengerBySeatNumber(dave.getSeatNumber()));

  }

}
