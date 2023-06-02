interface InventoryRepository {
    suspend fun getItemCategory() : String
    suspend fun getItemName() : String
    suspend fun getImages() : List<InventoryImage>
}

class DefaultInventoryRepository: InventoryRepository{
    override suspend fun getItemCategory() : String{
       return BarcodeAPI.getPhotos()}
}