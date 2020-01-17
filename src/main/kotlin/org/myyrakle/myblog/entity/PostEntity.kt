package org.myyrakle.myblog.entity

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name="Post")
data class PostEntity
(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "_id")
    var id: Int,
    @Column(name = "time")
    var time: Date,
    @Column(name = "writer_id")
    var writerID: Int,
    @Column(name = "category_id")
    var categoryID: Int,
    @Column(name = "title")
    var title: String,
    @Column(name = "body")
    var body: String
);