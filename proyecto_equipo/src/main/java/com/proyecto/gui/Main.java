package com.proyecto.gui;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proyecto.dao.PersonaDAO;
import com.proyecto.model.Persona;
import com.proyecto.util.HibernateUtil;

public class Main {

    static void mostrarMenu() {
        System.out.println("Seleccione opción:");
        System.out.println("1. Insertar persona");
        System.out.println("2. Borrar persona");
        System.out.println("0. Salir");
    }
    public static void main(String[] args) {
        
        int opcion = 12;
        do {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            PersonaDAO personaDAO = new PersonaDAO();

            try {
                Scanner s = new Scanner(System.in);
				mostrarMenu();
	            opcion = s.nextInt();
	            s.nextLine();
	            
	            switch(opcion) {

                    //INSERTAR PERSONA:
	            
	            	case 1:
                    
                    System.out.println("Pon un nombre");
                    String nombre = s.next();

                    System.out.println("Pon una edad");
                    int edad = s.nextInt();

                    Persona p = new Persona(nombre, edad);

                    personaDAO.insertPersona(p);

                    List<Persona> personas = personaDAO.selectAllPersona();

                    for (Persona pe : personas) {
                        System.out.println(pe.getIdPersona + " " + pe.getNombre + " " + pe.getEdad); 
                    }
	            		
	            	break;
	            	
	            	case 2:
	            		
	            		//BORRAR UN ESTUDIANTE:

                        System.out.println("Pon un id");
                        int id = s.nextInt();

                        personaDAO.deletePersona(id);

                        personas = personaDAO.selectAllPersonas();

                        for (Persona pe : personas) {
                            System.out.println(pe.getIdPersona + " " + pe.getNombre + " " + pe.getEdad); 
                        }

	            		
	            	break;
	            	
	            	case 0:
		                    System.out.println("Fin del programa");
		            break;
		            
		            default:
		                    System.out.println("Opción no válida");
	            		
	            }
			}catch (Exception e) {
                e.printStackTrace();
            }
            

        } while (opcion != 0);


    }
}