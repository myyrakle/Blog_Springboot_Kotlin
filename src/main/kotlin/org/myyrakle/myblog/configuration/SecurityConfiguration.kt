package org.myyrakle.myblog.configuration

import org.myyrakle.myblog.service.UserLoginDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter()
{
    @Bean
    fun passwordEncoder(): PasswordEncoder
        = BCryptPasswordEncoder()

    override fun configure(webSecurity: WebSecurity): Unit
    {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/images/**")
    }

    @Throws(Exception::class)
    override fun configure(authentication: AuthenticationManagerBuilder)
    {
        authentication.userDetailsService<UserDetailsService>(loginService).passwordEncoder(passwordEncoder())
    }

    @Autowired
    private lateinit var loginService: UserLoginDetailsService

    override fun configure(http: HttpSecurity?)
    {
        http?.let {
            it.authorizeRequests()
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.roleless())
                    .antMatchers("/member/**").hasRole(Role.MEMBER.roleless())
                    //.antMatchers("/editor").hasAnyRole()
                    .antMatchers("/**").permitAll()
            .and()
                    .formLogin()
                    .loginPage("/login_form?message=needLogin")
                    .loginProcessingUrl("/login.do")
                    .defaultSuccessUrl("/")
                    .failureUrl("/error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
            .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/home")
                    .permitAll()
            .and()
                    .exceptionHandling()
                    .accessDeniedPage("/error")
        }
    }
}