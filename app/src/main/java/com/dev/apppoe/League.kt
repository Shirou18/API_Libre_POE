package com.dev.apppoe

data class League(
    val id: String,
    val realm: String,
    val url: String,
    val startAt: String?,
    val endAt: String?,
    val description: String,
    val category: Category,
    val registerAt: String?,
    val delveEvent: Boolean,
    val rules: List<Rule>
)

data class Category(
    val id: String,
    val current: Boolean?
)

data class Rule(
    val id: String,
    val name: String,
    val description: String
)
