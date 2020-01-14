package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostRepository: JpaRepository<PostEntity, Int>
{
    //메인페이지용. 3개만 가져옴.
    fun findTop3ByOrderByTimeDesc(): List<PostEntity>

    fun findAllByCategoryID(@Param("category_id") categoryId: Int): List<PostEntity>

    fun findAllByOrderByTimeDesc(pageable: Pageable): Page<PostEntity>
}