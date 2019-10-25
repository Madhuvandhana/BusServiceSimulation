package madhu_bussimulation;

public abstract class EventsManager {
	private Double eventTime;
	public abstract void generateEvent(); 
	private Integer position = -1;
	public void eventPerformed() { 
		position = 0;
	}
	public Double getTimeOfEvent() {
	return eventTime; 
	}
	protected void setTimeOfEvent(Double eventTime) {
		this.eventTime = eventTime;
	}
	
	public Integer getPosition() { 
		return position;
	}
	public void addEvents(EventsManager event) { 
	
		Main.eventsTracker.forEach((E) -> {
			if (E.getTimeOfEvent() <=	event.getTimeOfEvent()) { 
				position++;
	}});
	Main.eventsTracker.add(event.getPosition(), event); }
}
