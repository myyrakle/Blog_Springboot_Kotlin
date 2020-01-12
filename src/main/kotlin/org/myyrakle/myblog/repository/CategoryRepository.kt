package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Int>
{
    fun findAllByGroupId(@Param("its_group") id: Int): List<CategoryEntity>
}