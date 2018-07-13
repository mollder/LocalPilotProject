<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My test page</title>
    <script src="https://unpkg.com/vue@2.3.3"></script>
</head>
<body>
    <p id = "testText"> readme </p>

    <div id = 'add-book-div'>
    <input type="button" id = "add-book-button" value="책 추가" v-on:click="add">

        <form id = "add-book-form" action = "/myproject/book" method="post" v-show = "visible">
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" />
            </div>

            <div>
                 <label for="id">id:</label>
                 <input type="text" id="bookId" />
            </div>
            <input type="submit" value="전송">
        </form>
    </div>

    <p> ${message} </p>

    <script src = "js/index.js"></script>
</body>
</html>