package pl.coderslab.userCrud.servlets;

import pl.coderslab.userCrud.Defaults;
import pl.coderslab.userCrud.User;
import pl.coderslab.userCrud.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/user/add")
public class AddUser extends HttpServlet {
    UserDao dao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("component", "/users/addUser.jsp");
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("submit").equals("Dodaj")) {
            User user = new User();
            user.setUserName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("pass"));
            dao.create(user);
        }
        response.sendRedirect(Defaults.HOME);
    }
}
