package com.aldyyoga.hackernewstest.presentation.stories

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.*
import com.google.gson.Gson
import com.aldyyoga.hackernewstest.R
import com.aldyyoga.hackernewstest.adapter.StoriesAdapter
import com.aldyyoga.hackernewstest.model.DetailItem
import com.aldyyoga.hackernewstest.presentation.detail.DetailActivity
import com.aldyyoga.hackernewstest.presentation.detail.INTENT_DETAIL
import com.aldyyoga.hackernewstest.network.ApiRepository
import com.aldyyoga.hackernewstest.utils.invisible
import com.aldyyoga.hackernewstest.utils.progressBar
import com.aldyyoga.hackernewstest.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class StoriesActivity : AppCompatActivity(), StoriesView {

    private lateinit var presenter: StoriesPresenter
    private lateinit var adapter: StoriesAdapter

    private lateinit var spinnerLayout: LinearLayout
    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyDataView: LinearLayout

    private var events: MutableList<DetailItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupLayout()
        setupEnv()
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
        emptyDataView.invisible()

//        if (presenter.menu == 3) spinnerLayout.gone()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
        emptyDataView.invisible()
    }

    override fun showEmptyData() {
        progressBar.invisible()
        recyclerView.invisible()
        emptyDataView.visible()
    }

//    override fun showStoriesList(data: TopStoriesResponse) {
//        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, data.leagues) as SpinnerAdapter?
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                league = spinner.selectedItem as StoriesItem
//
//                when (presenter.menu) {
//                    1 -> presenter.getArticle(league.idStories!!)
//                    2 -> presenter.getEventsNext(league.idStories!!)
//                }
//            }
//        }
//    }

    override fun showEventList(data: List<DetailItem>) {
        showEventListData(data)
    }

    private fun setupLayout() {
        linearLayout {
            orientation = LinearLayout.VERTICAL

            spinnerLayout = linearLayout {
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.LTGRAY

//                spinner = spinner {
//                    id = R.id.spinner
//                    padding = dip(16)
//                    minimumHeight = dip(80)
//                }
            }

            relativeLayout {
                emptyDataView = linearLayout {
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        setImageResource(R.drawable.ic_no_data)
                    }

                    textView {
                        gravity = Gravity.CENTER
                        padding = dip(8)
                        text = "No Data Provided"
                    }
                }.lparams {
                    centerInParent()
                }

                recyclerView = recyclerView {
                    id = R.id.recycler_view
                    layoutManager = LinearLayoutManager(ctx)
                }.lparams(matchParent, matchParent) {
                    topOf(R.id.bottom_navigation_view)
                }

                progressBar(R.id.progress_bar).lparams {
                    centerInParent()
                }

//                bottomNavigationView {
//                    id = R.id.bottom_navigation_view
//                    backgroundColor = Color.WHITE
//
//                    menu.apply {
//                        add(0, R.id.bnv_match_prev, 0, "Prev. Match")
//                                .setIcon(R.drawable.ic_trophy)
//                                .setOnMenuItemClickListener {
//                                    presenter.getArticle(league.idStories!!)
//                                    false
//                                }
//
//                        add(0, R.id.bnv_match_next, 0, "Next Match")
//                                .setIcon(R.drawable.ic_event)
//                                .setOnMenuItemClickListener {
//                                    presenter.getEventsNext(league.idStories!!)
//                                    false
//                                }
//
//                        add(0, R.id.bnv_favorites, 0, "Favorites")
//                                .setIcon(R.drawable.ic_favorites)
//                                .setOnMenuItemClickListener {
//                                    presenter.getFavoritesAll(ctx)
//                                    false
//                                }
//                    }
//                }.lparams(matchParent, wrapContent) {
//                    alignParentBottom()
//                }
            }
        }
    }

    private fun setupEnv() {
        progressBar = find(R.id.progress_bar)

        presenter = StoriesPresenter(this, ApiRepository(), Gson())
        adapter = StoriesAdapter(events) {
            startActivity<DetailActivity>(INTENT_DETAIL to it)
        }

        presenter.getTopStories()
        recyclerView.adapter = adapter
    }

    private fun showEventListData(data: List<DetailItem>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(0)
    }
}
