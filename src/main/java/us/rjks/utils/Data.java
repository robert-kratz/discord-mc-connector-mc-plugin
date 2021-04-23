package us.rjks.utils;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:16
 *
 **************************************************************************/

public class Data {

    //"CREATE TABLE IF NOT EXISTS discord_connector (name VARCHAR(255), uuid VARCHAR(255), disid VARCHAR(255), distag VARCHAR(255), role VARCHAR(255), date VARCHAR(255))"

    public static void createFields() {
        MySQL.update("CREATE TABLE IF NOT EXISTS discord_connector (name VARCHAR(255), uuid VARCHAR(255), disid VARCHAR(255), distag VARCHAR(255), role VARCHAR(255), date VARCHAR(255))");
    }

    public static void registerUser(Player all, String role, String discord) {
        MySQL.update("INSERT INTO discord_connector(name, uuid, disid, distag, role, date) VALUES ('"
                + all.getName() + "','"
                + all.getUniqueId().toString() + "','"
                + "null" + "', '"
                + discord + "', '"
                + role + "', '"
                + System.currentTimeMillis() + "')");
    }

    public static void unregisterUser(String uuid) {
        MySQL.update("DELETE FROM discord_connector WHERE uuid='" + uuid + "'");
    }

    public static Structure getUserProfile(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM discord_connector WHERE uuid='" + uuid + "'");
        try {
            while (rs.next()) {
                return new Structure(rs.getString("name"), rs.getString("uuid"), rs.getString("disid"), rs.getString("distag"), rs.getString("role"), rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean userExists(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM discord_connector WHERE uuid='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static class Structure {
        String name, uuid, disid, distag, role, date;

        public Structure(String name, String uuid, String disid, String distag, String role, String date) {
            this.name = name;
            this.uuid = uuid;
            this.disid = disid;
            this.distag = distag;
            this.role = role;
            this.date = date;
        }

        public Structure() {

        }

        public boolean isConnected() {
            return disid.equalsIgnoreCase("null");
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getDisid() {
            return disid;
        }

        public String getDistag() {
            return distag;
        }

        public String getRole() {
            return role;
        }

        public String getUuid() {
            return uuid;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setDisid(String disid) {
            this.disid = disid;
        }

        public void setDistag(String distag) {
            this.distag = distag;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
