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

    // null 그룹 이외의 모든 그룹에, 카테고리를 넣어서 반환
    fun getAllCategoryGroups(): List<CategoryGroupEntity>
    {
        val groups = categoryGroupRepository.findAllByGroupNameIsNotOrderByPosition(NULL_CATEGORY)

        for(group in groups)
        {
            group.categories = categoryRepository.findAllByGroupIdOrderByPosition(group.id)
        }
        return groups
    }

    // null 그룹과 null 그룹의 카테고리들 반환
    fun getNullGroupCategories(): List<CategoryEntity>
    {
        val nullCategoryGroup = categoryGroupRepository.findByGroupName(NULL_CATEGORY).get()
        return categoryRepository.findAllByGroupId(nullCategoryGroup.id)
    }

    //카테고리 획득
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