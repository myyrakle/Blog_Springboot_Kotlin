package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicSetting
import org.myyrakle.myblog.service.CategoryService
import org.myyrakle.myblog.service.PostService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

//기본 페이지
@Controller
class HomeController
{
    @Autowired
    lateinit var postService: PostService
    @Autowired
    lateinit var categoryService: CategoryService

    //메인 페이지
    @RequestMapping(value=["/", "/home", "/index"], method= [RequestMethod.GET])
    fun homePage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        model.addAttribute("posts", postService.getTop3PostsInMainPage())
        model.addAttribute("subhead", "")

        return "index"
    }

    //카테고리 페이지
    @RequestMapping(value=["/category"], method= [RequestMethod.GET])
    fun categoryPage(model: Model): String
    {
        model.addAllAttributes(BasicSetting.defaultModel);
        model.addAttribute("allCategoryGroups", categoryService.getAllCategoryGroups())
        model.addAttribute("nullGroupCategories", categoryService.getNullGroupCategories())
        model.addAttribute("subhead", "")

        return "category"
    }

    //로그인 페이지
    @RequestMapping(value=["/login_form"], method= [RequestMethod.GET])
    fun loginFormPage(@RequestParam(required = false) message:String?, model: Model): String
    {
        /*if(auth?.isAuthenticated)
        {
            model.addAttribute("error", "alreadyLogin")
            return "error"
        }*/

        model.addAllAttributes(BasicSetting.defaultModel);
        model.addAttribute("message", message?:"no")
        model.addAttribute("subhead", "")

        return "login_form"
    }
}