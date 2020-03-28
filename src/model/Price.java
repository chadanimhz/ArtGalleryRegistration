package model;

public class Price {
	
	private int id;
	private String ageGroup;
	private String rate;
	private int eventId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@Override
	public String toString() {
		return "Price [id=" + id + ", ageGroup=" + ageGroup + ", rate=" + rate + ", eventId=" + eventId + "]";
	}
	

}
