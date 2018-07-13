<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My test page</title>
    <script src="https://unpkg.com/vue"></script>
</head>
<body>
    <p id = "testText"> readme </p>

    <input type="button" id = "add-book" value="책 추가" v-on:click="addBook">

    <div id = "book-table">

    </div>
    <script src = "js/index.js"></script>
</body>
</html>