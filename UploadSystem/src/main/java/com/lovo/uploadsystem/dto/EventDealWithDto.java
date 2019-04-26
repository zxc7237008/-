package com.lovo.uploadsystem.dto;

/**
 * 传给上报的Dto
 */
public class EventDealWithDto {
    /**
     * 事件编号
     */
    private String eventId;
    /**
     * 事件的阶段
     */
    private int eventPeriod;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(int eventPeriod) {
        this.eventPeriod = eventPeriod;
    }
}
