package org.myyrakle.myblog.repository

import org.myyrakle.myblog.entity.CategoryGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryGroupRepository: JpaRepository<CategoryGroupEntity, Int>
{}