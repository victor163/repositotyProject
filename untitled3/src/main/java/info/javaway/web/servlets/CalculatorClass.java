package info.javaway.web.servlets;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalculatorClass {
    String finishCrowflight,finishMatrix;
    DataSource dataSource=null;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private InitialContext initialContext;
    private double distance;
    private int latitudeCity1,longitudCity1,latitudeCity2,longitudCity2;
    public void setLatitudeCity1(int latitudeCity1) {
        this.latitudeCity1 = latitudeCity1;
    }
    public void setLongitudCity1(int longitudCity1) {
        this.longitudCity1 = longitudCity1;
    }
    public void setLatitudeCity2(int latitudeCity2) {
        this.latitudeCity2 = latitudeCity2;
    }
    public void setLongitudCity2(int longitudCity2) {
        this.longitudCity2 = longitudCity2;
    }
    public void setFinishCrowflight(String finishCrowflight) {
        this.finishCrowflight = finishCrowflight;
    }
    public String getFinishCrowflight() {
        return finishCrowflight;
    }
    public String getFinishMatrix() {
        return finishMatrix;
    }
    public void setFinishMatrix(String finishMatrix) {
        this.finishMatrix = finishMatrix;
    }

    public void startDB(String fromCity,String toCity,int koef) {
        try {
            initialContext=new InitialContext();
            dataSource=(DataSource) initialContext.lookup("java:/magenta/datasource/test-distance-calculator");
            connection=dataSource.getConnection();
            statement = connection.createStatement();
            switch (koef){
                case 1:
                    startSity1DB(fromCity,statement);
                    startSity2DB(toCity,statement);
                    distanceCalc(fromCity,toCity);
                    break;
                case 2:
                    distanseMatrix(fromCity,toCity,statement);
                    break;
                case 3:
                    startSity1DB(fromCity,statement);
                    startSity2DB(toCity,statement);
                    distanceCalc(fromCity,toCity);
                    distanseMatrix(fromCity,toCity,statement);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    private void startSity1DB(String fromCity,Statement statement){
        try {
            resultSet = statement.executeQuery("select * from city where name=\"" + fromCity + "\"");
            while (resultSet.next()) {
                int latitude = resultSet.getInt(2);
                int longitude = resultSet.getInt(3);
                setLatitudeCity1(latitude);
                setLongitudCity1(longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    private void startSity2DB(String toCity, Statement statement){
        try {
            resultSet = statement.executeQuery("select * from city where name=\"" + toCity + "\"");
            while (resultSet.next()) {
                int latitude = resultSet.getInt(2);
                int longitude = resultSet.getInt(3);
                setLatitudeCity2(latitude);
                setLongitudCity2(longitude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    private void distanceCalc(String fromCity,String toCity){
        distance = Math.sqrt(Math.pow((latitudeCity2 - latitudeCity1), 2) + Math.pow((longitudCity2 - longitudCity1), 2));
        finishCrowflight = "distanse " + fromCity + " in city" + toCity + " = " + distance;
        setFinishCrowflight(finishCrowflight);
    }
    private void distanseMatrix(String fromCity, String toCity, Statement statement){
        try {
            resultSet = statement.executeQuery("select * from distance where concat( from_city, to_city) like\"%"+fromCity+"%"+toCity+"%\"");
            while (resultSet.next()){
                distance=resultSet.getInt(3);
                finishMatrix = "distanse " + fromCity + " in city" + toCity + " = " + distance;
                setFinishMatrix(finishMatrix);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
