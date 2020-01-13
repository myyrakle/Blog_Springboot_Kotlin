package org.myyrakle.myblog.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name="Category")
data class CategoryEntity(
        @Id
    @Column(name = "_id")
    var id: Int,
    @Column(name = "category_name")
    var categoryName: String,
    @Column(name = "visible")
    var visible: Boolean,
    @Column(name = "position")
    var position: Int?,
    @Column(name = "post_count")
    var postCount: Int,
    @Column(name="its_group")
    var groupId: Int
)