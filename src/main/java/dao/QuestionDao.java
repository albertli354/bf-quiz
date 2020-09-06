package dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Question;

public class QuestionDao {
	
	
	public List<Question> getAllQuestion() {
		List<Question> result = new LinkedList<>();
		Session session = HibernateConfigUtil.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = "from Question";
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
