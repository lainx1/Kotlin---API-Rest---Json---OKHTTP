package com.example.eheca.kotlin_resquesapiwithokhttp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmField
        val url = "https://raw.githubusercontent.com/irontec/android-kotlin-samples/master/common-data/bilbao.json"
        //val urlN = "https://server2.planetgroupbd.com/ords/pepsi/v1/outlet/118786"
        val urlN = "https://api.lyrics.ovh/v1/caifanes/afuera"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetJsonWithOkHttpClient(this.text).execute()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_settings) return true

        return super.onOptionsItemSelected(item)
    }

    open class GetJsonWithOkHttpClient(textView: TextView) : AsyncTask<Unit, Unit, String>(){
        val mInnerTextView = textView
        override fun doInBackground(vararg p0: Unit?): String? {
            val networkClient = NetworkClient()
            val stream = BufferedInputStream(networkClient.get(urlN))
            return readStream(stream)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            mInnerTextView.text = result
        }
        fun readStream(inputStream: BufferedInputStream): String{
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }
    }
}
