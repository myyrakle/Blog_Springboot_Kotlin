package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.CategoryGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryGroupRepository: JpaRepository<CategoryGroupEntity, Int>
{
    fun findAllByGroupNameIsNot(@Param("group_name") groupName: String): List<CategoryGroupEntity>
    fun findByGroupName(@Param("group_name") groupName: String): Optional<CategoryGroupEntity>
}