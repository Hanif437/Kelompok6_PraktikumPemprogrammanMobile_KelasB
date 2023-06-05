package id.ac.unpas.showroom.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.showroom.model.SepedaMotor
import id.ac.unpas.showroom.repositories.SepedaMotorRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanSepedaMotorViewModel @Inject constructor(private val sepedaMotorRepository: SepedaMotorRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<SepedaMotor>> =
        MutableLiveData()
    val list: LiveData<List<SepedaMotor>> get() = _list

    private val _listSepedaMotor: MutableLiveData<List<SepedaMotor>> =
        MutableLiveData()
    val listSepedaMotor: LiveData<List<SepedaMotor>> get() = _listSepedaMotor

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        sepedaMotorRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(model: String,
                       warna: String,
                       kapasitas: String,
                       tanggal_rilis: String,
                       harga: String,
    ){
        _isLoading.postValue(true)
        sepedaMotorRepository.insert(model, warna, kapasitas, tanggal_rilis, harga,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (SepedaMotor?) ->
    Unit) {
        val item = sepedaMotorRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       model: String,
                       warna: String,
                       kapasitas: String,
                       tanggal_rilis: String,
                       harga: String,){
        _isLoading.postValue(true)
        sepedaMotorRepository.update(id, model, warna, kapasitas, tanggal_rilis, harga,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}
