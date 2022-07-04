package utility;

import DTO.Emotion.EmotionInItemDTO;
import DTO.Emotion.EmotionWithUserDTO;
import DTO.Item.*;
import DTO.User.*;
import domain.EmotionRelation;
import domain.HashTagRelation;
import domain.Item;
import domain.User;
import domainGroup.Emotion.EmotionInItem;
import domainGroup.Item.ItemAndUser;
import domainGroup.Item.ItemAndUserList;
import domainGroup.Item.ItemIdAndIsDone;
import domainGroup.Item.ItemInfo;
import domainGroup.User.LoginUser;
import domainGroup.User.Rank;
import domainGroup.User.UserRankInfo;
import domainGroup.User.UserRepresentInfo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOEntityMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getNickname());
    }

    public ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getId(), item.getTitle(), item.getIsDone(), item.getPriority(), item.getCreatedAt(), item.getEditedAt(), item.getDeadline(), item.getDescription(), item.getIsEdited(), item.getIsShared());
    }
    public ItemInUserDTO toItemInUserDTO(Item item) {
        return new ItemInUserDTO(item.getId(), item.getTitle(), item.getIsDone(), item.getPriority(), item.getCreatedAt());
    }

    public UserRepresentInfoDTO toDTO(UserRepresentInfo userRepresentInfo) {
        UserRepresentInfoDTO userRepresentInfoDTO = new UserRepresentInfoDTO();
        userRepresentInfoDTO.setUser(toDTO(userRepresentInfo.getUser()));
        userRepresentInfoDTO.setItemList(userRepresentInfo.getItemList().isEmpty() ? null : userRepresentInfo.getItemList().stream().map(this::toItemInUserDTO).collect(Collectors.toList()));
        return userRepresentInfoDTO;
    }

    public RankDTO toDTO(Rank rank) {
        return new RankDTO(rank.getUserRankList().isEmpty() ? null : rank.getUserRankList().stream().map(this::toEntity).collect(Collectors.toList()));
    }

    public ItemAndUserDTO toDTO(ItemAndUser itemAndUser) {
        return new ItemAndUserDTO(toDTO(itemAndUser.getItem()), toDTO(itemAndUser.getUser()));
    }

    public ItemAndUserDTOList toDTO(ItemAndUserList itemAndUserList) {
        return new ItemAndUserDTOList(itemAndUserList.getItemAndUserList().isEmpty() ? null : itemAndUserList.getItemAndUserList().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public EmotionInItemDTO toDTO(EmotionInItem emotionInItem) {
        return new EmotionInItemDTO(emotionInItem.getEmotionId(),emotionInItem.getUserInfoList().isEmpty() ? null :emotionInItem.getUserInfoList().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public ItemInfoDTO toDTO(ItemInfo itemInfo) {
        ItemInfoDTO itemInfoDTO = new ItemInfoDTO(toDTO(itemInfo.getOwner()), itemInfo.getItemEmotionList().isEmpty() ? null :  itemInfo.getItemEmotionList().stream().map(this::toDTO).collect(Collectors.toList()), itemInfo.getHashTagList());
        itemInfoDTO.setId(itemInfo.getId());
        itemInfoDTO.setTitle(itemInfo.getTitle());
        itemInfoDTO.setIsDone(itemInfo.getIsDone());
        itemInfoDTO.setPriority(itemInfo.getPriority());
        itemInfoDTO.setCreatedAt(itemInfo.getCreatedAt());
        itemInfoDTO.setEditedAt(itemInfo.getEditedAt());
        itemInfoDTO.setDeadline(itemInfo.getDeadline());
        itemInfoDTO.setDescription(itemInfo.getDescription());
        itemInfoDTO.setIsEdited(itemInfo.getIsEdited());
        itemInfoDTO.setIsShared(itemInfo.getIsShared());
        return itemInfoDTO;
    }
    public LoginUser toEntity(LoginUserDTO loginUserDTO) {
        return new LoginUser(loginUserDTO.getAccount(),loginUserDTO.getPassword());
    }
    public User toEntity(SignupUserDTO signupUserDTO) {
        User user = new User();
        user.setAccount(signupUserDTO.getAccount());
        user.setNickname(signupUserDTO.getNickname());
        user.setPassword(signupUserDTO.getPassword());
        return user;
    }

    public User toEntity(UpdateUserDTO updateUserDTO) {
        User user = new User();
        user.setAccount(updateUserDTO.getAccount());
        user.setNickname(updateUserDTO.getNickname());
        user.setPassword(updateUserDTO.getPassword());
        return user;
    }

    public UserRankInfoDTO toEntity(UserRankInfo userRankInfo) {
        return new UserRankInfoDTO(userRankInfo.getPoint(),userRankInfo.getRanking(),userRankInfo.getId(), userRankInfo.getNickname());
    }


    public Item toEntity(ItemInfoDTO itemInfoDTO) {
        return new Item(itemInfoDTO.getId(),itemInfoDTO.getOwner().getId() ,itemInfoDTO.getTitle(), itemInfoDTO.getIsDone(), itemInfoDTO.getPriority(), itemInfoDTO.getCreatedAt(), itemInfoDTO.getEditedAt(), itemInfoDTO.getDeadline(), itemInfoDTO.getDescription(), itemInfoDTO.getIsEdited(), itemInfoDTO.getIsShared(),false);
    }

    public Item toEntity(UpdateItemInfoDTO updateItemInfoDTO) {
        return new Item(updateItemInfoDTO.getId(),updateItemInfoDTO.getUserId() ,updateItemInfoDTO.getTitle(), updateItemInfoDTO.getIsDone(), updateItemInfoDTO.getPriority(), updateItemInfoDTO.getCreatedAt(), updateItemInfoDTO.getEditedAt(), updateItemInfoDTO.getDeadline(), updateItemInfoDTO.getDescription(), updateItemInfoDTO.getIsEdited(), updateItemInfoDTO.getIsShared(),false);
    }
    public EmotionRelation toEntity(EmotionWithUserDTO emotionWithUserDTO) {
        return new EmotionRelation(emotionWithUserDTO.getItemId(),emotionWithUserDTO.getEmotionId(), emotionWithUserDTO.getUserId());
    }

    public HashTagRelation toEntity(ItemIdAndHashTagIdDTO itemIdAndHashTagIdDTO) {
        return new HashTagRelation(itemIdAndHashTagIdDTO.getItemId(), itemIdAndHashTagIdDTO.getHashTagId());
    }
    public ItemIdAndIsDone toEntity(ItemIdAndIsDoneDTO itemIdAndIsDoneDTO) {
        return new ItemIdAndIsDone(itemIdAndIsDoneDTO.getId(), itemIdAndIsDoneDTO.getIsDone());
    }
}
