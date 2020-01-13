package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Int>
{
    fun findAllByGroupId(@Param("its_group") id: Int): List<CategoryEntity>

    //fun findByCategoryName(@Param("category_name") categoryName: String): Optional<CategoryEntity>
}