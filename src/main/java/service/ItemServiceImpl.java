package service;

import domain.HashTagRelation;
import domain.Item;
import domainGroup.Item.ItemAndUserList;
import domainGroup.Item.ItemIdAndIsDone;
import domainGroup.Item.ItemInfo;
import enums.ExceptionMessage;
import exception.item.ItemNotFoundException;
import exception.item.ItemNotPassedDeadlineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ItemMapper;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(  ItemMapper itemMapper ) {
        this.itemMapper = itemMapper;
    }

    private long findHashTagIdByName(String hashTag) {
        return itemMapper.findHashTagId(hashTag);
    }

    private void createHashTag(String hashTag) {
        itemMapper.createHashTag(hashTag);
    }

    private void createHashTagRelationship(long id, String hashTagName) {
        itemMapper.createHashTagsRelationship(new HashTagRelation(id,findHashTagIdByName(hashTagName)));
    }

    @Override
    public Item createItem(Item item) {
        itemMapper.createItem(item);
        return item;
    }

    @Override
    public void makeHashTags(List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            if(!itemMapper.isExistHashTag(hashTag)) createHashTag(hashTag);
        });
    }

    @Override
    public void createHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hasTag -> createHashTagRelationship(id,hasTag));
    }

    @Override
    public void checkIsExistItem(long id, String errorMessage) {
        if(!itemMapper.isExistItem(id))
            throw new ItemNotFoundException(errorMessage);

    }
    @Override
    public ItemInfo getItemById(long id) {
        checkIsExistItem(id, ExceptionMessage.INPUT_ITEM_NOT_FOUND.getMessage());
        return itemMapper.getItemInfoById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMapper.updateItem(item);
    }

    @Override
    public void updateHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            HashTagRelation hashTagRelation = new HashTagRelation(id, itemMapper.findHashTagId(hashTag));
            if (!itemMapper.isExistHashTagRelationship(hashTagRelation))
                itemMapper.createHashTagsRelationship(hashTagRelation);
        });
    }

    @Override
    public long getItemOwnerIdById(long id) {
        return itemMapper.getItemOwnerIdById(id);
    }

    @Override
    public void deleteItem(long id) {
        itemMapper.deleteItem(id);
    }

    @Override
    public void updateItemStatus(ItemIdAndIsDone itemIdAndIsDone) {
        if(!itemMapper.isPassedDeadline(itemIdAndIsDone.getId()))
            throw new ItemNotPassedDeadlineException(ExceptionMessage.ITEM_NOT_PASSED_DEADLINE.getMessage());
        itemMapper.updateItemStatus(itemIdAndIsDone);
    }

    @Override
    public ItemAndUserList getAllItem() {
        return itemMapper.getAllItem();
    }

}