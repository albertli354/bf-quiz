package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import domain.Quiz;
import domain.User;

public class QuizDao {
	private final static String UPDATE_ANSWER = "update quiz set userChoiceID = ?, isCorrect = ?\n" + 
			"where quizID = ? and choiceID = ?;";
	private final static String GET_QUESTION = "select questionID, questionContent from question where quizType = ? order by rand() limit 5";
	private final static String GET_CHOICE = "select choice_id, choice from choice where questionID = ?";
	private final static String MAPPING_ID = "select choice_id from choice where questionID = ?";
	private final static String NEW_QUIZ = "insert into quiz (userID, questionID, quizType, startTime, choiceID, quizID) values (?,?,?,?,?,?)";
	private final static String MAX_ID = "select max(quizID) from quiz";
	private final static String CORRECT_ANSWER = "select choice_id from choice where questionID = ? and isCorrect = 1";
	
	Connection connection = DBConnection.getConnection();
	
	public Map<Integer, String> getQuestionMap(Quiz quiz) {
		Map<Integer, String> questionMap = new HashMap<>();
		PreparedStatement preparedStatement = null;
		 try {
        	preparedStatement = connection.prepareStatement(GET_QUESTION);
			preparedStatement.setString(1, quiz.getQuizType());
			boolean result = preparedStatement.execute();
			if (result) {
				ResultSet resultSet = preparedStatement.getResultSet();
				while (resultSet.next()) {
					int questionID = resultSet.getInt(1);
					String questionContent = resultSet.getString(2);
					questionMap.put(questionID, questionContent);
				}
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return questionMap;
	}
	
	@SuppressWarnings("null")
	public Map<Integer, String> getChoiceMap(Map<Integer, String> questionMap) {
		Map<Integer, String> choiceMap = new HashMap<>();
		for (int questionID : questionMap.keySet()) {
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = null;
				preparedStatement = connection.prepareStatement(GET_CHOICE);
				preparedStatement.setInt(1, questionID);
				boolean result = preparedStatement.execute();
				if (result) {
					ResultSet resultSet = preparedStatement.getResultSet();
					while (resultSet.next()) {
						int choiceID = resultSet.getInt(1);
						String choiceContent = resultSet.getString(2);
						choiceMap.put(choiceID, choiceContent);
					}
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return choiceMap;
	}
	
	public Map<Integer, LinkedList<Integer>> getMapping(Map<Integer, String> questionMap) {
		Map<Integer, LinkedList<Integer>> resultMap = new HashMap<>();
		for (int questionID : questionMap.keySet()) {
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = null;
				preparedStatement = connection.prepareStatement(MAPPING_ID);
				preparedStatement.setInt(1, questionID);
				boolean result = preparedStatement.execute();
				if (result) {
					ResultSet resultSet = preparedStatement.getResultSet();
					while (resultSet.next()) {
						int choiceID = resultSet.getInt(1);
						if (!resultMap.containsKey(questionID)) {
							resultMap.put(questionID, new LinkedList<>());
						}
						resultMap.get(questionID).add(choiceID);
					}
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
		
	}
	
	public int createNewQuiz(Map<Integer, LinkedList<Integer>> questionChoice, int userID, String quizType) {
		int maxID = getMaxID();
		
//		"insert into quiz (userID, questionID, quizType, startTime, choiceID) values (?,?,?,?,?)
		java.sql.Timestamp cuurentTime = new java.sql.Timestamp(new java.util.Date().getTime());
		for (int questionID : questionChoice.keySet()) {
			PreparedStatement preparedStatement = null;
			LinkedList<Integer> ids = questionChoice.get(questionID);
			for (int id : ids) {
				try {
					preparedStatement = null;
					preparedStatement = connection.prepareStatement(NEW_QUIZ);
					preparedStatement.setInt(1, userID);
					preparedStatement.setInt(2, questionID);
					preparedStatement.setString(3, quizType);
					preparedStatement.setTimestamp(4, cuurentTime);
					preparedStatement.setInt(5, id);
					preparedStatement.setInt(6, maxID);
					boolean result = preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return maxID;
	}
	
	private int getMaxID() {
		java.sql.Statement statement = null;
		try {
			
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(MAX_ID);
			while (result.next()) {
				int max = result.getInt(1);
				return max + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public void updateUserChoice(Quiz quiz, int quizID) {
//		UPDATE_ANSWER = "update quiz set userChoiceID = ?, isCorrect = ?\n" + 
//				"where quizID = ? and choiceID = ?;";
		 Map<Integer, Integer> userChoice = quiz.getUserChoice();
		 Map<Integer, LinkedList<Integer>> questionChoice = quiz.getQuestionChoice();
		 Map<Integer, String> choiceMap = quiz.getChoiceMap();
		 for (int questionID : questionChoice.keySet()) {
			 int correctChoiceID = getCorrectChoiceID(questionID);
			 int isCorrect = 0;
			 int userChoiceID = userChoice.get(questionID);
			 if (userChoiceID == correctChoiceID) {
				 isCorrect = 1;
			 }
			 for (int choiceID : questionChoice.get(questionID)) {
				 PreparedStatement preparedStatement = null;
				 try {
					 preparedStatement = connection.prepareStatement(UPDATE_ANSWER);
					 preparedStatement.setInt(1, userChoiceID);
					 preparedStatement.setInt(2, isCorrect);
					 preparedStatement.setInt(3, quizID);
					 preparedStatement.setInt(4, choiceID);
					 preparedStatement.execute();
				 } catch (SQLException e) {
						e.printStackTrace();
				} 
			 }
		 }
		 
	}
	
	private int getCorrectChoiceID(int questionID) {
		int correctChoiceID = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(CORRECT_ANSWER);
			preparedStatement.setInt(1, questionID);
			boolean result = preparedStatement.execute();
			if (result) {
				ResultSet resultSet = preparedStatement.getResultSet();
				while (resultSet.next()) {
					correctChoiceID = resultSet.getInt(1);
				}
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return correctChoiceID;
	}
	
	public Map<Integer, Integer> getCorrectAnswers(Map<Integer, String> questionMap) {
		Map<Integer, Integer> resultMap = new HashMap<>();
		PreparedStatement preparedStatement = null;
		int correctChoiceID = -1;
		for (int questionID : questionMap.keySet()) {
			try {
				preparedStatement = connection.prepareStatement(CORRECT_ANSWER);
				preparedStatement.setInt(1, questionID);
				boolean result = preparedStatement.execute();
				if (result) {
					ResultSet resultSet = preparedStatement.getResultSet();
					while (resultSet.next()) {
						correctChoiceID = resultSet.getInt(1);
					}
					resultMap.put(questionID, correctChoiceID);
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultMap;
	}
	
}
