package com.example.sophie.jsontest

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class NetworkClient(val myTag: String?, context: Context) {
    /**create a queue */
    val queue = Volley.newRequestQueue(context)
    /**create mapper*/
    val mapper = jacksonObjectMapper()

    val context = this.context


    data class Experiment(val success: String)


    private var currentDate: String? = null
    private var device: String? = null
    private var buildVersion: String? = null
    fun getRequest(URL: String) {
        val stringRequest = StringRequest(Request.Method.GET, URL,
                Response.Listener<String> { response ->
                    /**
                     * On success
                     */
                    /**map my response*/
                    val data = mapper.readValue(response, Experiment::class.java)
                    /**print my response in a Toast*/
                    Toast.makeText(context, "Response: ${data}", Toast.LENGTH_LONG).show()

                },
                Response.ErrorListener {
                    /**
                     * On error
                     * inform me in a Toast
                     */
                    Toast.makeText(context, "That didn't work!", Toast.LENGTH_LONG).show()
                })
        stringRequest.tag = myTag

        queue.add(stringRequest)
    }

    /**
     * post request for overlay on
     *
     * @param URL
     * @param context
     */

    fun postOverlayOn(URL: String, onComplete: (String) -> Unit, onFailure: (String) -> Unit) {

        /**gat date and time */
        getDeviceInfo()
        /**create post request */
        val postRequest = object : StringRequest(

                Request.Method.POST, URL, Response.Listener { response ->
            /**
             * on success
             */

            if (response == "true") {
                onComplete("")
            } else {
                onFailure("")
            }
        }, Response.ErrorListener { error ->
            /**
             * on failure
             */
            onFailure("")
        }) {
            override
                    /**
                     * post these json pairs
                     */
            fun getParams(): Map<String, String?> {
                val params = HashMap<String, String?>()
                params["tx"] = "activating tx"
                params["device"] = device
                params["datetime"] = currentDate
                params["version"] = buildVersion
                return params
            }
        }
        queue.add(postRequest)
    }

    /**
     * post request for overlay off
     *
     * @param URL
     * @param context
     */
    fun postOverlayOff(URL: String, context: Context) {
        /**create a queue */
        val queue = Volley.newRequestQueue(context)
        /**gat date and time */
        getDeviceInfo()
        /**create post request */
        val postRequest = object : StringRequest(

                Request.Method.POST, URL, Response.Listener { response ->
            /**
             * on success
             */
            Toast.makeText(context, "Response: $response", Toast.LENGTH_LONG).show()
        }, Response.ErrorListener { error ->
            /**
             * on failure
             */
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
        }) {
            override
                    /**
                     * post these json pairs
                     */
            fun getParams(): Map<String, String?> {
                val params = HashMap<String, String?>()
                params["device"] = device
                params["datetime"] = currentDate
                params["version"] = buildVersion
                return params
            }
        }
        queue.add(postRequest)
    }

    private fun getDeviceInfo() {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        currentDate = dateFormat.format(date)
        device = Build.SERIAL
        val i = BuildConfig.VERSION_CODE
        buildVersion = i.toString()
    }
/*
    fun test(context: Context) {
        val client = NetworkClient("", context)
        client.postOverlayOn("", { str ->
            Log.d("Success", str)
        }, { err ->
            Log.e("Error", "Error messgae $err")
        })
    }*/
}
}


