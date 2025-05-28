package com.proyecto.dao;

import java.util.List;
import org.hibernate.*;
import com.proyecto.model.Persona;
import com.proyecto.util.HibernateUtil;

public class PersonaDAO {

    // Insertar persona
    public void insertPersona(Persona p) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(p);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // Borrar Persona

    public void deletePersona(int id) {// pasamos el identificador de la persona a borrar
        Transaction transaction = null;
        Persona p = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            p = session.get(Persona.class, id);
            session.remove(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // Mostrar Persona por id
    // SELECCION SIMPLES
    public Persona selectPersonaById(int id) {
        Transaction transaction = null;
        Persona p = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            p = session.get(Persona.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            System.out.println("Error al seleccionar la persona por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return p;
    }

    // Mostrar Persona
    public List<Persona> selectAllPersona() {
        Transaction transaction = null;
        List<Persona> listPerson = null;
        Persona p = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listPerson = session.createQuery("from Persona", Persona.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Error al mostrar las personas: " + e.getMessage());
                e.printStackTrace();
                transaction.rollback();
            }
        }
        return listPerson;
    }

}