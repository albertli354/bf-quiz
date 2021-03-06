package domain;


import java.util.LinkedList;
import java.util.Map;

public class Quiz {
	private String quizType;
	private String userName;
	private String startTime;
	private String endTime;
	private int userID;
	private int quizID;
	private Map<Integer, String> questionMap;
	private Map<Integer, String> choiceMap;
	private Map<Integer, LinkedList<Integer>> questionChoice; 
	private Map<Integer, Integer> correctAnswers;
	private int currentQuestionIndex;
	
	// id is question id, value is choice id
	private Map<Integer, Integer> userChoice;
	private int[] questionID;
	

	
	
	public Quiz() {

	}

	public Quiz(String quizType, String startTime, String endTime, int userID, int quizID, Map<Integer, String> questionMap,
			Map<Integer, String> choiceMap, Map<Integer, LinkedList<Integer>> questionChoice, int currentQuestionIndex,
			Map<Integer, Integer> userChoice, Map<Integer, Integer> correctAnswers, String userName) {
		this.quizType = quizType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.userID = userID;
		this.quizID = quizID;
		this.questionMap = questionMap;
		this.choiceMap = choiceMap;
		this.questionChoice = questionChoice;
		this.currentQuestionIndex = currentQuestionIndex;
		this.userChoice = userChoice;
		this.correctAnswers = correctAnswers;
		this.userName = userName;
		// initialize all choice to -1
		// 5 means 5 questions


		
		// initialize quesitonIndex
		questionID = new int[5];
		int i = 0;
		for (int id : questionMap.keySet()) {
			questionID[i++] = id;
		}
	}

	public Quiz(String quizType, int userID) {
		super();
		this.quizType = quizType;
		this.userID = userID;
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex;
	}

	public void setCurrentQuestionIndex(int currentQuestionIndex) {
		this.currentQuestionIndex = currentQuestionIndex;
	}

	public String getQuizType() {
		return quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}
	
	public void updateUserChoice(int questionID, int choiceID) {
		userChoice.put(questionID, choiceID);
	}
	
	public String getQuestionTitle(int index) {
		return questionMap.get(index);
	}
	
	public LinkedList<Integer> getChoices(int index) {
		return questionChoice.get(index);
	}
	
	public String getSingleChoice(int index) {
		return choiceMap.get(index);
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public int getQuestionID(int i) {
		return questionID[i];
	}

	public Map<Integer, Integer> getUserChoice() {
		return userChoice;
	}

	public Map<Integer, String> getQuestionMap() {
		return questionMap;
	}

	public Map<Integer, String> getChoiceMap() {
		return choiceMap;
	}

	public Map<Integer, LinkedList<Integer>> getQuestionChoice() {
		return questionChoice;
	}

	public Map<Integer, Integer> getCorrectAnswers() {
		return correctAnswers;
	}

	public int getUserID() {
		return userID;
	}

	public int getQuizID() {
		return quizID;
	}

	public String getUserName() {
		return userName;
	}
	
	
	
	
	
	
	
	
}
