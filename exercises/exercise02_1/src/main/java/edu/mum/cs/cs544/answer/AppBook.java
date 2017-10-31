package edu.mum.cs.cs544.answer;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class AppBook {

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static void main(String[] args) {
		// Hibernate placeholders
		Session session = null;
		Transaction tx = null;

		// • Open a session
		// • Create 3 books save them to the database
		// • Close the session

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Create new instance of book and set values in it
			Book book1 = new Book("Book1", "ISBN1", "author1", 1, new Date());
			// save the book
			session.persist(book1);
			// Create new instance of book and set values in it
			Book book2 = new Book("Book2", "ISBN2", "author2", 2, new Date());
			// save the book
			session.persist(book2);// Create new instance of book and set values in it
			Book book3 = new Book("Book3", "ISBN3", "author3", 3, new Date());
			// save the book
			session.persist(book3);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// • Open a session
		// • Retrieve all books and output them to the console
		// • Close the session

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// retieve all books
			@SuppressWarnings("unchecked")
			List<Book> bookList = session.createQuery("from Book").list();
			for (Book book : bookList) {
				System.out.println("book= " + book.getTitle() + ", author= " + book.getAuthor() + ", Publish_date= "
						+ book.getPublish_date() + ", price= $" + book.getPrice());
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// • Open a session
		// • Retrieve a book from the database and change its title and price
		// • Delete a book (not the one that was just updated)
		// • Close the session

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// retieve all books

			Book book1 = (Book) session.get(Book.class, 1);
			book1.setTitle("New Tile for book 1");
			book1.setPrice(100);
			Book book2 = (Book) session.get(Book.class, 2);
			session.delete(book2);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// • Open a session
		// • Retrieve all books and output them to the console
		// • Close the session

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// retieve all books
			@SuppressWarnings("unchecked")
			List<Book> bookList = session.createQuery("from Book").list();
			for (Book book : bookList) {
				System.out.println("book= " + book.getTitle() + ", author= " + book.getAuthor() + ", Publish_date= "
						+ book.getPublish_date() + ", price= $" + book.getPrice());
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				System.err.println("Rolling back: " + e.getMessage());
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
		System.exit(0);
	}

}
