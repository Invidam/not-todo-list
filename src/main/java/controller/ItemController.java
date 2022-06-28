package controller;


import DTO.Item.ItemInfoDTO;
import DTO.User.UserInfoDTO;
import auth.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TodoService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final TodoService todoService;


    @Autowired
    public ItemController(TodoService todoService) {
        this.todoService = todoService;
    }



    @RequestMapping(value="", method = RequestMethod.POST)
    public void createItem(@RequestBody ItemInfoDTO itemInfoDTO,@RequestHeader(value = "Authorization") String authHeader,  HttpServletRequest httpServletRequest) {
        todoService.createItemFromInfo(itemInfoDTO, authHeader);
    }
    @RequestMapping(value="", method = RequestMethod.PUT)
    public void updateItem(@RequestBody ItemInfoDTO itemInfoDTO,@RequestHeader(value = "Authorization") String authHeader, HttpServletRequest httpServletRequest) {
        todoService.updateItemFromInfo(itemInfoDTO, authHeader);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ItemInfoDTO getItemById(@PathVariable long id) {
        return todoService.getItemById(id);
    }
    @DeleteMapping("/{id}")
    public String withdrawItem(@PathVariable long id) {
        todoService.deleteItem(id);
        return "comp";
    }
}