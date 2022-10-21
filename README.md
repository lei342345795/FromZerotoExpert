# FromZerotoExpert
一、一个最简单的web项目
1. 创建一个远程仓库√
2. 拥有一个最简单的web页面√
3. 拥有短暂的记忆√
4. 关于任务三的思考
    * cookie的值value尽量不要用true, false 
   > 在判断用户是否是首次来网站时，在前端判断比较好还是在后端判断比较好
    * 前端：性能较好，不需要进行网络传输；安全性较差（但仅仅是判断是否是首次使用，其实也不会涉及过多的安全性问题）
    * 后端：将判断放在后端更符合前后端分工的要求；

二、登陆与注册模块
1. 拥有注册功能√
   这里进行了简单的检验和要求——用户名长度不超过8位，密码长度在8-14位以及用户名不能重复，后期进一步完善应当考虑用户名和密码中的特殊字符和一些格式要求。另外，前端目前的提示都是以alert进行提示的，后期可以考虑在页面上加入元素实现文字或者图片提示。

   前端这里遇到了比较大的问题，之前从来没有自己独立写过前端代码，springboot中的前端都是直接复制视频附带的资料的。这次是第一次尝试
   
   1. 前端文件的结构要求

      public: 用来存放html文件
      static: 用来存放静态资源
         
         - imgs：图片资源
         - js： js文件
         - style： css文件
   2. 两个有关前端资源的重要配置
      
      * `web:resources:static-locations:classpath:/static/imgs classpath:/static/js classpath:/static/style classpath:/public`
         
         spring.resources.static-locations用于告诉Spring Boot应该在何处查找静态资源文件，这是一个列表性的配置，查找文件时会依赖于配置的先后顺序依次进行
      
         不配置的话可以访问static下的直属文件，或通过子文件夹/文件的方式访问；配置子文件夹后可以通过文件名直接访问
      
      * `mvc:static-path-pattern:/FromZerotoExpert/static/**`
         
         spring.mvc.static-path-pattern代表的含义是我们应该以什么样的路径来访问静态资源，换句话说，只有静态资源满足什么样的匹配条件，Spring Boot才会处理静态资源请求
      * 参考：https://blog.csdn.net/weixin_38924500/article/details/109739021
   3. 通过server:servlet:context-path配置了url前缀后，访问图片的地址也应加上这个前缀
      * 更改了这个配置之后访问静态资源那里会出现比较大的坑
      * 参考：https://www.cnblogs.com/hxun/p/11934769.html
   4. 调试前端代码时在控制台禁用缓存！！！（血泪教训）
   
   另外，Java远程连接MySQL时也遇到了很多问题，总结见：https://blog.csdn.net/weixin_57923019/article/details/127439538