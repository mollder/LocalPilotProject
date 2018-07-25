<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <title>Awesome Stories</title>
</head>
<body>
    <div class="container">
        <story plot="My horse is amazing."></story>
        <story plot="Narwhals invented Shish Kebab"></story>
        <story plot="The dark side of the Force is stronger."></story>
    </div>

</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.3.4/vue.js"></script>
<template id="story-template">
    <h1>{{ plot }}</h1>
</template>
<script type="text/javascript">
    Vue.component('story', {
        props: ['plot'],
        template: "#story-template"
    });

    new Vue({
        el: '.container',
    })
</script>
</html>