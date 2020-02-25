package made.sub3.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Sort {
    private boolean sorted;
    private boolean unsorted;
    private boolean empty;


}

