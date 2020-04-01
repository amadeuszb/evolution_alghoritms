package database;

import model.EvaluatedIndividual;
import model.Individual;
import model.SolutionScore;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    String filename;

    public DatabaseConnection(String filename) {
        this.filename = filename;
        doAllShit();
    }

    private void doAllShit() {
        createNewDatabase();
        connectToDatabase();
        createStatement();
        createTablesIfNotExist();
    }

    private void createStatement() {
        try {
            statement = connection
                    .createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewDatabase() {
        String url = "jdbc:sqlite:" + "e:/" + filename;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void connectToDatabase() {
        final String DB_SQL_LITE_URL = "jdbc:sqlite:";
        final String TEST_DB_URL = DB_SQL_LITE_URL + "e:/" + filename;
        try {
            connection = DriverManager.getConnection(TEST_DB_URL);
        } catch (SQLException e) {
            System.out.println("INCORRECT DATABASE"); //TODO
        }
    }

    private void createTablesIfNotExist() {
        String CALCULATION_CREATE_TABLE_SQL =
                "CREATE TABLE IF NOT EXISTS CALCULATION (\n"
                        + "    CALCULATION_ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                        + "    NAME TEXT\n"
                        + ");";
        String EPOCH_CREATE_TABLE_SQL =
                "CREATE TABLE IF NOT EXISTS EPOCH (\n"
                        + "    EPOCH_ID INTEGER PRIMARY KEY AUTOINCREMENT ,\n"
                        + "    EPOCH_NUMBER INTEGER NOT NULL,\n"
                        + "    CALCULATION_ID INTEGER NOT NULL,\n"
                        + "FOREIGN KEY(CALCULATION_ID) REFERENCES CALCULATION(CALCULATION_ID)"
                        + ");";
        String INDIVIDUAL_CREATE_TABLE_SQL =
                "CREATE TABLE IF NOT EXISTS INDIVIDUAL (\n"
                        + "    ID INTEGER PRIMARY KEY AUTOINCREMENT ,\n"
                        + "    X1 REAL NOT NULL,\n"
                        + "    X2 REAL NOT NULL,\n"
                        + "    Y REAL NOT NULL,\n"
                        + "    SCORE REAL NOT NULL,\n"
                        + "    EPOCH_ID INTEGER,\n"
                        + "FOREIGN KEY(EPOCH_ID) REFERENCES EPOCH(EPOCH_ID)"
                        + ");";
        try {
            statement.execute(CALCULATION_CREATE_TABLE_SQL);
            statement.execute(EPOCH_CREATE_TABLE_SQL);
            statement.execute(INDIVIDUAL_CREATE_TABLE_SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCalculation(SolutionScore solutionScore){
        String sql = "INSERT INTO CALCULATION(NAME) VALUES('TEST')";
        int calculationId = 1; //TODO: OBTAIN HOW TO GET ID
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int epochNumber = 1;
        for(List<EvaluatedIndividual> sc: solutionScore.getEpochs()){
            insertEpoch(sc,calculationId, epochNumber++);
        }
    }

    public void insertEpoch(List<EvaluatedIndividual> evaluatedIndividuals, int calculationId, int epochNumber){
        String sql = "INSERT INTO EPOCH(EPOCH_NUMBER, CALCULATION_ID) VALUES(?,?)";
        int epochId = 1; //TODO: OBTAIN HOW TO GET ID
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, epochNumber);
            ps.setInt(2, calculationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("xd");
            System.out.println(e.getMessage());
        }
        evaluatedIndividuals.forEach(ei -> insertIndividual(ei, epochId));
    }

    public void insertIndividual(EvaluatedIndividual evaluatedIndividual, int epochId) {
        String sql = "INSERT INTO INDIVIDUAL(X1,X2,Y,SCORE, EPOCH_ID) VALUES(?,?,?,?,?)";
        Individual individual = evaluatedIndividual.getIndividual();
        double score = evaluatedIndividual.getScore();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setDouble(1, individual.getX1());
                    ps.setDouble(2, individual.getX2());
                    ps.setDouble(3, score);
                    ps.setDouble(4, score);
                    ps.setInt(5, epochId);
                    ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("xddd");
            System.out.println(e.getMessage());
        }
    }

}
