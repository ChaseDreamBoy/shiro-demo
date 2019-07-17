

<h1>一、官网 </h1>

<p><span>官网：</span><a href="http://shiro.apache.org/">http://shiro.apache.org/</a></p>
<p><span>官方源码：</span><a href="https://github.com/apache/shiro">https://github.com/apache/shiro</a></p>
<p><span>官网例子：</span><a href="http://shiro.apache.org/tutorial.html">http://shiro.apache.org/tutorial.html</a></p>
<p><span>官网git：</span><a href="https://github.com/apache/shiro/tree/master/samples/quickstart">https://github.com/apache/shiro/tree/master/samples/quickstart</a></p>


<h1>二、常用的 API </h1>

<pre><code>
#获取当前用户
Subject currentUser = SecurityUtils.getSubject(); 

#判断用户已经认证
currentUser.isAuthenticated() 

#用户登录
UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa"); 

#记住我
token.setRememberMe(true); currentUser.login(token); 

#判断是否有角色权限
currentUser.hasRole("schwartz") 

#判断是否有资源操作权限
currentUser.isPermitted("lightsaber:wield") 

#登出
currentUser.logout();
</code></pre>


<h1>三、注解</h1>

<p>
注解判断是否有权限，通过spring aop的形式完成
</p>

<pre><code>
//属于user角色
@RequiresRoles("user")

//必须同时属于user和admin角色
@RequiresRoles({"user","admin"})

//属于user或者admin之一;修改logical为OR 即可
@RequiresRoles(value={"user","admin"},logical=Logical.OR)

//符合index:hello权限要求 
@RequiresPermissions("index:hello")

//必须同时复核index:hello和index:world权限要求 
@RequiresPermissions({"index:hello","index:world"})

//符合index:hello或index:world权限要求即可 
@RequiresPermissions(value={"index:hello","index:world"},logical=Logical.OR)

@RequiresAuthentication
@RequiresUser
@RequiresGusst
</code></pre>


<h1>四、常见异常</h1>

<pre><code>
org.apache.shiro.authc.DisabledAccountException （禁用的帐号）
org.apache.shiro.authc.LockedAccountException （锁定的帐号）
org.apache.shiro.authc.UnknownAccountException （错误的帐号）
org.apache.shiro.authc.ExcessiveAttemptsException （登录失败次数过多）
org.apache.shiro.authc.IncorrectCredentialsException  （错误的凭证）
org.apache.shiro.authc.ExpiredCredentialsException （过期的凭证）
</code></pre>



