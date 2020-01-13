package org.myyrakle.myblog.service

import org.myyrakle.myblog.entity.CategoryEntity
import org.myyrakle.myblog.entity.CategoryGroupEntity
import org.myyrakle.myblog.repository.CategoryGroupRepository
import org.myyrakle.myblog.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService
{
    val NULL_CATEGORY = "__null__"

    @Autowired
    lateinit var categoryGroupRepository: CategoryGroupRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    fun getAllCategoryGroups(): List<CategoryGroupEntity>
    {
        val groups = categoryGroupRepository.findAllByGroupNameIsNot(NULL_CATEGORY)

        for(group in groups)
        {
            group.categories = categoryRepository.findAllByGroupId(group.id)
        }

        return groups
    }

    fun getNullCategoryGroup(): CategoryGroupEntity
    {
        val nullCategoryGroup =
                categoryGroupRepository.findByGroupName(NULL_CATEGORY).get()
        nullCategoryGroup.categories = categoryRepository.findAllByGroupId(nullCategoryGroup.id)

        return nullCategoryGroup
    }

    fun getCategory(categoryId: Int): Optional<CategoryEntity>
        = categoryRepository.findById(categoryId)

    //해당 카테고리 포스트 개수 증가
    fun increasePostCount(categoryId: Int)
    {
        val entityOption = categoryRepository.findById(categoryId)
        if(entityOption.isEmpty)
            return

        val entity = entityOption.get()
        entity.postCount++
        categoryRepository.save(entity)
    }

    //해당 카테고리 포스트 개수 감소
    fun decreasePostCount(categoryId: Int)
    {
        val entityOption = categoryRepository.findById(categoryId)
        if(entityOption.isEmpty)
            return

        val entity = entityOption.get()
        entity.postCount--
        categoryRepository.save(entity)
    }

    
    //테스트
    fun test()
    {

    }
}