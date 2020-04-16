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
public class ClientTest {
    
    public ClientTest() {
    }

    /**
     * Test of create method, of class Client.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        Client result = Client.create(con, "Tintin", "Milou", "81DC9BDB52D04DC2036DBD8313ED055", "tintin@gmail.com");
        Client client = Client.getByMail(con, "tintin@gmail.com");
        assertEquals("Tintin", client.getNom());
        client.delete(con);
    }

    /**
     * Test of delete method, of class Client.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        assertEquals(2, Client.size(con));
        Client client = Client.create(con, "Tintin", "Milou", "81DC9BDB52D04DC2036DBD8313ED055", "tintin@gmail.com");
        assertEquals(3, Client.size(con));
        client.delete(con);
        assertEquals(2, Client.size(con));
    }

    /**
     * Test of save method, of class Client.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Client result = Client.create(con, "Tintin", "Milou", "81DC9BDB52D04DC2036DBD8313ED055", "tintin@gmail.com");
        result.setNom("Haddock");
        result.save(con);
        Client client = Client.getByMail(con, "tintin@gmail.com");
        assertEquals("Haddock", client.getNom());
        client.delete(con);
    }

    /**
     * Test of getByID method, of class Client.
     */
    @Test
    public void testGetByID() throws Exception {
        System.out.println("getByID");
        Connection con = ConnexionMySQL.newConnexion();
        Client result = Client.getByID(con, 1);
        assertEquals("deodat", result.getNom());
        result = Client.getByID(con, 2);
        assertEquals("livh", result.getNom());
    }

    /**
     * Test of getByMail method, of class Client.
     */
    @Test
    public void testGetByMail() throws Exception {
        System.out.println("getByMail");
        Connection con = ConnexionMySQL.newConnexion();
        Client client = Client.getByMail(con, "deodat@gmail.com");
        assertEquals("deodat", client.getNom());
        client = Client.getByMail(con, "livh@gmail.com");
        assertEquals("livh", client.getNom());
    }
    
}
