package com.coding.challenge.contact.list.api;

import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;

@Api(value="Contact List API")
public interface ContactListApi {
    @ApiResponses({@ApiResponse(code=200 , message="OK")})
    @ApiOperation(value="processContactList",nickname = "proessContactList", notes= "Process a Contact List Changes")
    ResponseEntity<ContactListResponseV2> procesContactList(String originalContactRequest);
}
