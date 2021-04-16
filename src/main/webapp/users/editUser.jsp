<%--
  Created by IntelliJ IDEA.
  User: falcon
  Date: 16.04.2021
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<div class="col-xl-8 col-lg-7">
    <div class="card shadow mb-4">
        <!-- Card Header -->
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h6 class="m-0 font-weight-bold text-primary">Edytuj użytkownika</h6>
        </div>
        <!-- Card Body -->
        <div class="card-body">
            <div class="p5">
                <form action="/user/edit" method="post">
                    <input type="text" name="name" value="${user.userName}">
                    <input type="text" name="email" value="${user.email}">
                    <input type="password" name="pass" placeholder="Hasło">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" name="submit" value="Zapisz">
                    <input type="submit" name="submit" value="Anuluj">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
