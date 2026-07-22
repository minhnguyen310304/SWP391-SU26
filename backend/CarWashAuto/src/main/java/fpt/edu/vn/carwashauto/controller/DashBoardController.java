package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.dto.response.RevenueByServiceResponse;
import com.autowashpro.dto.response.RevenueSummaryResponse;
import com.autowashpro.entity.Booking;
import com.autowashpro.entity.Payment;
import com.autowashpro.repository.BookingRepository;
import com.autowashpro.repository.PaymentRepository;
import com.autowashpro.service.BookService;
import com.autowashpro.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class DashBoardController {

    private final DashBoardService dashBoardService;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final BookService bookService;

    @GetMapping("/revenue")
    public ResponseEntity<ApiResponse<RevenueSummaryResponse>> getRevenueSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        RevenueSummaryResponse summary = dashBoardService.getRevenueSummary(fromDate, toDate);
        return ResponseEntity.ok(ApiResponse.success("Revenue summary retrieved successfully", summary));
    }

    @GetMapping("/revenue/today")
    public ResponseEntity<ApiResponse<RevenueSummaryResponse>> getTodayRevenue() {
        LocalDate today = LocalDate.now();
        RevenueSummaryResponse summary = dashBoardService.getRevenueSummary(today, today);
        return ResponseEntity.ok(ApiResponse.success("Today's revenue retrieved successfully", summary));
    }

    @GetMapping("/revenue/this-month")
    public ResponseEntity<ApiResponse<RevenueSummaryResponse>> getThisMonthRevenue() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        RevenueSummaryResponse summary = dashBoardService.getRevenueSummary(firstDayOfMonth, today);
        return ResponseEntity.ok(ApiResponse.success("This month's revenue retrieved successfully", summary));
    }

    @GetMapping("/payments")
    public ResponseEntity<Map<String, Object>> getPaymentDashboard() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        List<Payment> allPayments = paymentRepository.findAll();

        List<Booking> todayCompletedBookings = bookingRepository.findByBookingDate(today).stream()
                .filter(b -> "COMPLETED".equals(b.getStatus()))
                .toList();
        BigDecimal totalCollectedToday = todayCompletedBookings.stream()
                .map(b -> b.getTotalAmount() != null ? b.getTotalAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Map<String, Object>> payments = allPayments.stream().map(p -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", "PAY-" + p.getId());
            map.put("customer", p.getBooking() != null && p.getBooking().getCustomer() != null
                    ? p.getBooking().getCustomer().getFullName() : "N/A");
            map.put("service", p.getBooking() != null ? "Booking #" + p.getBooking().getId() : "N/A");
            map.put("date", p.getPaymentDate() != null ? p.getPaymentDate().toString() : "N/A");
            map.put("amount", p.getAmount() != null ? p.getAmount().longValue() : 0);
            map.put("method", p.getPaymentMethod() != null ? p.getPaymentMethod() : "CASH");
            map.put("status", "SUCCESS".equals(p.getStatus()) ? "PAID" : "UNPAID");
            return map;
        }).collect(Collectors.toList());

        BigDecimal totalPending = allPayments.stream()
                .filter(p -> !"SUCCESS".equals(p.getStatus()))
                .map(p -> p.getAmount() != null ? p.getAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCash = allPayments.stream()
                .filter(p -> "SUCCESS".equals(p.getStatus()) && "CASH".equals(p.getPaymentMethod()))
                .map(p -> p.getAmount() != null ? p.getAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalTransfer = allPayments.stream()
                .filter(p -> "SUCCESS".equals(p.getStatus()) && !"CASH".equals(p.getPaymentMethod()))
                .map(p -> p.getAmount() != null ? p.getAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> result = new HashMap<>();
        result.put("payments", payments);
        result.put("totalCollectedToday", totalCollectedToday.longValue());
        result.put("totalPending", totalPending.longValue());
        result.put("totalCash", totalCash.longValue());
        result.put("totalTransfer", totalTransfer.longValue());

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDashboard(@RequestParam(value = "filter", defaultValue = "month") String filter) {
        LocalDate today = LocalDate.now();
        LocalDate fromDate;

        if ("today".equals(filter)) {
            fromDate = today;
        } else {
            fromDate = today.withDayOfMonth(1);
        }

        RevenueSummaryResponse revenue = dashBoardService.getRevenueSummary(fromDate, today);

        RevenueSummaryResponse todayRevenue = dashBoardService.getRevenueSummary(today, today);

        LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - 1);
        RevenueSummaryResponse weekRevenue = dashBoardService.getRevenueSummary(weekStart, today);

        List<Booking> todayBookings = bookingRepository.findByBookingDate(today);

        List<Map<String, Object>> todaySchedule = todayBookings.stream().map(b -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", b.getId());
            map.put("customer", b.getCustomer() != null ? b.getCustomer().getFullName() : "Khach vang lai");
            map.put("service", "Booking #" + b.getId());
            map.put("time", b.getStartTime() != null ? b.getStartTime().toString() : "N/A");
            map.put("status", b.getStatus());
            return map;
        }).collect(Collectors.toList());

        List<RevenueByServiceResponse> serviceData = revenue.getRevenueByService() != null ? revenue.getRevenueByService() : List.of();
        BigDecimal maxRevenue = serviceData.stream()
                .map(RevenueByServiceResponse::getTotalRevenue)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ONE);
        if (maxRevenue.compareTo(BigDecimal.ZERO) == 0) maxRevenue = BigDecimal.ONE;
        BigDecimal finalMax = maxRevenue;

        String[] colors = {"bg-cyan-500", "bg-purple-500", "bg-emerald-500", "bg-amber-500", "bg-red-500", "bg-pink-500"};
        List<Map<String, Object>> revenueBreakdown = new ArrayList<>();
        for (int i = 0; i < serviceData.size(); i++) {
            RevenueByServiceResponse svc = serviceData.get(i);
            Map<String, Object> item = new HashMap<>();
            item.put("name", svc.getServiceName());
            item.put("revenue", svc.getTotalRevenue());
            item.put("color", colors[i % colors.length]);
            item.put("percent", svc.getTotalRevenue().multiply(BigDecimal.valueOf(100)).divide(finalMax, 0, java.math.RoundingMode.HALF_UP).intValue());
            revenueBreakdown.add(item);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalRevenue", revenue != null ? revenue.getTotalRevenue() : BigDecimal.ZERO);
        result.put("todayRevenue", todayRevenue.getTotalRevenue() != null ? todayRevenue.getTotalRevenue() : BigDecimal.ZERO);
        result.put("weekRevenue", weekRevenue.getTotalRevenue() != null ? weekRevenue.getTotalRevenue() : BigDecimal.ZERO);
        result.put("todayBookings", todayRevenue.getTotalBookings());
        result.put("weekBookings", weekRevenue.getTotalBookings());
        result.put("totalBookingsToday", todayBookings.size());
        result.put("todaySchedule", todaySchedule);
        result.put("revenueBreakdown", revenueBreakdown);

        return ResponseEntity.ok(result);
    }
}
