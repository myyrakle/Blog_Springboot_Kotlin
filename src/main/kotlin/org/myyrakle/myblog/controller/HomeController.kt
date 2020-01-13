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
import org.springframework.web.bind.annotation.RequestParam

//기본 페이지
@Controller
class HomeController
{
    @RequestMapping(value=["/", "/home", "/index"], method= [RequestMethod.GET])
    fun homePage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);

        return "index"
    }

    @Autowired
    lateinit var categoryService: CategoryService

    @RequestMapping(value=["/category"], method= [RequestMethod.GET])
    fun categoryPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        model.addAttribute("allCategoryGroups", categoryService.getAllCategoryGroups())

        return "category"
    }

    @RequestMapping(value=["/all_posts"], method= [RequestMethod.GET])
    fun allPostsPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        return "all_posts"
    }

    @RequestMapping(value=["/login_form"], method= [RequestMethod.GET])
    fun loginFormPage(@RequestParam(required = false) message:String?, model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        model.addAttribute("message", message?:"no")

        return "login_form"
    }

    @RequestMapping(value=["/login.do"], method= [RequestMethod.GET])
    fun doLogin(): String
    {
        return "redirect:/home"
    }

    @Autowired
    lateinit var postService: PostService

    //포스트 조회
    @RequestMapping(value=["/post/{id}"])
    fun singlePost(@PathVariable id:Int, model:Model): String
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

    //카테고리 조회
    @RequestMapping(value=["/category/{categoryId}"])
    fun categoryPosts(@PathVariable categoryId:Int, model:Model): String
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