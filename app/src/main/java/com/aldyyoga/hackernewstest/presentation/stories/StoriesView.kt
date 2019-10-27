package com.aldyyoga.hackernewstest.presentation.stories

import com.aldyyoga.hackernewstest.model.DetailItem
import com.aldyyoga.hackernewstest.model.TopStoriesResponse

interface StoriesView {

    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showStoriesList(data: TopStoriesResponse)
    fun showEventList(data: List<DetailItem>)
}
