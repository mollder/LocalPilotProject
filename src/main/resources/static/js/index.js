var addBook = new Vue({
    el: "#add-book-div",
    data: {
        visible : false
    },
    methods: {
        add: function () {
            this.visible = !this.visible;
        }
    }
});

var findBookList = new Vue({
    el: '#check-book-div',
    data: {
      bookList: ''
    },
    methods: {
        findBookList: function() {
            axios({
                method:'get',
                url:'http://127.0.0.1:8082/myproject/book',
                responseType:'json'
            })
                .then(function(response) {
                    // 리스폰 개수 만큼 html 태그를 만들어서 값을 집어넣어주면 됨
                    var ready = response.data.map(function (book) {
                        if(book.borrow === false) book.borrow = '대여가능';
                        else book.borrow = '대여중';
                        return book
                    });
                    findBookList.bookList = ready;
                    console.log(response);
                });
        },

        hideBookList: function() {
            findBookList.bookList = '';
        }
    }
});