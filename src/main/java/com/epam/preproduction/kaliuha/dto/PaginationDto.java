package com.epam.preproduction.kaliuha.dto;

import org.apache.log4j.Logger;

public class PaginationDto {

    private final static Logger LOG = Logger.getLogger(PaginationDto.class);

    private static final int PAGE = 1;
    private static final int COUNT = 12;
    private int page;
    private int count;

    public PaginationDto() {
        this.page = PAGE;
        this.count = COUNT;
    }

    public void setPage(String page) {
        try {
            this.page = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            LOG.info("Page not correct");
        }
    }

    public void setCount(String count) {
        try {
            this.count = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            LOG.info("Count not correct");
        }
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }
}
