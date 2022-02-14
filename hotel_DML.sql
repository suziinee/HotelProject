-- customer insert[고객 정보 저장]
-- 고객 이름, 아이디, 비밀번호
insert into customer values('윤지원', 'hotel1', '1111');
insert into customer values('장수진', 'hotel2', '2222');
insert into customer values('진미란', 'hotel3', '3333');
insert into customer values('전정국', 'hotel4', '4444');
insert into customer values('김태형', 'hotel5', '5555');
insert into customer values('박지민', 'hotel6', '6666');


-- room insert[룸 정보 저장]
-- 룸아이디, 룸타입, 가격, 침대사이즈
insert into room values('room1', 'Single Room', 100000, 'Small');
insert into room values('room2', 'Double Room', 150000, 'Medium');
insert into room values('room3', 'Deluxe Room', 200000, 'Large');


-- booking insert[예약 정보 저장]
-- 예약넘버, 체크인날짜, 체크아웃날짜, 인원정보(persontype)<성인, 어린이>, 방 정보)
insert into booking values('2022/02/01', '2022/02/05', 'Single Room', 1, 0, booking_reservationNum_seq.nextval);
insert into booking values('2022/02/14', '2022/02/16', 'Double Room', 0, 1, booking_reservationNum_seq.nextval);
insert into booking values('2022/02/18', '2022/02/19', 'Deluxe Room', 2, 1, booking_reservationNum_seq.nextval);
insert into booking values('2022/02/06', '2022/02/07', 'Deluxe Room', 2, 2, booking_reservationNum_seq.nextval);
insert into booking values('2022/02/20', '2022/02/24', 'Double Room', 2, 0, booking_reservationNum_seq.nextval);
insert into booking values('2022/02/26', '2022/02/28', 'Single Room', 1, 0, booking_reservationNum_seq.nextval);

commit;

