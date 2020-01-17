package org.myyrakle.myblog.controller

import org.json.JSONObject
import org.myyrakle.myblog.entity.PostEntity
import org.myyrakle.myblog.service.CategoryService
import org.myyrakle.myblog.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.util.*

@Controller
class AuthenticatedController
{
    @RequestMapping(value= ["/editor"])
    fun editor(model:Model): String
    {
        model.addAttribute("allCategoryGroups", categoryService.getAllCategoryGroups())
        model.addAttribute("nullGroupCategories", categoryService.getNullGroupCategories())

        return "editor"
    }

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var postService: PostService

    @PostMapping(value=["/write_post"])
    @ResponseBody
    fun postReceiver(@RequestBody body: String): String
    {
        var jsonObject = JSONObject(body)
        println(body)

        postService.writePost(
            PostEntity(0,
                java.sql.Date(java.util.Date().time),
                jsonObject.getInt("writer"),
                jsonObject.getInt("category"),
                jsonObject.getString("title"),
                jsonObject.getString("body")
            )
        )

        val result = "{'foo':'foo'}"
        return result
    }
}