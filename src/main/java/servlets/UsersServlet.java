package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UsersServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work


    //get public user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"/>\n" +
                "    <title>L2.1</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>L2.1 example</p>\n" +
                "\n" +
                "<form action=\"/api/v1/sessions\" method=\"POST\">\n" +
                "    Login: <input type=\"text\" name=\"login\"/>\n" +
                "    Password: <input type=\"password\" name=\"password\"/>\n" +
                "    <input type=\"submit\" value=\"Sign in\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    //sign up
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UserProfile user = new UserProfile(login, pass);
        try {
            long id = Main.dbService.addUser(user.getLogin());
            System.out.println("Added user with login: " + user.getLogin() + " id = " + id);
            //Main.accountService.addSession(request.getSession().getId(), user);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    //change profile
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }
}