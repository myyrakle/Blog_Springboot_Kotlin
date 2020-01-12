package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.CategoryEntity
import org.myyrakle.myblog.entity.CategoryGroupEntity
import org.myyrakle.myblog.repository.CategoryGroupRepository
import org.myyrakle.myblog.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService
{
    @Autowired
    lateinit var categoryGroup: CategoryGroupRepository

    @Autowired
    lateinit var category: CategoryRepository

    fun getAllCategory(): List<CategoryGroupEntity>
    {
        var groups = categoryGroup.findAll()

        for(group in groups)
        {
            group.categories = category.findAllByGroupId(group.id)
        }

        return groups
    }

    fun test()
    {
        viewCategoryGroupAll()
    }

    fun viewCategoryAll()
    {
        var groups = category.findAll()
        for(e in groups)
            println(e)
    }

    fun viewCategoryGroupAll()
    {
        var groups = categoryGroup.findAll()
        for(e in groups)
            println(e)
    }
}