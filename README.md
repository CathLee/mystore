改编自其他作者的servlet项目。使用Springmvc+spring+mybatis+mysql实现图书管理系统

~~~
├─src
  └─main
      ├─java
      │  └─com
      │      └─ly
      │          ├─controller 请求控制
      │          ├─dao        将controller与service联系在一起
      │          ├─model      构造工厂
      │          └─service    处理请求
      │              └─impl   
      ├─resources
      │  ├─mapper    mybatis与数据库交互
      │  └─spring    sprinng配置文件
      └─webapp
          ├─css
          │  └─fonts
          ├─fonts
          ├─img
          ├─js
          ├─jsp      controller层跳转的页面地址
          ├─manage   管理员相关jsp
          ├─person   用户修改信息相关jsp
          └─WEB-INF
~~~

