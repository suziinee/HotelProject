package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.RoomDTO;
import model.util.DBUtil;


public class RoomDAO {  
    
	

    /* insert */
	
    public static boolean addRoom(RoomDTO room) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("insert into room values(?, ?, ?, ?)");
            pstmt.setString(1, room.getId());
            pstmt.setString(2, room.getRoomType());
            pstmt.setInt(3, room.getPrice()); 
            pstmt.setString(4, room.getBedSize());
            int result = pstmt.executeUpdate();
            if(result == 1) {
                return true;
            }
        }finally {
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    
    
    /* delete */
    
    public static boolean deleteRoom(String roomId) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("delete from room where room_id=?");
            pstmt.setString(1, roomId);
            int result = pstmt.executeUpdate();
            if(result == 1){
                return true;
            }
        }finally{
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    
    
    /* update (Price) */
    
    public static boolean updateRoomPrice(String roomId, int price) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update room set room_price=? where room_id=?");
            pstmt.setString(1, roomId);
            pstmt.setInt(2, price);
            int result = pstmt.executeUpdate();
            if(result == 1){
                return true;
            }
        }finally{
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    
    
    /* Update (BedSize) */
    
    public static boolean updateRoomBedSize(String roomId, String bedSize) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("update room set room_bedSize=? where room_id=?");
            pstmt.setString(1, roomId);
            pstmt.setString(2, bedSize);
            int result = pstmt.executeUpdate();
            if(result == 1){
                return true;
            }
        }finally{
            DBUtil.close(con, pstmt);
        }
        return false;
    }
    
    
    /* room id로 room 정보 반환 */
    
    public static RoomDTO getRoom(String roomId) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        RoomDTO room = null;
        try{
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from room where room_id=?");
            pstmt.setString(1, roomId);
            rset = pstmt.executeQuery();
            if(rset.next()){
                room = new RoomDTO(rset.getString(1), rset.getString(2), rset.getInt(3) ,rset.getString(4));
            }
        }finally{
            DBUtil.close(con, pstmt, rset);
        }
        return room;
    }
    
    
    /* 모든 room 정보 반환 */
    
    public static ArrayList<RoomDTO> getAllRooms() throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<RoomDTO> list = null;
        try{
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement("select * from room");
            rset = pstmt.executeQuery();
            list = new ArrayList<RoomDTO>();
            while(rset.next()){
                list.add(new RoomDTO(rset.getString(1), rset.getString(2), rset.getInt(3) ,rset.getString(4)));
            }
        }finally{
            DBUtil.close(con, pstmt, rset);
        }
        return list;
    }
 
    
    
}