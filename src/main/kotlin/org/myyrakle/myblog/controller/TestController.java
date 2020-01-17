package org.myyrakle.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController
{
    @GetMapping(value="/test")
    String test()
    {
        return "test/test";
    }

    @PostMapping(value="/ajax")
    @ResponseBody
    String ajax(@RequestBody String body)
    {
        System.out.println(body);
        return "foo";
    }
}
