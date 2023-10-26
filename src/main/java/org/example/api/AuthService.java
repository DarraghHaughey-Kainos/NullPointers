package org.example.api;

import org.example.cli.Login;
import org.example.client.FailedToGenerateTokenException;
import org.example.client.FailedToLoginException;
import org.example.db.AuthDao;

import java.sql.SQLException;

public class AuthService {

    private AuthDao authDao = new AuthDao();

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException {

        if(authDao.validLogin(login)){
            try{

                return authDao.generateToken(login.getUsername());

            } catch (SQLException e) {

                throw new FailedToGenerateTokenException();

            }
        }

        throw new FailedToLoginException();

    }

}
