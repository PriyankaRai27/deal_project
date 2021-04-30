package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.deal.DealListFragment
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val navHostFragment: NavHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController: NavController = navHostFragment.navController
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.credit_card -> {
        PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
        true
      }
      else -> false
    }
  }
}
