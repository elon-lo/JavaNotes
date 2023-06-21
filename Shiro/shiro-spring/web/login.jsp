<%--
  Created by IntelliJ IDEA.
  User: elonlo
  Date: 2023/6/18
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>
    <h3>please login in</h3>

    <form action="/shiro/login">
      <label>
        <input type="text" name="username" /><br><br>

        <input type="password" name="password" /><br><br>

        <input type="submit" name="submit" /><br><br>
      </label>
    </form>
  </body>
</html>
