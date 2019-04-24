package br.com.mdr.testegitapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marlon D. Rocha on 23/04/2019.
 */

@Entity
class Owner : Serializable {
    @PrimaryKey(autoGenerate = true)
    var OwnerId: Int = 0
    @SerializedName("avatar_url") var avatarUrl: String = ""
    var login: String = ""
    @SerializedName("html_url") var htmlUrl: String? = ""
    var reposUrl: String = ""
}