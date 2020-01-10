package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicSetting
import org.myyrakle.myblog.entity.PostEntity
import org.myyrakle.myblog.service.PostService
import org.myyrakle.myblog.utility.HtmlEscaper
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.swing.text.html.Option

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

    @RequestMapping(value=["/category"], method= [RequestMethod.GET])
    fun categoryPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);

        return "category"
    }

    @RequestMapping(value=["/all_posts"], method= [RequestMethod.GET])
    fun allPostsPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        return "all_posts"
    }

    @RequestMapping(value=["/login"], method= [RequestMethod.GET])
    fun loginPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);

        return "login"
    }

    @Autowired
    lateinit var postService: PostService

    //포스트 조회
    @RequestMapping(value=["/post/{id}"])
    fun viewSinglePost(@PathVariable id:Int, model:Model): String
    {
        val postEntity = postService.readPostById(id)
        return if(postEntity.isPresent)
        {
            model.addAllAttributes(BasicSetting.defaultModel);
            model.addAttribute("PostEntity", postEntity.get())
            //model.addAttribute("Body", postEntity.get().body)
            model.addAttribute("Body", HtmlEscaper(postEntity.get().body).toEscapedText())
            "single_post";
        }
        else
        {
            model.addAttribute("ErrorLog", "존재하지 않는 게시글입니다.")
            "error"
        }
    }

    //카테고리 조회
    @RequestMapping(value=["/category/{categoryName}"])
    fun viewSingleCategory(@PathVariable categoryName:String, model:Model): String
    {
        return "index"
    }
}