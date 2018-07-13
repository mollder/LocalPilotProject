new Vue({
    el: "#add-book-div",
    data: {
        visible : false
    },
    methods: {
        add: function (event) {
            this.visible = !this.visible;
        }
    }
});