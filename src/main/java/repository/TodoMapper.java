package repository;

import DTO.Item.ItemIdAndHashTagIdDTO;
import DTO.Item.ItemIdAndIsDoneDTO;
import DTO.Item.ItemInfoDTO;
import domain.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoMapper {
    long findHashTagId(String hashTag);
    String getHashTagById(long hashTagId);
    boolean isExistHashTag(String hashTag);
    boolean isExistHashTagRelationship(ItemIdAndHashTagIdDTO itemIdAndHashTagIdDTO);
    boolean isExistItem(long id);
//
    void createHashTag(String hashTag);
//
    void createHashTagsRelationship(ItemIdAndHashTagIdDTO itemIdAndHashTagIdDTO);
//
//    void deleteHashTagRelationShip(long id);
//

//    long getCategoryId(String category);
//    long getHashTagId(String hashTag);
//    long getItemIdByTitle(String title);
    long getInsertedItemId();
    void createItem(Item item);
    ItemInfoDTO getItemInfoById(long id);
    void updateItem(Item item);
    long getItemOwnerIdById(long id);
    void deleteItem(long id);

    void updateItemStatus(ItemIdAndIsDoneDTO itemIdAndIsDoneDTO);
}
