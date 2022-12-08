package br.com.mdr.base.domain

import android.os.Parcelable
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mdr.base.extension.convertDate
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class GitRepository(
    @PrimaryKey(autoGenerate = true)
    val repositoryId: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    val homepage: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @Embedded var owner: Owner,
    var language: String,
    @SerializedName("stargazers_count")
    val starsCount: Int?,
    @SerializedName("forks_count")
    val forksCount: Int?
): Parcelable {

    fun getCreateDate(): String? {
        return createdAt.convertDate()
    }

    fun getUpdateDate(): String? {
        return updatedAt.convertDate()
    }

    fun getStars(): Spanned = HtmlCompat.fromHtml("<b>$starsCount</b> stars",
        FROM_HTML_MODE_LEGACY)

    fun getForks(): Spanned = HtmlCompat.fromHtml("<b>$forksCount</b> forks",
        FROM_HTML_MODE_LEGACY)
}