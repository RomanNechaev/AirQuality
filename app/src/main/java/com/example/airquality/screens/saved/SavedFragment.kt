package com.example.airquality.screens.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.airquality.MAIN
import com.example.airquality.R
import com.example.airquality.databinding.FragmentMainBinding
import com.example.airquality.databinding.FragmentSavedBinding
import com.example.airquality.models.AirQuality
import com.example.airquality.models.Reading
import com.example.airquality.screens.main.MainAdapter
import com.example.airquality.screens.main.MainFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedFragment : Fragment() {
    private var mBinding: FragmentSavedBinding? = null
    private val binding get() = mBinding!!
    lateinit var recycleView: RecyclerView
    private val adapter by lazy { SavedAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[SavedFragmentViewModel::class.java]
        val viewModel2 = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        recycleView = binding.rvSaved
        recycleView.adapter = adapter
        viewModel.getAllSavedCondition()
            .observe(viewLifecycleOwner) { list -> adapter.setList(list.asReversed()) }
        mBinding?.refresh?.setOnClickListener {
            lifecycleScope.launch {
                updateData(viewModel, viewModel2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    suspend fun updateAirConditions(
        airQuality: AirQuality,
        viewmodel2: MainFragmentViewModel
    ): AirQuality {
        var update = viewmodel2.getAirConditionRetrofit(airQuality.city)
        return AirQuality(
            airQuality.id,
            airQuality.placeId,
            airQuality.city,
            update!!.value,
            update.level
        )
    }

    suspend fun updateList(
        airQuality: List<AirQuality>,
        viewmodel2: MainFragmentViewModel
    ): List<AirQuality> {
        return airQuality.map { x -> updateAirConditions(x, viewmodel2) }
    }


    fun updateData(viewModel: SavedFragmentViewModel, viewmodel2: MainFragmentViewModel) {
        viewModel.getAllSavedCondition().observe(viewLifecycleOwner) { list ->
            lifecycleScope.launch {
                adapter.setList(withContext(Dispatchers.Default) {
                    updateList(list.asReversed(), viewmodel2)
                })
            }
        }

    }
}