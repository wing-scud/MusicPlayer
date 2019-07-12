package com.example.projectprepare1.ui.item

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.projectprepare1.PlayerActivity
import com.example.projectprepare1.R

import com.example.projectprepare1.entity.Item
import kotlinx.android.synthetic.main.fragment_music.*
import kotlinx.android.synthetic.main.item_fragment.*





/**
 * 对应一个可视化的页面
 * 需要处理的数据从viewModel里拿取
 * onCreateView()：每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View组件。
     onActivityCreated()：当Fragment所在的Activity被启动完成后回调该方法。
Notes:
①静态的view不需要onActivityCreated
②保存view的状态的时候需要用onActivityCreated
③访问父activity的view层的时候需要在onActivityCreated 方法里面做
即如果view是静态的，那么没有必要在onActivityCreated 方法去调用，
        大多数的自定义的view，初始化时都需要一个context，而activity是context的子类，
        所以在onCreateView方法的时候非静态的view初始化调用可能出现异常，所以对于非静态的view
        最好在onActivityCreated方法调用
 */

class itemFragment : Fragment() {

    companion object {
        fun newInstance() = itemFragment()
    }

    private lateinit var viewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.projectprepare1.R.layout.item_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        enterMusic.setOnClickListener{
//            Navigation.findNavController(view).navigate(com.example.projectprepare1.R.id.action_item_to_music)
//        }

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        //TODO:调用viewModel的方法进行对应操作
        val itemliveData=viewModel.getItems()
        val itemObserver = object:Observer<ArrayList<Item>> {
            override fun onChanged(t: ArrayList<Item>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        playAll.setOnClickListener {
            startActivity(Intent(context,PlayerActivity::class.java))
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        itemliveData?.observe(this,itemObserver)

    }

}
