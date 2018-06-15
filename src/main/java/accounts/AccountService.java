package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import main.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AccountService {

    UsersDAO dao;
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final Connection connection;

    public AccountService(Connection connection) {
        dao = new UsersDAO(connection);
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        this.connection = connection;
    }

    private void addToMap(){

    }

    public long addNewUser(UserProfile userProfile) throws DBException {
        long id = Main.dbService.addUser(userProfile.getLogin());
        System.out.println("User with id = " + id + " added!");
        UsersDataSet dataSet = Main.dbService.getUser(id);
        System.out.println("User data set: " + dataSet);
        return id;
    }

    public long getUserByLogin(String login) throws SQLException {
        return dao.getUserId(login);
    }

    public UsersDataSet getUsersDataSet(long id) throws DBException {
        return Main.dbService.getUser(id);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public Map<String, UserProfile> getLoginMap(){
        return loginToProfile;
    }
    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}