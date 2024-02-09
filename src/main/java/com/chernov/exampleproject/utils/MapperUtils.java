package com.chernov.exampleproject.utils;

import com.chernov.exampleproject.model.dto.EmailDataDto;
import com.chernov.exampleproject.model.dto.PhoneDataDto;
import com.chernov.exampleproject.model.dto.UserDto;
import com.chernov.exampleproject.model.entity.EmailData;
import com.chernov.exampleproject.model.entity.PhoneData;
import com.chernov.exampleproject.model.entity.User;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MapperUtils {

    private final ModelMapper modelMapper = new ModelMapper();

    public static UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public static List<UserDto> toUsersDto(List<User> users) {
        return users.stream()
                .map(MapperUtils::toUserDto)
                .collect(Collectors.toList());
    }

    public static PhoneDataDto toPhoneDataDto(PhoneData phoneData) {
        return modelMapper.map(phoneData, PhoneDataDto.class);
    }

    public static EmailDataDto toEmailDataDto(EmailData emailData) {
        return modelMapper.map(emailData, EmailDataDto.class);
    }
}
