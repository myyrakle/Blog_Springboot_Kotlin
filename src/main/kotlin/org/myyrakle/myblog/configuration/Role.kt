package org.myyrakle.myblog.configuration

enum class Role(val value:String)
{
    ADMIN("ROLE_ADMIN"), //관리자
    MEMBER("ROLE_MEMBER"), //일반 멤버

    ;

    fun roleless(): String
        = value.substring(5)
    fun hasRole(): String
        = value
}