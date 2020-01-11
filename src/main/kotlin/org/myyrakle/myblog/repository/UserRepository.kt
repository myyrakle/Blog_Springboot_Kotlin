package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Int>
{
    fun findByUsername(@Param("username") username: String): UserEntity
    fun findByEmail(@Param("email") email: String): UserEntity
}