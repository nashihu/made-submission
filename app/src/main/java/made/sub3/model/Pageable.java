package made.sub3.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Pageable {
    Sort SortObject;
    private float pageNumber;
    private float pageSize;
    private float offset;
    private boolean unpaged;
    private boolean paged;

}
