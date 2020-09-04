package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserDao {
	private final static String LOGIN = "select pass_word from userTable where userName=?";
	private final static String SIGNUP = "insert into userTable (userName, pass_word, userType) values (?, ?, ?)";
	private final static String GET_USER_ID = "select userID from userTable where userName = ?";
	Connection connection = DBConnection.getConnection();
	
	public int signupUser(User user) {
		PreparedStatement preparedStatement = null;
        try {
        	preparedStatement = connection.prepareStatement(SIGNUP);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getUserType());
			int row = preparedStatement.executeUpdate();
            return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        // password doesn't match or user does not exist
        return 0;
	}
	
	public int loginUser(User user) throws SQLException {
		PreparedStatement preparedStatement = null;
        try {
        	preparedStatement = connection.prepareStatement(LOGIN);
			preparedStatement.setString(1, user.getUserName());
			boolean getResult = preparedStatement.execute();
            if (getResult) {
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();
                String password = resultSet.getString(1);
                resultSet.close();
                if (password.equals(user.getPassword())) {
                	return 1;
                }
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // password doesn't match or user does not exist
        return 0;
	}
	
	public int getUserID(User user) throws SQLException {
		int userID = 0;
		PreparedStatement preparedStatement = null;
		 try {
       	preparedStatement = connection.prepareStatement(GET_USER_ID);
			preparedStatement.setString(1, user.getUserName());
			boolean result = preparedStatement.execute();
			if (result) {
				ResultSet resultSet = preparedStatement.getResultSet();
				resultSet.next();
				userID = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        return userID;
	}
}
