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
	
	@Column(name = "quizCategory")
	private int userID;
	
	@Column(name = "grade")
	private int grade;

	
	
	
	public QuizResult() {

	}

	public QuizResult(int quizID, String timeTaken, String quizCategory, int userID, int grade) {
		super();
		this.quizID = quizID;
		this.timeTaken = timeTaken;
		this.quizCategory = quizCategory;
		this.userID = userID;
		this.grade = grade;
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

	@Override
	public String toString() {
		return "QuizResult [quizID=" + quizID + ", timeTaken=" + timeTaken + ", quizCategory=" + quizCategory
				+ ", userID=" + userID + ", grade=" + grade + "]";
	}

}
