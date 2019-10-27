package com.aldyyoga.hackernewstest.network

import com.aldyyoga.hackernewstest.BuildConfig

object TheHackerNewsDbApi {
    fun getTopStories(): String {
        return BuildConfig.BASE_URL +
                "topstories.json?print=pretty"
    }

    fun getArticle(id: String): String {
        return BuildConfig.BASE_URL +
                "/item/{id}.json?print=pretty"
    }

//    fun getTopStories(): String {
//        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
//                "/all_leagues.php"
//    }
//
//    fun getLeaguePrev(id: String): String {
//        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
//                "/eventspastleague.php?id=${id}"
//    }
//
//    fun getLeagueNext(id: String): String {
//        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
//                "/eventsnextleague.php?id=${id}"
//    }
//
//    fun getTeamDetails(id: String): String {
//        return "${BuildConfig.BASE_URL}${BuildConfig.TSDB_API_KEY}" +
//                "/lookupteam.php?id=${id}"
//    }
}
