package servlet;

import dao.ExpenseDao;
import util.HibernateUtil;
import entity.Expense;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveExpense")
public class SaveExpenseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String description = req.getParameter("description");
        String price = req.getParameter("price");

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginUser");
        Expense expense = new Expense(title, date, time, description, price, user);

        ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
        boolean f = dao.saveExpense(expense);

        if(f){
            session.setAttribute("msg", "Expense added successfully");
            resp.sendRedirect("user/add_expense.jsp");
        }else{
            session.setAttribute("msg", "Something wrong on server!");
            resp.sendRedirect("user/add_expense.jsp");
        }

    }
}
