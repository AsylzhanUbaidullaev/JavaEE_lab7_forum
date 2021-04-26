package com.example.JavaEE_lab7_forum.controller;

import com.example.JavaEE_lab7_forum.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "LikePostServlet", value = "/LikePostServlet")
public class LikePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement preparedStatement = con
                    .prepareStatement("update posts set likes = likes + 1 where id = ?");

            preparedStatement.setString(1, request.getParameter("id"));
            preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
