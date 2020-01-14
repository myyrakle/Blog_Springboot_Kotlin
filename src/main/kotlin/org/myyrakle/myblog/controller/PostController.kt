package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicSetting
import org.myyrakle.myblog.service.CategoryService
import org.myyrakle.myblog.service.PostService
import org.myyrakle.myblog.utility.HtmlEscaper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PostController
{
    @Autowired
    lateinit var postService: PostService
    @Autowired
    lateinit var categoryService: CategoryService

    //전체 게시글 페이지
    @RequestMapping(value=["/all_posts/{pageNumber}"], method= [RequestMethod.GET])
    fun allPostsPage(@PathVariable(required = false) pageNumber:Int, model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);

        val currentPage = postService.getCurrentPage(pageNumber-1)

        model.addAttribute("currentPosts", currentPage)
        model.addAttribute("empty", currentPage.totalElements == 0L)

        return "all_posts"
    }

    //포스트 조회
    @RequestMapping(value=["/post/{id}"])
    fun singlePost(@PathVariable id:Int, model: Model): String
    {
        val postEntity = postService.getPostById(id)
        return if(postEntity.isPresent)
        {
            model.addAllAttributes(BasicSetting.defaultModel);
            val entity = postEntity.get()
            entity.title = HtmlEscaper(entity.title).toEscapedText()
            entity.body = HtmlEscaper(entity.body).toEscapedText()
            model.addAttribute("PostEntity", entity)
            "single_post";
        }
        else
        {
            model.addAttribute("ErrorLog", "존재하지 않는 게시글입니다.")
            "error"
        }
    }

    //카테고리 포스트 조회
    @RequestMapping(value=["/category/{categoryId}"])
    fun categoryPosts(@PathVariable categoryId:Int, model: Model): String
    {
        if(categoryService.getCategory(categoryId).isEmpty)
        {
            model.addAttribute("error", "noCategory")
            return "error"
        }
        else
        {
            model.addAttribute("")
            return "all_posts"
        }
    }
}