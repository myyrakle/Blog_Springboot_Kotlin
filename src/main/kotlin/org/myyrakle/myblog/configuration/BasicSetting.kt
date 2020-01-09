package org.myyrakle.myblog.configuration

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

object BasicSetting
{
    //블로그명
    const val BLOG_NAME = "My Blog"

    //블로그 타이틀
    const val BLOG_TITLE = "Myyrakle's Note"

    //관리자명
    const val ADMIN_NAME = "myyrakle"

    //저작권 표시
    const val COPYRIGHT = "By Myyrakle 2019"

    //깃허브 링크
    const val GITHUB_LINK = "https://github.com/myyrakle"

    val defaultModel = mapOf<String, String>(
            "BlogName" to BLOG_NAME,
            "BlogTitle" to BLOG_TITLE,
            "AdminName" to ADMIN_NAME,
            "Copyright" to COPYRIGHT,
            "GithubLink" to GITHUB_LINK
    )

    val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
}