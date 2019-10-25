package madhu_bussimulation;

import java.util.LinkedList;

public class Main {
	public static LinkedList<EventsManager> eventsTracker = new LinkedList<EventsManager>(); 
	public static LinkedList<BoarderArrivalEvent> boarderArrivalEvent = new LinkedList<BoarderArrivalEvent>();
	private static LinkedList<PersonEvent> personEvent = new LinkedList<PersonEvent>(); 
	public static Double clock = 0.0; 
	public static void main(String[] args) {
		Integer noOfBuses = 5;
		Integer noOfBusStops = 15; 
		Double stop_time = 8 * 60 * 60.0; 
		generate_event(noOfBuses, noOfBusStops);
		long count = 0; 
		int hour =0;

		
		while (clock <= stop_time) { 
			EventsManager currentEvent = eventsTracker.removeFirst(); 
			clock = currentEvent.getTimeOfEvent(); 
			currentEvent.generateEvent();
			if (count<= clock) { 
				//System.out.println(getJourneyDetailsOfBus(hour)); 
				count += 60 * 60;
				hour++;
			}
			
		}
		  
	}
	private static void generate_event(Integer busCount, Integer stopCount) {
		for (int i = 1; i <= stopCount; i++) { 
			personEvent.add(new PersonEvent(i)); 
			personEvent.getLast().generateEvent(); // Generating first event for it i.e a passenger arrival 
			}
		
		for (int j = 1; j <= busCount; j++) { 
			boarderArrivalEvent.add(new BoarderArrivalEvent(personEvent, j, stopCount/busCount)); 
			boarderArrivalEvent.getLast().generateEvent();//Generating the first event for it 
			}
	}
	
	// use this function to generate and plot positions of bus as a function of time
	public static String getJourneyDetailsOfBus(int hour) {
		StringBuilder sb = new StringBuilder();
		sb.append("At hour "+hour+": ");
		boarderArrivalEvent.forEach((B) -> {sb.append(B.getJourneyDetailsOfBus() + "\t\t");});
		return sb.toString();
	}
	
	// use this function to check average waiting queue at each stop
	public static String getJourneyDetails(int hour) {
		StringBuilder sb = new StringBuilder();
		sb.append("At hour "+hour+": ");
		personEvent.forEach((B) -> {sb.append(B.getJourneyDetails()+") \t\t");}); 
		return sb.toString(); 
	}
	//Referenced from the lecture - main program pseudo-code

}
