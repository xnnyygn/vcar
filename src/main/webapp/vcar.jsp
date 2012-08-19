<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Visual Crop and Resize</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/vcar" method="post">
      <fieldset>
        <legend>Visual Crop and Resize</legend>
        <img src="${pageContext.request.contextPath}/showImage?vcarId=${param.vcarId}" />
      </fieldset>
    </form>
  </body>
</html>
