package Hibernate;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Objetos.Empresas;
import Objetos.Videojuegos;


public class AccesHibernate {

	Session session;
	
	public AccesHibernate() {
		
		HibernateUtil util = new HibernateUtil(); 
		
		session = util.getSessionFactory().openSession();
		
	}
	
}