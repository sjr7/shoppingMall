# shoppingMall
使用主流框架组合SSM开发,并引入新技术,全面丰富的一个商城项目

#### java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "http-bio-8083-exec-1"  内存溢出
```html
java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "http-bio-8083-exec-1"
```
看到这个错误大概就知道是跟内存有点关系了,直接去`Tomcat`里面添加运行参数吧,下面是我在`IDEA`里面的做法
 - MAVEN tomcat插件
 ![mavenTomcat.png](note/images/mavenTomcat.png)  
 - IDEA配置的Tomcat容器
 ![mavenTomcat.png](note/images/ideaTomcat.png)  
参数:  ` -server -XX:PermSize=256M -XX:MaxPermSize=512m `
参数是根据你自己需求去改的,在你内存不是很紧张的情况下,尽量分配高一点的内存,减少`JVM`频繁`GC`次数,这里只是设置了几个基本的参数，参数的意义就是
  + `-server`是一个要为第一个参数的
  + `-XX:PermSize=256M` 持久区初始化的内存大小
  + `-XX:MaxPermSize=512m ` 持久去最大的内存大小
  




#### SpringMvc文件上传失败问题
在进行文件上传的时候出现一个这样的问题  
![SpringMvcFileUPloadError](note/images/SpringMvcFileUPloadError.png)  
仔细一看,我猜测是Spring文件上传的配置没有配置,然后去``applicationContext-Mvc.xml``中看了下我的配置文件
````xml
 <bean id="commonsMultipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--设置默认的编码-->
        <property name="defaultEncoding" value="UTF-8"/>
        <!--设置文件上传的最大值-->
        <property name="maxUploadSize" value="5242880"/>
    </bean>
````  
我感觉也没有配错,然后查了下资料,发现``bean``的名字错了,改为
```xml
 <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--设置默认的编码-->
        <property name="defaultEncoding" value="UTF-8"/>
        <!--设置文件上传的最大值-->
        <property name="maxUploadSize" value="5242880"/>
    </bean>
```
原理``DispatcherServlet`
![DispatcherServlet](note/images/DispatcherServlet.png)
这里把bean的名字固定了的

#### 搭建redis集群的时候无法安装Ruby搭建集群脚本
执行`gem install redis-3.0.0.gem`是报错的，找不到这个东西
```
ERROR:  Could not find a valid gem 'redis-3.0.0.gem' (>= 0) in any repository
```
然后`Google`找到了[官网的脚本](https://rubygems.org/gems/redis/versions/3.0.0?locale=zh-CN)
```
gem install redis -v 3.0.0
```
