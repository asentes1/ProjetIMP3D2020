/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.persistence.Utils.*;

/**
 *
 * @author jean-pierredumas
 */
public class CartoucheTest {
    
    public CartoucheTest() {
    }

    /**
     * Test of create method, of class Cartouche.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String Type = "Support";
        boolean EnUtilisation = false;
        Timestamp DateRemplacement = stringToTimestamp("2020/06/22 08:00:00");
        Timestamp DateFabrication = stringToTimestamp("2020/03/22 08:00:00");
        String IdentifiantType = "A32";
        String NumeroDeSerie = "B36";
        double QuantiteRestante = 200.0;
        int CoutAuCm3 = 5;
        int Imprimante3dID = 1;
        Cartouche result = Cartouche.create(con, Type, EnUtilisation, DateRemplacement, DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID);
        assertEquals(NumeroDeSerie, result.getNumeroDeSerie());
        assertEquals(IdentifiantType, result.getIdentifiantType());
        assertEquals(Type, result.getType());
        assertEquals(DateRemplacement, result.getDateRemplacement());
        result.delete(con);
        
    }

    /**
     * Test of save method, of class Cartouche.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        String Type = "Support";
        boolean EnUtilisation = false;
        Timestamp DateRemplacement = stringToTimestamp("2020/06/22 08:00:00");
        Timestamp DateFabrication = stringToTimestamp("2020/03/22 08:00:00");
        String IdentifiantType = "A32";
        String NumeroDeSerie = "B36";
        double QuantiteRestante = 200.0;
        int CoutAuCm3 = 5;
        int Imprimante3dID = 1;
        Cartouche result = Cartouche.create(con, Type, EnUtilisation, DateRemplacement, DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID);
        String Type1 = "Matiere";
        result.setType(Type1);
        result.save(con);
        Cartouche cartman = Cartouche.getLast(con);
        assertEquals(Type1 , cartman.getType());
        result.delete(con);
    }

    /**
     * Test of delete method, of class Cartouche.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        String Type = "Support";
        boolean EnUtilisation = false;
        Timestamp DateRemplacement = stringToTimestamp("2020/06/22 08:00:00");
        Timestamp DateFabrication = stringToTimestamp("2020/03/22 08:00:00");
        String IdentifiantType = "A32";
        String NumeroDeSerie = "B36";
        double QuantiteRestante = 200.0;
        int CoutAuCm3 = 5;
        int Imprimante3dID = 1;
        Cartouche result = Cartouche.create(con, Type, EnUtilisation, DateRemplacement, DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID);
        assertEquals(IdentifiantType, result.getIdentifiantType());
        result.delete(con);
        Cartouche cart = Cartouche.getLast(con);
        assertNotEquals(IdentifiantType, cart.getIdentifiantType());
    }
    
    @Test
    public void testGetLast() throws Exception{
        System.out.println("getLast");
        Connection con = ConnexionMySQL.newConnexion();
        String Type = "Support";
        boolean EnUtilisation = false;
        Timestamp DateRemplacement = stringToTimestamp("2020/06/22 08:00:00");
        Timestamp DateFabrication = stringToTimestamp("2020/03/22 08:00:00");
        String IdentifiantType = "A32";
        String NumeroDeSerie = "B36";
        double QuantiteRestante = 200.0;
        int CoutAuCm3 = 5;
        int Imprimante3dID = 1;
        Cartouche result = Cartouche.create(con, Type, EnUtilisation, DateRemplacement, DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID);
        Cartouche cart = Cartouche.getLast(con);
        assertEquals(NumeroDeSerie, cart.getNumeroDeSerie());
        result.delete(con);
    }

    /**
     * Test of getList method, of class Cartouche.
     */
    @Test
    public void testGetList() throws Exception {
        System.out.println("getList");
        Connection con = null;
        ArrayList<Cartouche> expResult = null;
        ArrayList<Cartouche> result = Cartouche.getList(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByNumeroDeSerie method, of class Cartouche.
     */
    @Test
    public void testGetByNumeroDeSerie() throws Exception {
        System.out.println("getByNumeroDeSerie");
        Connection con = null;
        int NumeroDeSerie = 0;
        Cartouche expResult = null;
        Cartouche result = Cartouche.getByNumeroDeSerie(con, NumeroDeSerie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnServiceAndType method, of class Cartouche.
     */
    @Test
    public void testGetEnServiceAndType() throws Exception {
        System.out.println("getEnServiceAndType");
        Connection con = null;
        String type = "";
        Cartouche expResult = null;
        Cartouche result = Cartouche.getEnServiceAndType(con, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Cartouche.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = null;
        int expResult = 0;
        int result = Cartouche.size(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Cartouche.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Cartouche instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
