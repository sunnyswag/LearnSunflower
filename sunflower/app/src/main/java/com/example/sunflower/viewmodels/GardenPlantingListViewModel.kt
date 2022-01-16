package com.example.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sunflower.data.repository.GardenPlantingRepository
import javax.inject.Inject

class GardenPlantingListViewModel @Inject internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
): ViewModel() {
    val plantAndGardenPlantings = gardenPlantingRepository.getPlantedGardens().asLiveData()
}