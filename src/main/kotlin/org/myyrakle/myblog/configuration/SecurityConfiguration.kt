package org.myyrakle.myblog.configuration

import org.myyrakle.myblog.domain.Role
import org.myyrakle.myblog.service.UserLoginDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
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

    @Autowired
    private lateinit var loginService: UserLoginDetailsService

    override fun configure(http: HttpSecurity?)
    {
        http?.let {
            it.authorizeRequests()
                    .antMatchers("/admin/**").hasRole(Role.ADMIN.roleless())
                    .antMatchers("/member/**").hasRole(Role.MEMBER.roleless())
                    .antMatchers("/**").permitAll()
            .and()
                    .formLogin()
                    .loginPage("/login_form")
                    .successForwardUrl("/home")
                    .permitAll()
            .and()
                    .logout()
                    .logoutSuccessUrl("/home")
                    .permitAll()
            .and()
                    .exceptionHandling()
                    .accessDeniedPage("/")
        }
    }
}