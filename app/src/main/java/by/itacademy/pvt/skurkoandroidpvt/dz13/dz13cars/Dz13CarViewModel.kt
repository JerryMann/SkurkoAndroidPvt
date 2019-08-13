package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz13CarViewModel : ViewModel() {

    private var poiList: List<Poi> = emptyList()

    private val repository = provideCarRepository()
    private var disposable: Disposable? = null

    val state: MutableLiveData<Dz13CarState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Dz13CarState>()
    }

    fun loadCarList(params: CoordinateParams) {
        if (poiList.isEmpty()) {
            disposable = repository
                .getCarByCoord(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    poiList = it.poiList
                    state.value = Dz13CarState.GetAll(poiList)
                }, {
                    state.value = Dz13CarState.Error
                })
        } else {
            state.value = Dz13CarState.GetAll(poiList)
        }
    }

    fun showCar(poi: Poi) {
        state.value = Dz13CarState.GetOneCar(poi)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}