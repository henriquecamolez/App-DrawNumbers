package br.com.camolez.sorteadordenumeros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import br.com.camolez.sorteadordenumeros.databinding.FragmentResultadoBinding
import kotlinx.coroutines.launch


class ResultadoFragment : Fragment() {

    private val viewModel: SorteioViewModel by activityViewModels()


    private var _binding: FragmentResultadoBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultadoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvResultado.text = getString(R.string.primeiro_resultado, "10")

            lifecycleScope.launch { viewModel.uiState.collect { uiState ->

                tvResultado.text = getString(R.string.primeiro_resultado, uiState.currentDrawNum.toString())

                clear()

                uiState.result.forEach {drawNum ->

                    gerarNumerosSorteados(drawNum)
                }
            }
            }
        }
    }

    private fun FragmentResultadoBinding.gerarNumerosSorteados(numDraw: Int) {
        val numeroSorteadoText = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = numDraw.toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))


        }
        root.addView(numeroSorteadoText)
        flowResultNum.referencedIds = flowResultNum.referencedIds.plus(numeroSorteadoText.id)
    }

    private fun FragmentResultadoBinding.clear(){
        flowResultNum.referencedIds.forEach {
            root.removeView(root.findViewById(it))
        }
    }
}