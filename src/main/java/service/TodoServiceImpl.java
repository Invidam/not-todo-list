package service;

import DTO.Item.ItemIdAndHashTagDTO;
import DTO.Item.ItemIdAndHashTagIdDTO;
import DTO.Item.ItemInfoDTO;
import auth.JwtToken;
import domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TodoMapper;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private final JwtToken jwtToken;

    @Autowired
    private TodoMapper todoMapper;

    public TodoServiceImpl(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public long findHashTagIdByName(String hashTag) {
        return todoMapper.findHashTagId(hashTag);
    }

    @Override
    public void createHashTag(String hashTag) {
        todoMapper.createHashTag(hashTag);
    }

    @Override
    public void createHashTagRelationship(ItemIdAndHashTagDTO itemIdAndHashTagDTO) {
        todoMapper.createHashTagsRelationship(new ItemIdAndHashTagIdDTO(itemIdAndHashTagDTO.getId(),findHashTagIdByName(itemIdAndHashTagDTO.getHashTag())));
    }

    @Override
    public void createItem(Item item) {
        todoMapper.createItem(item);
    }

    @Override
    public void makeHashTags(List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            if(!todoMapper.isExistHashTag(hashTag)) createHashTag(hashTag);
        });
    }

    @Override
    public void createHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hasTag -> createHashTagRelationship(new ItemIdAndHashTagDTO(id,hasTag)));
    }

    @Override
    public void updateHashTagsRelationship(long id, List<String> hashTagList) {
        hashTagList.forEach(hashTag -> {
            ItemIdAndHashTagIdDTO itemIdAndHashTagIdDTO = new ItemIdAndHashTagIdDTO(id, todoMapper.findHashTagId(hashTag));
            if (!todoMapper.isExistHashTagRelationship(itemIdAndHashTagIdDTO))
                todoMapper.createHashTagsRelationship(itemIdAndHashTagIdDTO);
        });
    }

    @Override
    public void createItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader) {
        itemInfoDTO.setUserId(jwtToken.verify(jwtToken.getAccessTokenInHeader(authHeader)).getId());
        makeHashTags(itemInfoDTO.getHashTagList());
        createItem(itemInfoDTO);
        System.out.println("AFTER CRATE: TITLE: " + itemInfoDTO.getId());
        createHashTagsRelationship(itemInfoDTO.getId(), itemInfoDTO.getHashTagList());
    }

    @Override
    public ItemInfoDTO getItemById(long id) {
        return todoMapper.getItemInfoById(id);
    }

    @Override
    public void updateItem(Item item) {
        todoMapper.updateItem(item);
        //title로 id를 얻으면 중복발생이 가능하다.
    }

    @Override
    public void updateItemFromInfo(ItemInfoDTO itemInfoDTO, String authHeader) {
        itemInfoDTO.setUserId(jwtToken.verify(jwtToken.getAccessTokenInHeader(authHeader)).getId());
        makeHashTags(itemInfoDTO.getHashTagList());
        updateItem(itemInfoDTO);
        //이미 있으면 어떡할지 생각하기.
        //user id , hashtag id, item id를 통해 확인해야 한다.
        updateHashTagsRelationship(itemInfoDTO.getId(), itemInfoDTO.getHashTagList());
    }
    @Override
    public void deleteItem(long id) {
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

