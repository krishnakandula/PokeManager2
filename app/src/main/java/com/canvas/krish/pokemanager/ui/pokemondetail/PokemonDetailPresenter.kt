package com.canvas.krish.pokemanager.ui.pokemondetail

import android.util.Log
import com.canvas.krish.pokemanager.data.models.Pokemon
import com.canvas.krish.pokemanager.data.source.PokemonRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Krishna Chaitanya Kandula on 9/21/17.
 */
class PokemonDetailPresenter @Inject constructor(private val view: PokemonDetailContract.View,
                                                 @Named("PokemonDetail.PokemonId") val pokemonId: Int,
                                                 private val pokemonRepository: PokemonRepository) : PokemonDetailContract.Presenter {

    companion object {
        private val LOG_TAG: String = PokemonDetailPresenter::class.simpleName!!
    }

    override fun start() {
        var existingData: Pokemon? = view.getExistingData()
        if(existingData == null) {
            getData()
        }
    }

    override fun getData() {
        view.showLoading()
        pokemonRepository.getPokemon(pokemonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SingleObserver<Pokemon> {
                    override fun onSuccess(t: Pokemon?) {
                        if(t != null) {
                            view.setData(t)
                        }
                        view.stopLoading()
                    }

                    override fun onSubscribe(d: Disposable?) {
                        //Not Implemented
                    }

                    override fun onError(e: Throwable?) {
                        if(e != null) {
                            Log.e(LOG_TAG, e.message, e)
                        }
                        view.stopLoading()
                    }
                })
    }

    override fun onRefresh() {
        getData()
    }
}
