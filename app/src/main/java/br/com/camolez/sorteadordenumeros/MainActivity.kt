package br.com.camolez.sorteadordenumeros

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import br.com.camolez.sorteadordenumeros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: SorteioViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager
            .findFragmentById(R.id.fcvContent) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        with(binding){
            button.setOnClickListener {
                when(button.text){
                    getString(R.string.sortear) -> {
                    navController?.navigate(R.id.action_configSorteioFragment_to_resultadoFragment)
                        button.apply {

                            text = getString(R.string.sortear_novamente)
                            setCompoundDrawablesWithIntrinsicBounds(null, null,
                                AppCompatResources.getDrawable(
                                    this@MainActivity,
                                    R.drawable.frame_play
                                ), null
                            )
                        }
                        viewModel.result()

                    }
                    getString(R.string.sortear_novamente) -> {
                        navController?.popBackStack()
                        button.apply { text = getString(R.string.sortear)
                            setCompoundDrawablesWithIntrinsicBounds(null, null,
                                AppCompatResources.getDrawable(
                                    this@MainActivity,
                                    R.drawable.ic_arrow
                                ), null
                            ) }



                    }
                }


            }
        }



    }
}