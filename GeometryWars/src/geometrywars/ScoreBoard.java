/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author MaxBu
 */
public class ScoreBoard {
    
    int score;
    String initials;
    String host = "geometrywars.mysql.database.azure.com";
    String database = "highscores";
    String user = "Player@geometrywars";
    String password = "Password1";
    Connection connection = null;
    String highscoreboard;
    String top;

    
    
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
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "false");
            properties.setProperty("verifyServerCertificate", "true");
            //properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);
        }
        catch (SQLException e)
        {
            throw new SQLException("Failed to create connection to database.", e);
        }
        if (connection != null) 
        { 
            System.out.println("Successfully created connection to database.");
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
    
    public void insertScoreBoardDB(String in) throws Exception {
        this.initials = in;
        connection = null;
        Statement statement = null;    // For the SQL statement

        try
        {
            
            // Ensure the SQL Server driver class is available.
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "false");
            properties.setProperty("verifyServerCertificate", "true");
            //properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties); 
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
                String sqlString = "INSERT INTO SCOREBOARD(PLAYER_INITIALS, PLAYER_SCORE)" +
                    "VALUES ('" + in + "'," + score  + ")";

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
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "false");
            properties.setProperty("verifyServerCertificate", "true");
            //properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);
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
                ResultSet results = statement.executeQuery("SELECT * FROM SCOREBOARD ORDER BY PLAYER_SCORE DESC LIMIT 10;");
                String outputString = "";
                String[] outputArr = new String[100];
                int i = 0;
                System.out.println("High Scores:");
                while (results.next())
                {
                    outputString = 
                        String.format(
                            "Name        Score\n%s        %s",
                            results.getString(1),
                            results.getString(2)).toUpperCase();
                    System.out.println(outputString);
                    outputArr[i++] = outputString;
                }
                String highscores = "";
                for (int j = 0; outputArr[j] != null; j++) {
                    highscores+="\n";
                    highscores+=outputArr[j];
                }
                highscoreboard = highscores;
                JOptionPane.showMessageDialog(null, "High Scores \n" + highscores);
                System.exit(0);
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
    
    public void selectTopScore() throws Exception{
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
            String url = String.format("jdbc:mysql://%s/%s", host, database);

            // Set connection properties.
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "false");
            properties.setProperty("verifyServerCertificate", "true");
            //properties.setProperty("requireSSL", "false");

            // get connection
            connection = DriverManager.getConnection(url, properties);
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
                ResultSet results = statement.executeQuery("SELECT * FROM SCOREBOARD ORDER BY PLAYER_SCORE DESC LIMIT 1;");
                String topScore = "";
                while (results.next())
                {
                    topScore = (results.getString(2)).toUpperCase();
                    System.out.println(topScore);
                }
                this.top = topScore;
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
    public String getTop() {
        return this.top;
    }
}