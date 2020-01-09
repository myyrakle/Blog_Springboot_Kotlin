package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository: JpaRepository<PostEntity, Int>
{
    override fun findById(@Param("_id") id: Int): Optional<PostEntity>
}