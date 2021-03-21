package com.example.habitandgoaltracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.habitandgoaltracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*Glavni Menu aplikacije, sadrži sve aktivnosti koje korisnik može koristiti. Kada korisnik prvi put pokrene aplikaciju dobije
    5 početnih aktivnosti, koje može koristiti. Pored toga, dodao sam dugme koje prelazi na dio gdje korisnik može napraviti aktivnost
    po svojoj mjeri.
    */

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    /*DataBiding je biblioteka koja nam je omogućila da spojimo UI komponente u Layoutima sa našom DB(Bazom podataka) u kojoj se nalaze
    o aktivnostima
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // kreiranje varijable za spajanje baze i layouta

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        // Navigacija kroz menu do ostalih aktivnosti

        navController = findNavController(R.id.myNavHostFragment)

        navController.setGraph(R.navigation.navigation)

        //NavController je "klasa" koju koristimo za navigaciju u nasoj aplikaciji. (Kreiran je kao neki niz koji sadrži šta sve imamo u apk.)

        drawerLayout = binding.drawerLayout

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    //Ugrađena metoda koju korisnik koristi kada želi "navigirati" kroz aplikaciju

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    //Hook koji se poziva kada je neka stvar u našem meniju kliknuta (selektovana)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.detailsFragment -> {
                findNavController(R.id.myNavHostFragment).navigate(R.id.action_homeFragment_to_createCategoryFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
