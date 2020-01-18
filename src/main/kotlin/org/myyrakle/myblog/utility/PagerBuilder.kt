package org.myyrakle.myblog.utility

import org.myyrakle.myblog.entity.PostEntity
import org.springframework.data.domain.Page

class PagerBuilder(val page: Page<PostEntity>)
{
    companion object
    {
        // 한번에 페이저에 띄울 페이지 개수
        const val PAGER_MAX_LENGTH = 5;
    }

    data class PageNumber(
            val number: Int,
            val isActive: Boolean
    )

    fun computeFirstNumberOfPager(pageNumber: Int): Int
    {
        var i = 1;
        while(true)
        {
            if(pageNumber < i+ PAGER_MAX_LENGTH)
                return i;

            i+= PAGER_MAX_LENGTH
        }
    }

    fun getPager(): List<PageNumber>
    {
        val pagerLength = page.totalPages % PAGER_MAX_LENGTH
        val firstNumber = computeFirstNumberOfPager(page.number+1)
        return (firstNumber until (firstNumber+pagerLength)).map {
            PageNumber(
                    it,
                    page.number + 1 == it
            )
                }
    }
}