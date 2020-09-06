package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quizResult")
public class QuizResult {

	@Id
	@Column(name = "quizID")
	private int quizID;
	
	@Column(name = "timeTaken")
	private String timeTaken;
	
	@Column(name = "quizCategory")
	private String quizCategory;
	
	@Column(name = "userID")
	private int userID;
	
	@Column(name = "grade")
	private int grade;

	@Column(name = "userName")
	private String userName;
	
	
	
	public QuizResult() {

	}

	public QuizResult(int quizID, String timeTaken, String quizCategory, int userID, int grade, String userName) {
		this.quizID = quizID;
		this.timeTaken = timeTaken;
		this.quizCategory = quizCategory;
		this.userID = userID;
		this.grade = grade;
		this.userName = userName;
	}



	public int getQuizID() {
		return quizID;
	}

	public String getTimeTaken() {
		return timeTaken;
	}

	public String getQuizCategory() {
		return quizCategory;
	}

	public int getUserID() {
		return userID;
	}

	public int getGrade() {
		return grade;
	}
	
	
	public String getUserName() {
		return userName;
	}


	@Override
	public String toString() {
		return "QuizResult [quizID=" + quizID + ", timeTaken=" + timeTaken + ", quizCategory=" + quizCategory
				+ ", userID=" + userID + ", grade=" + grade + "]";
	}

}
