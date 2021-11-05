package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/LogIn.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * Checks log in credentials
     */
    public static boolean checkLogIn(String userName, String pass) throws SQLException {

        // Default query, ? replaced by setString member function
        String test = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";

        JDBC.makePreparedStatement(test, JDBC.getConnection());
        PreparedStatement preparedStatement = JDBC.getPreparedStatement();

        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Checks user input against database for matches
        if (resultSet.next())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        launch(args);

//        String test = "SELECT * FROM users WHERE User_Name = 'test'";
//        JDBC.makePreparedStatement(test, JDBC.getConnection());
//        PreparedStatement preparedStatement = JDBC.getPreparedStatement();
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("User_Name"));
//        }

        if (checkLogIn("tes","test"))
            System.out.println("hi");
        else
            System.out.println("stupid Nick");

    }
}
