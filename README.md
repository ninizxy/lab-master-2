znn的碎碎念：
这是一个前后端综合项目，可以运行出一个可正常传输数据的网页，写的是责任教师端（选择原因：责任教师功能较多）的部分功能，
剩余还有待完善，普通教师、学生的页面可以通过复制该部分代码，修改部分内容进行实现。

·项目结构：
   后端：
   SpringBoot 2.1.0 + Mybatis-plus + hutool工具包 + Apache poi

   相关文件说明：
   Java文件夹下所有
   pom.xml：所有使用到的依赖都要在此声明

   前端：
   Vue2.0 + ElementUI + Jquery + tinymce（富文本插件）

   相关文件说明：
   css:存放css文件，设置外观
   fonts
   file:上传文件用，此处用来存储上传的头像
   images：用于存储素材图片，包括主页背景、图标
   js:存放js文件，设置逻辑
   page.end:存放end相关html文件
   page.front：存放front相关html文件

·移植后如何跑通项目
   设置数据库：
    1、将x-admin.sql在navicat中创建同名数据库，右键点击该数据库，选择"运行SQL文件"，然后选中本项目文件夹中的x-admin.sql文件创建即可
    2、将application.yml第6、7行的信息修改为本地数据库信息
   选择运行项为Application，点击绿色小三角或者绿色小爬虫运行，成功启动项目后，访问（建议使用Chrome）：http://localhost:9999/page/end/login.html
   初始Demo只设置了用户名密码登录，没有实现email登录
   登录用户名：admin，密码：admin

   
   
   
