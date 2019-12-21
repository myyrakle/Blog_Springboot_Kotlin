package org.myyrakle.myblog

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value=["/"], method= [RequestMethod.GET])
class HomeController
{
    @RequestMapping()
    public fun boom(): String
    {
        return "index"
    }
}