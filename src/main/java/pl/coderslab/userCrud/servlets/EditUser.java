package pl.coderslab.userCrud.servlets;

import pl.coderslab.userCrud.Defaults;
import pl.coderslab.userCrud.User;
import pl.coderslab.userCrud.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUser", value = "/user/edit")
public class EditUser extends HttpServlet {
    UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = dao.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("user", user);
        request.setAttribute("component", "/users/editUser.jsp");
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("submit").equals("Zapisz")) {
            User user = new User(
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("pass"));
            user.setId(Integer.parseInt(request.getParameter("id")));
            dao.update(user);
        }
        response.sendRedirect(Defaults.HOME);
    }
}
