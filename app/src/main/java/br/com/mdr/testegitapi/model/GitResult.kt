package br.com.mdr.testegitapi.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */
class GitResult (
    var id: Int,
    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("incomplete_results") var incompleteResults: Boolean,
    @SerializedName("items") var repositories: MutableList<Repository>
)