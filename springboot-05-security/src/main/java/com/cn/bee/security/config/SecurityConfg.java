package com.cn.bee.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mervin on 2018/7/4.
 */
@EnableWebSecurity
public class SecurityConfg extends WebSecurityConfigurerAdapter{

    /**
     * 定义授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected  void configure(HttpSecurity http) throws  Exception{
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        //开启自动配置的登陆功能，如果没有登陆/没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        //1./login来到登陆页
        //2.重定向到/login?error表示登陆失败
        //3.更新相信规则
        //4.默认post形式的/login代表处理登录
        //5.一旦定制loginPage；那么loginPage的post请求就是登录

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功后来到首页
        //1、访问/logout表示用户注销，清空session
        //2.注销成功会返回/login？logout页面

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //登录成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录，点击注销会删除cookie
    }

    /**
     * 定义认证规则
     */
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        //jdbc:auth.jdbcAuthentication(
        //内存
        auth.inMemoryAuthentication().withUser("mervin").password("123456").roles("VIP1","VIP2")
                .and()
                .withUser("jerry").password("123456").roles("VIP2","VIP3")
                .and()
                .withUser("zhangsan").password("123456").roles("VIP1","VIP3");
    }


}
