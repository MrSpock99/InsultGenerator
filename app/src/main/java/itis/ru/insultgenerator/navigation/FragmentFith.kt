package itis.ru.insultgenerator.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import itis.ru.insultgenerator.R
import kotlinx.android.synthetic.main.fragment_fith.*

class FragmentFith : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fith, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val safeArgs = FragmentFithArgs.fromBundle(it)
            tv_fith.text = "Number of photos: ${safeArgs.arg_example}"
        }
        btn_go.setOnClickListener {
            val nextAction = SecondFragmentDirections.actionDestinationFithToDestinationSixth()
            Navigation.findNavController(it).navigate(nextAction)
        }
    }
}
