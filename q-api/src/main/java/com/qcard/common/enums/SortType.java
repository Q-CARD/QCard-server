package com.qcard.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortType {

    SORT_HEART("heart"),
    SORT_RECENT("recent");

    private final String label;
}
