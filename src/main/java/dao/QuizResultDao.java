package dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Quiz;
import domain.QuizResult;


public class QuizResultDao {
	
	
	
	
	public QuizResultDao() {
		
	}

	public void addResult(Quiz quiz, int grade) {
		QuizResult quizResult = new QuizResult(quiz.getQuizID(), quiz.getStartTime(), quiz.getQuizType(), quiz.getUserID(), grade, quiz.getUserName());
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
	
	public List<QuizResult> getAllQuiz() {
		List<QuizResult> result = new LinkedList<>();
		Session session = HibernateConfigUtil.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from QuizResult";
			Query query = session.createQuery(hql);
			result = ((org.hibernate.query.Query) query).list();
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
