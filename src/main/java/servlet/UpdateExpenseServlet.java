package servlet;

import dao.ExpenseDao;
import util.HibernateUtil;
import entity.Expense;
import entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateExpense")
public class UpdateExpenseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String description = req.getParameter("description");
        String price = req.getParameter("price");

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginUser");
        Expense expense = new Expense(title, date, time, description, price, user);
        expense.setId(id);

        ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
        boolean f = dao.updateExpense(expense);

        if(f){
            session.setAttribute("msg", "Expense updated successfully");
            resp.sendRedirect("user/view_expense.jsp");
        }else{
            session.setAttribute("msg", "Something wrong on server!");
            resp.sendRedirect("user/view_expense.jsp");
        }

    }
}
