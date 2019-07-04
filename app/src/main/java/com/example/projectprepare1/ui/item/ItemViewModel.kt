package com.example.projectprepare1.ui.item

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData
import com.example.projectprepare1.entity.Item


/**
 * 描述：
 * 相对应fragment所需要处理的所有liveData的方法，及liveDta的声明
 */
class ItemViewModel : ViewModel() {
    private val items: MutableLiveData<ArrayList<Item>>?=null
    // TODO: Implement the ViewModel
     fun getItems():MutableLiveData<ArrayList<Item>>?{
        //初始化为repo内的liveData,调用repo
        return items
    }
}
