# HomewordManageSystem

- @auther:David

12606543:  Dingqian Huang
99128237 Sen Lin


Homework Management System
This system is aiming to provide a basic forum for
  Students to discuss and upload homework
  Teachers to manage the student users

It is based on Idea + Maven + SSM (Spring+Spring MVC+ MyBatis) + shiro + mysql + tomcat. The front-end UI uses AmazeUI. Cloud host uses AWS.
  Environment set up: http://blog.csdn.net/gallenzhang/article/details/51932152
  AmazeUI Introduction (http://amazeui.org/getting-started
Download: https://github.com/amazeui
  Cloud host address:http://13.59.60.98/homework/

Request Flow
View    ->   controller   ->   service   ->   dao    ->   entity
   Users interact with the view that generates the request. Requests are sent to the controller. Then, services and Dao are called, actions are performed, result in entity.



View（JSP pages），in the view directory
　   ▼
Controller，in the controller directory
　　▼
Busness logic Layer，in the service directory
　　▼
Data persistence layer，in the dao directory
　　▼
Entity Layer（JavaBean），in the entity directory

When modifying the layers, it is recommended to modify from the bottom layer to the top layer
Entity Layer
1. First, Modify the entity
a) User (id, username, password, email, etc)
b) Homework (title, content, etc)
c) Weather(temp, etc)
…
d) Then, modify the Page class
MyBatis Interceptor prepares the sql requests and separates it in pages
public class Page<T>
　　　　         List<T> pageNo, pageSize, total
Data persistence layer，
2. Modify the IUserDao
a) get user information based on user name, email
3. Modify the IRoleDao, IHomeworkDao
a) Get the permission resource ID
...
Business logic Layer, service，mainly for Authentication
4. Modify the UserService
a) Change password …
b) Modify the HomeworkService, dd HomeworkCommentService
5. Modify the HomeworkService, HomeworkCommentService (common/service)
a) Query paging data
…
Controller Layer, response to the request, implement the CRUD method
6. LoginController.java登录权限，实现Filter接口，doFilter()方
a) Login processing
b) User registration
c) forget password
7. HomworkController, UserController, RoleController, etc
a) Create the homework, User, Role
b) Read the home work, User, Role
c) Update the homework, User, Role
d) Delete the homework, User, Role
8. UserController，all the request from the /user，which follows the RequestMapping in the MVC framework
a) Request / create (RequestMethodGET, POSt)
b) Request / update (RequestMethodGET, POSt)
c) Request / delete (RequestMethodGET)
…
…
For examples, in the request /*, if the request is /login, there is a Ajax login failed  handling。It calls the user service, the getter from UserUtils.getLoginUserName(), if the name does not match the database, the controller then throws an exception, username or password is incorrect.

If the request is /forgetpassword, it will use the userService to get user by user email to match the user. If such users exist and match, the email will be formed with a random password by using the randomString in the randomUtils service.

Sending the value from the model/entity send, through the Controller, to the View. Attributes obtained in the jsp ${}, remember to add the Annotations, @Controller @RequestMapping, @Service, it use such Annotationa to set up the each layer including the controller.
　　　　


Presentation/View Layer, in the view directory
  The ‘view’ directory contains the view layer pages in the ../ WEB-INF/view.
The ‘error’ directory contains the error pages. The pages that have not been complete designed could also be directed to these pages.
  The ‘static’ directory contains the css files and the third-party libraries

Database Layer
It uses MySql as database
   Database specification is in the db directory which includes tables and attributes.



References
验证码
http://www.cnblogs.com/durui/p/7627226.html

RandomUtils
http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomStringUtils.html
http://blog.csdn.net/djun100/article/details/11879797/

http://www.cnblogs.com/gyjx2016/p/6077134.html
http://blog.csdn.net/djun100/article/details/11879797/
http://www.cnblogs.com/durui/p/7627226.html


Exceptions
http://blog.csdn.net/Mooner_guo/article/details/41979181
Exceptions & PropertiesLoader
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/PropertiesLoader.java
Date Utils
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/DateUtils.java

JsonMapper
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/mapper/JsonMapper.java

Spring MVC Login
http://www.cnblogs.com/tonglin0325/p/6242420.html
12606543:  Dingqian Huang
99128237 Sen Lin
Homework Management System
This system is aiming to provide a basic forum for
  Students to discuss and upload homework
  Teachers to manage the student users

It is based on Idea + Maven + SSM (Spring+Spring MVC+ MyBatis) + shiro + mysql + tomcat. The front-end UI uses AmazeUI. Cloud host uses AWS.
  Environment set up: http://blog.csdn.net/gallenzhang/article/details/51932152
  AmazeUI Introduction (http://amazeui.org/getting-started
Download: https://github.com/amazeui
  Cloud host address:

Request Flow
View    ->   controller   ->   service   ->   dao    ->   entity
   Users interact with the view that generates the request. Requests are sent to the controller. Then, services and Dao are called, actions are performed, result in entity.



View（JSP pages），in the view directory
　   ▼
Controller，in the controller directory
　　▼
Busness logic Layer，in the service directory
　　▼
Data persistence layer，in the dao directory
　　▼
Entity Layer（JavaBean），in the entity directory

When modifying the layers, it is recommended to modify from the bottom layer to the top layer
Entity Layer
1. First, Modify the entity
a) User (id, username, password, email, etc)
b) Homework (title, content, etc)
c) Weather(temp, etc)
…
d) Then, modify the Page class
MyBatis Interceptor prepares the sql requests and separates it in pages
public class Page<T>
　　　　         List<T> pageNo, pageSize, total
Data persistence layer，
2. Modify the IUserDao
a) get user information based on user name, email
3. Modify the IRoleDao, IHomeworkDao
a) Get the permission resource ID
...
Business logic Layer, service，mainly for Authentication
4. Modify the UserService
a) Change password …
b) Modify the HomeworkService, dd HomeworkCommentService
5. Modify the HomeworkService, HomeworkCommentService (common/service)
a) Query paging data
…
Controller Layer, response to the request, implement the CRUD method
6. LoginController.java登录权限，实现Filter接口，doFilter()方
a) Login processing
b) User registration
c) forget password
7. HomworkController, UserController, RoleController, etc
a) Create the homework, User, Role
b) Read the home work, User, Role
c) Update the homework, User, Role
d) Delete the homework, User, Role
8. UserController，all the request from the /user，which follows the RequestMapping in the MVC framework
a) Request / create (RequestMethodGET, POSt)
b) Request / update (RequestMethodGET, POSt)
c) Request / delete (RequestMethodGET)
…
…
For examples, in the request /*, if the request is /login, there is a Ajax login failed  handling。It calls the user service, the getter from UserUtils.getLoginUserName(), if the name does not match the database, the controller then throws an exception, username or password is incorrect.

If the request is /forgetpassword, it will use the userService to get user by user email to match the user. If such users exist and match, the email will be formed with a random password by using the randomString in the randomUtils service.

Sending the value from the model/entity send, through the Controller, to the View. Attributes obtained in the jsp ${}, remember to add the Annotations, @Controller @RequestMapping, @Service, it use such Annotationa to set up the each layer including the controller.
　　　　


Presentation/View Layer, in the view directory
  The ‘view’ directory contains the view layer pages in the ../ WEB-INF/view.
The ‘error’ directory contains the error pages. The pages that have not been complete designed could also be directed to these pages.
  The ‘static’ directory contains the css files and the third-party libraries

Database Layer
It uses MySql as database
   Database specification is in the db directory which includes tables and attributes.



References

JCaptcha
http://www.cnblogs.com/durui/p/7627226.html

RandomUtils
http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomStringUtils.html
http://blog.csdn.net/djun100/article/details/11879797/

http://www.cnblogs.com/gyjx2016/p/6077134.html
http://blog.csdn.net/djun100/article/details/11879797/
http://www.cnblogs.com/durui/p/7627226.html


Exceptions
http://blog.csdn.net/Mooner_guo/article/details/41979181

Exceptions & PropertiesLoader
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/PropertiesLoader.java
Date Utils
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/DateUtils.java

JsonMapper
https://www.javatips.net/api/bbks-master/src/main/java/com/fang/bbks/common/utils/mapper/JsonMapper.java

Spring MVC Login
http://www.cnblogs.com/tonglin0325/p/6242420.html

DataSourceContextHolder
DynamicDataSource
http://blog.csdn.net/gaofuqi/article/details/46417281
http://blog.csdn.net/wangpeng047/article/details/43450189
http://blog.csdn.net/songsongmianbao/article/details/50763392

shiro setting and userRealm
http://www.jianshu.com/p/0083e0171643
http://www.cnblogs.com/question-sky/p/6806419.html
http://blog.csdn.net/u013412066/article/details/50676079

Login/Register
http://blog.csdn.net/Marksinoberg/article/details/51482079
http://www.cnblogs.com/admol/articles/4199546.html
http://blog.csdn.net/owen_william/article/details/51592931
http://blog.csdn.net/buyaore_wo/article/details/50056353

shiro setting and session time
http://blog.csdn.net/u013412066/article/details/50676079

jackson mapper
http://blog.csdn.net/jilongliang/article/details/42888925

login/IUserDao/IUserService/page/ layer design/ demo /tutoril
http://www.cnblogs.com/tonglin0325/p/6242420.html

Spring cache ultil
http://www.bubuko.com/infodetail-1189530.html
http://blog.csdn.net/huanghongfei1/article/details/61195650

mybatis crud
http://blog.csdn.net/hcmdy/article/details/50678509

HttpServletRequestWrapper for prevent injection
http://blog.csdn.net/mid120/article/details/53897550
http://www.cnblogs.com/zdd-java/p/7528496.html

demo
Maven+SSM+shiro+idea+mysql+tomcat+jdk1.8
crud project
http://www.cnblogs.com/zdd-java/p/7528496.html

Study_Framework
http://code.taobao.org/p/study_framework/diff/2/trunk/src/main/java/com/afeng/

crud table spring boot+spring+spring mvc+mybatis+maven
http://www.zuidaima.com/share/3447204503538688.html


supermarket system ssm(spring+springMVC+mybatis)
http://mp.weixin.qq.com/s/2BvKwcFrtd4LgykFxYkzww

readme
http://www.cnblogs.com/tonglin0325/p/6242420.html


