package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.PostEntity
import org.myyrakle.myblog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService
{
    @Autowired
    private lateinit var postRepository: PostRepository

    fun getPostById(id:Int): Optional<PostEntity>
        = postRepository.findById(id)

    fun getTop3MainPage(): List<PostEntity>
        = postRepository.findTop3ByOrderByTimeDesc()

    fun getPostsByCategory(categoryName:String)
    {

    }

    fun readLatestPosts()
    {

    }
}