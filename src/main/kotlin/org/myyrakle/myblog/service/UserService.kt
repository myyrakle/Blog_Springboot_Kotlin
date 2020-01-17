package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.UserEntity
import org.myyrakle.myblog.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService
{
    @Autowired
    lateinit var userRepository: UserRepository

    fun findByUsername(username: String): Optional<UserEntity>
        = userRepository.findByUsername(username)

    fun signUp(entity: UserEntity)
    {}

    fun viewUserAll()
    {
        var users = userRepository.findAll()
        for(e in users)
            println(e)
    }
}