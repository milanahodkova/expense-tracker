package servlet;

import dao.UserDao;
import util.HibernateUtil;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDao dao = new UserDao(HibernateUtil.getSessionFactory());
        User user = dao.login(email, password);

        HttpSession session = req.getSession();
        if(user == null){
            session.setAttribute("msg","invalid Email & Password");
            resp.sendRedirect("login.jsp");
        }else{
            session.setAttribute("loginUser", user);
            resp.sendRedirect("index.jsp");
        }
    }
}
