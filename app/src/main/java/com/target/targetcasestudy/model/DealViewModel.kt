package com.target.targetcasestudy.model

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

import com.target.targetcasestudy.data.repository.DealRepository
import com.target.targetcasestudy.utils.DealResource
import com.target.targetcasestudy.data.entities.DealItem


class DealViewModel @ViewModelInject constructor(
    private val repository: DealRepository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()

    private val _deal = _id.switchMap { id ->
        repository.getDealDetail(id)
    }

    val dealDetail: LiveData<DealResource<DealItem>> = _deal

    fun start(id: Int) {
        _id.value = id
    }

    val deals = repository.getDeals()
}