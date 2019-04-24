package br.com.mdr.testegitapi.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.testegitapi.extensions.convertDate
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */

@Entity
class Repository: Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    @SerializedName("full_name") var fullName: String = ""
    var description: String = ""
    var homepage: String = ""
    @SerializedName("created_at") var createdAt: String = ""
    @SerializedName("updated_at") var updatedAt: String = ""
    @Embedded var owner: Owner = Owner()
    var language: String = ""
    var repository: String = ""

    fun getCreateDate(): String {
        return convertDate(createdAt)
    }

    fun getUpdateDate(): String {
        return convertDate(updatedAt)
    }

    fun checklanguage():Boolean{
        return !language.isNullOrEmpty()
    }
    fun checkHomepage():Boolean{
        return !homepage.isNullOrEmpty()
    }
}