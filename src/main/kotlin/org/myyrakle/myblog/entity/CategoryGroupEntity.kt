package org.myyrakle.myblog.entity

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name="Categorygroup")
data class CategoryGroupEntity(
    @Id
    @Column(name = "_id")
    var id:Int,
    @Column(name = "group_name")
    var groupName: String,
    @Column(name = "visible")
    var visible: Boolean,
    @Column(name="_position")
    var _position: Int?,

    @Transient
    var categories: List<CategoryEntity>
)