package itis.ru.insultgenerator

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import itis.ru.insultgenerator.model.Insult
import kotlinx.android.synthetic.main.item_insult.view.*

class InsultAdapter(
    private val insultLambda: (Insult) -> Unit
) : ListAdapter<Insult, InsultAdapter.InsultViewHolder>(InsultDiffCallback()) {

    var list: MutableList<Insult>? = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): InsultViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return InsultViewHolder(inflater.inflate(R.layout.item_insult, p0, false))
    }

    override fun onBindViewHolder(p0: InsultViewHolder, p1: Int) {
        p0.tvInsult.text = getItem(p1).insult
//        p0.icon.setImageResource(getItem(p1).);
        p0.itemView.setOnClickListener {
            insultLambda.invoke(getItem(p1))
        }
    }

    override fun submitList(list: MutableList<Insult>?) {
        this.list = list
        super.submitList(list)
    }

    class InsultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInsult = itemView.tv_insult
//        val icon = itemView.iv_icon
    }

    class InsultDiffCallback : DiffUtil.ItemCallback<Insult>() {
        override fun areItemsTheSame(oldItem: Insult, newItem: Insult): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Insult, newItem: Insult): Boolean = oldItem == newItem
    }
}
