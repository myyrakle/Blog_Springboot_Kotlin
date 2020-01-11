package org.myyrakle.myblog

import org.myyrakle.myblog.service.UserService
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class MyblogApplication

fun test(context:ConfigurableApplicationContext)
{
    var service: UserService = context.getBean(UserService::class)
    service.viewUserAll()
}

fun main(args: Array<String>)
{
    var context = runApplication<MyblogApplication>(*args)
    //test(context)
}