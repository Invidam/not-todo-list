package controller;


import DTO.Item.ItemInfoDTO;
import DTO.Response.SuccessInfo;
import DTO.User.UserInfoDTO;
import auth.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TodoService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.Date;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final TodoService todoService;


    @Autowired
    public ItemController(TodoService todoService) {
        this.todoService = todoService;
    }



    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<SuccessInfo> createItem(@RequestBody ItemInfoDTO itemInfoDTO, @RequestHeader(value = "Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        todoService.createItemFromInfo(itemInfoDTO, authHeader);
        //201 created
        //에러 없음
        return ResponseEntity.created(URI.create("/item/" + itemInfoDTO.getId())).body(new SuccessInfo("CREATE_ITEM", "success at create item."));
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemInfoDTO> getItemById(@PathVariable long id) {

        return ResponseEntity.ok(todoService.getItemById(id));
        //200 ok
        //아이템이 없는 경우 예외처리 ItemNotFound 404

    }
    @RequestMapping(value="", method = RequestMethod.PUT)
    public ResponseEntity<ItemInfoDTO> updateItem(@RequestBody ItemInfoDTO itemInfoDTO,@RequestHeader(value = "Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        itemInfoDTO.setIsEdited(true);
        itemInfoDTO.setEditedAt(new Date(new java.util.Date().getTime()));

        todoService.updateItemFromInfo(itemInfoDTO, authHeader);
        return ResponseEntity.ok(itemInfoDTO);
        //200 ok
        //user id가 서로 다른 경우 403
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessInfo> withdrawItem(@PathVariable long id, @RequestHeader(value = "Authorization") String authHeader) {
        todoService.deleteItem(id,authHeader);
        return ResponseEntity.ok(new SuccessInfo("WITHDRAW_SUCCESS", "success at withdraw item."));
        //200 ok
        //user id가 서로 다른 경우 403
    }
    /*
    * todo 체크
    *  user id check
    * 이미 체크되어있는지 체크
    * 존재하는 아이템인지 체크
    *  todo 언체크
    *   user id check
    * 이미 체크풀려있는지 체크
    * 존재하는 아이템인지 체크
    * 
    * 랭킹
    * 한달동안, 가장 많이 등록한 유저/(체크한 유저* 각각의 우선순위의 합이 가장 큰 유저) 를 top 10 추려서 출력
    * 자기 랭킹
    * 자기자신 랭킹도 표현하기
    * 최근 등록!
    * order: 최신 & 삭제 안된 것들 추려서 뽑기 
    * */
}