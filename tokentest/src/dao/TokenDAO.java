package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class TokenDAO extends OpenAndCloseDAO{
	private PreparedStatement statement = null;
	
	public boolean registrationToken(String token, String studentID){
		boolean isSuccess = false;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 1);
		Timestamp validTime = new Timestamp(cal.getTimeInMillis());
		
		try {
			statement = connect.prepareStatement("INSERT INTO token_table VALUES(?,?,?,0)");
			
			statement.setString(1, token);
			statement.setString(2, studentID);
			statement.setTimestamp(3, validTime);
			
			if(statement.executeUpdate() != 0){
				isSuccess = true;
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		return isSuccess;
	}
	
	public String existToken(String token){
		
		ResultSet result = null;
		String studentID = "";
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		
		try{
			
			
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		
		return studentID;
	}

}
