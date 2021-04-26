package com.example.JavaEE_lab7_forum.controller;

import com.example.JavaEE_lab7_forum.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "EditPostServlet", value = "/EditPostServlet")
public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement preparedStatement = con
                    .prepareStatement("update posts set title = ?, body = ? where id = ?");

            preparedStatement.setString(1, request.getParameter("title"));
            preparedStatement.setString(2, request.getParameter("body"));
            preparedStatement.setString(3, request.getParameter("id"));
            preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
