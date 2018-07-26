Vue.component('page-button', {
    template: '<button v-bind:id="pageId" v-bind:value="pageNum" @click="getBookList">{{pageNum}}</button>',
    data: function () {
        findBook.pageIndex++;
        return {
            pageId: 'page' + findBook.curPageGroup * 10 + findBook.pageIndex,
            pageNum: findBook.curPageGroup * 10 + findBook.pageIndex
        }
    },
    methods: {
        getBookList: function () {
            axios({
                method: 'get',
                url: './book',
                contentType: 'application/json',
                params: {
                    pageNum: this.pageNum
                }
            }).then(function (response) {
                // 리스폰 개수 만큼 html 태그를 만들어서 값을 집어넣어주면 됨
                var ready = response.data.map(function (book) {
                    if (book.borrow === false) book.borrow = '대여가능';
                    else book.borrow = '대여중';
                    return book
                });
                findBook.bookList = ready;
                console.log(response);
            });
        }
    }
});

var addBook = new Vue({
    el: "#add-book-div",
    data: {
        visible: false
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
        bookList: [],
        checkBookId: '',
        pageButtons: [],
        bookCount: 0,
        totalPageNum: 0,
        curPageNum: 0,
        curPageGroup: 0,
        pageIndex: 0
    },
    mounted: function () {
        axios({
            method: 'get',
            url: './bookNum', // 상대경로
            contentType: 'application/json'
        }).then(function (response) {
            // 리스폰 개수 만큼 html 태그를 만들어서 값을 집어넣어주면 됨
            findBook.bookCount = response.data;
            findBook.totalPageNum = parseInt(findBook.bookCount / 5);
            if (findBook.bookCount % 5 !== 0) findBook.totalPageNum++;

            findBook.curPageNum = 1;
            findBook.curPageGroup = 0;

            for (var i = 1; i <= 10 && findBook.curPageGroup * 10 + i <= findBook.totalPageNum; i++) {
                findBook.pageButtons.push('page-button');
            }
        });
        axios({
            method: 'get',
            url: './book',
            params: {
                pageNum: 1
            }
        }).then(function (response) {
            // 리스폰 개수 만큼 html 태그를 만들어서 값을 집어넣어주면 됨
            var ready = response.data.map(function (book) {
                if (book.borrow === false) {
                    book.borrow = '대여가능';
                    book.borrow
                }
                else {
                    book.borrow = '대여중';
                }

                if (book.memberName !== "") {
                    book.memberName = '대여자 : ' + book.memberName;
                }
                return book
            });
            findBook.bookList = ready;
            console.log(response);
        });
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
                        url: './book',
                        contentType: 'application/json',
                        data: {
                            book: {
                                bookId: borrowReturnBook.findBorrowReturnBook.bookId,
                                bookName: borrowReturnBook.findBorrowReturnBook.bookName,
                                isBorrow: false
                            }
                        },
                    }).then(function (response) {
                        console.log(response.data);
                        findBook.bookList[borrowReturnBook.index] = response.data;
                        findBook.bookList[borrowReturnBook.index].borrow = '대여중';
                        window.location.reload();
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
                        url: './book',
                        contentType: 'application/json',
                        data: {
                            book: {
                                bookId: borrowReturnBook.findBorrowReturnBook.bookId,
                                bookName: borrowReturnBook.findBorrowReturnBook.bookName,
                                isBorrow: true
                            }
                        },
                    }).then(function (response) {
                        console.log(response.data);
                        findBook.bookList[borrowReturnBook.index] = response.data;
                        findBook.bookList[borrowReturnBook.index].borrow = '대여가능';
                        findBook.bookList[borrowReturnBook.index].memberName = '';
                        window.location.reload();
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