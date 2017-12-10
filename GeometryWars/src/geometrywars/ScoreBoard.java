/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author MaxBu
 */
public class ScoreBoard {
    
    int score;
    String initials;
    String host = "geometrywars.mysql.database.azure.com";
    String database = "geometrywars";
    String user = "Player@geometrywars";
    String password = "Password1";
    Connection connection = null;
    
    // check that the driver is installed
        public ScoreBoard() throws Exception {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException("MySQL JDBC driver NOT detected in library path.", e);
        }
        System.out.println("MySQL JDBC driver detected in library path.");

        connection = null;
        
        try
        {
            //String url = String.format("jdbc:mysql://%s/%s", host, database);
            
            String url ="jdbc:mysql://geometrywars.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false"; 
            connection = DriverManager.getConnection(url, "Player@geometrywars", "Password1");

            /*// Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);*/
        }
        catch (SQLException e)
        {
            throw new SQLException("Failed to create connection to database.", e);
        }
        if (connection != null) 
        { 
            System.out.println("Successfully created connection to database.");

            // Perform some SQL queries over the connection.
            try
            {
                // Statement
                Statement statement = connection.createStatement();

                // Create table.
                statement.execute("CREATE TABLE SCOREBOARD (PLAYER_SCORE PRIMARY KEY, INITIALS CHAR(3));");
                System.out.println("Created table.");

            }
            catch (SQLException e)
            {
                throw new SQLException("Encountered an error when executing given sql statement.", e);
            }       
        }
        else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }
    
    public void setScore(int s) {
        score = s;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setInitials(String i) {
        initials = i;
    }
    
    public String getInitials() {
        return initials;
    }
    
    public void insertScoreBoardDB() throws Exception {
        connection = null;
        Statement statement = null;    // For the SQL statement

        try
        {
            String url ="jdbc:mysql://geometrywars.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false"; 
            connection = DriverManager.getConnection(url, "Player@geometrywars", "Password1");
            
            /*// Ensure the SQL Server driver class is available.
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties); */
        }
        catch (SQLException e)
        {
            throw new SQLException("Failed to create connection to database.", e);
        }
        if (connection != null) 
        { 
            System.out.println("Successfully created connection to database.");

            // Perform some SQL queries over the connection.
            try
                {
                // Define the SQL string.
                String sqlString = "INSERT INTO SCOREBOARD(PLAYER_NAME, PLAYER_SCORE)" +
                    "VALUES (" + initials + "," + score + ")";

                // Use the connection to create the SQL statement.
                statement = connection.createStatement();

                // Execute the statement.
                statement.executeUpdate(sqlString);

                // Provide a message when processing is complete.
                System.out.println("Processing complete.");
            
            } catch (SQLException e)
            {
            throw new SQLException("Encountered an error when executing given sql statement.", e);
            }
        }
        else {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    } 
    
    public void selectScoreBoardDB() throws Exception{
        // check that the driver is installed
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException("MySQL JDBC driver NOT detected in library path.", e);
        }
        System.out.println("MySQL JDBC driver detected in library path.");

        connection = null;
        // Initialize connection object
        try
        {
            String url ="jdbc:mysql://geometrywars.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false"; 
            connection = DriverManager.getConnection(url, "Player@geometrywars", "Password1");
            
            /*String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);*/
        }
        catch (SQLException e)
        {
            throw new SQLException("Failed to create connection to database", e);
        }
        if (connection != null) 
        { 
            System.out.println("Successfully created connection to database.");

            // Perform some SQL queries over the connection.
            try
            {

                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT TOP 10 PLAYER_SCORE, PLAYER_NAME FROM SCOREBOARD ORDER BY PLAYER_SCORE DESC");
                while (results.next())
                {
                    String outputString = 
                        String.format(
                            "Data row = (%s, %s)",
                            results.getString(1),
                            results.getString(2));
                    System.out.println(outputString);
                }
            }
            catch (SQLException e)
            {
                throw new SQLException("Encountered an error when executing given sql statement", e);
            }       
        }
        else {
            System.out.println("Failed to create connection to database."); 
        }
        System.out.println("Execution finished.");
    }
}