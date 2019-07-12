package com.example.projectprepare1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.projectprepare1.ui.music.MusicViewModel
import com.example.projectprepare1.ui.scan.ScanFragment
import com.example.projectprepare1.ui.scan.ScanPermissionUtils
import com.example.projectprepare1.ui.scan.ScanRepository
import com.example.projectprepare1.ui.scan.ScanViewModel

/**
 * fragment展示的容器
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val permissionUtils = ScanPermissionUtils(this)
    private var mHasPermissionRunnable: Runnable= Runnable {
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
//        var fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)!!;
//        return NavHostFragment.findNavController(fragment).navigateUp();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionUtils.checkStoragePermission(mHasPermissionRunnable)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings ->{
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent);
            }
        }
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    //下面函数没反应
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view itemFragment clicks here.
        when (item.itemId) {
            R.id.nav_about-> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent);
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
