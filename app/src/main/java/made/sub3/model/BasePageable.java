package made.sub3.model;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class BasePageable<T> {
    ArrayList< T > content = new ArrayList <> ();
    Pageable PageableObject;
    private float totalPages;
    private float totalElements;
    private boolean last;
    private boolean first;
    Sort SortObject;
    private float numberOfElements;
    private float size;
    private float number;
    private boolean empty;

}