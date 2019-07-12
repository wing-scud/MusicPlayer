package com.example.projectprepare1.ui.player

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Typeface
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
import com.example.projectprepare1.util.PlayerUtil.Companion.setAudioNext
import com.example.projectprepare1.util.PlayerUtil.Companion.setNexFromPlayWay
import com.example.projectprepare1.util.PlayerUtil.Companion.timeParse
import com.hw.lrcviewlib.ILrcViewSeekListener
import com.hw.lrcviewlib.LrcDataBuilder
import kotlinx.android.synthetic.main.item_fragment.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.player_fragment.*
import android.content.Intent
import android.content.Intent.*
import com.example.projectprepare1.PlayerActivity


class PlayerFragment : Fragment() {
     var mediaPlayer: MediaPlayer?=null
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
                2->
                {
                    //自动切歌，当一首歌播放结束后
                    viewModel.setPause(true)
                    mediaPlayer?.reset()
                    viewModel.setCurrentMusic(setNexFromPlayWay(viewModel.getPlayWay().value!!,currentMusic,musicListFra!!))
                    initMusic()
                    viewModel.setPause(false)
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
        var intent= activity?.intent
        var temp=intent?.getStringExtra("song")
        Log.d("temp","${temp}"+"  song")
        if(temp!=null)
            viewModel.setCurrentMusicName(temp)
        initMusic()
        val assets =context!!.assets
        var fromAsset = Typeface.createFromAsset(assets, "fonts/tff1.ttf");
        musicName.typeface = fromAsset
        musicSonger.typeface = fromAsset
        timePlay.typeface=fromAsset
        val pause = viewModel.getPause()
        pause.observe(this,Observer<Boolean>{
            //播放歌曲  ，操作pause
            gramophoneView.playing=!pause.value!!
            if(pause.value!!){
                if(mediaPlayer?.isPlaying!!)
                {
                    playSong.setImageResource(R.drawable.play)
                    mediaPlayer?.pause()
                }
            }
            else{
                playSong.setImageResource(R.drawable.stop)
                mediaPlayer?.start()
                seekBarListen()
            }
        })
        addSongList.setOnClickListener {
            var dialog = android.app.AlertDialog.Builder(this.context)
            dialog.setTitle("收藏到的歌单")
            //TODO:引用歌单
            var items=viewModel.getSongMenu()
           // var items:Array<String> = arrayOf("How", "Are", "You")
            var itemState= booleanArrayOf()
            for(i in 0 until  items.size){
                itemState.set(i,false)
            }
            dialog.setMultiChoiceItems(items,itemState,DialogInterface.OnMultiChoiceClickListener(){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                itemState[i]=true
            })
            dialog.setPositiveButton("确定",DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                for(i in 0 until items.size){
                    if(itemState[i]==true){
                        viewModel.addSongToList(items[i])
                    }
                }
            })
            dialog.setNegativeButton("取消") { dialog, which -> }
            dialog.show()
        }
        //播放按钮点击事件
        playSong.setOnClickListener{
            viewModel.setPause(!pause.value!!)
        }
        //进度条拖动
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                 //   Log.d("music", mediaPlayer?.currentPosition!!.toString()+"  currentPosition")
                    timePlay.text=timeParse((mediaPlayer!!.duration - mediaPlayer?.currentPosition!!).toLong())
//                    Log.d("music",seekBar.progress.toString()+"  seekBar.progress ")
//                    Log.d("music",progress.toString()+"  .progress ")
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        //切歌
        front.setOnClickListener {
             viewModel.setPause(true)
              mediaPlayer?.reset()
              viewModel.setCurrentMusic(setAudioNext(1,currentMusic,musicListFra!!))
              initMusic()
        }
        next.setOnClickListener {
            viewModel.setPause(true)
            mediaPlayer?.reset()
            viewModel.setCurrentMusic(setAudioNext(-1,currentMusic,musicListFra!!))
            initMusic()
        }
        val current=viewModel.getCurrentMusic()
        current.observe(this,Observer<Int>{
            //监听，重新缓冲歌
            Log.d("music","current  observe")
              //  initMusic()
        })
        gramophoneView.setOnClickListener {
            gramophoneView.visibility = View.GONE
            au_lrcView.visibility = View.VISIBLE
          //  Toast.makeText(this.context, "点击唱片，显示歌词", Toast.LENGTH_SHORT).show()
        }
        au_lrcView.setOnClickListener {
            gramophoneView.visibility = View.VISIBLE
            au_lrcView.visibility = View.GONE
            //Toast.makeText(this.context, "点击歌词，显示专辑封面", Toast.LENGTH_SHORT).show()
        }
        playWayView.setOnClickListener {
            var playWay=viewModel.getPlayWay()
            viewModel.setPlayWay(1)
            var str=""
            when(playWay.value){
                1->{
                    str="单曲循环"
                    playWayView.setImageResource(R.drawable.turn1)
                }
                2->{
                    str="顺序播放"
                    playWayView.setImageResource(R.drawable.turn2)
                }
                3->{
                    str="随机播放"
                    playWayView.setImageResource(R.drawable.turn3)
                }
            }
            Toast.makeText(context,str,Toast.LENGTH_SHORT).show()

        }
        //initLrc()
    }
    private fun initMusic() {
        if(mediaPlayer==null)
            mediaPlayer= MediaPlayer()
        if(musicListFra==null)
            musicListFra=viewModel.getMusicList()?.value
        currentMusic=viewModel.getCurrentMusic().value!!
        var path=musicListFra?.get(currentMusic)!!.path
        mediaPlayer?.setDataSource(path) //设置 播放路径
        mediaPlayer?.prepare()
        timePlay.text=timeParse(mediaPlayer?.duration!!.toLong())
        seekBar.max= mediaPlayer?.duration!!
        seekBar.progress=0
        musicName.text= musicListFra!![currentMusic].song
        musicSonger.text=musicListFra!![currentMusic].singer
    }
    private fun seekBarListen() {
        val thread = object : Thread() {
            override fun run() {
                val pause = viewModel.getPause()
                while (!pause.value!!) {
                    var current = mediaPlayer?.currentPosition!!
                    var timeSymbol = timeParse((mediaPlayer!!.duration - current).toLong())
                    var message = Message()
                    message.what = 1
                    seekBar.progress=current
                    message.data.putString("timePlay", timeSymbol)
                    handler.sendMessage(message)
                    if(!mediaPlayer?.isPlaying!!){
                        var message2 = Message()
                        message2.what = 2
                        handler.sendMessage(message2)
                        Log.d("music","new music ")
                    }
                    Thread.sleep(500)
                }
            }
        }.start()
    }
    private fun initLrc(){

       var lrcRows =  LrcDataBuilder().BuiltFromAssets(this.context, "yang1.lrc");
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
    private fun getListName(intent: Intent):String{
        var listName=""
        if(!intent.getStringExtra("local").equals("0")){//匹配本地音乐
            //不做事
        }
        if(!intent.getStringExtra("songList").equals("0")){//匹配歌单
            listName=intent.getStringExtra("songList")
            viewModel.setListNameList(listName)
        }
        if(!intent.getStringExtra("singer").equals("0")){//匹配歌手
            listName=intent.getStringExtra("singer")
            viewModel.setListNameSinger(listName)
        }
        return listName;
    }
    override fun onDestroy() {
        super.onDestroy()
        viewModel.setPause(true)
        mediaPlayer?.release()
        Log.d("bug","onDestroy")
    }
}