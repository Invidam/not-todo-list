package service;

import DTO.Item.ItemIdAndHashTagDTO;
import DTO.Item.ItemInfoDTO;
import domain.Item;
import domain.User;

import java.util.List;

public interface TodoService {

    long findHashTagIdByName(String hashTag);
    void createHashTag(String hashTag);
    void createHashTagRelationship(ItemIdAndHashTagDTO itemIdAndHashTagDTO);
    void makeHashTags(List<String> hashTagList);
    void createHashTagsRelationship(long id, List<String> hashTagList);
    void updateHashTagsRelationship(long id, List<String> hashTagList);
//    void deleteHashTagRelationship(long id);

//    void deleteItemEmotion(long id);


    void createItem(Item item);
    void createItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    ItemInfoDTO getItemById(long id);
    void updateItem(Item item);
    void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader);
    void deleteItem(long id);

}