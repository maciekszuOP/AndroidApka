package com.example.cocktailapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CocktailTimerViewModel : ViewModel() {

    private var timerJob: Job? = null

    private val _timeLeft = MutableStateFlow(0)
    val timeLeft: StateFlow<Int> = _timeLeft

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    fun startTimer(seconds: Int) {
        timerJob?.cancel()
        _timeLeft.value = seconds
        resumeTimer()
    }

    fun resumeTimer() {
        if (!_isRunning.value && _timeLeft.value > 0) {
            _isRunning.value = true
            timerJob = viewModelScope.launch {
                while (_timeLeft.value > 0 && _isRunning.value) {
                    delay(1000L)
                    _timeLeft.value -= 1
                }
                _isRunning.value = false
            }
        }
    }

    fun stopTimer() {
        _isRunning.value = false
    }

    fun resetTimer(seconds: Int) {
        timerJob?.cancel()
        _timeLeft.value = seconds
        _isRunning.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
