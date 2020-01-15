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

    companion object
    {
        // 한 페이지당 글 개수
        const val PAGE_SIZE = 10;

        // 한번에 페이저에 띄울 페이지 개수
        const val PAGER_LENGTH = 5;
    }

    fun getPostById(id:Int): Optional<PostEntity>
        = postRepository.findById(id)

    //메인페이지에 띄울 포스트 3개 획득
    fun getTop3MainPage(): List<PostEntity>
        = postRepository.findTop3ByOrderByTimeDesc()

    fun getAllPages(pageIndex: Int): Page<PostEntity>
        = postRepository.findAllByOrderByTimeDesc(PageRequest.of(pageIndex, PAGE_SIZE))

    fun getCurrentPage(pageIndex: Int): Page<PostEntity>
        = postRepository.findAllByOrderByTimeDesc(PageRequest.of(pageIndex, PAGE_SIZE))

    //fun countAllPost

    fun getPostsByCategory(categoryName:String)
    {
        postRepository.count()
    }

    fun readLatestPosts()
    {

    }
}