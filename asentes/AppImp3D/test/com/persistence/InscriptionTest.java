/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import static com.persistence.Utils.stringToTimestamp;
import java.sql.Connection;
import java.sql.Timestamp;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean-pierredumas
 */
public class InscriptionTest {
    
    public InscriptionTest() {
    }

    /**
     * Test of create method, of class Inscription.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp timestamp = stringToTimestamp("2020/01/17 08:40:00");
        Inscription result = Inscription.create(con, "toto@gmail.com", timestamp);
        Inscription ins = Inscription.getLast(con);
        assertEquals(ins.getMail(), "toto@gmail.com");
        result.delete(con);
    }

    /**
     * Test of delete method, of class Inscription.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        int size = Inscription.size(con);
        Timestamp timestamp = stringToTimestamp("2020/01/17 08:40:00");
        Inscription result = Inscription.create(con, "toto@gmail.com", timestamp);
        assertEquals(size + 1, Inscription.size(con));
        result.delete(con);
        assertEquals(size, Inscription.size(con));
    }

    /**
     * Test of getFirst method, of class Inscription.
     */
    @Test
    public void testGetFirst() throws Exception {
        System.out.println("getFirst");
        Connection con = ConnexionMySQL.newConnexion();
        Inscription result = Inscription.getFirst(con);
        assertEquals("jpdms@free.fr", result.getMail());
    }

    /**
     * Test of getLast method, of class Inscription.
     */
    @Test
    public void testGetLast() throws Exception {
        System.out.println("getLast");
        Connection con = ConnexionMySQL.newConnexion();
        Inscription result = Inscription.getLast(con);
        assertEquals("jpdumas@btslivh.eu", result.getMail());
    }

    /**
     * Test of getByMail method, of class Inscription.
     */
    @Test
    public void testGetByMail() throws Exception {
        System.out.println("getByMail");
        Connection con = ConnexionMySQL.newConnexion();
        Inscription result = Inscription.getByMail(con, "jpdumas@btslivh.eu");
        assertEquals("jpdumas@btslivh.eu", result.getMail());
    }

    /**
     * Test of size method, of class Inscription.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int size = Inscription.size(con);
        assertEquals(3, Inscription.size(con));
        Timestamp timestamp = stringToTimestamp("2020/01/17 08:40:00");
        Inscription result = Inscription.create(con, "toto@gmail.com", timestamp);
        assertEquals(size + 1, Inscription.size(con));
        result.delete(con);
        assertEquals(size, Inscription.size(con));
    }
}
