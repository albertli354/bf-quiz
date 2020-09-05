package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Quiz;
import domain.QuizResult;


public class QuizResultDao {
	
	
	
	
	public QuizResultDao() {
		
	}

	public void addResult(Quiz quiz, int grade) {
		QuizResult quizResult = new QuizResult(quiz.getQuizID(), quiz.getStartTime(), quiz.getQuizType(), quiz.getUserID(), grade);
		Session session = HibernateConfigUtil.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.persist(quizResult);
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
