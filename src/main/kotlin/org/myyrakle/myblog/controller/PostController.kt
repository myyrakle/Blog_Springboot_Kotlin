package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicSetting
import org.myyrakle.myblog.service.CategoryService
import org.myyrakle.myblog.service.PagerBuilder
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

        model.addAttribute("head", "All Posts")
        model.addAttribute("subhead", "")
        model.addAttribute("currentPosts", currentPage)
        model.addAttribute("currentPageNumber", pageNumber)
        model.addAttribute("pager", PagerBuilder(currentPage).getPager())
        model.addAttribute("empty", currentPage.totalElements == 0L)

        return "all_posts"
    }

    //카테고리 포스트 조회
    @RequestMapping(value=["/category/{categoryId}/{pageNumber}"])
    fun categoryPosts(@PathVariable categoryId:Int, @PathVariable pageNumber:Int, model: Model): String
    {
        val currentCategory = categoryService.getCategory(categoryId)
        if(currentCategory.isEmpty)
        {
            model.addAttribute("error", "noCategory")
            return "error"
        }
        else
        {
            val currentPage = postService.getCurrentPageByCategory(pageNumber-1, categoryId)

            model.addAttribute("head", currentCategory.get().categoryName)
            model.addAttribute("subhead", "")
            model.addAttribute("currentPosts", currentPage)
            model.addAttribute("currentPageNumber", pageNumber)
            model.addAttribute("pager", PagerBuilder(currentPage).getPager())
            model.addAttribute("empty", currentPage.totalElements == 0L)

            model.addAttribute("")
            return "all_posts"
        }
    }

    //단일 포스트 조회
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

            val categoryEntityOption = categoryService.getCategory(entity.categoryID)
            if(categoryEntityOption.isEmpty)
            {
                model.addAttribute("ErrorLog", "존재하지 않는 게시글입니다.")
                "error"
            }

            model.addAttribute("CategoryEntity", categoryEntityOption.get())

            "single_post";
        }
        else
        {
            model.addAttribute("ErrorLog", "존재하지 않는 게시글입니다.")
            "error"
        }
    }
}