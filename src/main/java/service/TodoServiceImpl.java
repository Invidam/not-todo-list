package service;

import DTO.Item.ItemIdAndHashTagDTO;
import DTO.Item.ItemIdAndHashTagIdDTO;
import DTO.Item.ItemIdAndIsDoneDTO;
import DTO.Item.ItemInfoDTO;
import auth.JwtToken;
import domain.Item;
import exception.item.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TodoMapper;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private final JwtToken jwtToken;
    @Autowired
    private final UserService userService;
    @Autowired
    private TodoMapper todoMapper;

    public TodoServiceImpl(JwtToken jwtToken, UserService userService) {
        this.jwtToken = jwtToken;
        this.userService = userService;
    }

    private long findHashTagIdByName(String hashTag) {
        return todoMapper.findHashTagId(hashTag);
    }

    private void createHashTag(String hashTag) {
        todoMapper.createHashTag(hashTag);
    }

    private void createHashTagRelationship(ItemIdAndHashTagDTO itemIdAndHashTagDTO) {
        todoMapper.createHashTagsRelationship(new ItemIdAndHashTagIdDTO(itemIdAndHashTagDTO.getId(),findHashTagIdByName(itemIdAndHashTagDTO.getHashTag())));
    }

    private void createItem(Item item) {
        todoMapper.createItem(item);
    }

    private void makeHashTags(List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            if(!todoMapper.isExistHashTag(hashTag)) createHashTag(hashTag);
        });
    }

    private void createHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hasTag -> createHashTagRelationship(new ItemIdAndHashTagDTO(id,hasTag)));
    }

    private void updateHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            ItemIdAndHashTagIdDTO itemIdAndHashTagIdDTO = new ItemIdAndHashTagIdDTO(id, todoMapper.findHashTagId(hashTag));
            if (!todoMapper.isExistHashTagRelationship(itemIdAndHashTagIdDTO))
                todoMapper.createHashTagsRelationship(itemIdAndHashTagIdDTO);
        });
    }

    @Override
    public void createItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader) {
        itemInfoDTO.setUserId(jwtToken.verify(jwtToken.getAccessTokenInHeader(authHeader)).getId());
        createItem(itemInfoDTO);

        makeHashTags(itemInfoDTO.getHashTagList());
        createHashTagsRelationship(itemInfoDTO.getId(), itemInfoDTO.getHashTagList());
    }
    private void checkIsExistItem(long id ,String errorMessage) {
        if(!todoMapper.isExistItem(id))
            throw new ItemNotFoundException(errorMessage);

    }
    @Override
    public ItemInfoDTO getItemById(long id) {
        checkIsExistItem(id, "Inputted Path's Item is not exists.");
        return todoMapper.getItemInfoById(id);
    }

    private void updateItem(Item item) {
        todoMapper.updateItem(item);
    }

    @Override
    public void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader) {
        userService.checkUserById(itemInfoDTO.getUserId(),authHeader);
        checkIsExistItem(itemInfoDTO.getId(), "To Update Item is not exists.");

        updateItem(itemInfoDTO);

        makeHashTags(itemInfoDTO.getHashTagList());
        updateHashTagsRelationship(itemInfoDTO.getId(), itemInfoDTO.getHashTagList());
    }
    @Override
    public void deleteItem(long id, String authHeader) {
        checkIsExistItem(id, "To Delete Item is not exists.");
        userService.checkUserById(todoMapper.getItemOwnerIdById(id),authHeader);
        todoMapper.deleteItem(id);
    }


    @Override
    public void updateItemStatus(ItemIdAndIsDoneDTO itemIdAndIsDoneDTO, String authHeader) {
        checkIsExistItem(itemIdAndIsDoneDTO.getId(), "To Update Item is not exists.");
        userService.checkUserById(todoMapper.getItemOwnerIdById(itemIdAndIsDoneDTO.getId()),authHeader);

        todoMapper.updateItemStatus(itemIdAndIsDoneDTO);
    }
    /*
* C
* R
* U
* D
*
* SHARE
*
* */

}