PetGo
====
Overview
----




#### 1.Change jdbc.properties
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

#### 2. Configure the project(Recommend using IntelliJ Idea)
1. Right Click ``pom.xml`` file, click the ``Add as Maven project`` and waiting to complete.
2. After that,  click the ``Reload all maven project`` button on the <strong>Maven</strong> window to install the packages.
3. Click the <strong>Edit Configuration</strong> to add Tomcat Server. Details can be seen as follows
4. Run the Sever and waiting
![Alt text](https://github.com/TimurJiangShan/AntdComponent/blob/master/imageFolder/tomcat-edit.png)<br>
![Alt text](https://github.com/TimurJiangShan/AntdComponent/blob/master/imageFolder/tomcat-deploy.png)<br>
 
 

#### Admin: Account and Password
Admin login page：
http://localhost:8080/admin/login
 
admin 123

#### PetGo
HomePage: http://localhost:8080




