package com.example.janaushadhifinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.model.Medicine

class PopularMedicineAdapter(
    private val medicines: List<Medicine>,
    private val onMedicineClick: (Medicine) -> Unit
) : RecyclerView.Adapter<PopularMedicineAdapter.PopularMedicineViewHolder>() {

    class PopularMedicineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val medicineName: TextView = view.findViewById(R.id.tvMedicineName)
        val genericName: TextView = view.findViewById(R.id.tvGenericName)
        val savingsText: TextView = view.findViewById(R.id.tvSavings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_medicine, parent, false)
        return PopularMedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        
        holder.medicineName.text = medicine.brandName
        holder.genericName.text = "→ ${medicine.genericName}"
        
        val savingsAmount = medicine.getSavingsAmount()
        val savingsPercentage = medicine.getSavingsPercentage()
        holder.savingsText.text = "Save ₹$savingsAmount ($savingsPercentage%)"
        holder.savingsText.setTextColor(holder.itemView.context.getColor(R.color.success_green))
        
        holder.itemView.setOnClickListener {
            onMedicineClick(medicine)
        }
    }

    override fun getItemCount(): Int = medicines.size
}
