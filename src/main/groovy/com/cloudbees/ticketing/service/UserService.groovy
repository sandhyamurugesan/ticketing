package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.dto.UserDTO;


interface UserService {
    String getUserSeat(Long userId)
    String removeUser(Long userId)
	String modifyUserSeat(Long userId, String newSeat)
    List<UserDTO> getUsersSeatBySection(String section)
    String addUser(UserDTO userDTO)
}
