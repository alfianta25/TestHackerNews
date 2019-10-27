package com.aldyyoga.hackernewstest.presentation.detail

import com.aldyyoga.hackernewstest.network.ApiRepository
import com.aldyyoga.hackernewstest.utils.CoroutineContextProvider
import com.google.gson.Gson

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetails(idHomeTeam: String?, idAwayTeam: String?) {
        view.showLoading()

//        async(context.main) {
//            val dataHomeTeam = bg {
//                gson.fromJson(apiRepository
//                        .doRequest(TheHackerNewsDbApi.getTeamDetails(idHomeTeam.toString())),
//                        TeamDetailResponse::class.java
//                )
//            }
//
//            val dataAwayTeam = bg {
//                gson.fromJson(apiRepository
//                        .doRequest(TheHackerNewsDbApi.getTeamDetails(idAwayTeam.toString())),
//                        TeamDetailResponse::class.java
//                )
//            }
//
//            view.hideLoading()
//            view.showTeamDetails(dataHomeTeam.await().teams!!, dataAwayTeam.await().teams!!)
//        }
    }

}
