package com.webworks.practiseapp.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("total_count") var totalCount: Int,
                     var items: List<Repository>?)
