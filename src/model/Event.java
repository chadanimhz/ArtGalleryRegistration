package model;

public class Event {
	
	private int id;
	private String name;
	private String date;
	private String venue;
	private double childRate;
	private double adultRate;
	private double agedRate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public double getChildRate() {
		return childRate;
	}
	public void setChildRate(double childRate) {
		this.childRate = childRate;
	}
	public double getAdultRate() {
		return adultRate;
	}
	public void setAdultRate(double adultRate) {
		this.adultRate = adultRate;
	}
	public double getAgedRate() {
		return agedRate;
	}
	public void setAgedRate(double agedRate) {
		this.agedRate = agedRate;
	}

	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", date=" + date + ", venue=" + venue + ", childRate=" + childRate
				+ ", adultRate=" + adultRate + ", agedRate=" + agedRate + "]";
	}

}
