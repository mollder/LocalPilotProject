<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>포털개발팀 도서대여</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
    <h1 id = "head-line"> 포털개발팀 도서대여 시스템 </h1>

    <div id = 'add-book-div'>
        <button id = "add-book-button" @click="add">책 추가</button>

        <form id = "add-book-form" action = "/myproject/book" method="post" v-show = "visible">
            <div>
                <label for="name">Name:</label>
                <input type="text" name="name" />
            </div>

            <div>
                 <label for="id">id:</label>
                 <input type="text" name="Id" />
            </div>
            <input type="submit" value="전송"/>
        </form>
    </div>

    <div id = 'find-book-div' class="container">
        <button id="check-book-button" @click="findBookList"> 책 리스트 확인 </button>
        <button id="hide-book-button" @click="hideBookList"> 책 리스트 숨기기</button>

        <div>
            <ul class="list-group">
                <li v-for="book in bookList" :key="book.bookId" class="list-group-item">
                   <input type="radio" v-bind:id ='book.bookId' v-bind:value="book.bookId" v-model="checkBookId"/>
                    책 이름 : {{book.bookName}} <br>
                    책 아이디: {{book.bookId}}  <br>
                    책 대여여부 : {{book.borrow}} <!-- 여기에 새로운 변수를 만들어서 대여중인 경우 대여자 이름까지 표시  -->
                </li>
            </ul>
        </div>
    </div>

    <div id = 'borrow-return-book-div'>
        <button id="book-borrow-button" @click="borrowBookFunc">대여</button>
        <button id="book-return-button" @click="returnBookFunc">반납</button>
    </div>

    <script src = "js/index.js"></script>
</body>
</html>