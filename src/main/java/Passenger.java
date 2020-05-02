public class Passenger {
  private String name;
  private int numBags;
  private Flight flight;
  private int seatNumber;

  public Passenger(String name, int numBags) {
  this.name = name;
  this.numBags = numBags;
  }

  public String getName() {
    return this.name;
  }

  public int getBagsCount() {
    return this.numBags;
  }

  public Flight getFlight() {
    return this.flight;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public int getSeatNumber(){
    return this.seatNumber;
  }

  public void bookFlight(Flight flight) {
    this.flight = flight;
  }
}
