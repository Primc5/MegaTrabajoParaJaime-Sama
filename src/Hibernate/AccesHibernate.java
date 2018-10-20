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
	public void borrarDatos(){
    	System.out.println("Inicio Borrado");

		session.beginTransaction();
    	
		Query q = session.createQuery("delete from JugadorPosicion");
		q.executeUpdate();
		q = session.createQuery("delete from Posicion");
		q.executeUpdate();
		q = session.createQuery("delete from Jugador");
		q.executeUpdate();
		q = session.createQuery("delete from Equipo");
		q.executeUpdate();
		
		session.getTransaction().commit();
		System.out.println("Fin Borrado");
	}
	
}