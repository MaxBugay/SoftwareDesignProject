/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.sql.*;

/**
 *
 * @author MaxBu
 */
public class ScoreBoard {
    
    int score;
    String initials;
    String hostName = "geowars.database.windows.net";
    String dbName = "GeometryWarsScoreBoard";
    String user = "mabugay";
    String password = "Password1";
    String url = String.format("jdbc:sqlserver://geowars.database.windows.net:1433;database=GeometryWarsScoreBoard;user=mabugay@geowars;password=Password1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
    Connection connection = null;
    
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
    
    public void insertScoreBoardDB() {
        Statement statement = null;    // For the SQL statement

        try
        {
            // Ensure the SQL Server driver class is available.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection.
            connection = DriverManager.getConnection(url);

            // Define the SQL string.
            String sqlString = "INSERT INTO SCOREBOARD(PLAYER_NAME, PLAYER_SCORE)" +
                "VALUES (" + initials + "," + score + ")";

            // Use the connection to create the SQL statement.
            statement = connection.createStatement();

            // Execute the statement.
            statement.executeUpdate(sqlString);

            // Provide a message when processing is complete.
            System.out.println("Processing complete.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
    } 
    
    public void selectScoreBoardDB() {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            System.out.println("Query data");
            System.out.println("=========================================");

            
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT TOP 10 PLAYER_SCORE, PLAYER_NAME FROM SCOREBOARD ORDER BY PLAYER_SCORE DESC";

            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(selectSql)) {

                        // Print results from select statement
                        System.out.println("HIGH SCORES");
                        while (resultSet.next())
                        {
                            System.out.println(resultSet.getString(1) + " "
                                + resultSet.getString(2));
                        }
                 connection.close();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
