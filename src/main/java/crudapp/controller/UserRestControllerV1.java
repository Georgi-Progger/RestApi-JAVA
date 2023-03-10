package crudapp.controller;

import com.google.gson.Gson;
import crudapp.model.User;
import crudapp.repository.base.HibernateUserRepositoryImpl;
import crudapp.service.GenericService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/users")
public class UserRestControllerV1 extends HttpServlet{
    GenericService<User, Integer> userService;

    public UserRestControllerV1(){
        userService = new GenericService<>(new HibernateUserRepositoryImpl());
    }
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = GSON.fromJson(request.getReader(), User.class);
        Integer id = user.getId();
        if (id == 0) {
            List<User> userList = userService.getAll();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userList);
            out.flush();
        } else {
            User u = userService.getById(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(u);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = GSON.fromJson(request.getReader(), User.class);
        userService.save(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save user");
        out.flush();
    }
}
