package dev.alpha.skybook.service;

import dev.alpha.skybook.dto.request.BookingRequest;
import dev.alpha.skybook.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getAllBookings();
    BookingResponse updateBooking(Long id, BookingRequest request);
    void deleteBooking(Long id);
}