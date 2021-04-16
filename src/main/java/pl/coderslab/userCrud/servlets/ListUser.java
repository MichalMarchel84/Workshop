package pl.coderslab.userCrud.servlets;

import pl.coderslab.userCrud.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserList", value = "/user/list")
public class ListUser extends HttpServlet {
    UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var users = dao.findAll();
        request.setAttribute("users", users);
        request.setAttribute("component", "/users/users.jsp");
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
