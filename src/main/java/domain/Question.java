package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questionID")
	private int questionID;
	
	@Column(name = "quizType")
	private String quizType;
	
	@Column(name = "questionContent")
	private String questionContent;

	public Question() {
		super();
	}

	public int getQuestionID() {
		return questionID;
	}

	public String getQuizType() {
		return quizType;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", quizType=" + quizType + ", questionContent=" + questionContent
				+ "]";
	}
	
	
}
