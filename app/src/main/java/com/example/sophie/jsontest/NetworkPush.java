package com.example.sophie.jsontest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NetworkPush {
    public void  postSecurityInfo(final String url, final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        Response.Listener<String> reponseListener
                = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
            }
        };
        Response.ErrorListener errorListener
                = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }};

        StringRequest request = new StringRequest(Request.Method.POST, url, reponseListener,errorListener)        {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params
                    = new HashMap<String, String>();
            params.put("success", "myname");
            return params;}};
    /*
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> postParam = new HashMap<String, String>();

                postParam.put("success", "asd@asd.com");



                return postParam;
            }

        };

        queue.add(jsonObjRequest);}
    /*StringRequest postRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(context, response, Toast.LENGTH_LONG).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        }
    }){
        @Override
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<String, String>();
            params.put("success", URL);


            return params;
        }
    };
        queue.add(postRequest);
    } /*new StringRequest(Request.Method.GET, URL,
            new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    // response
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                }
            },
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
    ) {
        @Override
        protected Map<String, String> getParams()
        {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("success", "false");

            return params;
        }
    };
queue.add(postRequest);}
    /* public void postSecurityInfo(final String URL, final Context context) {
        /**create a queue*//*
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest postRequest = new StringRequest(
                Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                        public void onResponse(String response) {
                        Toast.makeText(context, "Response: " + response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                            public void onErrorResponse(VolleyError error){
            Toast.makeText(context, "That didn't work!", Toast.LENGTH_LONG).show();}
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("success", URL);
                return params;
            }
        };*/

        queue.add(request);

        }

}