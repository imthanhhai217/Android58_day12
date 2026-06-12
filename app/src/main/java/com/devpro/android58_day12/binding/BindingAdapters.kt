package com.devpro.android58_day12.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * BindingAdapters — Kotlin thuần, kapt cho phép DataBinding scan file này.
 *
 * Với kapt, annotation processor tạo Java stubs từ Kotlin files TRƯỚC khi
 * DataBinding generate code → DataBinding tìm thấy @BindingAdapter ở đây.
 */
object BindingAdapters {

    /**
     * BindingAdapter 1 — visibleIf
     * Hiện / ẩn View dựa trên điều kiện Boolean.
     * XML: app:visibleIf="@{someBoolean}"
     */
    @JvmStatic
    @BindingAdapter("visibleIf")
    fun setVisibleIf(view: View, condition: Boolean?) {
        view.visibility = if (condition == true) View.VISIBLE else View.GONE
    }

    /**
     * BindingAdapter 2 — activeTextColor
     * Đổi màu chữ TextView theo trạng thái active.
     * Active → xanh (#4CAF50) | Inactive → đỏ (#F44336)
     * XML: app:activeTextColor="@{vm.isActive}"
     */
    @JvmStatic
    @BindingAdapter("activeTextColor")
    fun setActiveTextColor(textView: TextView, isActive: Boolean?) {
        val color = if (isActive == true) Color.parseColor("#4CAF50") else Color.parseColor("#F44336")
        textView.setTextColor(color)
    }

    /**
     * BindingAdapter 3 — dotActiveColor
     * Đổi màu nền (backgroundTint) của View theo trạng thái active.
     * Dùng cho chấm tròn chỉ thị trạng thái.
     * XML: app:dotActiveColor="@{vm.isActive}"
     */
    @JvmStatic
    @BindingAdapter("dotActiveColor")
    fun setDotActiveColor(view: View, isActive: Boolean?) {
        val color = if (isActive == true) Color.parseColor("#4CAF50") else Color.parseColor("#F44336")
        view.backgroundTintList = ColorStateList.valueOf(color)
    }
}

