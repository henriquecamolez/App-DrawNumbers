package br.com.camolez.sorteadordenumeros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import br.com.camolez.sorteadordenumeros.databinding.FragmentConfigSorteioBinding
import kotlin.getValue

class ConfigSorteioFragment : Fragment() {
    private val viewModel: SorteioViewModel by activityViewModels()

    private var _binding: FragmentConfigSorteioBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigSorteioBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            swtNotReapeat.setOnCheckedChangeListener { _, isChecked ->

                    swtNotReapeat.trackTintList = getColorStateList(
                        requireContext(),
                        if (isChecked) R.color.background_brand else R.color.content_tertiary
                    )
                viewModel.setRepeatNum(repeat = !isChecked)

            }

            etAmountNum.addTextChangedListener { text ->
                viewModel.setAmountNum(amount = text.toString().toIntOrNull() ?: 0)
            }

            etFromNum.addTextChangedListener { text ->
                viewModel.setInitLimit(limit = text.toString().toIntOrNull() ?: 0 )
            }

            etAtNum.addTextChangedListener { text ->
                viewModel.setEndLimit(limit = text.toString().toIntOrNull() ?: 0 )
            }


        }
    }
}
