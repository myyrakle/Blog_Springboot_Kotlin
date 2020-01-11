package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.UserEntity
import org.myyrakle.myblog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService
{
    @Autowired
    lateinit var userRepository: UserRepository

    fun findByUsername(username: String): UserEntity
        = userRepository.findByUsername(username)

    fun viewUserAll()
    {
        var users = userRepository.findAll()
        for(e in users)
            println(e)
    }
}