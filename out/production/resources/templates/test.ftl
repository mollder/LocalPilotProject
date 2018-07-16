<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <title>Awesome Stories</title>
</head>
<body>
    <div class="container">
        <h1> the multiplication table of 4.</h1>
        <ul class="list-group">
            <li v-for="(story, index) in stories" class="list-group-item">
                {{ index + 1 }} {{ story.writer }} said "{{ story.plot}}"
            </li>
        </ul>
    </div>
</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.3.4/vue.js"></script>
<script type="text/javascript">
    new Vue({
        el: '.container',
        data: {
            stories: [
                {
                    plot: "story1",
                    writer: "wri1"
                },
                {
                    plot: "story2",
                    writer: "wri2"
                },
                {
                    plot: "story3",
                    writer: "wri3"
                },
            ]
        }
    })
</script>
</html>