package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class TokenDAO extends OpenAndCloseDAO{
	private PreparedStatement statement = null;

	public boolean registrationToken(String token, String studentID){
		open();
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
			e.printStackTrace();
		}finally {
			close();
		}

		return isSuccess;
	}

	public String existToken(String token){
		open();

		ResultSet result = null;
		String studentID = "";
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());

		try{

			statement = connect.prepareStatement("SELECT StudentId FROM token_table WHERE Token = ? AND ValidTime > ? AND Used = 0");
			statement.setString(1, token);
			statement.setTimestamp(2, nowTime);

			result = statement.executeQuery();

			if(result.next()){
				studentID = result.getString("StudentId");
			}

		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}


		return studentID;
	}

	public void usedToken(String token){
		open();
		try{
			statement = connect.prepareStatement("UPDATE token_table SET Used = 1 WHERE Token = ?");
			statement.setString(1, token);

			statement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			close();
		}
	}

}
