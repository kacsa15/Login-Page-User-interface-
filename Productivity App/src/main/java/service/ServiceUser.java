package service;

import connection.DatabaseConnection;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

public class ServiceUser {

    private final Connection CONNECTION;

    public ServiceUser() {
        CONNECTION = DatabaseConnection.getInstance().getConnection();
    }

    public void insertUser(UserModel userModel) throws SQLException{
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "insert into `user` (UserName, Email, `Password`, VerifyCode) values (?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        String code = generateVerifyCode();

        preparedStatement.setString(1, userModel.getUserName());
        preparedStatement.setString(2,userModel.getEmail());
        preparedStatement.setString(3, userModel.getPassword());
        preparedStatement.setString(4, code);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.first();

        int userId = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();

        userModel.setUserID(userId);
        userModel.setVerifyCode(code);
    }

    private String generateVerifyCode() throws SQLException{
        DecimalFormat decimalFormat = new DecimalFormat("000000");
        Random random = new Random();
        String code = decimalFormat.format(random.nextInt(1000000));
        while ((checkDuplicateVerifyCode(code))){
            code = decimalFormat.format(random.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateVerifyCode(String code) throws SQLException {
        boolean duplicate = false;

        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "select UserID from `user` where VerifyCode=? limit 1"
        );
        preparedStatement.setString(1,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            duplicate = true;
        }

        resultSet.close();
        preparedStatement.close();
        return duplicate;

    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;

        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "select UserID from `user` where Email=? and `Status`='Verified' limit 1"
        );
        preparedStatement.setString(1,user);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            duplicate = true;
        }

        resultSet.close();
        preparedStatement.close();
        return duplicate;
    }

    public boolean checkDuplicateUserName(String user) throws SQLException {
        boolean duplicate = false;

        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "select UserID from `user` where UserName=? and `Status`='Verified' limit 1"
        );
        preparedStatement.setString(1,user);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            duplicate = true;
        }

        resultSet.close();
        preparedStatement.close();
        return duplicate;
    }

    public boolean verifyCodeWithUser(int userId, String code) throws SQLException {
        boolean verify = false;

        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "select UserID from 'user' where UserID=? and VerifyCode=? limit 1"
        );
        preparedStatement.setInt(1,userId);
        preparedStatement.setString(2,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()){
            verify = true;
        }

        preparedStatement.close();
        resultSet.close();
        return verify;
    }

    public void doneVerifying(int userId) throws SQLException {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement(
                "update 'user' set VerifyCode='', Status='Verified' where UserID=? limit 1"
        );
        preparedStatement.setInt(1,userId);
        preparedStatement.close();
    }
}
