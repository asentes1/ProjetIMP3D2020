/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean-pierredumas
 */
public class OperateurTest {
    
    public OperateurTest() {
    }

    /**
     * Test of create method, of class Operateur.
     * utilise getID(con)
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        Operateur result = Operateur.create(con, "Dumas", "Jean pierre", "81DC9BDB52D04DC2036DBD8313ED055", "jpdumas@btslivh.eu");
        Operateur lui = Operateur.getByID(con, result.getID(con));
        assertEquals("jpdumas@btslivh.eu", lui.getMail());
        lui.delete(con);
    }

    /**
     * Test of save method, of class Operateur.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Operateur result = Operateur.create(con, "Dumas", "Jean pierre", "81DC9BDB52D04DC2036DBD8313ED055", "jpdumas@btslivh.eu");
        result.setPrenom("alex");
        result.save(con);
        Operateur lui = Operateur.getByID(con, result.getID(con));
        assertEquals("alex", lui.getPreNom());
        lui.delete(con);
    }

    /**
     * Test of getByID method, of class Operateur.
     * utilise getID(con)
     */
    @Test
    public void testGetByID() throws Exception {
        System.out.println("getIt");
        Connection con = ConnexionMySQL.newConnexion();
        Operateur result = Operateur.create(con, "Dumas", "Jean pierre", "81DC9BDB52D04DC2036DBD8313ED055", "jpdumas@btslivh.eu");
        Operateur lui = Operateur.getByID(con, result.getID(con));
        assertEquals("jpdumas@btslivh.eu", lui.getMail());
        lui.delete(con);
        lui = Operateur.getByID(con, 1);
        assertEquals("hervelepine@gmail.com", lui.getMail());
    }
    
    /**
     * Test of getID method, of class Operateur.
     */
    @Test
    public void testGetID() throws Exception {
        System.out.println("getIt");
        Connection con = ConnexionMySQL.newConnexion();
        Operateur lui = Operateur.getByID(con, 1);
        assertEquals("hervelepine@gmail.com", lui.getMail());
    }

    /**
     * Test of size method, of class Operateur.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int result = Operateur.size(con);
        assertEquals(1, result);
        Operateur op = Operateur.create(con, "Dumas", "Jean pierre", "81DC9BDB52D04DC2036DBD8313ED055", "jpdumas@btslivh.eu");
        result = Operateur.size(con);
        assertEquals(2, result);
        op.delete(con);
        result = Operateur.size(con);
        assertEquals(1, result);
    }
    
}
