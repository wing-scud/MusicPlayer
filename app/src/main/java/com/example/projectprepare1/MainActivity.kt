package com.example.projectprepare1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import com.example.projectprepare1.ui.scan.ScanPermissionUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*

/**
 * fragment展示的容器
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val permissionUtils = ScanPermissionUtils(this)
    var temp =0
    private var mHasPermissionRunnable: Runnable= Runnable {
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionUtils.checkStoragePermission(mHasPermissionRunnable)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
//
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    @SuppressLint("ResourceAsColor")
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            var sharedPreferences=getSharedPreferences("temp",0)
            temp=sharedPreferences.getInt("key",0)
            when(temp){
                0->{
                    beijing.setImageResource(R.drawable.bg3)
                }
                1-> {
                    beijing.setImageResource(R.drawable.beijing)
                }
                2-> {
                    beijing.setImageResource(R.drawable.beijing2)
                }
            }
            recreate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                if (!drawerLayout.isDrawerOpen(nav_view))
                    drawerLayout.openDrawer(Gravity.LEFT)
                else
                    drawerLayout.closeDrawer(Gravity.LEFT)

            }
        }
//            R.id.action_settings ->{
//                val fragmentTransaction = beginTransaction()
//                fragmentTransaction.replaceFragment(HomeFragment(), ScanFragment())
//                val fragmentTransaction = beginTransaction()
//                fragmentTransaction.replace(res, fragment);
//                fragmentTransaction.commit();

//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.nav_host_fragment, ScanFragment())
//                    .commit()
//                val intent = Intent(this, PlayerActivity::class.java)
//                startActivity(intent);
//            }
//        }
        return true
//            when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view itemFragment clicks here.
        when (item.itemId) {
            R.id.changecolor->{
                var sharedPreferences=getSharedPreferences("temp",0)
                var temp=sharedPreferences.getInt("key",0)
                Log.d("bg",temp.toString())
                if(temp==2){
                    temp=0
                }
                else{
                    temp++
                }
                sharedPreferences.edit().putInt("key",temp).commit()
                when(temp){
                    0->{
                        beijing.setImageResource(R.drawable.bg3)
                    }
                    1-> {
                        beijing.setImageResource(R.drawable.beijing)
                    }
                    2-> {
                        beijing.setImageResource(R.drawable.beijing2)
                        imageView.setImageResource(R.drawable.yourname)
                        lv.setBackgroundResource(R.color.seaBlue)
                    }
                }
            }
            R.id.nav_about-> {
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra("temp",temp)
                startActivity(intent)
            }
            R.id.nav_out-> {
                System.exit(0)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
