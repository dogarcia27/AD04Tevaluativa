package ejercicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Student;

public class Ejercicio4 {

	public static void main(String[] args) {

		// crea sessionFactory y session
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
        EntityManager entityManager = factory.createEntityManager();
		
		try {			
		
			System.out.println("Borrando un Tuition y en cascada su Student asociado");
			
			int student_id = 11;
			
			Student tempStudent = entityManager.find(Student.class, student_id);
			// comienza la transacci�n
			entityManager.getTransaction().begin();
		
			// borra el Student y con CascadeType.ALL termina borrando su Tuition
			entityManager.remove(tempStudent);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci�n
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			factory.close();
		}
	}
}
