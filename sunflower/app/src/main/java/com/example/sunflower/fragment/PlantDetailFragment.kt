package com.example.sunflower.fragment

import androidx.fragment.app.Fragment
import com.example.sunflower.data.domain.Plant

class PlantDetailFragment : Fragment() {

    fun interface Callback {
        // TODO 这里为什么要加 ？
        fun add(plant: Plant?)
    }
}