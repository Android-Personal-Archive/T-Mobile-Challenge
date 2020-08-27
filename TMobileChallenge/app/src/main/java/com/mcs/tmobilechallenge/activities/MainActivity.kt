package com.mcs.tmobilechallenge.activities

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcs.tmobilechallenge.R
import com.mcs.tmobilechallenge.adapters.PageAdapter
import com.mcs.tmobilechallenge.injectables.RetrofitClientSingleton
import com.mcs.tmobilechallenge.interfaces.IGetPageService
import com.mcs.tmobilechallenge.pokos.PagePOKO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitClientSingleton.retrofitInstance?.create(IGetPageService::class.java)

        //makes the call to the json feed
        //puts on separate thread (since network operation, it's a good practice)
        val call = service?.getPage()

        //enqueue to parse the json
        //once parsed, callback onFailure or onResponse if successful
        call?.enqueue(object : Callback<PagePOKO> {
            override fun onFailure(call: Call<PagePOKO>, t: Throwable) {
                //Exception crashes the program. Use log instead.  I can't think of any situation for desiring the application to crash on error.
                //throw Exception("Error reading JSON:", t)
                Log.e(localClassName, "Error reading JSON")
            }

            override fun onResponse(call: Call<PagePOKO>, response: Response<PagePOKO>) {
                val body = response.body()
                val page = body
                if(page == null) {
                    Log.w(localClassName, "Response returned null")
                    moveToActivity(this@MainActivity, ErrorActivity())
                }

                //val layoutManager = LinearLayoutManager(this@MainActivity)
                //layoutManager.orientation = LinearLayoutManager.VERTICAL

                else
                {
                    val adapter = PageAdapter(this@MainActivity, page)
                    rv_feeds.adapter = adapter
                    rv_feeds.layoutManager = LinearLayoutManager(this@MainActivity)
                    rv_feeds.setHasFixedSize(true)
                }
            }
        })
    }

    private fun moveToActivity(context: Context, activity: AppCompatActivity) {
        val intent = Intent(context, activity::class.java)
        startActivity(intent)
    }
}