package ejercicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Course;
import entidades.Student;

public class Ejercicio3 {

	/**
	 * 1. ManyToMany bidireccional entre entidades Student y Course
	 * Crea un nuevo curso y a�ade un alumno al curso 
	 */
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
        EntityManager entityManager = factory.createEntityManager();
		
					
		// crea un objeto Student y Course
		System.out.println("Creando un nuevo curso y añadiendo un alumno...");
			
		Student student = entityManager.find(Student.class, 14);
		Course course = createCourse();
						
		student.getCourses().add(course);
		course.getStudents().add(student);//asociación bidireccional para mantener la coherencia en ambos lados
			
											
		// comienza la transacción
		entityManager.getTransaction().begin();
			
		// guarda el objeto Student y el curso
		System.out.println("Guardando el curso...");
						
		entityManager.persist(course);
		
		// hace commit de la transaccion
		entityManager.getTransaction().commit();	
			
		// Inicia una nueva transacci�n y recupera el curso de la base de datos para verificar los estudiantes asociados.
		// Esta parte est� comentada temporalmente para evitar operaciones adicionales de base de datos durante la demostraci�n.
		// Si necesitas verificar que la relaci�n ManyToMany se ha establecido correctamente, puedes descomentar estas l�neas.
		// session.beginTransaction();
		// Course dbCourse= (Course) session.get(Course.class, course.getId());
		// System.out.println(dbCourse.getStudents().iterator().next().getLastName());
		
		System.out.println("Hecho!");
		
		entityManager.close();
		factory.close();
		
	}
	
	private static Course createCourse() {
		Course tempCourse = new Course();
				
		tempCourse.setName("Bases de datos");
		tempCourse.setCredits(6);
		return tempCourse;		
	}

}
