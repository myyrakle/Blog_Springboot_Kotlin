package org.myyrakle.myblog.domain

enum class Role(val value:String)
{
    ADMIN("ROLE_ADMIN"), //관리자
    MEMBER("ROLE_MEMBER"), //일반 멤버
}