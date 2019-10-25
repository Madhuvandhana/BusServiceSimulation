package madhu_bussimulation;

import java.util.LinkedList;


public class BoarderArrivalEvent extends EventsManager {
	private LinkedList<PersonEvent> personEvent = new LinkedList<PersonEvent>(); 
	Double drive_time = 5 * 60.0; 
	private Integer busNo;
	Double boarding_time = 2.0;
	public Integer getBusNo() { 
		return busNo;
	}
	
	public BoarderArrivalEvent(LinkedList<PersonEvent> personEventList, Integer busNo, Integer interval) {
	this.busNo = busNo; 
	this.personEvent.addAll(personEventList);
	// Keep distance same among the buses and initialize the bus count 
	
	for (int count = 0; count < (busNo - 1) * interval; count++) {
	this.personEvent.addLast(this.personEvent.removeFirst()); 

	}
	}
	
	public String getJourneyDetailsOfBus() {
	return "Bus no." + busNo + personEvent.getFirst().getStopPassengerDetails();
	}
	
	public void generateEvent() {
		PersonEvent event = personEvent.getFirst(); 	
		
		// Check whether bus is already at stop
		if (!event.isThisBusHere(this)) { 
			event.busArrived(this);
		}
		//Referenced from the lecture - Event Generation
		
		//boarding event
		if (event.passengersAvailable()) {
			event.passengerBoardBus();
			setTimeOfEvent(Main.clock + boarding_time);
		} else {
			
			//arrival event
		BoarderArrivalEvent bus = event.busDeparting(this);
		if (bus == null) {
			//generate the arrival at the next bus stop
		setTimeOfEvent(Main.clock + drive_time);
		personEvent.removeFirst();
		personEvent.addLast(event);

		}else {
			//generate the boarder event (at clock)
			setTimeOfEvent(bus.getTimeOfEvent());
			}
			}
		eventPerformed();
		addEvents(this);
	}
	
	//Referenced from https://github.com/larryfenn/bus-network 

	


}
