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
    }

    fun getPostById(id: Int): Optional<PostEntity> = postRepository.findById(id)

    //메인페이지에 띄울 포스트 3개 획득
    fun getTop3PostsInMainPage(): List<PostEntity> = postRepository.findTop3ByOrderByTimeDesc()

    //현재 인덱스의 모든 페이지 긁어옴
    fun getCurrentPage(pageIndex: Int): Page<PostEntity> = postRepository.findAllByOrderByTimeDesc(PageRequest.of(pageIndex, PAGE_SIZE))

    //해당 인덱스와 카테고리의 전체 페이지 긁어옴
    fun getCurrentPageByCategory(pageIndex: Int, categoryId: Int): Page<PostEntity> = postRepository.findAllByCategoryIDOrderByTimeDesc(categoryId, PageRequest.of(pageIndex, PAGE_SIZE))

    //포스트 작성
    fun writePost(post: PostEntity)
    {
        postRepository.insertPost(
                post.title,
                post.body,
                post.writerID,
                post.categoryID
        )
    }

    //포스트 수정
    fun updatePost(post: PostEntity)
        = postRepository.save(post)

    //최근에 작성된 포스트 획득
    fun getLatestPost(): Optional<PostEntity>
        = postRepository.findFirstByOrderByTimeDesc()

    //게시글 삭제
    fun deletePost(postId:Int):Unit
        = postRepository.deleteById(postId)
}