package model.dto;


public class BookingDTO {

	private String checkin; //"2022/07/03"
	private String checkout;
	private String roomType;
	private int adults; 
	private int kids;
	private int reservationNum; //두자리 자연수
	
	public BookingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingDTO(String checkin, String checkout, String roomType, int adults, int kids, int reservationNum) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.roomType = roomType;
		this.adults = adults;
		this.kids = kids;
		this.reservationNum = reservationNum;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getKids() {
		return kids;
	}

	public void setKids(int kids) {
		this.kids = kids;
	}

	public int getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(int reservationNum) {
		this.reservationNum = reservationNum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookingDTO [checkin=");
		builder.append(checkin);
		builder.append(", checkout=");
		builder.append(checkout);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", adults=");
		builder.append(adults);
		builder.append(", kids=");
		builder.append(kids);
		builder.append(", reservationNum=");
		builder.append(reservationNum);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
