package com.example.janaushadhifinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.model.Medicine

class TopSavingsAdapter(
    private val medicines: List<Medicine>,
    private val onMedicineClick: (Medicine) -> Unit
) : RecyclerView.Adapter<TopSavingsAdapter.TopSavingsViewHolder>() {

    class TopSavingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val medicineName: TextView = view.findViewById(R.id.tvMedicineName)
        val savingsText: TextView = view.findViewById(R.id.tvSavings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSavingsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_saving, parent, false)
        return TopSavingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopSavingsViewHolder, position: Int) {
        val medicine = medicines[position]
        
        holder.medicineName.text = medicine.brandName
        
        val savingsAmount = medicine.getSavingsAmount()
        val savingsPercentage = medicine.getSavingsPercentage()
        holder.savingsText.text = "Save ₹$savingsAmount"
        holder.savingsText.setTextColor(holder.itemView.context.getColor(R.color.success_green))
        
        holder.itemView.setOnClickListener {
            onMedicineClick(medicine)
        }
    }

    override fun getItemCount(): Int = medicines.size
}
