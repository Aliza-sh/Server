<html>
<body>
<img src="/src/img/aliza.jpg" width="300"/>
<h1>Students:</h1>
<#list data as student>
    <h2>${student.firstName} ${student.lastName} (${student.code})</h2>
</#list>
</body>
</html>

