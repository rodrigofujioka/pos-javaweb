package dev.fujioka.fagnerlima.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.data.domain.Page;

public class ServiceAssertions {

    public static void assertPage(Page<?> page, int pageSize, int pageNumber, int numberOfElements, int totalPages, int totalElements) {
        assertThat(page.getContent().size()).isEqualTo(numberOfElements);
        assertThat(page.getNumberOfElements()).isEqualTo(numberOfElements);
        assertThat(page.getNumber()).isEqualTo(pageNumber);
        assertThat(page.getSize()).isEqualTo(pageSize);
        assertThat(page.getTotalPages()).isEqualTo(totalPages);
        assertThat(page.getTotalElements()).isEqualTo(totalElements);
    }

    public static void assertPageNoContent(Page<?> page, int pageSize, int pageNumber) {
        assertPage(page, pageSize, pageNumber, 0, 0, 0);
    }

}
