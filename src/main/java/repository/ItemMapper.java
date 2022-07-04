package repository;

import domain.HashTagRelation;
import domain.Item;
import domainGroup.Item.ItemAndUserList;
import domainGroup.Item.ItemIdAndIsDone;
import domainGroup.Item.ItemInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper {

    long findHashTagId(String hashTag);

    String getHashTagById(long hashTagId);

    boolean isExistHashTag(String hashTag);

    boolean isExistHashTagRelationship(HashTagRelation hashTagRelation);

    boolean isExistItem(long id);

    void createHashTag(String hashTag);

    void createHashTagsRelationship(HashTagRelation hashTagRelation);

    long getInsertedItemId();

    void createItem(Item item);

    ItemInfo getItemInfoById(long id);

    void updateItem(Item item);

    long getItemOwnerIdById(long id);

    void deleteItem(long id);
    boolean isPassedDeadline(long id);
    void updateItemStatus(ItemIdAndIsDone itemIdAndIsDone);
    ItemAndUserList getAllItem();
}
