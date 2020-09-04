package starter;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateConfigUtil;



public class ApplicationStarter {
public static void main(String[] args) {


		
		Session session = HibernateConfigUtil.openSession();
		Transaction transaction = null;
		String userInput = "albert";

		try {
			transaction = session.beginTransaction();
			String hql = "from User s where s.userName = :userInput";
			Query query = session.createQuery(hql);
			query.setParameter("userInput", userInput);
			List results = ((org.hibernate.query.Query) query).list();
			System.out.println(results.toString());
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
