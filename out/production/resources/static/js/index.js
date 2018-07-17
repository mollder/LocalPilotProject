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

var findBook = new Vue({
    el: '#find-book-div',
    data: {
        bookList: '',
        checkBookId: ''
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
                    findBook.bookList = ready;
                    console.log(response);
                });
        },

        hideBookList: function() {
            findBook.bookList = '';
        },
    }
});

var borrowReturnBook = new Vue({
    el: '#borrow-return-book-div',
    data: {
        findBorrowReturnBook: '',
        index: 0
    },
    methods: {
        findBookByBookId: function () {
            if (findBook.checkBookId === '') {
                return '';
            } else {
                for (var i = 0; i < findBook.bookList.length; i++) {
                    if (findBook.bookList[i]['bookId'] === findBook.checkBookId) {
                        borrowReturnBook.index = i;
                        return findBook.bookList[i];
                    }
                }
            }
        },
        borrowBookFunc: function () {
            borrowReturnBook.findBorrowReturnBook = borrowReturnBook.findBookByBookId();
            if (borrowReturnBook.findBorrowReturnBook !== '') {
                if (borrowReturnBook.findBorrowReturnBook.borrow === '대여가능') {
                    axios({
                        method: 'put',
                        url: 'http://127.0.0.1:8082/myproject/book',
                        responseType: 'json',
                        data: {
                            bookId: borrowReturnBook.findBorrowReturnBook.bookId,
                            bookName: borrowReturnBook.findBorrowReturnBook.bookName,
                            borrow: false,
                            borrowDate: borrowReturnBook.findBorrowReturnBook.borrowDate,
                            idx: borrowReturnBook.findBorrowReturnBook.idx
                        },
                    }).then(function (response) {
                        console.log(response.data);
                        findBook.bookList[borrowReturnBook.index] = response.data;
                        findBook.bookList[borrowReturnBook.index].borrow = "대여중";
                    });
                } else {
                    alert("이 책은 이미 대여중입니다.");
                }
            } else {
                alert("대여하실 책을 선택해주세요");
            }
        },
        returnBookFunc: function () {
            borrowReturnBook.findBorrowReturnBook = borrowReturnBook.findBookByBookId();
            if (borrowReturnBook.findBorrowReturnBook !== '') {
                if (borrowReturnBook.findBorrowReturnBook.borrow === '대여중') {
                    axios({
                        method: 'put',
                        url: 'http://127.0.0.1:8082/myproject/book',
                        responseType: 'json',
                        data: {
                            bookId: borrowReturnBook.findBorrowReturnBook.bookId,
                            bookName: borrowReturnBook.findBorrowReturnBook.bookName,
                            borrow: true,
                            borrowDate: borrowReturnBook.findBorrowReturnBook.borrowDate,
                            idx: borrowReturnBook.findBorrowReturnBook.idx
                        },
                    }).then(function (response) {
                        console.log(response.data);
                        findBook.bookList[borrowReturnBook.index] = response.data;
                        findBook.bookList[borrowReturnBook.index].borrow = "대여가능";
                    });
                } else {
                    alert("이 책은 대여 가능한 책입니다.");
                }
            } else {
                alert("반납하실 책을 선택해주세요");
            }
        }
    }
});
/*
var returnBook = new Vue({
    method: {
        returnBookfunc: function() {
            axios({
                method:'put',
                url:'http://127.0.0.1:8082/myproject/book',
                responseType:'json'
            }).then(function (response) {

            });
        }
    }
});*/