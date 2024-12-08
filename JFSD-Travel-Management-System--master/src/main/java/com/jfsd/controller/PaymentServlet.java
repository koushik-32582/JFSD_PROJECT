package com.jfsd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Twilio dependencies
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    // Twilio credentials
    private static final String ACCOUNT_SID = "your_account_sid";
    private static final String AUTH_TOKEN = "your_auth_token";
    private static final String FROM_PHONE = "your_twilio_phone_number";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userId = request.getParameter("userid");
        String bookingId = request.getParameter("bid");
        String amount = request.getParameter("amount");
        String paymentMode = request.getParameter("mode");
        
        // Assume phone number is stored in session
        String userPhoneNumber = (String) request.getSession().getAttribute("phone");

        // Send SMS confirmation
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            Message message = Message.creator(
                new com.twilio.type.PhoneNumber(userPhoneNumber),
                new com.twilio.type.PhoneNumber(FROM_PHONE),
                "Your booking (ID: " + bookingId + ") of amount " + amount + " has been confirmed. Thank you!"
            ).create();

            // Redirect to confirmation page
            response.sendRedirect("confirmation.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error (redirect to error page)
            response.sendRedirect("error.jsp");
        }
    }
}

