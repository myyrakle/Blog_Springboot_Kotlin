package org.myyrakle.myblog.controller

import org.myyrakle.myblog.configuration.BasicInfo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomeController
{
    @RequestMapping(value=["/"], method= [RequestMethod.GET])
    fun home(model: Model): String
    {
        model.addAttribute("BlogName", BasicInfo.BLOG_NAME)
        model.addAttribute("BlogTitle", BasicInfo.BLOG_TITLE)
        model.addAttribute("AdminName", BasicInfo.ADMIN_NAME)
        model.addAttribute("Copyright", BasicInfo.COPYRIGHT)

        return "index"
    }

}