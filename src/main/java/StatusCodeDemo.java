import com.google.gson.Gson;
import crudapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatusCodeDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = new User(1,"KKK");
        User user2 = new User(1,"KKK");

        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user);

        String string = new Gson().toJson(userList);

        response.getWriter().println(string);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
