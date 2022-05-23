package sk.globing.domain.feature.category.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("categories")
    val categoriesList: List<String>
)
