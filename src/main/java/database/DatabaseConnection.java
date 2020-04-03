package database;

import model.EvaluatedIndividual;
import model.Individual;
import model.SolutionScore;

import java.sql.*;
import java.util.List;

import static javax.swing.UIManager.getInt;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    String filename;

    public DatabaseConnection(String filename) {
        this.filename = filename;
        init();
    }

    private void init() {
        connectToDatabase();
        createStatement();
        createTablesIfNotExist();
    }

    private void createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() {
        final String dbUri = "jdbc:sqlite:" + filename;
        try {
            connection = DriverManager.getConnection(dbUri);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println("Database connection error!");
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
            System.err.println(e.getMessage());
        }
    }

    public void insertCalculation(SolutionScore solutionScore) {
        final String sql = "INSERT INTO CALCULATION(NAME) VALUES('TEST');";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            int calculationId = ExecuteScalar(ps);
            int epochNumber = 0;
            for (List<EvaluatedIndividual> sc : solutionScore.getEpochs()) {
                insertEpoch(sc, calculationId, epochNumber++);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private int ExecuteScalar(PreparedStatement ps) throws SQLException {
        PreparedStatement getId = getInsertedIdStatement();
        ps.execute();
        ResultSet resultSet = getId.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public void insertEpoch(List<EvaluatedIndividual> evaluatedIndividuals, int calculationId, int epochNumber) {
        try {
            PreparedStatement ps = getInsertEpochStatement(epochNumber, calculationId);
            int epochId = ExecuteScalar(ps);
            evaluatedIndividuals.forEach(ei -> insertIndividual(ei, epochId));
            getInsertIndividualStatement().executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private PreparedStatement insertedIdStatement;

    private PreparedStatement getInsertedIdStatement() throws SQLException {
        if (insertedIdStatement == null) {
            insertedIdStatement = connection.prepareStatement("SELECT last_insert_rowid();");
        }
        return insertedIdStatement;
    }

    private PreparedStatement insertEpoch;

    private PreparedStatement getInsertEpochStatement(int calculationId, int epochNumber) throws SQLException {
        if (insertEpoch == null) {
            final String sql = "INSERT INTO EPOCH(EPOCH_NUMBER, CALCULATION_ID) VALUES(?,?);";
            insertEpoch = connection.prepareStatement(sql);
        }
        insertEpoch.setInt(1, epochNumber);
        insertEpoch.setInt(2, calculationId);
        return insertEpoch;
    }


    private PreparedStatement insertIndividual;

    private PreparedStatement getInsertIndividualStatement() throws SQLException {

        if (insertIndividual == null) {
            final String sql = "INSERT INTO INDIVIDUAL(X1, X2, Y, SCORE, EPOCH_ID) VALUES(?,?,?,?,?)";
            insertIndividual = connection.prepareStatement(sql);
        }
        return insertIndividual;
    }

    private void insertIndividual(EvaluatedIndividual evaluatedIndividual, int epochId) {
        Individual individual = evaluatedIndividual.getIndividual();
        double score = evaluatedIndividual.getScore();
        try {
            PreparedStatement ps = getInsertIndividualStatement();
            ps.setDouble(1, individual.getX1());
            ps.setDouble(2, individual.getX2());
            ps.setDouble(3, score);
            ps.setDouble(4, score);
            ps.setInt(5, epochId);
            ps.addBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
