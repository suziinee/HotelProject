package model.dto;

public class RoomDTO {

	private String id;
	private String roomType; //Single or Double or Deluxe
	private int price;
	private String bedSize; //small or medium or large
	
	public RoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomDTO(String id, String roomType, int price, String bedSize) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.price = price;
		this.bedSize = bedSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBedSize() {
		return bedSize;
	}

	public void setBedSize(String bedSize) {
		this.bedSize = bedSize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomDTO [id=");
		builder.append(id);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", price=");
		builder.append(price);
		builder.append(", bedSize=");
		builder.append(bedSize);
		builder.append("]");
		return builder.toString();
	}
	
	

}
