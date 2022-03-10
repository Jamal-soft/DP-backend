package com.example.accountmanagement.ui.controller;


import com.example.accountmanagement.exceptions.OrganisationServiceException;
import com.example.accountmanagement.service.OrganisationServiceImpl;
import com.example.accountmanagement.shared.OrganisationDto;
import com.example.accountmanagement.ui.model.request.OrganisationDetailsRequestModel;
import com.example.accountmanagement.ui.model.response.OrganisationResp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class OrganisationController {
    @Autowired
    OrganisationServiceImpl organisationService;


    @GetMapping("/organisations")
    public List<OrganisationResp> getOrganisations(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "limit", defaultValue = "25") int limit){
        List<OrganisationDto> organisations= organisationService.getOrganisations(page,limit);
        List<OrganisationResp> returnValue = new ArrayList<>();

        for (OrganisationDto organisationDto: organisations){
            OrganisationResp organisationResp= new OrganisationResp();
            BeanUtils.copyProperties(organisationDto,organisationResp);
            returnValue.add(organisationResp);
        }



        return returnValue;

    }

/*    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id ){
        UserRest returnValue = new UserRest();
        UserDto userDto= organisationService.getUserByUserId(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(userDto, UserRest.class);
        return returnValue;
    }*/
    @PostMapping("/signup/organisations")
    public OrganisationResp createOrganisation(@RequestBody OrganisationDetailsRequestModel organisationDetailsRequestModel) throws Exception{
        if (organisationDetailsRequestModel.getEmail().isEmpty()) throw new OrganisationServiceException("missing required field");
        ModelMapper modelMapper = new ModelMapper();
        OrganisationDto organisationDto= modelMapper.map(organisationDetailsRequestModel, OrganisationDto.class);
        OrganisationDto createdOrganisation= organisationService.createOrganisation(organisationDto);
        OrganisationResp returnValue = modelMapper.map(createdOrganisation,OrganisationResp.class);
        //BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;

    }
/*    @PutMapping("/{userId}")
    public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);
        UserDto createdUser= userService.updateUser(userId,userDto);
        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;
    }
    @DeleteMapping("/{id}")
    public OperationStatusModel deleteUser(@PathVariable String id){
        OperationStatusModel returnValue = new OperationStatusModel();
        userService.deleteUser(id);
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());


        return returnValue;
    }

    @GetMapping(path = "/{userId}/addresses")
    public CollectionModel<AddressesRest> getUserAddresses(@PathVariable String userId ){
        List<AddressesRest> returnValue = new ArrayList<>();
        List<AddressDTO> addressesDTO= addressService.getAddresses(userId);
        if (addressesDTO != null && !addressesDTO.isEmpty()) {
            Type listType = new TypeToken<List<AddressesRest>>() {
            }.getType();
            returnValue = new ModelMapper().map(addressesDTO, listType);
        }
        for (AddressesRest addressesRest:returnValue){
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrganisationController.class)
                            .getUserAddress(userId,addressesRest.getAddressId()))
                    //slash(userId).slash("addresses").slash(addressId)
                    .withSelfRel();
            addressesRest.add(selfLink);
        }

        Link userLink = WebMvcLinkBuilder.linkTo(OrganisationController.class).slash(userId).withRel("user");
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrganisationController.class).getUserAddresses(userId))
                //slash(userId).slash("addresses").slash(addressId)
                .withSelfRel();

        return CollectionModel.of(returnValue,userLink,selfLink);
    }

    @GetMapping(path = "/{userId}/addresses/{addressId}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE, "application/hal+json" })
    public EntityModel<AddressesRest> getUserAddress(@PathVariable String userId, @PathVariable String addressId) {

        AddressDTO addressDto = addressService.getAddress(addressId);

        ModelMapper modelMapper = new ModelMapper();
        AddressesRest returnValue=modelMapper.map(addressDto,AddressesRest.class);
        Link userLink = WebMvcLinkBuilder.linkTo(OrganisationController.class).slash(userId).withRel("user");
        Link userAddressesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrganisationController.class).getUserAddresses(userId))
                //.slash(userId)
                //.slash("addresses")
                .withRel("addresses");
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrganisationController.class).getUserAddress(userId,addressId))
                //slash(userId).slash("addresses").slash(addressId)
                .withSelfRel();

        *//*returnValue.add(userLink);
        returnValue.add(userAddressesLink);
        returnValue.add(selfLink);*//*
        return EntityModel.of(returnValue, Arrays.asList(userLink,userAddressesLink,selfLink));
    }

    *//*
     * http://localhost:8080/mobile-app-ws/users/email-verification?token=sdfsdf
     * *//*
    @GetMapping(path = "/email-verification", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

        boolean isVerified = userService.verifyEmailToken(token);

        if(isVerified)
        {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        } else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }

        return returnValue;
    }

    *//*
     * http://localhost:8080/mobile-app-ws/users/password-reset-request
     * *//*
    @PostMapping(path = "/password-reset-request",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
        OperationStatusModel returnValue = new OperationStatusModel();

        boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

        returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

        if(operationResult)
        {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }

        return returnValue;
    }

    @PostMapping(path = "/password-reset",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel) {
        OperationStatusModel returnValue = new OperationStatusModel();

        boolean operationResult = userService.resetPassword(
                passwordResetModel.getToken(),
                passwordResetModel.getPassword());

        returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

        if(operationResult)
        {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }

        return returnValue;
    }*/


}
