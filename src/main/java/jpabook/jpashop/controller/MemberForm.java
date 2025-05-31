package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;

public class MemberForm {

    @NotEmpty(message= "회원이름이 비워져 있습니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
