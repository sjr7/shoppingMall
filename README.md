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

#### 　操作redis集群出现异常
部分异常出错代码:  
```
redis.clients.jedis.exceptions.JedisMovedDataException: MOVED 11149 127.0.0.1:7001

	at redis.clients.jedis.Protocol.processError(Protocol.java:108)
	at redis.clients.jedis.Protocol.process(Protocol.java:142)
	at redis.clients.jedis.Protocol.read(Protocol.java:196)
	at redis.clients.jedis.Connection.readProtocolWithCheckingBroken(Connection.java:288)
	at redis.clients.jedis.Connection.getStatusCodeReply(Connection.java:187)

```
这个错误的原因就是跟`redis`集群有关系，`redis`把这个key分配到了`127.0.0.1:7001`节点上去了，然后并没有找到这个节点，所以报错了.查看`applicationContext-redis.xml`文件  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd">
    <!--单机版跟集群版只能选其中一个-->
    <!-- 1.配置单机版的连接 -->
     <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
         <constructor-arg name="host" value="127.0.0.1"/>
         <constructor-arg name="port" value="6379"/>
     </bean>
     <bean id="jedisClientPool" class="com.taotao.jedis.JedisClientPool"/>

    <!-- 2.集群版的配置 -->
    <!-- <bean id="jedisCluster" class="redis.clients.jedis.JedisCluster">
        <constructor-arg>
            <set>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="6379"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7000"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7001"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7002"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7003"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7004"/>
                </bean>
            </set>
        </constructor-arg>
    </bean>
    <bean id="jedisClientCluster" class="com.taotao.jedis.JedisClientCluster"/>    -->
 </beans>
```
可以发现这里其实是配置一个`单机版`跟`集群版`的两个配置,然后这里是使用了`单机版`的，所以的话集群是使用不了的,然后就会出现这个错误了，把`单机版`的配置注释掉，再把`集群版`的**取消注释**就可以了,如下:
````xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd">
     <!--单机版跟集群版只能选其中一个-->
    <!-- 1.配置单机版的连接 -->
    <!--    <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
         <constructor-arg name="host" value="127.0.0.1"/>
         <constructor-arg name="port" value="6379"/>
     </bean>
     <bean id="jedisClientPool" class="com.taotao.jedis.JedisClientPool"/>    -->

    <!-- 2.集群版的配置 -->
     <bean id="jedisCluster" class="redis.clients.jedis.JedisCluster">
        <constructor-arg>
            <set>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="6379"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7000"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7001"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7002"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7003"/>
                </bean>
                <bean class="redis.clients.jedis.HostAndPort">
                    <constructor-arg name="host" value="127.0.0.1"/>
                    <constructor-arg name="port" value="7004"/>
                </bean>
            </set>
        </constructor-arg>
    </bean>
    <bean id="jedisClientCluster" class="com.taotao.jedis.JedisClientCluster"/>   
</beans>
````













