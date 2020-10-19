package com.example.madlevel4task2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var currentPage = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnGameHistory -> {
                try {
                    navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
                catch (e: Exception) {
                    navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}