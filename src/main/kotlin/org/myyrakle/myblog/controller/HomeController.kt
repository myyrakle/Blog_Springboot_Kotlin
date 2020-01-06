package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicSetting

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

//기본 페이지
@Controller
class HomeController
{
    @RequestMapping(value=["/", "/home", "/index"], method= [RequestMethod.GET])
    fun homePage(model: Model): String
    {
        model.addAttribute("BlogName", BasicSetting.BLOG_NAME)
        model.addAttribute("BlogTitle", BasicSetting.BLOG_TITLE)
        model.addAttribute("AdminName", BasicSetting.ADMIN_NAME)
        model.addAttribute("Copyright", BasicSetting.COPYRIGHT)

        return "index"
    }

    @RequestMapping(value=["/category"], method= [RequestMethod.GET])
    fun categoryPage(model: Model): String
    {
        model.addAttribute("BlogName", BasicSetting.BLOG_NAME)
        model.addAttribute("BlogTitle", BasicSetting.BLOG_TITLE)
        model.addAttribute("AdminName", BasicSetting.ADMIN_NAME)
        model.addAttribute("Copyright", BasicSetting.COPYRIGHT)

        return "category"
    }

    @RequestMapping(value=["/all_posts"], method= [RequestMethod.GET])
    fun allPostsPage(model: Model): String
    {
        model.addAttribute("BlogName", BasicSetting.BLOG_NAME)
        model.addAttribute("BlogTitle", BasicSetting.BLOG_TITLE)
        model.addAttribute("AdminName", BasicSetting.ADMIN_NAME)
        model.addAttribute("Copyright", BasicSetting.COPYRIGHT)

        return "posts"
    }

    @RequestMapping(value=["/login"], method= [RequestMethod.GET])
    fun loginPage(model: Model): String
    {
        model.addAttribute("BlogName", BasicSetting.BLOG_NAME)
        model.addAttribute("BlogTitle", BasicSetting.BLOG_TITLE)
        model.addAttribute("AdminName", BasicSetting.ADMIN_NAME)
        model.addAttribute("Copyright", BasicSetting.COPYRIGHT)

        return "login"
    }
}