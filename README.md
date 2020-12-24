PetGo is a social networking site for contemporary pet owners. The website can not only
provide communication problems between pet owners and pet lovers, but also enable pet
shops to display the content and particularities of their stores on the website, so that pet
owners can have a more professional platform to solve problems with pets. Related issues.
Each user can post a description of the problem or show a pet, and other users can comment
or answer the post. In this way, users can communicate well through the platform, and users
can get to know each other. Pet shops can display their shop pairs on the map provided by the
platform. When choosing a pet shop, users can be more targeted.


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




