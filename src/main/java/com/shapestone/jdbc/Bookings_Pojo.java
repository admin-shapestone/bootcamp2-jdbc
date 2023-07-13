package com.shapestone.jdbc;

public class Bookings_Pojo {
	private int bookingId;
	private int passengerId;
	private String originFrom;
	private String destinationTo;
	private long distance;
	private String modeOfTransport;
	private float pricePerKm;
	private String dateOfJourney ;
	public int getBookingId() {
		return bookingId;

	}
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getOriginFrom() {
		return originFrom;
	}
	public void setOriginFrom(String originFrom) {
		this.originFrom = originFrom;
	}
	public String getDestinationTo() {
		return destinationTo;
	}
	public void setDestinationTo(String destinationTo) {
		this.destinationTo = destinationTo;
	}
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public float getPricePerKm() {
		return pricePerKm;
	}
	public void setPricePerKm(float pricePerKm) {
		this.pricePerKm = pricePerKm;
	}
	public String getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(String dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	@Override
	public String toString() {
		return "Bookings_Pojo [bookingId=" + bookingId + ", passengerId=" + passengerId + ", originFrom=" + originFrom
				+ ", destinationTo=" + destinationTo + ", distance=" + distance + ", modeOfTransport=" + modeOfTransport
				+ ", pricePerKm=" + pricePerKm + ", dateOfJourney=" + dateOfJourney + "]";
	}




	}

