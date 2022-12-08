package br.com.mdr.base.domain

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String
) : Parcelable