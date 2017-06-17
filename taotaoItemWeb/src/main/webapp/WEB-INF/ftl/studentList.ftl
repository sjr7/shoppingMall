<html>
<head>
    <title>学生信息</title>
</head>
<body>
<#list list as student>
学生信息:<br>
下标:${student_index}<br>
学号：${student.id} <br>
名字：${student.name} <br>
年龄：${student.age} <br>
家庭地址：${student.address} <br>

    <#if student_index %2 ==0>
        我是if判断出来的结构
       <#else>
        我是else判断出来的结构
    </#if>
<hr>
</#list>
日期取出:${date?date}
时间取出:${date?time}
时间日期取出:${date?datetime}
自定义时间日期取出:${date?string("yyyy-MM-dd HH:mm:ss")}
取空值测试:${aaaa!"取空值的默认值"}
取一个空值:${aaaa!}

测试包含【<#include "hello.ftl">】
</body>
</html>