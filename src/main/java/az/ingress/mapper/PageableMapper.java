package az.ingress.mapper;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.response.PageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

public enum PageableMapper {
    PAGEABLE_MAPPER;

    public Pageable toPageable(PageCriteria pageCriteria) {
        return PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
    }

    public <T, E> PageableResponse<T> toPageableResponse(Page<E> page, Function<E, T> mapper) {
        return PageableResponse.<T>builder()
                               .content(page.stream().map(mapper).toList())
                               .totalElements(page.getTotalElements())
                               .totalPages(page.getTotalPages())
                               .hasNext(page.hasNext())
                               .build();
    }
}