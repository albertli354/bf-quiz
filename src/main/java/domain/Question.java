package domain;

public class Question {
	private String question;
	private String questionChoice[];
	private int correctQuestionIndex;
	private int userChoiceIndex = -1;
	
	
	
	
	
	
	
	// getter and setter
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String[] getQuestionChoice() {
		return questionChoice;
	}
	
	public void setQuestionChoice(String[] questionChoice) {
		this.questionChoice = questionChoice;
	}
	
	public int getCorrectQuestionIndex() {
		return correctQuestionIndex;
	}
	
	public void setCorrectQuestionIndex(int correctQuestionIndex) {
		this.correctQuestionIndex = correctQuestionIndex;
	}
	
	public int getUserChoiceIndex() {
		return userChoiceIndex;
	}
	
	public void setUserChoiceIndex(int userChoiceIndex) {
		this.userChoiceIndex = userChoiceIndex;
	}

}
