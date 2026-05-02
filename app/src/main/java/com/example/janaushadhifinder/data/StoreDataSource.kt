package com.example.janaushadhifinder.data

data class Store(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val distance: String,
    val isOpen: Boolean,
    val rating: Float,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

object StoreDataSource {
    
    fun getStores(): List<Store> {
        return listOf(
            Store(
                id = "1",
                name = "Jan Aushadhi Kendra - Rajajinagar",
                address = "80 Feet Road, Rajajinagar, Bangalore - 560010",
                phone = "080-23456789",
                distance = "0.8 km",
                isOpen = true,
                rating = 4.5f,
                latitude = 12.9833,
                longitude = 77.5833
            ),
            Store(
                id = "2",
                name = "Pradhan Mantri Jan Aushadhi Store - Malleshwaram",
                address = "8th Cross, Malleshwaram, Bangalore - 560003",
                phone = "080-23456790",
                distance = "1.2 km",
                isOpen = true,
                rating = 4.3f,
                latitude = 13.0019,
                longitude = 77.5713
            ),
            Store(
                id = "3",
                name = "Jan Aushadhi Medical Store - Vijayanagar",
                address = "Magadi Road, Vijayanagar, Bangalore - 560040",
                phone = "080-23456791",
                distance = "2.1 km",
                isOpen = false,
                rating = 4.1f,
                latitude = 12.9719,
                longitude = 77.5410
            ),
            Store(
                id = "4",
                name = "PMJAK - Yeshwanthpur",
                address = "Yeshwanthpur Main Road, Bangalore - 560022",
                phone = "080-23456792",
                distance = "3.5 km",
                isOpen = true,
                rating = 4.6f,
                latitude = 13.0167,
                longitude = 77.5833
            ),
            Store(
                id = "5",
                name = "Jan Aushadhi Kendra - Basaveshwaranagar",
                address = "80 Feet Road, Basaveshwaranagar, Bangalore - 560079",
                phone = "080-23456793",
                distance = "4.2 km",
                isOpen = true,
                rating = 4.2f,
                latitude = 12.9567,
                longitude = 77.5167
            ),
            Store(
                id = "6",
                name = "Pradhan Mantri Jan Aushadhi - Jayanagar",
                address = "4th Block, Jayanagar, Bangalore - 560041",
                phone = "080-23456794",
                distance = "5.8 km",
                isOpen = false,
                rating = 4.4f,
                latitude = 12.9257,
                longitude = 77.5833
            ),
            Store(
                id = "7",
                name = "Jan Aushadhi Medical - Indiranagar",
                address = "100 Feet Road, Indiranagar, Bangalore - 560038",
                phone = "080-23456795",
                distance = "6.3 km",
                isOpen = true,
                rating = 4.7f,
                latitude = 12.9719,
                longitude = 77.6412
            ),
            Store(
                id = "8",
                name = "PMJAK - Koramangala",
                address = "80 Feet Road, Koramangala, Bangalore - 560034",
                phone = "080-23456796",
                distance = "7.1 km",
                isOpen = true,
                rating = 4.3f,
                latitude = 12.9352,
                longitude = 77.6245
            ),
            Store(
                id = "9",
                name = "Jan Aushadhi Kendra - Whitefield",
                address = "ITPL Road, Whitefield, Bangalore - 560066",
                phone = "080-23456797",
                distance = "12.5 km",
                isOpen = false,
                rating = 4.0f,
                latitude = 12.9698,
                longitude = 77.7499
            ),
            Store(
                id = "10",
                name = "Pradhan Mantri Jan Aushadhi - Electronic City",
                address = "Hosur Road, Electronic City, Bangalore - 560100",
                phone = "080-23456798",
                distance = "15.2 km",
                isOpen = true,
                rating = 4.2f,
                latitude = 12.8399,
                longitude = 77.6770
            )
        )
    }
    
    fun getOpenStores(): List<Store> {
        return getStores().filter { it.isOpen }
    }
    
    fun getNearbyStores(maxDistance: String = "5.0 km"): List<Store> {
        return getStores().filter { 
            try {
                val distance = it.distance.replace(" km", "").toFloat()
                distance <= maxDistance.replace(" km", "").toFloat()
            } catch (e: Exception) {
                false
            }
        }
    }
}
