package us.rjks.utils;

import us.rjks.discord.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:15
 *
 **************************************************************************/

public class MySQL {

    private static String username = Config.getMySQLAsString("username");
    private static String password = Config.getMySQLAsString("password");
    private static String database = Config.getMySQLAsString("database");
    private static String host = Config.getMySQLAsString("host");
    private static String port = Config.getMySQLAsString("port");

    public static Connection con;


    public static void connect() {

        if(!isConnected()) {
            try {
                System.out.println("jdbc:mysql://" + host +":" + port + "/" + database + "?autoReconnect=true:" + username + ":" + password);
                con = DriverManager.getConnection("jdbc:mysql://" + host +":" + port + "/" + database + "?autoReconnect=true", username, password);
                System.out.println("[DB] Connected successfully to SQL Database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[DB] Disconnected successfully to SQL Database");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public static boolean isConnected() {
        return con != null;
    }

    public static void createTable() {

        if(isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS discord_connector (name VARCHAR(255), uuid VARCHAR(255), disid VARCHAR(255), distag VARCHAR(255), role VARCHAR(255), date VARCHAR(255))");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void update(String qry) {

        if(isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static ResultSet getResult(String qry) {
        if(isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }

}
