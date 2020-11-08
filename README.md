
## 1.Change jdbc.properties
Database Name: pet<br>
Database Version: mysql5.6+<br>
In this project, we use the AWS RDS which is a remote databse. So for your convinence, you should change the jdbc.properties.<br>
If you use mysql on localhost, you may need to copy these code to jdbc.properties file
 
```
   jdbc.driver=com.mysql.jdbc.Driver
   jdbc.url=jdbc:mysql://localhost:3306/pet?useUnicode=true&characterEncoding=UTF-8
   jdbc.username=root
   jdbc.password=123456
```
After that, you need to create a database called <strong>pet</strong><br>
The sql is in the ``pet_system.sql``. You need to copy this sql to the Mysql and run.

## 2. Configure the project(Recommend using IntelliJ Idea)
1. Right Click ``pom.xml`` file, click the ``Add as Maven project`` and waiting to complete.
2. After that,  click the ``Reload all maven project`` button on the <strong>Maven</strong> window to install the packages.
3. Click the <strong>Edit Configuration</strong> to add Tomcat Server. 

![Alt text](https://github.com/TimurJiangShan/AntdComponent/blob/master/imageFolder/tomcat-edit.png)

## 后台账号密码
管理员登录页面：
http://localhost:8080/admin/login
 
admin 123

## 答疑
1. 首页这三个帖子是如何通过后台控制展示的
- 后台代码：IndexController 95-97行
- 前台代码：index.jsp  33-59行
流程如下：
    - 用户通过浏览器访问项目首页 http://localhost:8080/
    - 后台 IndexController 类的 index 方法接受请求
    - 该方法第  95-97 行分别查询这三者的数据列表
    - 第 104 行返回前端页面 index.jsp，并将map数据传输给前端
    - index.jsp 33-59 行遍历数据，进行展示


