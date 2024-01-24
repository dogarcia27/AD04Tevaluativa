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
		
		System.out.println("Borrando un Student y en cascada sus elementos asociados");
			
		int student_id = 22;
			
		Student tempStudent = entityManager.find(Student.class, student_id);
		// comienza la transacci�n
		entityManager.getTransaction().begin();
		
		// borra el Student y con CascadeType.ALL termina borrando su Tuition
		entityManager.remove(tempStudent);
			
		// hace commit de la transaccion
		entityManager.getTransaction().commit();
					
		System.out.println("Hecho!");
	
		entityManager.close();
		factory.close();
		
	}
}
