package utility;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CheckField<T> {
    public boolean isValidFields(T obj, List<String> toCheckField) {
        return checkFields(obj,toCheckField).size() == 0;
    }

    public List<String> checkFields(T obj, List<String> toCheckField) {
        return toCheckField.stream().filter(field ->  {
                    try {
                        System.out.println(obj.toString());
                        return obj.getClass().getDeclaredField(field).get(obj) == null;
                    } catch (Exception e) {
                        return true;
                    }
                }
        ).collect(Collectors.toList());

    }
    public boolean isValidAllFields(T obj) {
        return checkAllFields(obj).size() == 0;
    }
    public List<String> checkAllFields(T obj) {
        return Stream.of(obj.getClass().getDeclaredFields()).filter(field -> {
            try {
                return field.get(obj) == null;
            } catch (IllegalAccessException e) {
                return true;
            }
        }).map(Field::toString).collect(Collectors.toList());

    }

}
