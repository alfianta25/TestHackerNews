package com.aldyyoga.hackernewstest.presentation.detail

interface DetailView {

    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(dataHomeTeam: List<TeamsItem>, dataAwayTeam: List<TeamsItem>)
}
