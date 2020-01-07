package org.myyrakle.myblog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PostController
{
    @RequestMapping(value=["/post/{id}"])
    fun viewSinglePost(@PathVariable id:Long): String
    {
        return "";
    }
}