package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
		if (!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String updateDates(Date checkin, Date checkout) throws DomainException {
		Date now = new Date();

		if (checkout.before(now) || checkin.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates.");
		} else if (!checkout.after(checkin)) {
			throw new DomainException(" Check-out date must be after check-in date.");
		} else {
			this.checkin = checkin;
			this.checkout = checkout;
			return null;
		}
	}

	@Override
	public String toString() {
		return "Reservation: Room " + roomNumber + ", check-in : " + sdf.format(checkin) + ", check-out: "
				+ sdf.format(checkout) + " , " + duration() + " nights.";
	}

}
