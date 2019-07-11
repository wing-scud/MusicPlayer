package com.example.projectprepare1.ui.localmusic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.projectprepare1.ui.music.MusicFragment
import com.example.projectprepare1.ui.singer.SingerFragment

class TabAdapter(fm: FragmentManager):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0->{return MusicFragment()
            }
            else->{return SingerFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->{return "单曲"}
            else->{return "歌手"}
        }
    }
    override fun getCount(): Int {
        return 2
    }
}
