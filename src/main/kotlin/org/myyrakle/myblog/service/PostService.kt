package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.PostEntity
import org.myyrakle.myblog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService
{
    @Autowired
    private lateinit var postRepository: PostRepository

    val PAGE_SIZE = 10;

    fun getPostById(id:Int): Optional<PostEntity>
        = postRepository.findById(id)

    //메인페이지에 띄울 포스트 3개 획득
    fun getTop3MainPage(): List<PostEntity>
        = postRepository.findTop3ByOrderByTimeDesc()

    fun getAllPages(pageIndex: Int): Page<PostEntity>
        = postRepository.findAllByOrderByTimeDesc(PageRequest.of(pageIndex, PAGE_SIZE))

    fun getCurrentPage(pageIndex: Int): Page<PostEntity>
        = postRepository.findAllByOrderByTimeDesc(PageRequest.of(pageIndex, PAGE_SIZE))

    fun getPostsByCategory(categoryName:String)
    {

    }

    fun readLatestPosts()
    {

    }
}