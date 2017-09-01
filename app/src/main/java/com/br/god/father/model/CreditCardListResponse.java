package com.br.god.father.model;

import java.io.Serializable;
import java.util.List;

public class CreditCardListResponse implements Serializable {

    private List<CreditCardResponse> creditCardResults;
    private Integer page;
    private Integer totalPages;
    private Integer totalRecords;
    private Boolean hasMore;

    public List<CreditCardResponse> getCreditCardResults() {
        return creditCardResults;
    }

    public void setCreditCardResults(List<CreditCardResponse> creditCardResults) {
        this.creditCardResults = creditCardResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
