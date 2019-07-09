package com.example.projectprepare1.ui.scan


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.android.roomwordssample.Song

import com.example.projectprepare1.R
import com.example.projectprepare1.ui.music.MusicViewModel
import kotlinx.android.synthetic.main.fragment_scan.*

class ScanFragment : Fragment() {
    private lateinit var scanViewModel: ScanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scan.setOnClickListener {
            scanViewModel = ViewModelProviders.of(this).get(ScanViewModel::class.java)
            Thread(Runnable {
                context?.let { it1 -> ScanViewModel.MusicUtils.getMusicData(it1) }
                scanViewModel.insert(ScanViewModel.MusicUtils.getList())
            }).start()
        }
    }
}
