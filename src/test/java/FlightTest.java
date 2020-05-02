import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightTest {

  private Flight eb419;
  private Flight eb420;
  private Passenger dave;
  private Passenger clarissa;
  private Passenger hammy;
  private Date departureTime;
  @Before
  public void before(){
    departureTime = new Date(120,11, 15, 18, 30  );
    eb419 = new Flight(PlaneType.BOEING747, "EB419", "GLA", "EDI", departureTime);
    eb420 = new Flight(PlaneType.SMALLPLANE100, "EB419", "GLA", "EDI", departureTime);
    dave = new Passenger("Dave", 2);
    clarissa = new Passenger("Clarissa", 1);
    hammy = new Passenger("Hammy", 1);
  }

  @Test
  public void canGetPlaneType(){
    assertEquals(PlaneType.BOEING747, eb419.getPlaneType());
  }

  @Test
  public void canGetPlaneCapacity(){
    assertEquals(200, eb419.getPlaneCapacity());
  }

  @Test
  public void canGetPlaneTotalWeight(){
    assertEquals(5000, eb419.getPlaneTotalWeight());
  }

  @Test
  public void canGetFlightNum() {
    assertEquals("EB419", eb419.getFlightNum());
  }
@Test
  public void canGetDeparture() {
    assertEquals("GLA", eb419.getDeparture());
  }
  @Test
  public void canGetDestination() {
    assertEquals("EDI", eb419.getDestination());
  }
  @Test
  public void canGetDepartureTime() {
    System.out.println(this.departureTime);
    assertEquals(this.departureTime, eb419.getDepartureTime());
  }
  @Test
  public void canGetAvailableSeats(){
    eb419.bookPassenger(dave);
    assertEquals(199, eb419.getAvailableSeatCount());
  }
  @Test
  public void canGetBookedPassengerCount(){
    eb419.bookPassenger(dave);
    assertEquals(1, eb419.getBookedPassengerCount());
  }

  @Test
  public void canBookPassenger(){
    eb419.bookPassenger(dave);
    assertEquals(dave, eb419.getBookedPassengers().get(0));
    assertEquals(eb419, dave.getFlight());
  }

  @Test
  public void passengersAssignedUniqueSeats(){
    eb420.bookPassenger(dave);
    eb420.bookPassenger(clarissa);
    eb420.bookPassenger(hammy);
    System.out.println("Clarissa seat => " + clarissa.getSeatNumber());
    System.out.println("Dave seat => " + dave.getSeatNumber());
    System.out.println("Hammy seat => " + hammy.getSeatNumber());
    assertTrue((dave.getSeatNumber() != clarissa.getSeatNumber()) &&
                                    (dave.getSeatNumber() != hammy.getSeatNumber()) &&
                                    (hammy.getSeatNumber() != clarissa.getSeatNumber())
      );

  }

}
