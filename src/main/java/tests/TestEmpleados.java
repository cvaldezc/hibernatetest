package tests;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.christoph.hibernate.modelo.Empleado;

public class TestEmpleados {
	
	private static EntityManagerFactory emf;
	private static EntityManager em; 
	
	public static void main(String[] args)
	{
		// crea el gestor de persistencia
		emf = Persistence.createEntityManagerFactory("Persistencia");
		em = emf.createEntityManager();
		
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1990, 4, 13));
		Empleado e2 = new Empleado(20L, "Martinez", "Jose", LocalDate.of(2011, 10, 15));
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(e);
		em.persist(e2);
		tx.commit();
		
		imprimirTodo();
		em.close();
		emf.close();
	}
	
	private static void imprimirTodo()
	{
		@SuppressWarnings("unchecked")
		List<Empleado> lista = (List<Empleado>) em.createQuery("FROM Empleado").getResultList();
		lista.forEach(System.out::println);
	}

}
