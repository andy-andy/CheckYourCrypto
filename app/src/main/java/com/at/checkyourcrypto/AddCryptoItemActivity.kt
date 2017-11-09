package com.at.checkyourcrypto

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.at.checkyourcrypto.api.CryptoApiService
import kotlinx.android.synthetic.main.activity_add_crypto_item.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AddCryptoItemActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_crypto_item)
        setSupportActionBar(toolbar)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        // Create the CryptoApiService
        val cryptoApiService = CryptoApiService.create()
        val resultTop200Cryptos = cryptoApiService.getTop200Cryptos()

        resultTop200Cryptos
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    Log.d("Result", "There are $result Cryptos in the list")
                    recyclerView.adapter = CryptoRecyclerViewAdapter(result)
                }, { error ->
                    error.printStackTrace()
                })
    }
}
