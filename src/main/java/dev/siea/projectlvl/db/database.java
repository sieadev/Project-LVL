package dev.siea.projectlvl.db;

import dev.siea.projectlvl.models.LevelData;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.sql.*;

import static org.bukkit.Bukkit.getServer;


public class database {

    private static String url;
    private static String user;
    private static String psw;
    private static String name;
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection != null){
            return connection;
        }

        establishConnection();
        return connection;
    }
    public LevelData findLevelDataByUUID(String uuid) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM PlayerSettings WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet results = statement.executeQuery();

        if (results.next()){
            double exp = results.getInt("exp");
            int speed = results.getInt("speed");
            int health = results.getInt("health");
            int oxygen = results.getInt("oxygen");
            int damage = results.getInt("damage");
            int jumpingForce = results.getInt("jumpingForce");
            int harvesting = results.getInt("harvesting");
            int fireResistance = results.getInt("fireResistance");

            LevelData data = new LevelData(uuid, exp, speed, health, oxygen, damage, jumpingForce, harvesting, fireResistance);
            statement.close();
            return data;
        }else{
            statement.close();
            return null;
        }

    }

    public void createLevelData(LevelData data) throws SQLException{
        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO PlayerSettings (uuid,exp,speed,health,oxygen,damage,jumpingForce,harvesting,fireResistance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, data.getUuid());
        statement.setDouble(2, data.getExp());
        statement.setInt(3, data.getSpeed());
        statement.setInt(4, data.getHealth());
        statement.setInt(5, data.getOxygen());
        statement.setInt(6, data.getDamage());
        statement.setInt(7, data.getJumpingForce());
        statement.setInt(8, data.getHarvesting());
        statement.setInt(9, data.getFireResistance());
        statement.executeUpdate();
        statement.close();
    }

    public void updateLevelData(LevelData data) throws SQLException{
        PreparedStatement statement = getConnection()
                .prepareStatement("UPDATE LevelData SET exp = ?, speed = ?, health = ?, oxygen = ?, damage = ?, jumpingForce = ?, harvesting = ?, fireResistance = ? WHERE uuid = ?");
        statement.setDouble(1, data.getExp());
        statement.setInt(2, data.getSpeed());
        statement.setInt(3, data.getHealth());
        statement.setInt(4, data.getOxygen());
        statement.setInt(5, data.getDamage());
        statement.setInt(6, data.getJumpingForce());
        statement.setInt(7, data.getHarvesting());
        statement.setInt(8, data.getFireResistance());
        statement.setString(9, data.getUuid());
        statement.executeUpdate();
        statement.close();
    }

    public static void onEnable(Plugin p) throws SQLException{
        String ip = p.getConfig().getString("ip");
        name = p.getConfig().getString("name");
        url = "jdbc:mysql://" + ip + "/" + name;
        user = p.getConfig().getString("user");
        psw = p.getConfig().getString("password");
        createTables();
    }

    public static void onDisable() throws SQLException{
        destroyConnection();
    }

    private static void establishConnection ()throws SQLException{
        connection = DriverManager.getConnection(url, user, psw);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DB] Verbindung zur DataBase konnte erfolgreich hergestellt werden");
    }

    private static void createTables() throws SQLException{
        Connection connection = getConnection();

        // CREATE && LOAD SKILL-TABLE
        Statement statementMoneyTable = connection.createStatement();
        String sqlMoneyTable = "CREATE TABLE IF NOT EXISTS LevelData(uuid varchar(36) primary key, exp double, speed int, health int, oxygen int, damage int, jumpingForce int, harvesting int, fireResistance int)";
        statementMoneyTable.execute(sqlMoneyTable);
        statementMoneyTable.close();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[DB] LevelData table wurde erfolgreich geladen");
    }

    private static void destroyConnection() throws SQLException{

        connection.close();
    }
}

