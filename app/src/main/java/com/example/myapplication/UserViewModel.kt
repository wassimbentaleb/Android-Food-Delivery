package com.example.myapplication

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response



class DataViewModel : ViewModel() {
    private var fetchData: MutableLiveData<List<Data>>? = null
    val errorMessage = MutableLiveData<String>()
    //we will call this method to get the data
    fun fecthRandom(): MutableLiveData<List<Data>>? {
        //if the list is null
        if (fetchData == null) {
            fetchData = MutableLiveData<List<Data>>()
            //we will load it asynchronously from server in this method
            loadDatas()
        }
        //finally we will return the list
        return fetchData
    }
    //This method is using Retrofit to get the JSON data from URL
    fun loadDatas() {

        val retIn =
            RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        var response = retIn.getDataImage()

        response?.enqueue(object : Callback<MutableList<Data>?> {
            override fun onResponse(
                call: Call<MutableList<Data>?>,
                response: Response<MutableList<Data>?>
            ) {


                var res= response.body()
                
                fetchData?.postValue(res)
            }

            override fun onFailure(call: Call<MutableList<Data>?>, t: Throwable) {
                errorMessage.postValue(t.message)

            }

        })
    }



}
