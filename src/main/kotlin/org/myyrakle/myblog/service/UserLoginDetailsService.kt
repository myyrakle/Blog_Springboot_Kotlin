package org.myyrakle.myblog.service

import org.myyrakle.myblog.domain.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserLoginDetailsService: UserDetailsService
{
    private var passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun loadUserByUsername(username: String?): UserDetails
    {
        var authorities = ArrayList<GrantedAuthority>()

        if(username == "admin")
        {
            authorities.add(SimpleGrantedAuthority(Role.ADMIN.value))
        }
        else
        {
            authorities.add(SimpleGrantedAuthority(Role.MEMBER.value))
        }

        return User(username, "?", authorities)
    }
}