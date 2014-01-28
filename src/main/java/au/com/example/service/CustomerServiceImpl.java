package au.com.example.service;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import au.com.example.entity.CustomerEntity;

@Singleton
public class CustomerServiceImpl implements CustomerService {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persist-unit");

	public CustomerEntity retrieve(Long id) {

		EntityManager em = emf.createEntityManager();

		CustomerEntity customer = null;

		try {
			customer = em.find(CustomerEntity.class, id);
		} finally {
			em.close();
		}

		return customer;
	}

	public void save(CustomerEntity customer) {

		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			
			em.persist(customer);
			
			transaction.commit();
		}
		catch(Exception e) {
			System.out.println("Error Saving Customer: " + e.getMessage());
			
			transaction.rollback();
		}
		finally {
			em.close();
		}
	}
}
