package madhu_bussimulation;

import java.util.LinkedList;
import java.util.Random;



public class PersonEvent extends EventsManager{
	Double meanArrivalRate = 5.0;
	Double meanInterArrivalRate = (double) (1/meanArrivalRate)*60;
	private LinkedList<Person> passengers = new LinkedList<Person>();
	private Integer busStopNo;
	private LinkedList<BoarderArrivalEvent> buses = new LinkedList<BoarderArrivalEvent>();
	private int busNum = 0;
	private Double timeOfEvent = 0.0;
	

	public PersonEvent(Integer busStopNo) {
	this.busStopNo = busStopNo;
	}
	
	public void generateEvent() {
		double  x = RandomNumberGenerator.random();
		System.out.println("random numbers"+x);
		Double arrivalTime = Main.clock + meanInterArrivalRate * x;
	// person Event
	passengers.add(new Person(arrivalTime, 2.0)); // Adding New passenger 
	eventPerformed();
	setTimeOfEvent(arrivalTime);
	addEvents(this);
	}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	
	public void passengerBoardBus() { passengers.removeFirst();
	}
	public boolean passengersAvailable() { 
		if (passengers.size() > 0) {
			return true; 
	}
	else
		return false;
		}

	public Integer getPassengersCount(){ 
		return passengers.size();
	}
	public Integer getBusStopNumber(){ 
		return busStopNo;
	}
	public void busArrived(BoarderArrivalEvent bus) {
		buses.add(bus); 
	} 
	public BoarderArrivalEvent busDeparting(BoarderArrivalEvent bus) {
		if (buses.peekFirst().equals(bus)){ 
			buses.remove(bus); 
			return null;
	} 
	else 
		return buses.get(buses.indexOf(bus) -1); }
	
	public boolean isThisBusHere(BoarderArrivalEvent bus) { 
		return buses.contains(bus); 
	}
	
	public StringBuilder getJourneyDetails() {

	StringBuilder sb = new StringBuilder();
	buses.forEach((B)->{
	busNum = B.getBusNo();
	}
	);
	sb.append("Bus no."+busNum+getStopPassengerDetails()); 
	return sb;
	}
	
	public String getStopPassengerDetails() {
		return "|Bus Stop No." + busStopNo+"|" + "(No. of passengers " + passengers.size()+")";
	}
	
	//Referenced from https://github.com/larryfenn/bus-network 
}
