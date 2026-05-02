package com.example.janaushadhifinder.data

import com.example.janaushadhifinder.model.Medicine

object MedicineDataSource {
    
    fun getMedicines(): List<Medicine> {
        return listOf(
            // Pain Relief
            Medicine("Crocin", "Paracetamol", "Pain Relief", 120, 25),
            Medicine("Tylenol", "Paracetamol", "Pain Relief", 150, 30),
            Medicine("Calpol", "Paracetamol", "Pain Relief", 100, 20),
            Medicine("Aspirin", "Acetylsalicylic Acid", "Pain Relief", 80, 15),
            Medicine("Disprin", "Aspirin", "Pain Relief", 60, 12),
            Medicine("Ibugesic", "Ibuprofen", "Pain Relief", 140, 35),
            Medicine("Brufen", "Ibuprofen", "Pain Relief", 160, 40),
            Medicine("Combiflam", "Ibuprofen+Paracetamol", "Pain Relief", 180, 45),
            Medicine("Voveran", "Diclofenac", "Pain Relief", 200, 50),
            Medicine("Dynapar", "Diclofenac", "Pain Relief", 180, 45),
            Medicine("Nimesulide", "Nimesulide", "Pain Relief", 120, 30),
            Medicine("Ketorol", "Ketorolac", "Pain Relief", 90, 25),
            Medicine("Mobis", "Ibuprofen", "Pain Relief", 110, 28),
            
            // Antibiotics
            Medicine("Augmentin", "Amoxicillin+Clavulanate", "Antibiotic", 350, 80),
            Medicine("Amoxil", "Amoxicillin", "Antibiotic", 250, 60),
            Medicine("Moxikind", "Amoxicillin", "Antibiotic", 200, 50),
            Medicine("Azithral", "Azithromycin", "Antibiotic", 400, 100),
            Medicine("Zithromax", "Azithromycin", "Antibiotic", 450, 120),
            Medicine("Ciprobid", "Ciprofloxacin", "Antibiotic", 300, 75),
            Medicine("Cifran", "Ciprofloxacin", "Antibiotic", 280, 70),
            Medicine("Levoflox", "Levofloxacin", "Antibiotic", 500, 150),
            Medicine("Gatiflox", "Gatifloxacin", "Antibiotic", 350, 90),
            Medicine("Doxycycline", "Doxycycline", "Antibiotic", 180, 45),
            Medicine("Minocycline", "Minocycline", "Antibiotic", 320, 80),
            
            // Allergy
            Medicine("Cetzine", "Cetirizine", "Allergy", 150, 30),
            Medicine("Zyrtec", "Cetirizine", "Allergy", 180, 40),
            Medicine("Allegra", "Fexofenadine", "Allergy", 200, 60),
            Medicine("Avil", "Pheniramine", "Allergy", 80, 20),
            Medicine("Phenergan", "Promethazine", "Allergy", 100, 25),
            Medicine("Benadryl", "Diphenhydramine", "Allergy", 120, 30),
            Medicine("Montair", "Montelukast", "Allergy", 250, 80),
            Medicine("Singulair", "Montelukast", "Allergy", 300, 100),
            Medicine("Cetrizine", "Cetirizine", "Allergy", 80, 15),
            Medicine("Alerid", "Cetirizine", "Allergy", 100, 20),
            Medicine("Zyrtec", "Cetirizine", "Allergy", 120, 25),
            Medicine("Allegra", "Fexofenadine", "Allergy", 140, 30),
            Medicine("Avil", "Pheniramine", "Allergy", 60, 12),
            Medicine("Loratadine", "Loratadine", "Allergy", 90, 18),
            Medicine("Desloratadine", "Desloratadine", "Allergy", 110, 22),
            
            // Diabetes
            Medicine("Glycomet", "Metformin", "Diabetes", 200, 40),
            Medicine("Metform", "Metformin", "Diabetes", 180, 35),
            Medicine("Janumet", "Sitagliptin+Metformin", "Diabetes", 500, 150),
            Medicine("Galvus", "Vildagliptin", "Diabetes", 400, 120),
            Medicine("Amaryl", "Glimepiride", "Diabetes", 300, 80),
            Medicine("Daonil", "Glibenclamide", "Diabetes", 150, 30),
            Medicine("Actos", "Pioglitazone", "Diabetes", 350, 90),
            Medicine("Lantus", "Insulin Glargine", "Diabetes", 800, 200),
            Medicine("Novorapid", "Insulin Aspart", "Diabetes", 750, 180),
            Medicine("Huminsulin", "Human Insulin", "Diabetes", 600, 150),
            Medicine("Metformin", "Metformin", "Diabetes", 150, 30),
            Medicine("Glycomet", "Metformin", "Diabetes", 180, 40),
            Medicine("Glucophage", "Metformin", "Diabetes", 200, 45),
            Medicine("Janumet", "Sitagliptin", "Diabetes", 800, 200),
            Medicine("Galvus", "Vildagliptin", "Diabetes", 750, 180),
            Medicine("Trajenta", "Linagliptin", "Diabetes", 900, 220),
            Medicine("Onglyza", "Alogliptin", "Diabetes", 850, 190),
            Medicine("Nesina", "Sitagliptin", "Diabetes", 700, 160),
            Medicine("Glimiperide", "Glimepiride", "Diabetes", 120, 25),
            
            // Acidity/Gastric
            Medicine("Pantocid", "Pantoprazole", "Acidity", 180, 45),
            Medicine("Pantop", "Pantoprazole", "Acidity", 150, 35),
            Medicine("Omez", "Omeprazole", "Acidity", 120, 30),
            Medicine("Ocid", "Omeprazole", "Acidity", 100, 25),
            Medicine("Ranitidine", "Ranitidine", "Acidity", 80, 20),
            Medicine("Zantac", "Ranitidine", "Acidity", 100, 25),
            Medicine("Ganaton", "Itopride", "Acidity", 200, 60),
            Medicine("Librax", "Chlordiazepoxide+Clidinium", "Acidity", 250, 70),
            Medicine("Pantoprazole", "Pantoprazole", "Acidity", 150, 30),
            Medicine("Pantocid", "Pantoprazole", "Acidity", 120, 25),
            Medicine("Sompraz", "Omeprazole", "Acidity", 100, 20),
            Medicine("Omez", "Omeprazole", "Acidity", 80, 15),
            Medicine("Aciloc", "Ranitidine", "Acidity", 60, 12),
            Medicine("Rantac", "Ranitidine", "Acidity", 90, 18),
            Medicine("Famotidine", "Famotidine", "Acidity", 70, 14),
            
            // Blood Pressure
            Medicine("Amlodip", "Amlodipine", "Blood Pressure", 150, 40),
            Medicine("Amlopress", "Amlodipine", "Blood Pressure", 180, 50),
            Medicine("Telma", "Telmisartan", "Blood Pressure", 250, 80),
            Medicine("Tazloc", "Telmisartan", "Blood Pressure", 280, 90),
            Medicine("Losar", "Losartan", "Blood Pressure", 200, 60),
            Medicine("Covance", "Losartan", "Blood Pressure", 220, 65),
            Medicine("Metolar", "Metoprolol", "Blood Pressure", 180, 55),
            Medicine("Betaloc", "Metoprolol", "Blood Pressure", 200, 60),
            Medicine("Concor", "Bisoprolol", "Blood Pressure", 250, 75),
            Medicine("Cardivas", "Carvedilol", "Blood Pressure", 300, 90),
            Medicine("Amlodipine", "Amlodipine", "Blood Pressure", 120, 25),
            Medicine("Amlopress", "Amlodipine", "Blood Pressure", 100, 20),
            Medicine("Telmisartan", "Telmisartan", "Blood Pressure", 200, 50),
            Medicine("Telma", "Telmisartan", "Blood Pressure", 180, 40),
            Medicine("Losar", "Losartan", "Blood Pressure", 150, 35),
            Medicine("Cozaar", "Losartan", "Blood Pressure", 220, 55),
            Medicine("Olmesartan", "Olmesartan", "Blood Pressure", 250, 60),
            Medicine("Benicar", "Olmesartan", "Blood Pressure", 280, 70),
            Medicine("Valsartan", "Valsartan", "Blood Pressure", 160, 38),
            
            // Cholesterol
            Medicine("Stator", "Atorvastatin", "Cholesterol", 300, 80),
            Medicine("Lipitor", "Atorvastatin", "Cholesterol", 350, 100),
            Medicine("Rosuvas", "Rosuvastatin", "Cholesterol", 400, 120),
            Medicine("Crestor", "Rosuvastatin", "Cholesterol", 450, 140),
            Medicine("Fenofib", "Fenofibrate", "Cholesterol", 250, 70),
            Medicine("Ezetrol", "Ezetimibe", "Cholesterol", 350, 100),
            Medicine("Atorvastatin", "Atorvastatin", "Cholesterol", 200, 50),
            Medicine("Lipitor", "Atorvastatin", "Cholesterol", 350, 80),
            Medicine("Rosuvastatin", "Rosuvastatin", "Cholesterol", 400, 100),
            Medicine("Crestor", "Rosuvastatin", "Cholesterol", 450, 120),
            Medicine("Simvastatin", "Simvastatin", "Cholesterol", 150, 40),
            Medicine("Ezetimibe", "Ezetimibe", "Cholesterol", 300, 75),
            Medicine("Fenofibrate", "Fenofibrate", "Cholesterol", 180, 45),
            
            // Asthma
            Medicine("Asthalin", "Salbutamol", "Asthma", 120, 30),
            Medicine("Ventolin", "Salbutamol", "Asthma", 150, 40),
            Medicine("Foracort", "Budesonide+Formoterol", "Asthma", 400, 120),
            Medicine("Symbicort", "Budesonide+Formoterol", "Asthma", 450, 140),
            Medicine("Seroflo", "Salmeterol+Fluticasone", "Asthma", 500, 150),
            Medicine("Montair LC", "Montelukast+Levocetirizine", "Asthma", 300, 90),
            Medicine("Salbutamol", "Salbutamol", "Asthma", 180, 45),
            Medicine("Asthalin", "Salbutamol", "Asthma", 150, 35),
            Medicine("Foracort", "Budesonide", "Asthma", 250, 60),
            Medicine("Budecort", "Budesonide", "Asthma", 200, 50),
            Medicine("Montelukast", "Montelukast", "Asthma", 220, 55),
            Medicine("Singulair", "Montelukast", "Asthma", 280, 70),
            
            // Vitamins
            Medicine("Shelcal", "Calcium + Vitamin D3", "Vitamins", 120, 30),
            Medicine("Supradyne", "Vitamin B Complex", "Vitamins", 150, 40),
            Medicine("Zincovit", "Zinc + Vitamin C", "Vitamins", 100, 25),
            Medicine("Becosules", "Vitamin B Complex", "Vitamins", 80, 20),
            Medicine("Neurobion", "Vitamin B Complex", "Vitamins", 90, 22),
            Medicine("Vitamin D3", "Cholecalciferol", "Vitamins", 60, 15),
            Medicine("Calcitriol", "Calcitriol", "Vitamins", 140, 35),
            Medicine("Neurobion", "B1+B6+B12", "Vitamins", 150, 40),
            Medicine("Becosules", "B-Complex", "Vitamins", 120, 30),
            Medicine("Zincovit", "Zinc+Vitamins", "Vitamins", 180, 50),
            Medicine("Seacod", "Cod Liver Oil", "Vitamins", 250, 80),
            
            // Skin
            Medicine("Betnovate", "Betamethasone", "Skin", 150, 40),
            Medicine("Clobetasol", "Clobetasol Propionate", "Skin", 200, 60),
            Medicine("Lulifin", "Luliconazole", "Skin", 300, 100),
            Medicine("Onabet", "Sertaconazole", "Skin", 350, 120),
            Medicine("Ketoconazole", "Ketoconazole", "Skin", 180, 50),
            Medicine("Terbifine", "Terbinafine", "Skin", 250, 80),
            
            // Eye/Ear
            Medicine("Tobrex", "Tobramycin", "Eye/Ear", 120, 35),
            Medicine("Gatiflox", "Gatifloxacin", "Eye/Ear", 150, 45),
            Medicine("Moxiflox", "Moxifloxacin", "Eye/Ear", 200, 60),
            Medicine("Ciplox", "Ciprofloxacin", "Eye/Ear", 100, 30),
            Medicine("Gentamicin", "Gentamicin", "Eye/Ear", 80, 25)
        )
    }
    
    fun getCategories(): List<String> {
        return listOf(
            "Pain Relief", "Antibiotic", "Allergy", "Diabetes", 
            "Acidity", "Blood Pressure", "Cholesterol", "Asthma",
            "Vitamins", "Skin", "Eye/Ear"
        )
    }
    
    fun getTopSavingsMedicines(): List<Medicine> {
        return getMedicines()
            .sortedByDescending { it.getSavingsAmount() }
            .take(3)
    }
}
