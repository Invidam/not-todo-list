package DTO.Item;

import DTO.Emotion.EmotionInItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateItemInfoDTO extends ItemDTO{
    @NotBlank(message = "UpdateItemInfoDTO, ID is empty")
    private long id;
    @NotBlank(message = "UpdateItemInfoDTO, USER ID is empty")
    private long userId;
    private List<EmotionInItemDTO> itemEmotionList;
    private List<String> hashTagList;
    private Date deadline;

}