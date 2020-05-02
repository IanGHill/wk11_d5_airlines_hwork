public enum PlaneType {
  BOEING747(200, 5000),
  AIRBUS300( 150, 2000),
  SMALLPLANE100(3, 200);

  private final int capacity;
  private final int totalWeight;

  PlaneType(int capacity, int totalWeight) {

    this.capacity = capacity;
    this.totalWeight = totalWeight;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getTotalWeight(){
    return totalWeight;
  }
}
