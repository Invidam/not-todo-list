package service;

import DTO.Item.ItemIdAndHashTagDTO;
import DTO.Item.ItemInfoDTO;
import domain.Item;
import domain.User;

import java.util.List;

public interface TodoService {



    void createItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    ItemInfoDTO getItemById(long id);
    void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    void deleteItem(long id, String authHeader);
}