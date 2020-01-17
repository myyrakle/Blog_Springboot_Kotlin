package org.myyrakle.myblog.controller

import org.json.JSONObject
import org.myyrakle.myblog.entity.PostEntity
import org.myyrakle.myblog.service.CategoryService
import org.myyrakle.myblog.service.PostService
import org.myyrakle.myblog.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(value=["/auth"])
class AuthenticatedController
{
    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var postService: PostService

    @Autowired
    lateinit var userService: UserService

    @RequestMapping(value= ["/editor"])
    fun editor(model:Model): String
    {
        model.addAttribute("allCategoryGroups", categoryService.getAllCategoryGroups())
        model.addAttribute("nullGroupCategories", categoryService.getNullGroupCategories())

        return "editor"
    }

    @PostMapping(value=["/write_post"])
    @ResponseBody
    fun postReceiver(@RequestBody body: String): String
    {
        var jsonObject = JSONObject(body)
        println(body)

        val writerId = userService.findByUsername(jsonObject.getString("writer")).get().id

        postService.writePost(
            PostEntity(0,
                java.sql.Date(java.util.Date().time),
                writerId,
                jsonObject.getInt("category"),
                jsonObject.getString("title"),
                jsonObject.getString("body")
            )
        )

        val result = JSONObject()
        result.put("success", true)
        result.put("postId", postService.getLatestPost().get().id)
        return result.toString()
    }

    @DeleteMapping(value=["/deletePost/{postId}"])
    fun deletePost(@PathVariable postId:Int)
    {

    }
}