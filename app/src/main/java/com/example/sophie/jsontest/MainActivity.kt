package com.example.sophie.jsontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log


class MainActivity : AppCompatActivity() {

    val url = "http://4m.to/apps/tesco/api/security/enabled.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onStart() {
        super.onStart()
        val networkClient = NetworkClient("tag", this)
        networkClient.postOverlayOn(url,{ tag -> Log.d("xyz", tag)}, {tag2 -> Log.e("xyz", tag2)})
        networkClient.getRequest(url)
    }

}
