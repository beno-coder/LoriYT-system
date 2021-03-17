package de.loriyt.api.Manager.elo;

import com.mysql.jdbc.PreparedStatement;
import de.loriyt.api.Manager.datenbank.Datenbank;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class ELO {

    public static void createTable() {
        try {
            PreparedStatement ps = Datenbank.getStatement("CREATE TABLE IF NOT EXISTS Manager (Spielername VARCHAR(100), UUID VARCHAR(100), ELO INT(100))");
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void register(Player p) {
        try {
            PreparedStatement ps = Datenbank.getStatement("INSERT INTO Manager (Spielername, UUID, ELO) VALUES (?, ?, ?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getUniqueId().toString());
            ps.setInt(3, 0);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isRegistered(Player p) {
        try {
            PreparedStatement ps = Datenbank.getStatement("SELECT * FROM Manager WHERE UUID= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            boolean user = rs.next();
            rs.close();
            rs.close();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean isRegistered(String name) {
        try {
            PreparedStatement ps = Datenbank.getStatement("SELECT * FROM Manager WHERE Spielername= ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            boolean user = rs.next();
            rs.close();
            rs.close();
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int getElo(Player p) {
        try {
            PreparedStatement ps = Datenbank.getStatement("SELECT * FROM Manager WHERE UUID= ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int elo = rs.getInt("Elo");
            rs.close();
            ps.close();
            return elo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static void setElo(Player p, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE UUID= ?");
            ps.setInt(1, elo);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addElo(Player p, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE UUID= ?");
            ps.setInt(1, getElo(p) + elo);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void removeElo(Player p, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE UUID= ?");
            ps.setInt(1, getElo(p) - elo);
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getElo(String name) {
        try {
            PreparedStatement ps = Datenbank.getStatement("SELECT * FROM Manager WHERE Spielername= ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int elo = rs.getInt("Elo");
            rs.close();
            ps.close();
            return elo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static void setElo(String name, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE Spielername= ?");
            ps.setInt(1, elo);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addElo(String name, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE Spielername= ?");
            ps.setInt(1, getElo(name) + elo);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void removeElo(String name, int elo) {
        try {
            PreparedStatement ps = Datenbank.getStatement("UPDATE Manager SET ELO= ? WHERE Spielername= ?");
            ps.setInt(1, getElo(name) - elo);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
