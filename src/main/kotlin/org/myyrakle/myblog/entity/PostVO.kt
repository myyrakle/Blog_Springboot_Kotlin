package org.myyrakle.myblog.entity

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class PostVO
(val category: String, val title:String, val body:String)