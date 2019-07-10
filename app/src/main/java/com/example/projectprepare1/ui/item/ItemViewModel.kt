package com.example.projectprepare1.ui.item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.MusicRoomDatabase
import com.example.projectprepare1.entity.Item
import com.example.projectprepare1.ui.scan.ScanRepository
import kotlinx.coroutines.launch


/**
 * 描述：
 * 相对应fragment所需要处理的所有liveData的方法，及liveDta的声明
 */
class ItemViewModel (application: Application) : AndroidViewModel(application) {
    var repository: ScanRepository
    private val items: MutableLiveData<ArrayList<Item>>?=null
    // TODO: Implement the ViewModel
     fun getItems():MutableLiveData<ArrayList<Item>>?{
        //初始化为repo内的liveData,调用repo
        return items
    }
    init {
        //val musicDao = MusicRoomDatabase.getDatabase(application, viewModelScope).musicDao()
        val musicDao = MusicRoomDatabase.instance.musicDao()
        repository = ScanRepository(musicDao)
    }
}
