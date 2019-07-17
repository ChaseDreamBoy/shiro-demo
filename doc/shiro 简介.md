
# 一、简介
官方源码：https://github.com/apache/shiro
<br />
官网：shiro.apache.org

在Web系统中我们经常要涉及到权限问题，例如不同角色的人登录系统，他操作的功能、按钮、菜单是各不相同的，这就是所谓的权限。
<br/>
而构建一个互联网应用，权限校验管理是很重要的安全措施，这其中主要包含：

1. 用户认证 - 用户身份识别，即登录
1. 用户授权 - 访问控制
1. 密码加密 - 加密敏感数据防止被偷窥
1. 会话管理 - 与用户相关的时间敏感的状态信息

Shiro对以上功能都进行了很好的支持，它可以非常容易的开发出足够好的应用，其不仅可以用在JavaSE环境，也可以用在JavaEE环境。Shiro可以帮助我们完成：认证、授权、加密、会话管理、与Web集成、缓存等。这不就是我们想要的嘛，而且Shiro的API也是非常简单。



# 二、组件结构
外部架构
Shiro有三个主要的概念，Subject、SecurityManager 和 Realms。

##### 1、Subject
Subject 就是与系统交互的当前”用户”，用户不仅仅是人，也可以是第三方服务，爬虫等正在与系统交互的任何事物。
<br />
获取 Subject 代码：
<pre><code>
Subject currentUser = SecurityUtils.getSubject();
</code></pre>

在获取了Subject对象之后，就可以执行包括登录、登出、获取会话、权限校验等操作。Shiro的简单易用的API，使得我们在程序的任何地方都能很方便地获取当前登录用户，并进行登录用户的各项基本操作。

##### 2、SecurityManager
<p>
SecurityManager 是Shiro架构的核心，协调内部各个安全组件之间的交互，通常情况下，一旦SecurityManager和它的内部各个组件被配置好之后就不会再用到，开发者通常是查看Subject 的API。
</p>
<p>
当我们和Subject交互的时候，实际上是SecurityManager在背后协调跟Subject安全相关的操作。
</p>
<p>
SecurityManager则管理所有用户的安全操作，它是Shiro框架的核心。一旦其初始化配置完成，我们就不会再调用其相关API了，而是将精力集中在了Subject相关的权限操作上了。
</p>

##### 3、Realms
<p>
Realms 是 Shiro 和用户的应用程序之间扮演着桥梁和连接器的作用。当需要验证或者授权的时候，Shiro从一个或者多个配置的Realms中查找。
</p>
<p>
这种情况下，Realm是一个安全的DAO，它封装了具体数据库连接的细节，当Shiro需要的时候为Shiro提供需要的数据。当配置Shiro的时候，必须配置至少一个Realm来验证和授权。SecurityManager 可以配置多个Realm，至少需要一个。
</p>
<p>
Shiro提供了即用的Realm用来连接到各种安全的数据源，像LDAP, 关系型数据库(JDBC), 文本配置的INI和properties文件等。 用户可以插入自己的Realm 实现，如果默认的Realm 不能满足需求的话。
</p>

##### 4、Subject、SecurityManager 和 Realms 的关系图
![Alt text](https://github.com/ChaseDreamBoy/shiro-demo/blob/master/doc/image/Subject-SecurityManager-Realms.png "Optional title")


**注意：Shiro不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给Shiro即可。**


# 三、详细架构

Subject：主体，可以看到主体可以是任何可以与应用交互的“用户”；

SecurityManager：相当于SpringMVC中的DispatcherServlet或者Struts2中的FilterDispatcher；是Shiro的心脏；所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。

Authenticator：认证器，负责主体认证的，这是一个扩展点，如果用户觉得Shiro默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；

Authorizer：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；

Realm：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；

Subject及Realm，分别是主体及验证主体的数据源。

Session：Shiro提供一个权限的企业级Session解决方案，可以运行在简单的命令行或者是智能手机平台上，也可以工作在大型的集群应用上。
以往我们需要使用Session的一些特性支持时，往往只能将服务部署在web容器或者EJB的Session特性。
Shiro的Session管理方案比上述两种方案都更简单，而且他可以运行在任何应用中，与容器无关。
在Shiro中，session的生命周期都在SessionManager中进行管理

SessionManager：如果写过Servlet就应该知道Session的概念，Session呢需要有人去管理它的生命周期，这个组件就是SessionManager；而Shiro并不仅仅可以用在Web环境，也可以用在如普通的JavaSE环境、EJB等环境；所有呢，Shiro就抽象了一个自己的Session来管理主体与应用之间交互的数据；这样的话，比如我们在Web环境用，刚开始是一台Web服务器；接着又上了台EJB服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到Memcached服务器）；

SessionDAO：DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；另外SessionDAO中可以使用Cache进行缓存，以提高性能；

CacheManager：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能

Cryptography：密码模块，Shiro提高了一些常见的加密组件用于如密码加密/解密的。

The SecurityManager
SecurityManager 执行安全操作，管理用户的状态，Shiro的默认SecurityManager 实现包括以下部分
Authentication
Authorization
Session Management
Cache Management
Realm coordination
Event propagation
“Remember Me”
Services
Subject creation
Logout 
and more.
为了简化配置并且使应用灵活，Shiro的实现都是高度模块化设计的。 
SecurityManager 通常扮演一个轻量的容器，代表其他组件，这个装饰模式的设计可以通过上面的架构图看出。
其他组件各司其职，而SecurityManager 负责协调各个组件。





