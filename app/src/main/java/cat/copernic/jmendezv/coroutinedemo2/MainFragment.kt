package cat.copernic.jmendezv.coroutinedemo2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cat.copernic.jmendezv.coroutinedemo2.databinding.MainFragmentBinding

const val TAG: String = "Application"

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)
//        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        return binding.root
//        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)
        binding.buttonStart.setOnClickListener {
//            Toast.makeText(requireContext(), "onClick Fragment", Toast.LENGTH_SHORT).show()
            // Síncrona
//            Log.d(TAG, Thread.currentThread().name)
            viewModel.login("pepe", "mysecretpassword")
            ////
        }
        binding.buttonStop.setOnClickListener {
            Toast.makeText(requireContext(), "onClick onStrop button", Toast.LENGTH_SHORT).show()
        }
    }




}