/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import static com.persistence.Utils.stringToTimestamp;
import java.sql.*;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean-pierredumas
 */
public class AmbianceTest {
    final String FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public AmbianceTest() {
    }

    /**
     * Test of create method, of class Ambiance.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp timestamp = stringToTimestamp("2020/03/22 08:00:00");
        Ambiance result = Ambiance.create(con, 16.5, 54.7, timestamp);
        assertEquals(timestamp, result.getDatation());
        result.delete(con);
    }

    /**
     * Test of save method, of class Ambiance.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp timestamp = stringToTimestamp("2020/03/22 08:10:00");
        Ambiance result = Ambiance.create(con, 16.5, 54.7, timestamp);
        double humidite = 55.2;
        result.setHumidite(humidite);
        result.save(con);
        Ambiance amb = Ambiance.getLast(con);
        // on ne peut pas comarer des réels donc on convert en entiers
        assertEquals((int)(10*humidite), (int)(10*amb.getHumidite()));
        result.delete(con);
    }

    /**
     * Test of delete method, of class Ambiance.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp timestamp = stringToTimestamp("2020/03/22 08:00:00");
        Ambiance result = Ambiance.create(con, 16.5, 54.7, timestamp);
        assertEquals(timestamp, result.getDatation());
        result.delete(con);
        Ambiance amb = Ambiance.getLast(con);
        assertNotEquals(timestamp, amb.getDatation());
    }

    /**
     * Test of getLast method, of class Ambiance.
     */
    @Test
    public void testGetLast() throws Exception {
        System.out.println("getLast");
        Connection con = ConnexionMySQL.newConnexion();
        Timestamp timestamp = stringToTimestamp("2020/03/22 08:10:00");
        Ambiance result = Ambiance.create(con, 16.5, 54.7, timestamp);
        Ambiance amb = Ambiance.getLast(con);
        assertEquals(timestamp, amb.getDatation());
        result.delete(con);
    }

    /**
     * Test of getBetweenDates method, of class Ambiance.
     */
    @Test
    public void testGetBetweenDates() throws Exception {
        System.out.println("getBetweenDates");
        Connection con = ConnexionMySQL.newConnexion();
        ArrayList<Ambiance> result = Ambiance.getBetweenDates(con, "2020/03/22 07:10:00", "2020/03/22 07:30:00");
        assertEquals(3, result.size());
        result = Ambiance.getBetweenDates(con, "2020/03/22 07:30:00", "2020/03/22 07:30:00");
        assertEquals(1, result.size());
        result = Ambiance.getBetweenDates(con, "2020/03/22 07:40:00", "2020/03/22 07:50:00");
        assertEquals(2, result.size());
        result = Ambiance.getBetweenDates(con, "2020/03/22 08:40:00", "2020/03/22 09:50:00");
        assertEquals(0, result.size());
    }

    /**
     * Test of size method, of class Ambiance.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int size = Ambiance.size(con);
        assertEquals(6, size);
        Timestamp timestamp = stringToTimestamp("2020/03/22 08:10:00");
        Ambiance result = Ambiance.create(con, 16.5, 54.7, timestamp);
        size = Ambiance.size(con);
        assertEquals(7, size);
        result.delete(con);
        size = Ambiance.size(con);
        assertEquals(6, size);
    }
    
}
