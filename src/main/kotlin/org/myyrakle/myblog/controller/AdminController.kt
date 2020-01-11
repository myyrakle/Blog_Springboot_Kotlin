package org.myyrakle.myblog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = ["/admin"])
class AdminController
{
    @RequestMapping
    fun test(): String
    ="admin"
}