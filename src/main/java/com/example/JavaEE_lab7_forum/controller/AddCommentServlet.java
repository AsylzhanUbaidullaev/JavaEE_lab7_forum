package com.example.JavaEE_lab7_forum.controller;

import com.example.JavaEE_lab7_forum.DBConnection;
import com.example.JavaEE_lab7_forum.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddCommentServlet", value = "/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            con = DBConnection.init();
            PreparedStatement preparedStatement = con
                    .prepareStatement("insert into comments (text, user_id, post_id) values(?, ?, ?)");

            preparedStatement.setString(1, request.getParameter("text"));
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setString(2, request.getParameter("post_id"));
            preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
