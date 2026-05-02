package com.example.janaushadhifinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.model.Medicine

class MedicineAdapter(
    private var medicines: List<Medicine>,
    private val cartItems: List<Medicine>,
    private val onActionClick: (Medicine, String) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val brandName: TextView = view.findViewById(R.id.tvBrandName)
        val genericName: TextView = view.findViewById(R.id.tvGenericName)
        val category: TextView = view.findViewById(R.id.tvCategory)
        val brandPrice: TextView = view.findViewById(R.id.tvBrandPrice)
        val genericPrice: TextView = view.findViewById(R.id.tvGenericPrice)
        val savings: TextView = view.findViewById(R.id.tvSavings)
        val addToCartBtn: Button = view.findViewById(R.id.btnAddToCart)
        val removeFromCartBtn: Button = view.findViewById(R.id.btnRemoveFromCart)
        val checkAvailabilityBtn: Button = view.findViewById(R.id.btnCheckAvailability)
        val setReminderBtn: Button = view.findViewById(R.id.btnSetReminder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine, parent, false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        val isInCart = cartItems.any { it.brandName == medicine.brandName }

        holder.brandName.text = medicine.brandName
        holder.genericName.text = "→ ${medicine.genericName}"
        holder.category.text = medicine.category
        
        // Price display with strike-through for branded price
        holder.brandPrice.text = "₹${medicine.brandPrice}"
        holder.brandPrice.paintFlags = holder.brandPrice.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        
        holder.genericPrice.text = "₹${medicine.genericPrice}"
        holder.genericPrice.setTextColor(holder.itemView.context.getColor(R.color.success_green))
        
        // Savings calculation
        val savingsAmount = medicine.getSavingsAmount()
        val savingsPercentage = medicine.getSavingsPercentage()
        holder.savings.text = "You Save ₹$savingsAmount ($savingsPercentage%)"
        holder.savings.setTextColor(holder.itemView.context.getColor(R.color.success_green))

        // Cart button visibility
        holder.addToCartBtn.visibility = if (isInCart) View.GONE else View.VISIBLE
        holder.removeFromCartBtn.visibility = if (isInCart) View.VISIBLE else View.GONE

        // Click listeners
        holder.addToCartBtn.setOnClickListener {
            onActionClick(medicine, "add_to_cart")
        }

        holder.removeFromCartBtn.setOnClickListener {
            onActionClick(medicine, "remove_from_cart")
        }

        holder.checkAvailabilityBtn.setOnClickListener {
            onActionClick(medicine, "check_availability")
        }

        holder.setReminderBtn.setOnClickListener {
            onActionClick(medicine, "set_reminder")
        }
    }

    override fun getItemCount(): Int = medicines.size

    fun updateData(newList: List<Medicine>) {
        medicines = newList
        notifyDataSetChanged()
    }
}