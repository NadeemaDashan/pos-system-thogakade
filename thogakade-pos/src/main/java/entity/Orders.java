package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Orders {
    private String id;
    private String date;
    private String cusId;
}
