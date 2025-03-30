package com.ecommerce.project.payLoad;


/*@Data is lonbok annotation that combines
@Getter – Generates getter methods for all fields.

@Setter – Generates setter methods for all non-final fields.

@ToString – Generates a toString() method.

@EqualsAndHashCode – Generates equals() and hashCode() methods.

@RequiredArgsConstructor – Generates a constructor for final fields.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private List<CategoryDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
