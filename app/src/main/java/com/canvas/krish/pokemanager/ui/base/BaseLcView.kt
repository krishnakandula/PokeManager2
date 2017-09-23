package com.canvas.krish.pokemanager.ui.base

/**
 * Created by Krishna Chaitanya Kandula on 9/20/2017.
 */
interface BaseLcView<T> {
    fun showLoading()
    fun stopLoading()
    fun setData(data: T)
    fun getExistingData(): T?
    fun showErrorLoadingData()
}
