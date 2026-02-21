package br.com.camolez.sorteadordenumeros

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class UiState(
    val drawNum: Int =2,
    val initLimit: Int =1,
    val endLimit: Int =100,
    val repeatNum: Boolean = false,
    val currentDrawNum: Int =0,
    val result: List<Int> = emptyList()
)

class SorteioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setAmountNum(amount: Int){
        _uiState.value = _uiState.value.copy(drawNum = amount)

    }
    fun setInitLimit(limit: Int){
        _uiState.value = _uiState.value.copy(initLimit = limit)


    }
    fun setEndLimit(limit: Int) {
        _uiState.value = _uiState.value.copy(endLimit = limit)
    }
    fun setRepeatNum(repeat: Boolean) {
        _uiState.value = _uiState.value.copy(repeatNum = repeat)
    }
    fun result(){

        val result = mutableListOf<Int>()

       repeat( _uiState.value.drawNum){
           var num = Random.nextInt(_uiState.value.initLimit, _uiState.value.endLimit)
           while (result.contains(num) && _uiState.value.repeatNum){
               num = Random.nextInt(_uiState.value.initLimit, _uiState.value.endLimit)

           }
           result.add(num)
       }



        _uiState.value = _uiState.value.copy(currentDrawNum =  _uiState.value.currentDrawNum + 1,
            result = result.toList())

    }









}