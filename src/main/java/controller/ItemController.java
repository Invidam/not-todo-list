package controller;


import DTO.Item.*;
import DTO.User.UserDTO;
import auth.JwtToken;
import domain.Item;
import domainGroup.Item.ItemIdAndIsDone;
import enums.ExceptionMessage;
import enums.SuccessStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ItemService;
import service.UserService;
import utility.DTOEntityMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final UserService userService;
    private final ItemService itemService;
    private final JwtToken jwtToken;
    private final DTOEntityMapper dtoEntityMapper;

    @Autowired
    public ItemController(UserService userService, ItemService itemService, JwtToken jwtToken, DTOEntityMapper dtoEntityMapper) {
        this.userService = userService;
        this.itemService = itemService;
        this.jwtToken = jwtToken;
        this.dtoEntityMapper = dtoEntityMapper;
    }



    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<SuccessStatus> createItem(@RequestBody ItemInfoDTO itemInfoDTO, @RequestHeader(value = "Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        itemInfoDTO.setOwner(new UserDTO());
        itemInfoDTO.getOwner().setId(jwtToken.verifyAccessToken(jwtToken.getAccessTokenInHeader(authHeader)));

        Item item = itemService.createItem(dtoEntityMapper.toEntity(itemInfoDTO));
        ItemDTO itemDTO  = dtoEntityMapper.toDTO(item);

        itemService.makeHashTags(itemInfoDTO.getHashTagList());
        itemService.createHashTagsRelationship(itemDTO.getId(),itemInfoDTO.getHashTagList());

        return ResponseEntity.created(URI.create("/item/" + itemDTO.getId())).body(SuccessStatus.CREATE_ITEM);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemInfoDTO> getItemById(@PathVariable long id) {

        return ResponseEntity.ok(dtoEntityMapper.toDTO((itemService.getItemById(id))));
    }

    @RequestMapping(value="", method = RequestMethod.PUT)
    public ResponseEntity<SuccessStatus> updateItem(@RequestBody UpdateItemInfoDTO updateItemInfoDTO, @RequestHeader(value = "Authorization") String authHeader) {
        userService.checkUserById(updateItemInfoDTO.getUserId(),jwtToken.getAccessTokenInHeader(authHeader));
        itemService.checkIsExistItem(updateItemInfoDTO.getId(), ExceptionMessage.INPUT_ITEM_NOT_FOUND.getMessage());
        itemService.updateItem(dtoEntityMapper.toEntity(updateItemInfoDTO));
        itemService.updateHashTagsRelationship(updateItemInfoDTO.getId(), updateItemInfoDTO.getHashTagList());
        itemService.makeHashTags(updateItemInfoDTO.getHashTagList());
        return ResponseEntity.ok(SuccessStatus.UPDATE_ITEM);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessStatus> withdrawItem(@PathVariable long id, @RequestHeader(value = "Authorization") String authHeader) {
        itemService.checkIsExistItem(id, ExceptionMessage.DELETE_ITEM_NOT_FOUND.getMessage());
        userService.checkUserById(itemService.getItemOwnerIdById(id), jwtToken.getAccessTokenInHeader(authHeader));
        itemService.deleteItem(id);
        return ResponseEntity.ok(SuccessStatus.WITHDRAW_ITEM);
    }

    @RequestMapping(value="/{id}/check", method = RequestMethod.GET)
    public ResponseEntity<SuccessStatus> getItemById(@PathVariable long id, @RequestParam(value = "isDone") boolean isDone, @RequestHeader(value = "Authorization") String authHeader) {
        itemService.checkIsExistItem(id, ExceptionMessage.INPUT_ITEM_NOT_FOUND.getMessage());
        userService.checkUserById(itemService.getItemOwnerIdById(id),jwtToken.getAccessTokenInHeader(authHeader));

        itemService.updateItemStatus(new ItemIdAndIsDone(id,isDone));
        return ResponseEntity.ok(SuccessStatus.UPDATE_ITEM_STATUS);

    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ItemAndUserDTOList> getAllItem() {
        return ResponseEntity.ok(dtoEntityMapper.toDTO(itemService.getAllItem()));
    }
}
