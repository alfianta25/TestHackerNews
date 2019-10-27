package com.aldyyoga.hackernewstest.presentation.stories

import com.aldyyoga.hackernewstest.model.DetailResponse
import com.aldyyoga.hackernewstest.model.TopStoriesResponse
import com.google.gson.Gson
import com.aldyyoga.hackernewstest.network.ApiRepository
import com.aldyyoga.hackernewstest.network.TheHackerNewsDbApi
import com.aldyyoga.hackernewstest.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class StoriesPresenter(private val view: StoriesView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getTopStories() {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheHackerNewsDbApi.getTopStories()),
                        TopStoriesResponse::class.java
                )
            }

            view.hideLoading()
            view.showStoriesList(data.await())
        }
    }

    fun getArticle(id: String) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheHackerNewsDbApi.getArticle(id)),
                        DetailResponse::class.java
                )
            }

            view.hideLoading()

            try {
                view.showEventList(data.await().events!!)
            } catch (e: NullPointerException) {
                view.showEmptyData()
            }
        }
    }
//
//    fun getEventsNext(id: String) {
//        menu = 2
//        view.showLoading()
//
//        async(context.main) {
//            val data = bg {
//                gson.fromJson(apiRepository
//                        .doRequest(TheHackerNewsDbApi.getLeagueNext(id)),
//                        DetailResponse::class.java
//                )
//            }
//
//            view.hideLoading()
//
//            try {
//                view.showEventList(data.await().events!!)
//            } catch (e: NullPointerException) {
//                view.showEmptyData()
//            }
//        }
//    }
//
//    fun getFavoritesAll(context: Context) {
//        menu = 3
//        view.showLoading()
//
//        val data: MutableList<DetailItem> = mutableListOf()
//
//        context.database.use {
//            val favorites = select(DetailItem.TABLE_FAVORITES)
//                    .parseList(classParser<DetailItem>())
//
//            data.addAll(favorites)
//        }
//
//        view.hideLoading()
//
//        if (data.size > 0) {
//            view.showEventList(data)
//        } else {
//            view.showEmptyData()
//        }
//    }
}
