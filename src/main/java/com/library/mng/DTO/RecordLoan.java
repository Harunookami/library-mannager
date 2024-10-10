package com.library.mng.DTO;

import java.util.Date;

public record RecordLoan(Long user_id, Long book_id, Date created_at, Date returned_at ) {
}
