package com.simplemobiletools.commons.adapters

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplemobiletools.commons.R
import com.simplemobiletools.commons.activities.BaseSimpleActivity
import com.simplemobiletools.commons.interfaces.RenameTab

class RenameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val renameTab: RenameTab = itemView as RenameTab
}

class RenameAdapter(
    val activity: BaseSimpleActivity,
    val paths: ArrayList<String>
) : RecyclerView.Adapter<RenameViewHolder>() {
    private val tabs = SparseArray<RenameTab>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenameViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return RenameViewHolder(view)
    }

    override fun onBindViewHolder(holder: RenameViewHolder, position: Int) {
        holder.renameTab.initTab(activity, paths)

        tabs.put(position, holder.renameTab)
    }

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.tab_rename_simple
            1 -> R.layout.tab_rename_pattern
            else -> throw RuntimeException("Only 2 tabs allowed")
        }
    }

    fun dialogConfirmed(useMediaFileExtension: Boolean, position: Int, callback: (success: Boolean) -> Unit) {
        tabs[position]?.dialogConfirmed(useMediaFileExtension, callback)
    }

}
