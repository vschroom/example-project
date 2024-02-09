package com.chernov.exampleproject.service.impl;

import com.chernov.exampleproject.model.dto.PaginationDto;
import com.chernov.exampleproject.model.dto.UserDto;
import com.chernov.exampleproject.model.dto.UserFilterDto;
import com.chernov.exampleproject.model.entity.User;
import com.chernov.exampleproject.repositoy.UserRepository;
import com.chernov.exampleproject.repositoy.spec.UserFilterSpecification;
import com.chernov.exampleproject.service.UserFilterService;
import com.chernov.exampleproject.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFilterServiceImpl implements UserFilterService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserDto> findBy(UserFilterDto filter) {
        UserFilterSpecification specification = new UserFilterSpecification(filter);
        PaginationDto pagination = filter.getPagination();
        PageRequest pageRequest = PageRequest.of(pagination.getPage(), pagination.getSize());
        List<User> users = userRepository.findAll(specification, pageRequest).getContent();

        return MapperUtils.toUsersDto(users);
    }
}
