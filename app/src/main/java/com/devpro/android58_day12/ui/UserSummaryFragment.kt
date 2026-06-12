package com.devpro.android58_day12.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.devpro.android58_day12.databinding.FragmentUserSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * UserSummaryFragment — minh họa activityViewModels().
 *
 * activityViewModels() trả về cùng ViewModel instance với Activity cha.
 * Khi người dùng nhập tên / đổi trạng thái ở Activity,
 * Fragment này tự động cập nhật vì cùng quan sát một LiveData.
 *
 * So sánh:
 *   viewModels()         → ViewModel riêng, scoped to Fragment
 *   activityViewModels() → ViewModel chung, scoped to Activity (chia sẻ state)
 */
@AndroidEntryPoint
class UserSummaryFragment : Fragment() {

    // activityViewModels(): lấy MainViewModel từ Activity cha
    // CÙNG instance với binding.vm trong MainActivity
    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentUserSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserSummaryBinding.inflate(inflater, container, false)
        // Gắn lifecycleOwner để LiveData tự động cập nhật UI
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.vm = viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

