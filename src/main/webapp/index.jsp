<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.example.JavaEE_lab7_forum.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.example.JavaEE_lab7_forum.model.Post" %>
<%@ page import="com.example.JavaEE_lab7_forum.model.Comment" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%! User user = null; %>
<% user = (User) session.getAttribute("user"); %>
<jsp:include page="header.jsp"/>
<h1>Welcome to Forum</h1>
<% if (request.getAttribute("user") != null) { %>
<div class="my-5">
    <form action="CreatePost" method="post" class="card card-body">
        <div class="form-group">
            <label>Title</label>
            <input name="title" type="text" class="form-control">
        </div>
        <div class="form-group">
            <label>Body</label>
            <textarea name="body" class="form-control" rows="3"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Save</button>
        </div>
    </form>
</div>
<%}%>
</div>
<div class="my-5">
    <%
        for (Post post : (List<Post>) request.getAttribute("posts")) {%>
    <div class="card">
        <div class="card-header">
            <%post.getTitle();%>
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <p>
                    <%post.getBody();%>
                </p>
                <footer class="blockquote-footer">
                    <%post.getUser().getName();%>
                </footer>
            </blockquote>
        </div>
        <div class="d-flex justify-content-between">
            <div class="m-2">
                <%if (post.getUser().getId() == user.getId()) {%>
                <form action="UpdatePost">
                    <input hidden name="id" value="<%post.getId();%>">
                    <button class="btn btn-primary">Update</button>
                </form>
                <%}%>
            </div>
        </div>

        <% if (request.getAttribute("user") != null) { %>
        <div class="m-3">
            <form action="CreateComment" method="post" class="card card-body">
                <div class="form-group">
                    <label>Body</label>
                    <textarea name="body" class="form-control" rows="3"></textarea>
                </div>
                <input hidden name="post_id" value="<%post.getId();%>">
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Save</button>
                </div>
            </form>
        </div>
        <%}%>

        <%for (Comment comment : post.getComments()) {%>
        <div class="card card-body m-3">
            <strong><%comment.getUser().getName();%></strong>
            <p><%comment.getText();%></p>
        </div>
        <%}%>
    </div>
    <%}%>
</div>
</body>
</html>
