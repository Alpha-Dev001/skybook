package dev.alpha.skybook.controller;

import dev.alpha.skybook.common.ApiResponse;
import dev.alpha.skybook.dto.request.BookingRequest;
import dev.alpha.skybook.dto.response.BookingResponse;
import dev.alpha.skybook.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Booking created successfully",
                        response
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(@PathVariable Long id) {
        BookingResponse response = bookingService.getBookingById(id);
        return ResponseEntity.ok(
                        ApiResponse.success(
                                "Booking retrieved successfully",
                                response
                        )
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        List<BookingResponse> response = bookingService.getAllBookings();
        return ResponseEntity.ok(
                        ApiResponse.success(
                                "Bookings retrieved successfully",
                                response
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBooking(@PathVariable Long id,@Valid @RequestBody BookingRequest request) {
        BookingResponse response =bookingService.updateBooking(id, request);
        return ResponseEntity.ok(
                        ApiResponse.success(
                                "Booking updated successfully",
                                response
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBooking(
            @PathVariable Long id
    ) {

        bookingService.deleteBooking(id);

        return ResponseEntity.ok(
                        ApiResponse.success(
                                "Booking deleted successfully",
                                null
                        )
                );
    }
}