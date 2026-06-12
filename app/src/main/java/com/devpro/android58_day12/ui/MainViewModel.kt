package com.devpro.android58_day12.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.devpro.android58_day12.data.model.User
import com.devpro.android58_day12.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * MainViewModel — MVVM ViewModel được inject bởi Hilt.
 *
 * - name, email      : MutableLiveData dùng cho Two-way DataBinding (@={})
 * - greeting         : LiveData dẫn xuất từ name  (Transformations.map)
 * - statusLabel      : LiveData dẫn xuất từ isActive
 * - hasSaveMessage   : LiveData dẫn xuất từ saveMessage
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    // ── Two-way DataBinding (@={vm.name}, @={vm.email}) ──────────────
    val name = MutableLiveData("")
    val email = MutableLiveData("")

    // ── One-way state ────────────────────────────────────────────────
    val isActive = MutableLiveData(false)
    val isLoading = MutableLiveData(false)
    val saveMessage = MutableLiveData("")

    // ── Derived LiveData via Transformations.map ─────────────────────
    val greeting: LiveData<String> = name.map { n ->
        if (n.isBlank()) "Hello, Guest! 👋" else "Hello, $n! 👋"
    }

    val demoLiveDataObject = MutableLiveData(User(name = "Alice", email = "hi"))

    val demoMutableLive = MutableLiveData("Hello")

    init {
        updateDemoLiveData()
    }

    fun updateDemoLiveData(){
//        demoMutableLive.value = "Xinchao"
        demoMutableLive.postValue("Post Xinchao")
    }


    val statusLabel: LiveData<String> = isActive.map { active ->
        if (active) "Active" else "Inactive"
    }

    val hasSaveMessage: LiveData<Boolean> = saveMessage.map { it.isNotEmpty() }

    // ── Actions ──────────────────────────────────────────────────────
    fun toggleStatus() {
        isActive.value = !(isActive.value ?: false)
        saveMessage.value = ""
    }

    fun saveUser() {
        if (isLoading.value == true) return
        isLoading.value = true
        saveMessage.value = ""

        // Giả lập API call 1.2 giây
        Handler(Looper.getMainLooper()).postDelayed({
            repository.saveUser(
                User(
                    name = name.value.orEmpty(),
                    email = email.value.orEmpty(),
                    isActive = isActive.value ?: false
                )
            )
            isLoading.value = false
            saveMessage.value = "Saved: ${name.value.orEmpty().ifBlank { "Guest" }}"
        }, 1200)
    }
}


