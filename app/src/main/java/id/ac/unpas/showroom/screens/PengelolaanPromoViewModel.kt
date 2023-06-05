package id.ac.unpas.showroom.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.showroom.model.Promo
import id.ac.unpas.showroom.repositories.PromoRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanPromoViewModel @Inject constructor(private val promoRepository: PromoRepository) : ViewModel()
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
    private val _list: MutableLiveData<List<Promo>> =
        MutableLiveData()
    val list: LiveData<List<Promo>> get() = _list

    private val _listPromo: MutableLiveData<List<Promo>> =
        MutableLiveData()
    val listPromo: LiveData<List<Promo>> get() = _listPromo

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        promoRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(model: String,
                       tanggal_awal: String,
                       tanggal_akhir: String,
                       persentase : String,
    ){
        _isLoading.postValue(true)
        promoRepository.insert(model, tanggal_awal, tanggal_akhir, persentase,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (Promo?) ->
    Unit) {
        val item = promoRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       model: String,
                       tanggal_awal: String,
                       tanggal_akhir: String,
                       persentase : String,){
        _isLoading.postValue(true)
       promoRepository.update(id, model, tanggal_awal, tanggal_akhir, persentase,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }
}
