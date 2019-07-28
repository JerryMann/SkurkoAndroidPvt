package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.pvt.skurkoandroidpvt.dz9.CarRepository
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi
import by.itacademy.pvt.skurkoandroidpvt.dz9.provideCarRepository

class Dz11ViewModel : ViewModel() {

    private val carRepository: CarRepository = provideCarRepository()
    private var carsList: List<Poi> = emptyList()

    val state: MutableLiveData<Dz11State> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Dz11State>()
    }

    val lastSelectedCar: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Poi>()
    }

    fun loadCarsList(coordinate: CoordinateParams) {
        if (state.value is Dz11State.Loaded) {
            return
        }
        state.value = Dz11State.Loading
        carRepository.getCarByCoordinate(coordinate, { list: List<Poi> ->
            carsList = list
            state.value = Dz11State.Loaded
        }, { throwable: Throwable ->
            state.value = Dz11State.Error(throwable)
        })
    }

    fun selectCarById(id: String) {
        carsList.find { it.id == id }.apply { lastSelectedCar.value = this }
    }

    fun hasCars(): Boolean {
        return carsList.isNotEmpty()
    }

    fun getCars(): List<Poi> {
        return carsList
    }
}