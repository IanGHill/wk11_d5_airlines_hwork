import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PassengerTest {

  Passenger dave;
  Flight eb419;
  Flight eb420;
  Date departureTime;

  @Before
  public void before(){
    dave = new Passenger("Dave", 2);
    departureTime = new Date(2020,05, 15, 18, 30  );
    eb419 = new Flight(PlaneType.BOEING747, "EB419", "GLA", "EDI", departureTime);
    eb420 = new Flight(PlaneType.SMALLPLANE100, "EB419", "GLA", "EDI", departureTime);

  }

  @Test
  public void hasName(){
    assertEquals("Dave", dave.getName());
  }

  @Test
  public void canGetNumBags(){
    assertEquals(2, dave.getBagsCount());
  }

  @Test
  public void canGetSeatNum(){
    eb420.bookPassenger(dave);
    System.out.println("Dave seat => " + dave.getSeatNumber());
    assertTrue(1 <= dave.getSeatNumber() && dave.getSeatNumber() <= 3);
  }
}
