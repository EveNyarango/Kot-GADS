package com.webworks.practiseapp

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import com.webworks.practiseapp.DisplayAdapter.MyViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.webworks.practiseapp.R
import android.widget.TextView
import android.widget.ImageButton
import android.content.Intent
import android.net.Uri
import android.view.View
import com.webworks.practiseapp.DisplayAdapter
import com.webworks.practiseapp.model.Repository
import kotlinx.android.synthetic.main.list_item.view.*

class DisplayAdapter(private val context: Context, private var repositoryList: List<Repository>) :
    RecyclerView.Adapter<MyViewHolder>() {

    private val inflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = repositoryList[position]
        holder.setData(current, position)
    }

    override fun getItemCount(): Int = repositoryList.size


    //    public void swap(List<Repository> data){
    //        if(data.isEmpty())
    //            Util.showMessage(mContext, "No Items Found");
    //        repositoryList = data;
    //        notifyDataSetChanged();
    //    }
    public inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var pos = 0
        private var current: Repository? = null

        fun setData(current: Repository?, position: Int) {

            current?.let {
                itemView.tvTitleList.text = current.name
                itemView.tvLanguageList.text = current.language
                itemView.tvForkList.text = current.forks.toString()
                itemView.tvWatchersList.text = current.watchers.toString()
                itemView.tvStarsList.text = current.stars.toString()
            }
            this.pos = position
            this.current = current
        }

        private fun bookmarkRepository(current: Repository?) {}

        init {

            itemView.imgBookmark.setOnClickListener { bookmarkRepository(current) }
            itemView.setOnClickListener {
                current?.let {
                    val url = current!!.htmlUrl
                    val webpage = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_VIEW, webpage)
                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)
                }

                }
            }
        }
    }

    companion object {
        private val TAG = DisplayAdapter::class.java.simpleName
    }


}