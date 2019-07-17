package com.xh;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 此处将Mock登录类抽取为一个基类，注意需要将该类声明为抽象类。否则会报“No Runnable Methods”，这是因为BaseMockBeforeTests中没有单元测试方法，所以产生异常：已加载至SpringBootTest容器中的单元测试类中没有可运行的单元测试方法。若将该类声明为抽象类，则该类不会被加载进SpringBootTest容器，而是根据多态，加载其子类对象。
 *
 * @author xiaohe
 * @version V1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseMockBeforeTests {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void loginByMock() {
        final String userName = "admin";
        final String password = "123456";
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);
        SecurityUtils.setSecurityManager(securityManager);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Subject subject = new WebSubject
                .Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);
        ThreadContext.bind(subject);
    }

    String getReturnValue(String uri) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
