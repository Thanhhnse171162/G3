package com.SWP.SkinCareService.dto.response;

import com.SWP.SkinCareService.entity.BookingSession;
import com.SWP.SkinCareService.entity.Payment;
import com.SWP.SkinCareService.entity.Services;
import com.SWP.SkinCareService.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    int id;
    //User user;
    String userId;
    String fullName;

    //Services service;
    int serviceId;
    String serviceName;

    String status;
    String paymentStatus;
    //Payment payment;
    String paymentMethod;

    String notes;
    Date createAt;
    Date updateAt;
    int sessionRemain;
    //private User staff;
    String staffId;
    String staffName;

    List<BookingSession> bookingSessions;
    int price;

}
