package com.example.ui.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.UiState
import com.satyajit.knetworking.KNetworkRequest
import com.satyajit.knetworking.KNetworking
import com.satyajit.knetworking.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.CacheControl
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.util.concurrent.TimeUnit

class MainViewModel(var kNetworking: KNetworking) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)

    val uiState: StateFlow<UiState<String>> = _uiState.asStateFlow()

    init {
        fetchAllUsers()
        //postUser()
    }

    private fun fetchAllUsers() {
        data class TestHeader(var authorization:String,var apikey:String)
        data class TestQueryParam(var id:String)
        data class TestPathParam(var abc:String)

        val kNetworkRequestGet = kNetworking.newGetRequestBuilder(url = "https://reqres.in/api/users/{userID}/orders/{orderID}")
            .setTag("TestTag1")
            .setPriority(Priority.MEDIUM)

            .setHeaders("Header1","HeaderValue1")
            .setHeaders(mapOf(Pair("Header2","HeaderValue2")))
            .setHeaders(mutableMapOf(Pair("Header3","HeaderValue3")))
            .setHeaders(hashMapOf(Pair("Header4","HeaderValue4")))
            .setHeaders(TestHeader("Zahgr@1376n","1234"))

            .setQueryParameter("page","2")
            .setQueryParameter(mapOf(Pair("QueryParam2","QueryParamValue2")))
            .setQueryParameter(mutableMapOf(Pair("QueryParam3","QueryParamValue3")))
            .setQueryParameter(hashMapOf(Pair("QueryParam4","QueryParamValue4")))
            .setQueryParameter(TestQueryParam("QueryParamValue5"))

            .setPathParameter("userID","abc12")
            .setPathParameter("orderId","1242")
            .setPathParameter(mapOf(Pair("PathParam2","PathParamValue2")))
            .setPathParameter(mutableMapOf(Pair("PathParam3","PathParamValue3")))
            .setPathParameter(hashMapOf(Pair("PathParam4","PathParamValue4")))
            .setPathParameter(TestPathParam("PathParamValue5"))

            .setCacheControl(CacheControl.Builder().noCache().maxAge(1,TimeUnit.DAYS).build())
            .setUserAgent("AndroidApp")

            .build()

        val kNetworkRequestPost : KNetworkRequest = kNetworking.newPostRequestBuilder(url = "https://reqres.in/api/users")
            .setTag("TestTag1")
            .setPriority(Priority.MEDIUM)

            .setHeaders("Header1","HeaderValue1")
            .setHeaders(mapOf(Pair("Header2","HeaderValue2")))
            .setHeaders(mutableMapOf(Pair("Header3","HeaderValue3")))
            .setHeaders(hashMapOf(Pair("Header4","HeaderValue4")))
            .setHeaders(TestHeader("Zahgr@1376n","1234"))

            .setQueryParameter("QueryParam1","QueryParamValue1")
            .setQueryParameter(mapOf(Pair("QueryParam2","QueryParamValue2")))
            .setQueryParameter(mutableMapOf(Pair("QueryParam3","QueryParamValue3")))
            .setQueryParameter(hashMapOf(Pair("QueryParam4","QueryParamValue4")))
            .setQueryParameter(TestQueryParam("QueryParamValue5"))

            .setPathParameter("PathParam1","PathParamValue1")
            .setPathParameter(mapOf(Pair("PathParam2","PathParamValue2")))
            .setPathParameter(mutableMapOf(Pair("PathParam3","PathParamValue3")))
            .setPathParameter(hashMapOf(Pair("PathParam4","PathParamValue4")))
            .setPathParameter(TestPathParam("PathParamValue5"))

            .setCacheControl(CacheControl.Builder().noCache().maxAge(1,TimeUnit.DAYS).build())
            .setUserAgent("AndroidApp")

            .build()

        val url= "https://reqres.in/api/users/test 1/orders/abcd"

        val knetGet= kNetworkRequestGet
        val knetPost= kNetworkRequestPost

        viewModelScope.launch {
            kNetworking.enqueue(kNetworkRequestGet, onSuccess = {
                _uiState.value = UiState.Success(it)
            }, onError = {
                _uiState.value = UiState.Error(it)
            })
        }

    }

    private fun postUser() {
//        val kNetworkRequest = kNetworking.newPostRequestBuilder(url = "https://reqres.in/api/users")
//            .tag("Tag1")
//            .headers(mapOf(Pair("Auth","3294yh")))
//            .build()
//
//        viewModelScope.launch {
//            kNetworking.enqueue(kNetworkRequest, onSuccess = {
//                _uiState.value = UiState.Success(it)
//            }, onError = {
//                _uiState.value = UiState.Error(it)
//            })
//        }

    }



}