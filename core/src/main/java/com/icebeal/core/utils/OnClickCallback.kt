package com.icebeal.core.utils

import com.icebeal.core.model.presenter.Movie

interface OnClickCallback {
    fun onClick(movie: Movie)
}