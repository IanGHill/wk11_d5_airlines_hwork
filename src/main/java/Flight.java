import java.util.ArrayList;
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

  public ArrayList<Passenger> getPassengersBySeatsAscending() {
    Passenger tempPassenger;

    for (int i = 0; i < bookedPassengers.size() - 1; i++) {
      for (int j = 1; j < bookedPassengers.size() - i; j++) {
        if (bookedPassengers.get(j - 1).getSeatNumber() > bookedPassengers.get(j).getSeatNumber()) {
          tempPassenger = bookedPassengers.get(j-1);
          bookedPassengers.set((j-1), bookedPassengers.get(j));
          bookedPassengers.set(j, tempPassenger);
        }
      }
    }
    return bookedPassengers;

  }

  public Passenger getPassengerBySeatNumber(int seatNumber) {
    this.bookedPassengers = getPassengersBySeatsAscending();

    Passenger foundPassenger = binaryPassengerSearch(bookedPassengers, seatNumber);
    return foundPassenger;
  }

  private Passenger binaryPassengerSearch(ArrayList<Passenger> passengers, int seatNumber) {
      if (passengers.size() == 0){
        return null;
      }

      int midIndex = 0;
      if (passengers.size() >1) {
        midIndex = (int) Math.ceil((double) passengers.size() / 2);
      }

      Passenger midPoint = passengers.get(midIndex);

      if (seatNumber == midPoint.getSeatNumber()){
        return midPoint;
      }

      ArrayList<Passenger> newSearchArea;

      if (seatNumber < midPoint.getSeatNumber()){
        newSearchArea = new ArrayList<Passenger>(passengers.subList(0, midIndex));
      } else {
        newSearchArea = new ArrayList<Passenger>(passengers.subList(midIndex + 1, passengers.size()));
      }
    return binaryPassengerSearch(newSearchArea, seatNumber);
  }



}
