package de.loriyt.api.Manager.datenbank;

import com.mysql.jdbc.PreparedStatement;
import de.loriyt.api.Manager.Manager;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    public static String host = "*********";

    public static String port = "*********";

    public static String database = "*********";

    public static String username = "*********";

    public static String password = "*********";

    public static com.mysql.jdbc.Connection con;

    public static boolean isConnected() {
        return (con != null);
    }

    public static void connect() {
        if (!isConnected())
            try {
                con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                Bukkit.getConsoleSender().sendMessage(Manager.info + "konnte erfolgreich mit der Datenbank verbunden werden");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(Manager.info + "konnte nicht mit der Datenbank verbunden werden");
            }
    }

    public static void disconnect() {
        try {
            con.close();
            Bukkit.getConsoleSender().sendMessage(Manager.info + "Verbindung zur Datenbank konnte erfolgreich geschlossen werden");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Manager.info + "Verbindung zur Datenbank konnte nicht geschlossen werden");
        }
    }

    public static com.mysql.jdbc.PreparedStatement getStatement(String sql) {
        if (isConnected())
            try {
                com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static ResultSet getResult(String sql) {
        if (isConnected())
            try {
                PreparedStatement ps = getStatement(sql);
                ResultSet rs = ps.executeQuery();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
