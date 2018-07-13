<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>My test page</title>
</head>
<body>
<script src="https://unpkg.com/vue@2.3.3"></script>

<div id = "app">
    <todo-item v-bind:todo="todo" v-for="todo in Todos" :key="todo.id"></todo-item>

    <!-- 할일 #3 -->
    <!-- todo-list 컴포넌트 등록 -->
    <todo-list v-bind:passed-data="language" v-for="language in Languages" :key="language.id"></todo-list>

</div>

<script>


</script>

</body>
</html>