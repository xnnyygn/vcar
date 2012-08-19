<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Enter Online Image URL or Upload</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/retrieveImage" method="post" enctype="multipart/form-data">
      <fieldset>
        <legend>Enter Online Image URL or Upload</legend>
        <label for="imageUrl">Image URL</label>
        <input type="text" name="imageUrl" /><br />
        <label for="image">Image</label>
        <input type="file" name="image" /><br />
        <button type="submit">Submit</button>
      </fieldset>
    </form>
  </body>
</html>
