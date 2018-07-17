<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <title>Awesome Stories</title>
</head>
<body>
    <div class="container">
        <h1>Let's hear some stories!</h1>
        <div>
            <h3>Alex's stories</h3>
            <ul class="list-group">
                <li v-for="story in storiesBy('Alex')" class="list-group-item">
                    {{ story.writer }} said "{{ story.plot }}"
                </li>
            </ul>
            <h3>John's stories</h3>
            <ul class="list-group">
                <li v-for="story in storiesBy('John')" class="list-group-item">
                    {{ story.writer }} said "{{ story.plot }}"
                </li>
            </ul>
        </div>
        <pre>
            {{ $data }}
        </pre>
    </div>
</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.3.4/vue.js"></script>
<script type="text/javascript">
    new Vue({
        el: '.container',
        data: {
            stories: [
                {
                    plot: "I crashed my car today!",
                    writer: "Alex"
                },
                {
                    plot: "Yesterday, someone stole my bag!",
                    writer: "John"
                },
                {
                    plot: "Someone ate my chocolate...",
                    writer: "Alex"
                },
            ]
        },
        methods: {
            storiesBy: function (writer) {
                return this.stories.filter(function (story) {
                    return story.writer ===writer;
                })
            },
        }
    });
</script>
</html>