package org.sopt.and.feature.home

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.and.feature.home.model.ContentModel

data class HomeState(
    val topBannerContent: PersistentList<ContentModel> = persistentListOf(),
    val recommendContent: PersistentList<ContentModel> = persistentListOf(),
    val top20Content: PersistentList<ContentModel> = persistentListOf()
)
