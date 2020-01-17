package org.myyrakle.myblog.service

import lombok.Getter
import org.myyrakle.myblog.configuration.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
@Getter
class UserLoginDetailsService: UserDetailsService
{
    @Autowired
    lateinit var userService: UserService

    private var passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun loadUserByUsername(username: String?): UserDetails
    {
        var authorities = ArrayList<GrantedAuthority>()

        val userEntity = userService.findByUsername(username ?: "").get()

        if(userEntity.itsRole == "ADMIN")
        {
            authorities.add(SimpleGrantedAuthority(Role.ADMIN.hasRole()))
        }
        else
        {
            authorities.add(SimpleGrantedAuthority(Role.MEMBER.hasRole()))
        }

        return User(username, passwordEncoder.encode(userEntity.password), authorities)
    }
}