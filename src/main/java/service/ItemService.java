package service;

import domain.Item;
import domainGroup.Item.ItemAndUserList;
import domainGroup.Item.ItemIdAndIsDone;
import domainGroup.Item.ItemInfo;

import java.util.List;

public interface ItemService {


    Item createItem(Item item);

    void makeHashTags(List<String> hashTagList);

    void createHashTagsRelationship(long id, List<String> hashTagList);


    void updateHashTagsRelationship(long id, List<String> hashTagList);

    void checkIsExistItem(long id, String errorMessage);

    ItemInfo getItemById(long id);

    void updateItem(Item item);

    long getItemOwnerIdById(long id);

    void deleteItem(long id);

    void updateItemStatus(ItemIdAndIsDone itemIdAndIsDone);

    ItemAndUserList getAllItem();
}