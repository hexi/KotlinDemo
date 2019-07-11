package com.hexi.kotlindemo.api

import com.hexi.kotlindemo.data.Contributor
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<Contributor>>
}