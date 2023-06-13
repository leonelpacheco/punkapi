package com.leonel.intercamlp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.leonel.intercamlp.utils.CustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorites_menu -> {
                when(Navigation.findNavController(this,R.id.nav_host_fragment).currentDestination?.label){
                    getString(R.string.menu_txt_beer_label)->Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_beerFragment_to_favoritesFragment)
                    getString(R.string.menu_txt_detail_beer_label)->Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.action_detailbeerFragment_to_favoritesFragment)
                }
            }
            R.id.exit_menu->{
                val customDialog = CustomDialog(this)
                customDialog.one(
                    description = getString(R.string.dialog_info_txt_contenido),
                    titleOfPositiveButton = getString(R.string.dialog_btn_accept),
                    titleOfNegativeButton = getString(R.string.dialog_btn_cancel),
                    positiveButtonFunction = {
                        finish()
                    },
                    negativeButtonFunction = {

                    }).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}