package com.wooribank.backend.controller;

import com.wooribank.backend.component.CommonResponseMaker;
import com.wooribank.backend.constant.ResponseCode;
import com.wooribank.backend.dto.*;
import com.wooribank.backend.service.ReturnRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReturnRequestController extends AppApiV1Controller {

    private final ReturnRequestService returnRequestService;
    private final CommonResponseMaker commonResponseMaker;

    @PostMapping("/return_requests")
    public CommonResponse<Void> makeReturnRequest(
            @RequestBody MakeReturnRequestRequestDto requestDto) throws IOException {

        returnRequestService.makeReturnRequest(requestDto.toVo());

        return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
    }

    @GetMapping("/return_requests/sending")
    public CommonResponse<GetSentReturnRequestsResponseDto> getSentReturnRequest(
            @RequestParam final long id) throws IOException {

        final GetSentReturnRequestsResponseDto responseDto = GetSentReturnRequestsResponseDto.of(
                returnRequestService.getSentReturnRequest(id));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

    @GetMapping("/return_requests/receiving")
    public CommonResponse<GetReceivedReturnRequestsResponseDto> getReceivedReturnRequest(
            @RequestParam final long id) throws IOException {

        final GetReceivedReturnRequestsResponseDto responseDto = GetReceivedReturnRequestsResponseDto.of(
                returnRequestService.getReceivedReturnRequest(id));

        return commonResponseMaker.makeSucceedCommonResponse(responseDto);
    }

}
