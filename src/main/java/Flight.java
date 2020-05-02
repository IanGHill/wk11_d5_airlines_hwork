import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Flight {
  private PlaneType planeType;
  private String flightNum;
  private String departure;
  private String destination;
  private Date departureTime;
  private ArrayList<Passenger> bookedPassengers;
  private Integer assignedSeat;
  private Integer allocatedSeat;
  private Random randomNum;
  private Boolean freeSeat;
  private ArrayList<Integer> allocatedSeats;
  private ArrayList<Integer> allocatedSeatsAscending;

  public Flight(PlaneType planeType, String flightNum, String departure, String destination, Date departureTime) {
    this.planeType = planeType;
    this.flightNum = flightNum;
    this.departure = departure;
    this.destination = destination;
    this.departureTime = departureTime;
    this.bookedPassengers = new ArrayList<Passenger>();
    this.allocatedSeats = new ArrayList<Integer>();
    this.randomNum = new Random();
  }

  public PlaneType getPlaneType() {
    return this.planeType;
  }

  public int getPlaneCapacity() {
    return this.planeType.getCapacity();
  }

  public int getPlaneTotalWeight() {
    return this.planeType.getTotalWeight();
  }

  public String getFlightNum() {
    return this.flightNum;
  }

  public String getDeparture() {
    return this.departure;
  }

  public String getDestination() {
    return this.destination;
  }

  public Date getDepartureTime() {
    return this.departureTime;
  }

  public int getBookedPassengerCount() {
    return bookedPassengers.size();
  }

  public int getAvailableSeatCount() {
    return (getPlaneCapacity() - getBookedPassengerCount());
  }

  public void bookPassenger(Passenger passenger) {
    if (getAvailableSeatCount() > 0){
      bookedPassengers.add(passenger);
      passenger.bookFlight(this);;
      assignedSeat = allocateSeat(1, getPlaneCapacity());
      passenger.setSeatNumber(assignedSeat);
      this.allocatedSeats.add(assignedSeat);
    }
  }

  private int allocateSeat(int minimum, int maximum) {
    int range = maximum - minimum + 1;
    freeSeat = false;
    while (freeSeat == false){
      allocatedSeat = (randomNum.nextInt(range) + minimum);
      if (!allocatedSeats.contains(allocatedSeat)){
        freeSeat = true;
      }
    }
    return allocatedSeat;
  }


  public int getBaggageWeightReserved() {
    return (getPlaneTotalWeight() / 2);
  }

  public ArrayList<Passenger> getBookedPassengers() {
    return this.bookedPassengers;
  }

  public ArrayList<Integer> getAllocatedSeats() {
    return this.allocatedSeats;
  }

  public ArrayList<Integer> getAllocatedSeatsAscending() {
    int temp;
    for (int i = 0; i < allocatedSeats.size() - 1; i++) {

      for (int j = 1; j < allocatedSeats.size() - i; j++) {
        if (allocatedSeats.get(j - 1) > allocatedSeats.get(j)) {
          temp = allocatedSeats.get(j-1);
          allocatedSeats.set((j-1), allocatedSeats.get(j));
          allocatedSeats.set(j, temp);
        }
      }
    }
    return allocatedSeats;

  }

  public Passenger getPassengerBySeatNumber(int seatNumber) {

  }

}
