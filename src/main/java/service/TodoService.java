package service;

import DTO.Item.ItemIdAndIsDoneDTO;
import DTO.Item.ItemInfoDTO;

public interface TodoService {



    void createItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    ItemInfoDTO getItemById(long id);
    void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    void deleteItem(long id, String authHeader);


    void updateItemStatus(ItemIdAndIsDoneDTO itemIdAndIsDoneDTO, String authHeader);
}