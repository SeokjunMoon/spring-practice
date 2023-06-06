package com.kakaotech.mysql_practice.util;

import java.util.List;

public record PageCursor<T> (
        CursorRequest nextCursorRequest,
        List<T> body
) {

}