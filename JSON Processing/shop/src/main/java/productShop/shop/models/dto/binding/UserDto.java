package productShop.shop.models.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import productShop.shop.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String firstName;

    @NotNull
    @Size(min = 3)
    private String lastName;
    private int age;
}
