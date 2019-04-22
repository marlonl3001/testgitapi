package br.com.mdr.testegitapi.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */


class Repository(
    var id: Int,
    var name: String,
    @SerializedName("full_name") var fullName: String,
    var description: String,
    var homepage: String,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("updated_at") var updatedAt: String,
    var owner: Owner,
    var language: String?,
    var repository: String?
)

class Owner(
    var id: Int,
    @SerializedName("avatar_url") var avatarUrl: String,
    var login: String,
    @SerializedName("html_url") var htmlUrl: String?,
    var reposUrl: String?
)