package com.telegram.springboot;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.*;

@Entity
@Table(name = "tbl_schedule")
public class Schedule {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/tbl_schedule";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE TBL_SCHEDULE(group_ VARCHAR(9), data DATE,schedule TEXT)");
            stmt.execute("INSERT INTO TBL_SCHEDULE(group_, data,schedule) VALUES('КТбо2-2', '2018-03-19','Вакансия ВКСиРЭБ -.-. " +
                    "ВП (лек.) Г-242	Вакансия ВКСиРЭБ -.-. " +
                    "ВП (лек.) Г-242	Вакансия ВКСиРЭБ -.-. " +
                    "ВП (лек.) Г-242	Вакансия ВКСиРЭБ -.-. " +
                    "ВП (лек.) Г-242	Вакансия ВКСиРЭБ -.-. " +
                    "ВП (лек.) Г-242')");


//            ResultSet rs = stmt.executeQuery("select * from PERSON");
//            System.out.println("H2 Database inserted through Statement");
//            while (rs.next()) {
//                System.out.println("Id "+rs.getInt("id")+" Name "+rs.getString("name"));
//            }
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
