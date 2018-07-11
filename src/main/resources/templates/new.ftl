<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>RestTemplate Demo</title>
    <meta charset=utf-8”/>
</head>
<body>
<h1>New</h1>

<form action="#" th:action="@{/posts}" th:object="${book}" method="post">
    <p>title: <input type="text" th:field="*{bookId}" /></p>
    <p>content: </p>
    <p><textarea th:field="*{bookName}" /></p>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>