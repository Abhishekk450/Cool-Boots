package com.example.coolbootsdemo.model

data class Data(
    val answer_hint: String,
    val chapter_id: Int,
    val chapter_meta: ChapterMeta,
    val chapter_name: String,
    val created_by: String,
    val difficulty_level: String,
    val explaination: Any,
    val id: Int,
    val is_latex: Int,
    val marks: Int,
    val no_of_plays: Int,
    val options: List<Option>,
    val question_title: String,
    val question_type: String,
    val title: String,
    val total_user_attempted_correct: Int
)