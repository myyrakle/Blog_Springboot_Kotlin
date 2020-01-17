package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface PostRepository: JpaRepository<PostEntity, Int>
{
    //메인페이지용. 3개만 가져옴.
    fun findTop3ByOrderByTimeDesc(): List<PostEntity>

    fun findAllByOrderByTimeDesc(pageable: Pageable): Page<PostEntity>

    fun findAllByCategoryIDOrderByTimeDesc(@Param("category_id") categoryId: Int, pageable: Pageable): Page<PostEntity>

    @Query(value="insert into Post(time, title, body, writer_id, category_id) value(now(), :title, :body, :writerId, :categoryId)", nativeQuery = true)
    @Transactional
    @Modifying
    fun insertPost(@Param("title") title:String,
                   @Param("body") body: String,
                   @Param("writerId") writerId:Int,
                   @Param("categoryId") categoryId: Int)

    fun findFirstByOrderByTimeDesc(): Optional<PostEntity>
}