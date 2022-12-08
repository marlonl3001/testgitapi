package br.com.mdr.base.data.entity

import br.com.mdr.base.domain.GitRepository
import com.google.gson.annotations.SerializedName

class GitResponse (
    @SerializedName("items") var repositories: List<GitRepository>
)