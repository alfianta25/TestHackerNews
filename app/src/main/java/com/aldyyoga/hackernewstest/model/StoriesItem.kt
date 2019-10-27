package com.aldyyoga.hackernewstest.model

data class StoriesItem(val idStories: String?, val strStories: String?) {

    override fun toString(): String {
        return strStories.toString()
    }
}