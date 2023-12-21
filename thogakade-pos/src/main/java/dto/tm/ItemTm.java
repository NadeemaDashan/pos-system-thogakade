
package dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemTm {
    private String code;
    private String desc;
    private double unitPrice;
    private int qty;

}