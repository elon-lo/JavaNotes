<%--
  Created by IntelliJ IDEA.
  User: elonlo
  Date: 2023/6/18
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
  <body>
    <h3>welcome to index</h3>

    hello,<shiro:principal /><br><br>

    <shiro:hasRole name="user">
      <a href="user.jsp">user page</a><br><br>
    </shiro:hasRole>

    <shiro:lacksRole name="user">
      <shiro:principal />没有user角色<br><br>
    </shiro:lacksRole>

    <shiro:lacksPermission name="user">
      <shiro:principal />没有user权限<br><br>
    </shiro:lacksPermission>

    <a href="/shiro/ShiroAnnotationTest">ShiroAnnotationTest</a><br><br>

    <shiro:hasRole name="admin">
      <a href="admin.jsp">admin page</a><br><br>
    </shiro:hasRole>

    <a href="/shiro/logout">logout</a>
  </body>
</html>
