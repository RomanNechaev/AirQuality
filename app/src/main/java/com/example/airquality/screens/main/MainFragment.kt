package com.example.airquality.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.airquality.MAIN
import com.example.airquality.R
import com.example.airquality.databinding.FragmentMainBinding
import com.example.airquality.models.AirQuality
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.random.Random

class MainFragment : androidx.fragment.app.Fragment() {
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var newText:String
    private var isSaved = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this@MainFragment)[MainFragmentViewModel::class.java]
        var placeId = ""
        var city = ""
        var value = ""
        var level = ""
        viewModel.initDataBase()
            mBinding?.mainButton?.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    newText = withContext(Dispatchers.IO) {
                        placeId = viewModel.getPlaceRetrofit(mBinding!!.inputText.text.toString())?.place_id.toString()
                        city = mBinding!!.inputText.text.toString()
                        val air = viewModel.getAirConditionRetrofit(mBinding!!.inputText.text.toString())
                        level = air!!.level
                        value = air.value
                        "Значение: "+ air.value + " Состояние: " + air.level
                    }
                mBinding!!.result.text = newText
            }
        }

        binding.imageFavoriteCity.setOnClickListener(){
            if (!isSaved) {
                viewModel.insert(AirQuality(id = placeId.hashCode(),placeId,city,value,level)){}
                isSaved = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_savedFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}