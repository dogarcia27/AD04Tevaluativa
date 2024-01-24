package ejercicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Student;

public class Consulta {

	public static void main(String[] args) {

		// crea sessionFactory y session
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
        EntityManager entityManager = factory.createEntityManager();
		
		System.out.println("Preparando consulta . . .");
			
		List<Student> tempStudents = entityManager.createQuery("from Student").getResultList();
		System.out.println("Listado de estudiantes:");
		displayStudents(tempStudents);
	
		entityManager.close();
		factory.close();
		
	}
	
	public static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.printf("%-10s %-10s - universidad: %s\n",tempStudent.getFirstName(), tempStudent.getLastName(), tempStudent.getUniversity().getName());
		}
	}
}
