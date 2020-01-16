package org.myyrakle.myblog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(value = ["/auth"])
class AuthenticatedController
{
    @RequestMapping(value= ["/editor"])
    fun editor(): String
    {
        return "editor"
    }

    @RequestMapping(value=["/post.do"], method = [RequestMethod.POST], produces = ["plain/text;charset=utf8"])
    @ResponseBody
    fun postReceiver(@RequestBody body: String): String
    {

        val result = ""
        return result
    }

    @RequestMapping(value=["/test.do"], method = [RequestMethod.POST], produces = ["plain/text;charset=utf8"])
    @ResponseBody
    fun test():String
    {
        return ""
    }
}