package org.myyrakle.myblog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PostController
{
    @RequestMapping(value=["/do_login"], method= [RequestMethod.POST])
    fun doLogin(): String
    {
        return "redirect:/"
    }

    @RequestMapping(value=["/do_logout"], method= [RequestMethod.POST])
    fun doLogout(): String
    {
        return "redirect:/"
    }
}