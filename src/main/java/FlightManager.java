import java.util.ArrayList;

public class FlightManager {

  private ArrayList<Flight> flightList;
  private float baggageWeightBooked;

  public FlightManager() {
    flightList = new ArrayList<Flight>();
  }

  public void manageFlight(Flight flight) {
    this.flightList.add(flight);
  }

  public int getFlightCount() {
    return this.flightList.size();
  }

  public float getFlightBaggageWeightReserved(Flight flight) {
    return flight.getBaggageWeightReserved();
  }

  private float getWeightofBagPerPerson(Flight flight) {
    return (getFlightBaggageWeightReserved(flight) / flight.getPlaneCapacity());
  }

  public float getFlightBaggageWeightBooked(Flight flight) {
    baggageWeightBooked = 0;
    for (Passenger passenger : flight.getBookedPassengers()){
      baggageWeightBooked += (getWeightofBagPerPerson(flight) * passenger.getBagsCount());
    }
    return baggageWeightBooked;
  }

  public double getBaggageWeightRemaining(Flight flight) {
    return (getFlightBaggageWeightReserved(flight) - getFlightBaggageWeightBooked(flight));
  }

  public ArrayList<Integer> getFlightAllocatedSeats(Flight flight) {
    return flight.getAllocatedSeats();
  }

  public ArrayList<Integer> getSortedAllocatedSeats(Flight flight) {
    return flight.getAllocatedSeatsAscending();
  }
}
