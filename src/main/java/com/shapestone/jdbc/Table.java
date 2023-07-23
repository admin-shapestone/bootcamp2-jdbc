package com.shapestone.jdbc;

import java.util.ArrayList;

import java.util.List;
import java.util.Date;


public class Table {

	public static String createTable1() {
		StringBuffer sb = new StringBuffer("create table bookingsquerry (");
		sb.append("BookingId INT PRIMARY KEY ,");
		sb.append("PassengerId INT NOT NULL, ");
		sb.append("OriginFrom VARCHAR(30) NOT NULL, ");
		sb.append("DestinationTo VARCHAR(30) NOT NULL, ");
		sb.append("Distance INT NOT NULL, ");
		sb.append("ModeOfTransport VARCHAR(10) NOT NULL, ");
		sb.append("PricePerKm INT NOT NULL,");
		sb.append("DateOfJourney VARCHAR(20) NOT NULL )");
		
		return sb.toString();
	}

	public static ArrayList<String> prepareInsertQueries(List<Bookings_Pojo> bookings) {
		ArrayList<String> queryList = new ArrayList<String>();
		for (int i = 0; i < bookings.size(); i++) {
			StringBuffer sb = new StringBuffer("INSERT INTO bookings (");
			sb.append("bookingId, passengerId, originFrom, destinationTo, distance, modeOfTransport, pricePerKm,dateOfJourney) ");
			sb.append("VALUES (");
			sb.append(bookings.get(i).getBookingId() + ", ");
			sb.append(bookings.get(i).getPassengerId() + ", ");
			sb.append("'" + bookings.get(i).getOriginFrom() + "' , ");
			sb.append("'" + bookings.get(i).getDestinationTo() + "' , ");
			sb.append(bookings.get(i).getDistance() + ", ");
			sb.append("'" + bookings.get(i).getModeOfTransport() + "' , ");
			sb.append(bookings.get(i).getPricePerKm() + ",");
			sb.append("'" + bookings.get(i).getDateOfJourney() + "'  )");
			queryList.add(sb.toString());
		}
		return queryList;

	}
}
