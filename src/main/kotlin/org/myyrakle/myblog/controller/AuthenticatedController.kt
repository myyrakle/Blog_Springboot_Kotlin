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
        model.addAttribute("onUpdate", false)
        return "editor"
    }

    @RequestMapping(value= ["/editor/update/{postId}"])
    fun updateEditor(@PathVariable postId: Int, model:Model): String
    {
        val postEntityOption = postService.getPostById(postId)

        if(postEntityOption.isPresent)
        {
            model.addAttribute("allCategoryGroups", categoryService.getAllCategoryGroups())
            model.addAttribute("nullGroupCategories", categoryService.getNullGroupCategories())
            model.addAttribute("onUpdate", true)
            model.addAttribute("postEntity", postEntityOption.get())

            return "editor"
        }
        else
        {
            model.addAttribute("ErrorLog", "존재하지 않는 포스트입니다.")
            return "error"
        }
    }

    //포스트 작성. ajax
    @PostMapping(value=["/write_post"])
    @ResponseBody
    fun writePost(@RequestBody body: String): String
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

        categoryService.increasePostCount(jsonObject.getInt("category"))

        val result = JSONObject()
        result.put("success", true)
        result.put("postId", postService.getLatestPost().get().id)
        return result.toString()
    }

    //포스트 갱신. ajax
    @PostMapping(value=["/update_post/{postId}"])
    @ResponseBody
    fun updatePost(@RequestBody body: String, @PathVariable postId: Int, model: Model): String
    {
        //포스트 유효성 검사
        if(postService.getPostById(postId).isEmpty)
        {
            model.addAttribute("ErrorLog", "존재하지 않는 포스트입니다.")
            return "error"
        }

        var jsonObject = JSONObject(body)
        println(body)

        val writerId = userService.findByUsername(jsonObject.getString("writer")).get().id

        postService.updatePost(
                PostEntity(
                        postId,
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

    //삭제 요청. ajax.
    @DeleteMapping(value=["/delete_post/{postId}"])
    @ResponseBody
    fun deletePost(@PathVariable postId:Int): String
    {
        val postEntityOption = postService.getPostById(postId)

        if(postEntityOption.isPresent)
        {
            postService.deletePost(postId)
            categoryService.decreasePostCount(postEntityOption.get().categoryID)
            return "success"
        }
        else
        {
            return "error"
        }
    }
}