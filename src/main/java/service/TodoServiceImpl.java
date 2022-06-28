package service;

import DTO.Item.ItemIdAndHashTagDTO;
import DTO.Item.ItemIdAndHashTagIdDTO;
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

    @Override
    public ItemInfoDTO getItemById(long id) {
        if(!todoMapper.isExistItem(id))
            throw new ItemNotFoundException("Inputted Path's Item is not exists.");
        return todoMapper.getItemInfoById(id);
    }

    private void updateItem(Item item) {
        todoMapper.updateItem(item);
    }

    @Override
    public void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader) {
        userService.checkUserById(itemInfoDTO.getId(),jwtToken.getAccessTokenInHeader(authHeader));

        updateItem(itemInfoDTO);

        makeHashTags(itemInfoDTO.getHashTagList());
        updateHashTagsRelationship(itemInfoDTO.getId(), itemInfoDTO.getHashTagList());
    }
    @Override
    public void deleteItem(long id, String authHeader) {
        userService.checkUserById(todoMapper.getItemOwnerIdById(id),jwtToken.getAccessTokenInHeader(authHeader));
        todoMapper.deleteItem(id);
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

