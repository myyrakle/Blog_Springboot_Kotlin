package org.myyrakle.myblog.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name="User")
data class UserEntity(
    @Id
    @Column(name="_id")
    var id: Int,
    @Column(name="username")
    var username:String,
    @Column(name="email")
    var email:String,
    @Column(name="password")
    var password:String,
    @Column(name="its_role")
    var itsRole: String
)