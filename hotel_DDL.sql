DROP TABLE room cascade constraint; 
DROP TABLE customer cascade constraint;
DROP TABLE booking cascade constraint; 
DROP SEQUENCE booking_reservationNum_seq;

CREATE TABLE room(
	room_id varchar2(20),
	room_roomType varchar2(20),
	room_price number(20),
	room_bedSize varchar2(20)
);

CREATE TABLE customer(
	customer_name varchar2(20),
	customer_id varchar2(10),
	customer_pw varchar2(10)
);

CREATE SEQUENCE booking_reservationNum_seq;
CREATE TABLE booking(
	booking_checkin DATE,
	booking_checkout DATE,
	booking_roomType varchar2(20),
	booking_adults number(10),
	booking_kids number(10),
	booking_reservationNum NUMBER(5)
);

COMMIT;