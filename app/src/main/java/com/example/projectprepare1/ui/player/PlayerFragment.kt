package com.example.projectprepare1.ui.player

import android.content.DialogInterface
import android.graphics.Color
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.android.roomwordssample.Song
import com.example.projectprepare1.R
import com.example.projectprepare1.util.DisPlayUtil
import com.hw.lrcviewlib.ILrcViewSeekListener
import com.hw.lrcviewlib.LrcDataBuilder
import kotlinx.android.synthetic.main.player_fragment.*


class PlayerFragment : Fragment() {
    private var mediaPlayer: MediaPlayer?=null
    var musicListFra:List<Song>?=null
    var currentMusic:Int=0
    companion object {
        fun newInstance() = PlayerFragment()
    }

    private lateinit var viewModel: PlayerViewModel

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1->{
                    timePlay.text=msg.data.getString("timePlay")
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        initMusic()
        val pause = viewModel.getPause()
        pause.observe(this,Observer<Boolean>{
            //播放歌曲  ，操作pause
            if(pause?.value==true){
                if(mediaPlayer?.isPlaying!!)
                    mediaPlayer?.pause()
            }
            else{
                mediaPlayer?.start()
                seekBarListen()
            }

        })
        addSongList.setOnClickListener {
            var dialog = android.app.AlertDialog.Builder(this.context)
            dialog.setTitle("收藏到的歌单")
            var items:Array<String> = arrayOf("How", "Are", "You")
            var itemState:BooleanArray= booleanArrayOf(false,false,false)
            dialog.setMultiChoiceItems(items,itemState,DialogInterface.OnMultiChoiceClickListener(){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                itemState[i]=true

            })
            dialog.setPositiveButton("确定",DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                for(i in 0 until items.size){
                    if(itemState[i]==true){
                        //添加到歌单中
                    }
                }
            })
            dialog.setNegativeButton("取消") { dialog, which -> }
            dialog.show()
        }


        playSong.setOnClickListener{
            viewModel.setPause(!pause.value!!)
            //更新pause,取反
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(seekBar.progress)
                    timePlay.text=timeParse((mediaPlayer!!.duration
                            - mediaPlayer?.currentPosition!!).toLong())
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        //切歌
        front.setOnClickListener {
            if (mediaPlayer?.isPlaying!!)
                mediaPlayer?.stop()
            viewModel.setPause(true)
            viewModel.setCurrentMusic(1)
            initMusic()
        }
        next.setOnClickListener {
            if (mediaPlayer?.isPlaying!!)
                mediaPlayer?.stop()
            viewModel.setPause(true)
            viewModel.setCurrentMusic(-1)
            initMusic()
        }
        gramophoneView.setOnClickListener {
            gramophoneView.visibility = View.GONE
            au_lrcView.visibility = View.VISIBLE
            Toast.makeText(this.context, "点击唱片，显示歌词", Toast.LENGTH_SHORT).show()
        }
        au_lrcView.setOnClickListener {
            gramophoneView.visibility = View.VISIBLE
            au_lrcView.visibility = View.GONE
            Toast.makeText(this.context, "点击歌词，显示专辑封面", Toast.LENGTH_SHORT).show()
        }

        var lrcRows =  LrcDataBuilder().BuiltFromAssets(this.context, "yang1.lrc");
        //ro  List<LrcRow> lrcRows = new LrcDataBuilder().Build(file);
        au_lrcView.setTextSizeAutomaticMode(true);//是否自动适配文字大小

        au_lrcView.setLrcViewSeekListener(ILrcViewSeekListener { currentLrcRow, CurrentSelectedRowTime ->
            //在这里执行播放器控制器控制播放器跳转到指定时间
            mediaPlayer?.seekTo(CurrentSelectedRowTime.toInt())
        })
        au_lrcView.smoothScrollToTime(mediaPlayer?.currentPosition!!.toLong())
        //init the lrcView
        au_lrcView.getLrcSetting()
            .setTimeTextSize(40)//时间字体大小
            .setSelectLineColor(Color.parseColor("#ffffff"))//选中线颜色
            .setSelectLineTextSize(25)//选中线大小
            .setHeightRowColor(Color.parseColor("#aaffffff"))//高亮字体颜色
            .setNormalRowTextSize(DisPlayUtil.sp2px(this.context, 17.toFloat()))//正常行字体大小
            .setHeightLightRowTextSize(DisPlayUtil.sp2px(this.context, 17.toFloat()))//高亮行字体大小
            .setTrySelectRowTextSize(DisPlayUtil.sp2px(this.context, 17.toFloat()))//尝试选中行字体大小
            .setTimeTextColor(Color.parseColor("#ffffff"))//时间字体颜色
            .setTrySelectRowColor(Color.parseColor("#55ffffff"));//尝试选中字体颜色

        au_lrcView.commitLrcSettings()
        au_lrcView.setLrcData(lrcRows)



    }
    private fun initMusic() {
        if(mediaPlayer==null)
            mediaPlayer= MediaPlayer()
        if(musicListFra==null)
            musicListFra=viewModel.getMusicList()?.value
        currentMusic=viewModel.getCurrentMusic().value!!
        seekBar.progress=0
        var path=viewModel.getMusicList()?.value!!.get(viewModel.getCurrentMusic().value!!).path
        Log.d("music",path+"  path ")
        mediaPlayer!!.setDataSource(path)   //设置 播放路径
      //  mediaPlayer = MediaPlayer.create(this.context,R.raw.yang)
        mediaPlayer!!.prepareAsync()
        Log.d("music",mediaPlayer?.duration.toString()+"duration")
        seekBar.max= mediaPlayer?.duration!!
        timePlay.text=timeParse(mediaPlayer?.duration!!.toLong())
        musicName.text= musicListFra!![currentMusic].song
        musicSonger.text=musicListFra!![currentMusic].singer
        //gramophoneView.setPictureRes(getResourceMusicImage(music.image))
    }
    private fun seekBarListen() {
        var thread = object : Thread() {
            override fun run() {
                val pause = viewModel.getPause()
                while (!pause.value!!) {
                    var current = mediaPlayer?.currentPosition!!
                    var timeSymbol = timeParse((mediaPlayer!!.duration - current).toLong())
                    var message = Message()
                    message.what = 1
                    message.data.putString("timePlay", timeSymbol)
                    handler.sendMessage(message)
                    gramophoneView.playing=!pause.value!!
                }
            }
        }.start()
    }
    private fun timeParse(duration: Long): String {
        var time = ""
        var minute = duration / 60000
        var seconds = duration % 60000
        var second = Math.round(seconds.toDouble() / 1000)
        if (minute < 10) {
            time += "0"
        }
        time += minute.toString() + ":"
        if (second < 10) {
            time += "0"
        }
        time += second
        return time
    }

    override fun onResume() {
        super.onResume()
        //mediaPlayer?.stop()
    }
}