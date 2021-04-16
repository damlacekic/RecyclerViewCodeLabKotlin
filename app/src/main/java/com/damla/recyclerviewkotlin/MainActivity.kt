package com.damla.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.util.*

class MainActivity : AppCompatActivity() {

    var listWord : LinkedList<String> = LinkedList()
    lateinit var recyclerView : RecyclerView
    lateinit var mAdapter: RecyclerViewAdapter
    lateinit var fab : FloatingActionButton
    val STATE_ACTIVITY : String
    init{
        STATE_ACTIVITY = "state_of_activity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for(i in 0..20){
            listWord.addLast("Word! ${i}")

        }
        var toolBar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        recyclerView = findViewById(R.id.recyclerView)
        communicationWithAdaptor()
        if(savedInstanceState != null){
            listWord = savedInstanceState.getSerializable(STATE_ACTIVITY) as LinkedList<String>
            communicationWithAdaptor()
        }
        fab = findViewById(R.id.fab)
        fab.setOnClickListener(View.OnClickListener {
            var wordlistSize = listWord.size
            listWord.addLast("+ Word $wordlistSize")
            recyclerView.adapter?.notifyItemInserted(wordlistSize)
            recyclerView.smoothScrollToPosition(wordlistSize)
        })
    }
    fun communicationWithAdaptor(){
        mAdapter = RecyclerViewAdapter(this,listWord)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(outState!=null){
            super.onSaveInstanceState(outState)
            outState.putSerializable(STATE_ACTIVITY,listWord)
        }

    }
}