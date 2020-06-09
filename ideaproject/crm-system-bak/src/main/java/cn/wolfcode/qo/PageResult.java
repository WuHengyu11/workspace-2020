package cn.wolfcode.qo;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResult<T> {
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private int totalCount;
    private int prevPage;
    private int nextPage;
    private int totalPage;

    public PageResult(List<T> data, int currentPage, int pageSize, int totalCount){
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        if (totalCount <= pageSize){
            this.totalPage = 1;
            this.prevPage = 1;
            this.nextPage = 1;
            return;
        }

        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = this.currentPage - 1 < 1 ? 1 : this.currentPage - 1;
        this.nextPage = this.currentPage + 1 > this.totalPage ? this.totalPage : this.currentPage + 1;
    }
}

